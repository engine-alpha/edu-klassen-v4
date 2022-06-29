import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.net.URL;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import ea.edu.*;
import ea.actor.Actor;
import ea.edu.EduActor;
import ea.edu.event.*;
/**
 * Klasse AUDIOSYSTEM zum Abspielen von Dateien mit dem Dateitypen: ".midi", ".wav" aus dem lokalen Projekt-Ordner.
 * 
 * @author      Andreas Moosbauer (an-moos)
 * 
 * @version     2.0 (2022-06-29)
 * 
 * @changelog   2.0 Unterstützt nun ".wav"-Dateien.
 * 
 *              1.2 ".midi"-Dateien können nun mit "loop"-Funktion abgespielt werden.
 * 
 *              1.1 Dateien können nun nur noch vom Lokalen Ordner abgerufen werden.
 *                  => Ladezeiten aufgrund des Abrufens der Datei aus dem Internet fallen weg.
 * 
 *              1.0 ".midi"-Dateien können aus dem Internet (via Download Link) abgespielt,
 *                  pausiert und fortgesetzt werden.
 *              
 */
public class AUDIOSYSTEM extends Spiel implements Ticker{
    private long position, length;
    private Sequencer sequencer;
    private Clip clip;
    private File file;
    private char running;
    private String fileExt;
    /**
     * Konstruktor der Klasse AUDIOSYSTEM
     * 
     * @param   dateiName                   relativer Dateipfad der zu ausführenden Datei (ohne Erweiterung) [Bsp.: "test"; "../ordner/test"; "/ordner/test"]
     * @param   dateiNamensErweiterung      die Dateinamenserweiterung angeben (ohne Punkt) [Bsp.: "wav"; "midi"]
     */
    public AUDIOSYSTEM(String dateiName, String dateiNamensErweiterung){
        file = new File(dateiName + "." + dateiNamensErweiterung);
        fileExt = dateiNamensErweiterung;
        running = 's';
        if(fileExt == "wav" || fileExt == "midi"){}
        else{
            System.out.println("Der Dateityp: ." + fileExt + " wird nicht unterstützt.");
            System.out.println("Bitte versuche es mit einer unterstützten Datei nochmal (.midi / .wav).");
            System.exit(0);
        }
    }
    /**
     * Lässt die Datei einmal abspielen
     */
    public void wiedergabeStarten(){
        position = 0;
        wiedergabeStoppen();
        if(fileExt == "midi"){
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
        if(fileExt == "wav"){
            if(running == 's'){
                try{
                    URL url = new URL("file:///" + file.getAbsolutePath());
                    clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                    clip.open(ais);
                    clip.start();
                } 
                catch (Exception e){
                    e.printStackTrace();
                }
                running = 'p';
                length = clip.getMicrosecondLength();
            }
        }
    }
    /**
     * Lässt die Datei unendlich wiederholend abspielen
     */
    public void loopWiedergabeStarten(){
        position = 0;
        wiedergabeStoppen();
        if(fileExt == "midi"){
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
        if(fileExt == "wav"){
            if(running == 's'){
                try{
                    URL url = new URL("file:///" + file.getAbsolutePath());
                    clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                    clip.open(ais);
                    clip.start();
                } 
                catch (Exception e){
                    e.printStackTrace();
                }
                running = 'p';
                length = clip.getMicrosecondLength();
                starteTickerNeu(1);
            }
        }
    }
    /**
     * Stoppt die Wiedergabe der Datei völlig
     */
    public void wiedergabeStoppen(){
        if(fileExt == "midi"){
            if(running == 'p' || running == 'l' || running == 'o'){
                sequencer.stop();
                position = 0;
                running = 's';
            }
        }
        if(fileExt == "wav"){  
            if(running == 'p' || running == 'l' || running == 'o'){
                clip.stop();
                position = 0;
                running = 's';
            }
        }
    }
    /**
     * Pausiert die Wiedergabe der Datei
     */
    public void wiedergabePausieren(){
        if(fileExt == "midi"){
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
        if(fileExt == "wav"){
            if(running == 'p'){
                position = clip.getMicrosecondPosition();
                clip.stop();
                running = 'h';
            }
            if(running == 'l'){
                stoppeTicker();
                position = clip.getMicrosecondPosition();
                clip.stop();
                running = 'o';
            }
        }
    }
    /**
     * Setzt die Wiedergabe der Datei fort
     */
    public void wiedergabeFortsetzen(){
        if(fileExt == "midi"){
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
        if(fileExt == "wav"){
            if(running == 'h'){
                clip.start();
                clip.setMicrosecondPosition(position);
                running = 'p';
            }
            if(running == 'o'){
                clip.start();
                clip.setMicrosecondPosition(position);
                starteTickerNeu(1);
                running = 'l';
            }
        }
    }
    /**
     * Setzt eine neue Datei für die Wiedergabe, kann auch während den Abspielen 
     * einer anderen Datei geändert werden und die andere Datei würde vorerst 
     * eine Änderungen aufweisen.
     */
    public void setzeNeuenDateiNamen(String dateiName, String dateiNamensErweiterung){
        String tempExt;
        tempExt = fileExt;
        File temp;
        temp = file;
        file = new File(dateiName + "." + dateiNamensErweiterung);
        fileExt = dateiNamensErweiterung;
        if(fileExt == "wav" || fileExt == "midi"){}
        else{
            System.out.println("Der Dateityp: ." + fileExt + " wird nicht unterstützt.");
            System.out.println("Bitte versuche es mit einer unterstützten Datei nochmal (.midi / .wav).");
            System.out.println("Die vorherige Datei wird beibehalten.");
            file = temp;
            fileExt = tempExt;
        }
    }
    /**
     * Gibt den aktuellen Status aus
     * 
     * @return      <i>s</i> - Musik ist gerade gestoppt <br>
     *              <i>p</i> - Musik wird gerade als eine normale Wiederabe abgespielt <br>
     *              <i>l</i> - Musik wird gerade als eine loop Wiedergabe abgespielt <br>
     *              <i>h</i> - Musik ist gerade während einer normalen Wiederabe pausiert <br>
     *              <i>o</i> - Musik ist gerade während einer loop Wiedergabe pausiert <br>
     */
    public char gebeStatusAus(){
        return(running);
    }
    public void setzeTickerIntervall( double sekunden ) {
        super.registriereTicker( sekunden , this );
    }
    public void stoppeTicker(){
        super.entferneTicker( this );
    }
    public void starteTickerNeu( double sekunden ){
        super.registriereTicker( sekunden , this );
    }
    @Override
    /**
     * Hier wird der Loop verarbeitet
     */
    public void tick(){
        if(fileExt == "midi"){
            position = sequencer.getMicrosecondPosition();
            if(position == length){
                wiedergabeStoppen();
                wiedergabeStarten();
            }
        }
        if(fileExt == "wav"){
            position = clip.getMicrosecondPosition();
            if(position == length){
                wiedergabeStoppen();
                wiedergabeStarten();
            }
        }
    }
}
