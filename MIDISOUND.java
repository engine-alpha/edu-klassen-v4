import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Toolkit;
import java.net.URL;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
/**
 * Klasse MIDISOUND zum Abspielen von Midi-Dateien aus dem Internet
 * 
 * @author      Andreas Moosbauer (an-moos)
 * 
 * @version     1.0 (2022-06-25)
 * 
 * @changelog   1.0 ".midi"-Dateien können aus dem Internet (via Download Link) abgespielt,
 *                  pausiert und fortgesetzt werden.
 *              
 */
public class MIDISOUND{
    private long position;
    private Sequencer sequencer;
    private String link;
    private boolean running;
    /** 
     * Erstellt ein "MIDISOUND"-Objekt, welches aufgerufen werden kann, um ".midi"-Dateien abzuspielen.
     * 
     * @param nLink Einen Link einfügen, der zu einer <b>".midi"-Datei</b> führt.
     *              Es genügt eine ".midi"-Datei auf eine Cloud (z.B.: terabox.com) hochzuladen und diese zu teilen und den <b>exakten</b> Download-Link zu verwenden.
     *              Bei den Download einer midi Datei erscheint oft die ".mid"-Dateiendung, diese muss dann durch die ".midi"-Dateiendung einfach ersetzt werden.
     */
     //(z.B.: https://data.terabox.com/file/3e2c9ed2fcecc2b279104b79f20bb26c?bkt=en-2d9e6f81f9f5bca0b39104842c0bfa2b9c8cf8c1596e45f7a11a0858c9f991ba767aa480fa20176aef497c4b4bd530b819a31b20bc768511f4bf52d3037936f7&fid=4402285793607-250528-80559783724929&time=1656178579&sign=FDTAXUGERLQlBHSKfW-DCb740ccc5511e5e8fedcff06b081203-xlk35xJ%2F0GUpxnJCXe5HaFUxu4A%3D&signbak=&to=140&size=37029&sta_dx=37029&sta_cs=0&sta_ft=midi&sta_ct=0&sta_mt=0&fm2=MH%2Cdefault_region%2CAnywhere%2C%2C%2Cany&region=default_region&ctime=1656178542&mtime=1656178542&resv0=-1&resv1=0&resv2=rlim&resv3=5&resv4=37029&vuk=4402285793607&iv=0&htype=&randtype=&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=en-cca8b529c5a3e784c32df41427cc381630b7de2396d4db72001fefd87c6bcdc9a26d9edfc26ee0ea62aa488669c7fdd86e721cb155559082305a5e1275657320&sl=68091977&expires=1656207379&rt=sh&r=208175735&sh=1&vbdid=-&fin=rickroll.midi&fn=rickroll.midi&rtype=1&dp-logid=4083487938312682544&dp-callid=0.1&hps=1&tsl=2000&csl=2000&fsl=-1&csign=VCNYHj9URBR6HVrEbheuYl46BCY%3D&so=0&ut=6&uter=4&serv=0&uc=3477518198&ti=14a3010384c1ca3ce9983754e684d47495aa8aee1f07df0ee3611405bef53ec1&adg=&reqlabel=250528_f_d45be81bc5c941f4eccaf60ca6463643_-1_dbba83b8c2dfb363e818f5a1951c687e&ccn=DE&by=themis)    
    public MIDISOUND(String nLink){
        link = nLink;
    }
    /**
     * Startet die Wiedergabe der ".midi"-Datei.
     */
    public void midiWiedergabeStarten(){
        if(running == false){
            try{
                URL url = new URL(link);
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
    public void setzeMidiLink(String nLink){
        link = nLink;
        position = 0;
    }
}
