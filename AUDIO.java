 

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;

/* !!! Diese Klasse ist ein NOTNAGEL zum Abspielen von Audio-Dateien im MP3-Format, 
 * bis die engine-alpha-eigene Sound-Engine fertig gestellt ist.
 * 
 * @author David J. Barnes und Michael Kölling
 * @version 31.07.2011
 * @modified    2019-08-09  mike_gans@yahoo.de
 */

public class AUDIO
{
    private AdvancedPlayer player;

    // =====   K o n s t r u k t o r e n   =========================================================
    
    /**
     * Konstruktor der Klasse AUDIO ohne Parameter. <br />
     * Die Audio-Datei muss mit der Methode starteAbspielen(String dateiname) gestartet werden. <br />
     * <b> N A C H T E I L </b>: Dies fuehrt zu einer leichten <b>Verzoegerung vor dem Abspielen</b>. <br />
     * <b> V O R T E I L </b>: Man kann <b>mit nur einem Player mehrere Audio-Dateien</b> abspielen. 
     *
     */
    public AUDIO()
    {
        player = null;
    }
    
    
    /**
     * Konstruktor der Klasse AUDIO mit Parameter. <br />
     * Die Audio-Datei muss mit der Methode starteAbspielen() (ohne Prameter!) gestartet werden. <br />
     * <b> N A C H T E I L </b>: Man kann mit diesem Player <b>nur eine Audio-Datei</b> abspielen. <br />
     * <b> V O R T E I L </b>: Das Abspielen erfolgt so gut wie <b>verzoegerungsfrei</b>. 
     * 
     * @param   dateiname   Name der abzuspielenden Audio-Datei
     *
     */
    public AUDIO( String dateiname )
    {
        playerVorbereiten( dateiname );
    }

    
    
    // =====   o e f f e n t l i c h e   M e t h o d e n   ========================================

    /**
     * Methode zum Abspielen einer Audio-Datei <b> fuer den parameterlosen Konstruktor </b>. 
     *
     * @param   dateiname   Dateiname der abzuspielenden Datei
     */
    public void starteAbspielen(final String dateiname)
    {
        try {
            playerVorbereiten(dateiname);
            Thread playerThread = new Thread() {
                public void run()
                {
                    try {
                        player.play(5000);
                    }
                    catch(JavaLayerException e) {
                        meldeProblem(dateiname);
                    }
                    finally {
                        killPlayer();
                    }
                }
            };
            playerThread.start();
        }
        catch (Exception ex) {
            meldeProblem(dateiname);
        }
    }
    
    
    /**
     * Methode zum Abspielen der Audio-Datei <b> fuer den Konstruktor mit Parameter</b>. 
     */
    public void starteAbspielen()
    {
        try {
            Thread playerThread = new Thread() {
                public void run()
                {
                    try {
                        player.play();
                    }
                    catch(JavaLayerException e) {
                        meldeProblem("SoundDatei");
                    }
                    finally {
                        killPlayer();
                    }
                }
            };
            playerThread.start();
        }
        catch (Exception ex) {
            meldeProblem("SoundDatei");
        }
    }
    
    
    /**
     * Methode zum stoppen der Wiedergabe. 
     */
    public void stop()
    {
        killPlayer();
    }


    
    
    // =====   H i l f s m e t h o d e n   ==============================================================

    private void playerVorbereiten(String dateiname)
    {
        try {
            InputStream is = gibEingabestream(dateiname);
            player = new AdvancedPlayer(is, erzeugeAudiogeraet());
        }
        catch (IOException e) {
            meldeProblem(dateiname);
            killPlayer();
        }
        catch(JavaLayerException e) {
            meldeProblem(dateiname);
            killPlayer();
        }
    }


    private InputStream gibEingabestream(String dateiname)
            throws IOException
    {
        return new BufferedInputStream(
                new FileInputStream(dateiname));
    }


    private AudioDevice erzeugeAudiogeraet()
            throws JavaLayerException
    {
        return FactoryRegistry.systemRegistry().createAudioDevice();
    }


    private void killPlayer()
    {
        synchronized(this) {
            if(player != null) {
                player.stop();
                player = null;
            }
        }
    }


    private void meldeProblem(String dateiname)
    {
        System.out.println("Es gab ein Problem beim Abspielen von: " + dateiname);
    }

}
