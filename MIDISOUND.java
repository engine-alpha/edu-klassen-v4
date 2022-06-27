import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.net.URL;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;
import ea.edu.*;
import ea.actor.Actor;
import ea.edu.EduActor;
import ea.edu.event.*;
/**
 * Klasse MIDISOUND zum Abspielen von Midi-Dateien aus dem lokalen Projekt-Ordner.
 * 
 * @author      Andreas Moosbauer (an-moos)
 * 
 * @version     1.2 (2022-06-27)
 * 
 * @changelog   1.2 ".midi"-Dateien können nun mit "loop"-Funktion abgespielt werden.
 * 
 *              1.1 Dateien können nun nur noch vom Lokalen Ordner abgerufen werden.
 *                  => Ladezeiten aufgrund des Abrufens der Datei aus dem Internet fallen weg.
 * 
 *              1.0 ".midi"-Dateien können aus dem Internet (via Download Link) abgespielt,
 *                  pausiert und fortgesetzt werden.
 *              
 */
public class MIDISOUND extends Spiel implements Ticker{
    private long position, length;
    private Sequencer sequencer;
    private File file;
    private char running;
    /** 
     * Erstellt ein "MIDISOUND"-Objekt, welches aufgerufen werden kann, um ".midi"-Dateien abzuspielen.
     * 
     * @param midifile Hier wird der relative Pfad zur Datei (beginnend im Projekt-Ordner) angegeben. 
     */
    public MIDISOUND(String midifile){
        file = new File(midifile);
        running = 's';
    }
    /**
     * Startet die Wiedergabe der ".midi"-Datei.
     */
    public void midiWiedergabeStarten(){
        midiWiedergabeStoppen();
        if(running == 's'){
            try{
                URL url = new URL("file:///" + file.getAbsolutePath());
                Sequence sequence = MidiSystem.getSequence(url);
                sequencer = MidiSystem.getSequencer();
                sequencer.open();
                sequencer.setSequence(sequence);
                sequencer.start();
            } 
            catch (Exception e){
                e.printStackTrace();
            }
            running = 'p';
            length = sequencer.getMicrosecondLength();
        }
    }
    /**
     * Startet die Loop-Wiedergabe der ".midi"-Datei.
     */
    public void midiLoopWiedergabeStarten(){
        midiWiedergabeStoppen();
        if(running == 's'){
            try{
                URL url = new URL("file:///" + file.getAbsolutePath());
                Sequence sequence = MidiSystem.getSequence(url);
                sequencer = MidiSystem.getSequencer();
                sequencer.open();
                sequencer.setSequence(sequence);
                sequencer.start();
            } 
            catch (Exception e){
                e.printStackTrace();
            }
            running = 'l';
            length = sequencer.getMicrosecondLength();
            starteTickerNeu(1);
        }
    }
    /**
     * Stoppt die Wiedergabe der ".midi"-Datei. 
     */
    public void midiWiedergabeStoppen(){
        if(running == 'p' || running == 'l' || running == 'o'){
            sequencer.stop();
            position = 0;
            running = 's';
        }
    }
    /**
     * Pausiert die Wiedergabe der ".midi"-Datei.
     */
    public void midiWiedergabePausieren(){
        if(running == 'p'){
            position = sequencer.getMicrosecondPosition();
            sequencer.stop();
            running = 'h';
        }
        if(running == 'l'){
            stoppeTicker();
            position = sequencer.getMicrosecondPosition();
            sequencer.stop();
            running = 'o';
        }
    }
    /**
     * Setzt die Wiedergabe der ".midi"-Datei fort.
     */
    public void midiWiedergabeFortsetzen(){
        if(running == 'h'){
            sequencer.start();
            sequencer.setMicrosecondPosition(position);
            running = 'p';
        }
        if(running == 'o'){
            sequencer.start();
            sequencer.setMicrosecondPosition(position);
            starteTickerNeu(1);
            running = 'l';
        }
    }
    /**
     * Setzt einen neuen Datei-Link.
     */
    public void setzeMidiLink(String midifile){
        file = new File(midifile);
        position = 0;
    }

    private void setzeTickerIntervall( double sekunden ) {
        super.registriereTicker( sekunden , this );
    }
    public void stoppeTicker(){
        super.entferneTicker( this );
    }
    public void starteTickerNeu( double sekunden ){
        super.registriereTicker( sekunden , this );
    }
    @Override
    public void tick(){
        position = sequencer.getMicrosecondPosition();
        if(position == length){
            midiWiedergabeStoppen();
            midiWiedergabeStarten();
        }
    }
}
