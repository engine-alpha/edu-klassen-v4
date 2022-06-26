import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * Klasse MIDISOUND zum Abspielen von WAV-Dateien aus dem Internet
 * 
 * @author      Andreas Moosbauer (an-moos)
 * 
 * @version     1.0 (2022-06-25)
 * 
 * @changelog   1.0 ".wav"-Dateien können aus dem Internet (via Download Link) abgespielt,
 *                  pausiert und fortgesetzt werden.
 *              
 */
public class WAVSOUND{
    private long position;
    private Clip clip;
    private String link;
    private boolean running;
    /** 
     * Erstellt ein "MIDISOUND"-Objekt, welches aufgerufen werden kann, um ".wav"-Dateien abzuspielen.
     * 
     * @param nLink Einen Link einfügen, der zu einer <b>".wav"-Datei</b> führt.
     *              Es genügt eine ".wav"-Datei auf eine Cloud (z.B.: terabox.com) hochzuladen (besser wäre ein eigener Server) und diese zu teilen und den <b>exakten</b> Download-Link zu verwenden.
     */
    public WAVSOUND(String nLink){
        link = nLink;
    }
    /**
     * Startet die Wiedergabe der ".wav"-Datei.
     */
    public void wavWiedergabeStarten(){
        if(running == false){
            try{
                URL url = new URL(link);
                clip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                clip.open(ais);
                clip.start();
            } 
            catch (Exception e){
                e.printStackTrace();
            }
            running = true;
        }
    }
    /**
     * Startet die unendliche Wiedergabe der ".wav"-Datei.
     */
    public void wavLoopWiedergabeStarten(){
        if(running == false){
            try{
                URL url = new URL(link);
                clip = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(url);
                clip.open(ais);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } 
            catch (Exception e){
                e.printStackTrace();
            }
            running = true;
        }
    }
    /**
     * Stoppt die Wiedergabe der ".wav"-Datei. 
     */
    public void wavWiedergabeStoppen(){
        if(running){
            clip.stop();
            position = 0;
            running = false;
        }
    }
    /**
     * Pausiert die Wiedergabe der ".wav"-Datei.
     */
    public void wavWiedergabePausieren(){
        if(running){
            position = clip.getMicrosecondPosition();
            clip.stop();
            running = false;
        }
    }
    /**
     * Setzt die Wiedergabe der ".wav"-Datei fort.
     */
    public void wavWiedergabeFortsetzen(){
        if(running == false){
            clip.start();
            clip.setMicrosecondPosition(position);
            running = true;
        }
    }
    /**
     * Setzt einen neuen Datei-Link.
     */
    public void setzeWavLink(String nLink){
        link = nLink;
        position = 0;
    }
}
