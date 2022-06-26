import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.net.URL;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;
/**
 * Klasse MIDISOUND zum Abspielen von Midi-Dateien aus dem lokalen Projekt-Ordner.
 * 
 * @author      Andreas Moosbauer (an-moos)
 * 
 * @version     1.1 (2022-06-26)
 * 
 * @changelog   1.1 Dateien können nun nur noch vom Lokalen Ordner abgerufen werden.
 *                  => Ladezeiten aufgrund des Abrufens der Datei aus dem Internet fallen weg.
 * 
 *              1.0 ".midi"-Dateien können aus dem Internet (via Download Link) abgespielt,
 *                  pausiert und fortgesetzt werden.
 *              
 */
public class MIDISOUND{
    private long position;
    private Sequencer sequencer;
    private File file;
    private boolean running;
    /** 
     * Erstellt ein "MIDISOUND"-Objekt, welches aufgerufen werden kann, um ".midi"-Dateien abzuspielen.
     * 
     * @param midifile Hier wird der relative Pfad zur Datei (beginnend im Projekt-Ordner) angegeben. 
     */
    public MIDISOUND(String midifile){
        file = new File(midifile);
    }
    /**
     * Startet die Wiedergabe der ".midi"-Datei.
     */
    public void midiWiedergabeStarten(){
        if(running == false){
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
            running = true;
        }
    }
    /**
     * Stoppt die Wiedergabe der ".midi"-Datei. 
     */
    public void midiWiedergabeStoppen(){
        if(running){
            sequencer.stop();
            position = 0;
            running = false;
        }
    }
    /**
     * Pausiert die Wiedergabe der ".midi"-Datei.
     */
    public void midiWiedergabePausieren(){
        if(running){
            position = sequencer.getMicrosecondPosition();
            sequencer.stop();
            running = false;
        }
    }
    /**
     * Setzt die Wiedergabe der ".midi"-Datei fort.
     */
    public void midiWiedergabeFortsetzen(){
        if(running == false){
            sequencer.start();
            sequencer.setMicrosecondPosition(position);
            running = true;
        }
    }
    /**
     * Setzt einen neuen Datei-Link.
     */
    public void setzeMidiLink(String midifile){
        file = new File(midifile);
        position = 0;
    }
}
