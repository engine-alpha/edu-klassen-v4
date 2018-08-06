import ea.sound.Music;

/**
 * Klasse MUSIK kann WAV, AU, (noch nicht MP3) Dateien abspielen
 * 
 * @author      mike ganshorn
 * 
 * @version     4.0 (2018-05-30)
 * 
 * @changelog   4.0 Umstieg auf EA 4
 *              2.0 Umstieg von javaZoom auf Engine-Alpha-Sound
 */
public class MUSIK 
extends Music
{
    

    /**
     * Konstruktor der Klasse MUSIK.
     * 
     * @param   datei   Name der Datei (mit Endung) - muss im Projekt-Ordner liegen
     */
    public MUSIK( String datei )
    {
        super( datei );
    }

    
    /**
     * Methode play.
     *
     */
    public void play()
    {
        super.play();
    }
    
    
    /**
     * Methode loop.
     *
     */
    public void loop()
    {
        super.loop();
    }
    
    
    /**
     * Methode pause.
     *
     */
    public void pause()
    {
        super.pause();
    }
    
    
    /**
     * Methode unpause.
     *
     */
    public void unpause()
    {
        super.resume();
    }
    
    
    /**
     * Methode stop.
     *
     */
    public void stop()
    {
        super.stop();
    }
}
