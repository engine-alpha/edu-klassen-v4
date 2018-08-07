import ea.edu.*;
import ea.actor.Actor;
import ea.edu.EduActor;

/**
 * Die Klasse SPIEL ist ein Template, das so wie es ist an Schueler ausgegeben werden kann.
 * (Einzige Voraussetzung ist, dass die engine-alpha-Bibliothek im Suchpfad erreichbar ist.)
 * Es startet alles Notwendige fuer ein Spiel.
 * 
 * Beim Konstruktor ohne Parameter gibt es keine Maus-Interaktion.
 * Die Methoden tick() und tasteReagieren() werden immer automatisch aufgerufen.
 * 
 * Beim Konstruktor mit Parameter kann Maus-Interaktion eingeschaltet werden.
 * 
 * 
 * @author      Michael Andonie und Mike Ganshorn  (nach Idee von Bendikt Lindemann)
 * 
 * 
 * @version     4.0 (2018-08-06)
 * 
 * 
 * @changelog   4.0 Umstieg auf EA 4
 * 
 *              2.3 Ticker startet NICHT mehr automatisch !!!
 *                  Methoden-Signaturen "geglaettet"
 *                  neue Konstruktoren
 *                  Methode tasteGedrueckt( int taste )
 * 
 */
public class SPIEL 
extends Spiel 
{

    
    /**
     * Dieser Zaehler ermoeglicht den Tik-Tak-Wechsel.
     */
    private int zaehler;
    
    private Figur hintergrundbild;
    
    
    
    /**
     * Erstellt ein einfaches Spiel: Breite 800 , Hoehe 600 , ohne Punktestand und Maus. <br /> 
     * Ueberschreibe bei Bedarf die Methoden tick() bzw. tasteReagieren(int taste).
     */
    public SPIEL() 
    {
        this( false );
    }
    
    
    
    /**
     * SPIEL Konstruktor mit allen Moeglichkeiten <br /> 
     * Ueberschreibe bei Bedarf die Methoden tick() bzw. tasteReagieren(int taste).
     *                          
     * @param   fensterBreite   in Pixel
     * @param   fensterHoehe    in Pixel
     * @param   maus            true  : man sieht den Mauszeiger  (Klick-Spiel) 
     *                          false : man sieht ihn nicht       (reines Tastatur-Spiel)
     */
    public SPIEL( int fensterBreite, int fensterHoehe, boolean maus ) 
    {
        setzeFensterGroesse(fensterBreite, fensterHoehe);
        
        //Zaehler fuer Tick, Tack, ...
        zaehler = 0;
        
        //Maus ggf. aktivieren
        if ( maus ) 
        {
            klickReagierbarAnmelden( this , true );
        }
        
        //Tastatur
        tastenReagierbarAnmelden( this );
        
    }
    
    
    /**
     * SPIEL Konstruktor 800 x 600
     *
     * @param   maus        'true' mit Maus , 'false' ohne Maus
     */
    public SPIEL ( boolean maus )
    {
        this ( 800 , 600 , maus );
    }
    
    
    /**
     * SPIEL Konstruktor ohne Maus.
     *
     * @param   x   Ein Parameter
     * @param   y   Ein Parameter
     */
    public SPIEL( int x , int y )
    {
        this( x , y , false );
    }
    
    
    /**
     * Setzt eine Hintergrundgrafik fuer das Spiel. Dieses Bild liegt immer hinter allen anderen Objekten. 
     * 
     * @param   pfad    Der Pfad der Bilddatei (jpg, bmp, png) des Bildes,
     *                  das benutzt werden soll. ZB: "hintergrund.jpg"
     */
    public void setzeHintergrundgrafik( String pfad ) 
    {
        if ( this.hintergrundbild != null )
        {
            this.hintergrundbild.setzeSichtbar( false );
        }
        this.hintergrundbild = new Figur ( "hintergrund" , pfad , 1 , 1 );
        // ToDo --- HINTERGRUND und VORDERGUND als Konstanten
        this.hintergrundbild.getActor().setLayer( -1 );
        this.hintergrundbild.setzeSichtbar( true );
    }
    
    
    // /**
     // * Setzt ein neues Maus-Icon.
     // * 
     // * @param   pfad        Der Pfad zu dem Bild (jpg, bmp, png), das 
     // *                      das neue Maus-Icon werden soll. ZB: "mausicon.png"
     // *                      
     // * @param   hotspotX    Die X-Koordinate des Hotspots fuer das neue
     // *                      Maus-Icon. (relativ im Icon)
     // *                      
     // * @param   hotspotY    Die Y-Koordinate des Hotspots fuer das neue
     // *                      Maus-Icon. (relativ im Icon)
     // */
    // public void setzeMausIcon( String pfad , int hotspotX , int hotspotY ) 
    // {
        // // TODO
        // //ea.edu.FensterE.getFenster().mausAnmelden( new Maus( new Bild(0,0,pfad) , new Punkt(hotspotX,hotspotY) ) , true );
    // }
    
    
    
    
    
    // ===  T i c k e r  ===
    
    /**
     * Setzt das Ticker-Intervall.
     * 
     * @param   ms  Die Zeit in Millisekunden zwischen zwei
     *              Aufrufen der <code>tick()</code>-Methode.
     */
    public void tickerIntervallSetzen( int ms ) 
    {
        super.tickerAnmelden( this , ms );
    }
    
    
    /**
     * Stoppt die Ticker-Funktion. Die <code>tick()</code>-Methode
     * wird nicht weiter aufgerufen. Der automatische Aufruf der 
     * <code>tick()</code>-Methode kann durch die Methode 
     * <code>tickerNeuStarten(int ms)</code> wiedergestartet werden.
     * 
     * @see     #tickerNeuStarten(int)
     */
    public void tickerStoppen() 
    {
        super.tickerAbmelden( this );
    }
    
    
    /**
     * Startet den Ticker neu.
     * 
     * @param   ms      Die Zeit in Millisekunden zwischen zwei
     *                  Aufrufen der <code>tick()</code>-Methode. 
     */
    public void tickerNeuStarten( int ms ) 
    {
        super.tickerAnmelden( this , ms );
    }
    
    
    
    
    
    // ===  M e t h o d e n   z u m   U e b e s c h r e i b e n  ===
    
    /**
     * Wird regelmaessig automatisch aufgerufen. So kommt Bewegung ins Spiel! 
     * Tick-Intervall kann angepasst werden. Ticker muss erst gestartet werden.
     */
    public void tick() 
    {
        //Einfache Bildschirmausgabe. Kann spaeter in Subklasse beliebig ueberschreiben werden.
        zaehler++;
        zaehler = zaehler % 2;
        if ( zaehler == 1 ) 
        {
            System.out.println( "Tick!" );
        }
        else 
        {
            System.out.println( "Tack!" );
        }
    }
    

    /**
     * Wird bei jedem Mausklick (Linksklick) automatisch aufgerufen.
     * 
     * @param   x   Die X-Koordinate des Klicks
     * 
     * @param   y   Die Y-Koordinate des Klicks
     */
    public void klickReagieren( int x , int y ) 
    {
        //Einfache Bildschirmausgabe. Kann spaeter in Subklasse beliebig ueberschrieben werden.
        System.out.println( "Klick bei (" + x  + ", " + y + ")." );
    }
    
    
    /**
     * Wird bei jedem Tastendruck automatisch aufgerufen und automatisch das Kuerzel der entsprechenden Taste mitgegeben.
     * 
     * @param   taste   ganzzahliges Kuerzel der Taste (Farben_Tastencode.pdf) 
     *                  oder ENUM-Typ aus Klasse TASTE (darin die Klassen-Doku lesen)
     */
    public void tasteReagieren( int taste ) 
    {
        System.out.println( "Taste mit Kuerzel " + taste + " wurde gedrueckt" );
    }
    
    
    
    
    
    
    // ===  T o o l s  ===
    
    /**
     * Gibt eine Zufallszahl aus.
     * 
     * @param von   Die Untergrenze der Zufallszahl (INKLUSIVE)
     * 
     * @param bis   Die Obergrenze der Zufallszahl (INKLUSIVE)
     * 
     * @return      Eine Zufallszahl z mit:   von <= z <= bis
     */
    public int zufallszahlVonBis( int von , int bis ) 
    {
        return ea.Random.nextInteger( bis - von ) + von;
    }
    
    
    /**
     * Wartet um die Angegebene Anzahl an Millisekunden <b>BLOCKIEREND</b> bis zur Ausfuehrung des naechsten Befehls. 
     * <b>!!! VORSICHT !!!</b> Innerhlab der Methode tick() <b>NICHT</b>  verwenden !!! 
     * (ausser es ist sicher gestellt, dass die Summe aller 'warte-Millisekunden' <b>KUERZER</b> ist als ein Tick-Intervall)
     *
     * @param   ms      Die zu wartende Zeit in Millisekunden
     */
    public void warte( int ms )
    {
        try
        {
            Thread.sleep( ms );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Ueberprueft, ob eine Taste gerade gedrueckt gehalten wird.
     * 
     * @param   taste   Der ganzzahlige Wert, der fuer die gedrueckte Taste steht.
     *                  Details koennen in der <i>Tabelle aller Tastaturkuerzel</i> abgelesen werden. 
     *                  Oder man verwendet die ENUM-Typen der Klasse TASTE (Klassen-Doku lesen).
     *                  
     * @return  true, falls die Taste gedrueckt gehalten wird.                 
     */
    public boolean tasteGedrueckt( int taste )
    {
        return ea.Game.isKeyPressed( taste );
    }
    
    
    
    
    
    // ===  K a m e r a  ===
    
    /**
     * Verschiebt die Kamera um ein Stueck. 
     *
     * @param   dX      Anzahl Pixel in x-Richtung
     * @param   dY      Anzahl Pixel in y-Richtung
     */
    public void verschiebeKamera( float dX , float dY )
    {
        super.verschiebeKamera( dX , dY );
    }
    
    /**
     * Setzt den Zoom-Faktor der Kamera. 1.0 ist normal. 
     *
     * @param   zoom    Zoom-Faktor: >1 vergroessert ; <1 (aber >0) verkleinert
     */
    public void setzeKameraZoom( float zoom )
    {
        super.setzeKameraZoom( zoom );
    }
    
    /**
     * Nennt den aktuellen Zoom-Wert der Kamera. 
     *
     * @return  aktueller Zoom-Wert der Kamera: >1 vergroessert ; <1 (aber >0) verkleinert
     */
    public float nenneKameraZoom()
    {
        return super.nenneKameraZoom();
    }
    
    /**
     * Setze den Kamera-Fokus auf ein bestimmtes Objekt. 
     *
     * @param   focus   Das neue Objekt im Zentrum der Kamera
     */
    public void setzeKameraFokus( Actor focus )
    {
        super.setzeKameraFokus( focus);
    }
    
    /**
     * Rotiert die Kamera im oder gegen den Uhrzeigersinn. 
     *
     * @param   winkelInGrad    Winkel, um den gedreht werden soll.
     *                          >0 im Uhrzeigersinn ; <0 gegen den Uhrzeigersinn
     */
    public void rotiereKamera( float winkelInGrad )
    {
        super.rotiereKamera( (float)(Math.toRadians( winkelInGrad )) );
    }
    
    /**
     * Setzt den Rotationswinkel der Kamera auf einen bestimmten Wert. 
     *
     * @param   winkelInGrad    Der neue Kamera-Winkel in Grad
     */
    public void setzeKameraRotation( float winkelInGrad )
    {
        super.setzeKameraRotation( (float)(Math.toRadians( winkelInGrad )) );
    }
    
    
    
    
    
    // ===  S z e n e n  ===
    
    // /**
     // * Benennt eine Szene
     // *
     // * @param   name    neuer Name der Szene
     // */
    // public void benenneSzene(String name) {
        // super.benenneSzene(name);
    // }
    
    // /**
     // * Laedt eine (andere) Szene
     // *
     // * @param   name    Name der zu ladenden Szene
     // */
    // public void setzeSzene(String name) {
        // super.setzeSzene(name);
    // }
    
    // public void entferneSzene(String name) {
        // //TO IMPLEMENT
    // }
    
    // /**
     // * Erzeugt eine neue Szene
     // */
    // public void neueSzene() {
        // super.neueSzene();
    // }
    
    
    
    
    
    // ===  D e b u g  ===
    
    /**
     * Methode zeigeKoordinatensystem
     *
     * @param anzeigen Ein Parameter
     */
    public static void zeigeKoordinatensystem( boolean anzeigen )
    {
        ea.Game.setDebug( anzeigen );
    }
    
    
    
    
    
    // ===  D i a l o g e  ===
    
    /**
     * Gibt ein <b>blockierendes</b> Nachricht-Fenster aus.
     *
     * @param   nachricht   angezeigte Nachricht in dem Fenster
     */
    public void nachricht( String nachricht )
    {
        super.nachricht( nachricht );
    }
    
    
    /**
     * Gibt ein <b>blockierendes</b> Frage-Fenster aus.
     *
     * @param   frage   angezeigte Frage in dem Fenster
     * @return  'true' = Ja , 'false' = Nein
     */
    public boolean frageJaNein( String frage )
    {
        return super.frageJaNein( frage );
    }
    
    
    /**
     * Gibt ein <b>blockierendes</b> Frage-Fenster aus.
     *
     * @param   frage   angezeigte Frage in dem Fenster
     * @return  'true' = Ok , 'false' = Abbrechen
     */
    public boolean nachrichtOkAbbrechen( String frage )
    {
        return super.nachrichtOkAbbrechen( frage );
    }
    
    
    /**
     * Gibt ein <b>blockierendes</b> Eingabe-Fenster aus.
     *
     * @param   angezeigte Nachricht in dem Fenster
     * @return  Benutzer-Eingabe
     */
    public String eingabe( String nachricht )
    {
        return super.eingabe( nachricht );
    }
    
    
    /**
     * Setzt die Intensitaet der Schwerkraft (normal=9.8). 
     *
     * @param   meterProQuadratsekunde      Wert fuer die gewuenschte Fallbeschleunigung
     */
    public void setzeSchwerkraft( float meterProQuadratsekunde )
    {
        super.setzeSchwerkraft( meterProQuadratsekunde );
    }
    
    
    /**
     * Hiermit kann die Skalierung des Bildschirms eingestellt werden z.B. fuer physikalische Simulationen. 
     * Standard-Wert ist 30.
     *
     * @param   ppm     Anzahl der Pixel am Monitor, die in Wirklichkeit einem Meter entspechen. (Standard=30)
     */
    public void setzePixelProMeter( int ppm )
    {
        setzePixelProMeter( ppm );
    }
}
