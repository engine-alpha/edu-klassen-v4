import ea.edu.*;
import ea.*;

/**
 * Klasse TEXT zum Darstellen von Texten in EDU-Engine
 * 
 * @author      mike ganshorn
 * 
 * @version     4.0 (2018-08-06)
 * 
 * @changelog   4.0 Umstieg auf EA 4
 * 
 *              1.3 WECHSELBILD erbt von Knoten und damit von Raum
 *                  verschiebenUm greift auf bewegen zurueck
 *                  Methoden in allen Klassen vereinheitlicht (bis auf indiv. Methoden)
 *                  setzeInhalt(int) hinzugefuegt (bisher nur String)
 *                  Konstruktor(int,int,int) hinzugefuegt (bisher nur int,int,String)
 * 
 *              1.2 Methode beruehrt(WECHSELBILD) hinzugefuegt
 * 
 *              1.1 Jump'n'Run-Physik hinzu gefuegt
 *              
 */
public class TEXT
extends EduText 
{
    private float M_x;
    
    private float M_y;
    
    
    /**
     * TEXT Konstruktor
     *
     * @param   x       x-Koordinate im Fenster (Pixel)
     * 
     * @param   y       y-Koordinate im Fenster (Pixel)
     * 
     * @param   text    anzuzeigender Text
     */
    public TEXT( int x , int y , String text ) 
    {
        super( text );
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * TEXT Konstruktor
     *
     * @param   x       x-Koordinate im Fenster (Pixel)
     * 
     * @param   y       y-Koordinate im Fenster (Pixel)
     * 
     * @param   zahl    anzuzeigende Zahl
     */
    public TEXT(int x , int y , int zahl ) 
    {
        super( ""+zahl );
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Legt die Ebene fest, in der das Objekt gezeichnet wird. 
     * Ebenen mit grossen Nummern ueberdecken Ebenen mit kleineren Nummern. 
     * Der Hintergrund ist -1. Jedes Objekt wird zunaechst in Ebene 0 erzeugt. 
     *
     * @param   ebenenNummer    -1=Hintergrund ; 0=Standard (ueberdeckt Hintergrund) ; 1=weiter vorne (ueberdeckt Hintergrund und Ebene 0) ; ...
     */
    public void setzeEbene( int ebenenNummer )
    {
        this.setLayer( ebenenNummer );
    }
    
    
    /**
     * Setzt den angezeigten Text neu.
     *
     * @param   text    Der neue Text
     */
    public void setzeInhalt( String text )
    {
        super.setzeInhalt( text );
    }
    
    
    /**
     * Setzt den angezeigten Text neu.
     *
     * @param   text    Der neue Text
     */
    public void setzeInhalt( float zahl )
    {
        super.setzeInhalt( "" + zahl );
    }
    
    
    /**
     * Setzt die Schriftgroesse auf einen neuen Wert.
     *
     * @param   schriftgroesse  neue Schriftgroesse
     */
    public void setzeGroesse( int schriftgroesse )
    {
        super.setzeGroesse( schriftgroesse );
    }
    
    
    /**
     * Setzt die Schriftfarbe neu.
     *
     * @param   schriftfarbe    Die neue Schriftfarbe
     */
    public void setzeFarbe( String schriftfarbe )
    {
        super.setzeFarbe( schriftfarbe );
    }
    
    
    /**
     * Setzt die Schriftart. Diese muss als TrueTypeFont als Datei im Projekt liegen. 
     *
     * @param   ttfDatei    Pfad zur TTF-Datei
     */
    public void setzeSchriftart( String ttfDatei )
    {
        super.setFont( ttfDatei );
    }
    
    
    /**
     * Setzt den Schriftstil (fett, kursiv) des Textes. 
     *
     * @param   schriftStil     0=normal , 1=fett , 2=kursiv , 3=fett+kursiv 
     */
    public void setzeStil( int schriftStil )
    {
        super.setStyle( schriftStil );
    }
    
    
    /**
     * Verschiebt das Objekt um die angegebenen Pixel. 
     *
     * @param   deltaX  Pixel in x-Richtung (wird bei Bedarf auf ganze Pixel gerundet)
     * 
     * @param   deltaY  Pixel in y-Richtung (wird bei Bedarf auf ganze Pixel gerundet)
     */
    public void verschiebenUm( float deltaX , float deltaY ) 
    {
        this.M_x += deltaX;
        this.M_y += deltaY;
        super.verschieben( deltaX , deltaY );
    }
    
    
    /**
     * Prueft, ob ein anderes Grafik-Objekt beruehrt wird. 
     *
     * @param   ea   Ein anderes BILD, RECHTECK, KREIS, DREIECK, ...
     * 
     * @return  true, wenn sich die beiden Objekte ueberschneiden
     */
    public boolean beruehrt( EduActor ea ) 
    {
        return super.schneidet( ea.getActor() );
    }

    
    /**
     * Prueft, ob das Objekt einen bestimmten Punkt (in Pixel-Koordinaten) beinhaltet. 
     *
     * @param   x   x-Koordinate des Punkts (Pixel)
     * 
     * @param   y   x-Koordinate des Punkts (Pixel)
     * 
     * @return      true, wenn Punkt innerhalb der Grafik
     */
    public boolean beinhaltetPunkt( float x , float y ) 
    {
        return super.beinhaltetPunkt( x  ,y );
    }
    
    
    /**
     * Setzt den Mittelpunkt des Objekts auf einen (in Pixel-Koordinaten) anzugebenden Punkt. 
     *
     * @param   x   x-Koordinate des Mittelpunkts (Pixel)
     * 
     * @param   y   y-Koordinate des Mittelpunkts (Pixel)
     */
    public void setzeMittelpunkt( float x , float y ) 
    {
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Nennt die Nummer der Ebene, in der dieses Objekt derzeit gezeichnet wird. 
     * Durch veraendern der Ebenen-Nummer kann man Objekte vor / hinter andere stellen. 
     * Ebenen mit groesserer Nummer verdecken Ebenen mit kleinerer Nummer.
     *
     * @return  Ebenen-Nummer: -1=Hintergrund ; 0=Standard (ueberdeckt Hintergrund) , 1=weiter vorne (ueberdeckt Hintergrund und Ebene 0) ; ...
     */
    public int nenneEbene()
    {
        return this.getLayer();
    }
    
    
    /**
     * Nennt die x-Koordinate (in Pixel) des Mittelpunkts dieses Objekts. 
     *
     * @return  x-Koordinate des Mittelpunkts (Pixel)
     */
    public float nenneMx() 
    {
        return this.M_x;
    }
    
    
    /**
     * Nennt die y-Koordinate (in Pixel) des Mittelpunkts dieses Objekts. 
     *
     * @return  y-Koordinate des Mittelpunkts (Pixel)
     */
    public float nenneMy() 
    {
        return this.M_y;
    }
    
    
    /**
     * Macht das Objekt sichtbar / unsichtbar. 
     *
     * @param   sichtbarNeu     true, wenn die Grafik sichtbar sein soll, sonst false
     */
    public void setzeSichtbar( boolean sichtbarNeu ) 
    {
        super.setzeSichtbar( sichtbarNeu );
    }
    
    
    /**
     * Prueft, od dieses Objekt gerade sichtbar ist. 
     *
     * @return  true, wenn die Grafik gerade sichbar ist, sonst false
     */
    public boolean nenneSichtbar()
    {
        return super.nenneSichtbar();
    }
    
    
    /**
     * Dreht die Grafik um einen Winkel
     *
     * @param   winkelAenderung     +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void drehenUm( float winkelAenderung )
    {
        float x = this.nenneMx();
        float y = this.nenneMy();
        this.setzeSichtbar( false );
        this.drehen( (float)Math.toRadians(winkelAenderung)  );
        this.setzeMittelpunkt( x , y );
        this.setzeSichtbar( true );
    }
    
    
    /**
     * Setzt den Drehwinkel auf eine absoluten neuen Wert
     *
     * @param   neuerDrehwinkel     der neue Drehwinkel
     *                              +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void setzeDrehwinkel( float neuerDrehwinkel )
    {
        this.drehenUm( neuerDrehwinkel - this.nenneDrehwinkel() );
    }
    
    
    /**
     * Nennt den Winkel, um den die Grafik gegenueber ihrer Erzeugung gedreht wurde. 
     *
     * @return      der Winkel, um den die Grafik gedreht wurde
     *              0: wenn nicht gedreht
     *              +: wenn mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *              -: wenn mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public float nenneDrehwinkel()
    {
        return (float)Math.toDegrees( super.nenneWinkel() );
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Objekts vom Mittelpunkt 
     * eines anderen Grafik-Objekts in x-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Rechtecks vom anderen Grafik-Objekt in x-Richtung 
     *          (>0, wenn dieses Rechteck rechts des anderen Grafik-Objekts liegt)
     */
    public float berechneAbstandX( EduActor ea )
    {
        return this.M_x - ea.mittelPunkt().x;
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Objekts vom Mittelpunkt 
     * eines anderen Grafik-Objekts in y-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Kreises vom anderen Grafik-Objekt in y-Richtung 
     *          (>0, wenn dieser Kreis unterhalb des anderen Grafik-Objekts liegt)
     */
    public float berechneAbstandY( EduActor ea )
    {
        return this.M_y - ea.mittelPunkt().y;
    }
    
    
    
    // === Jump'n'Run-Physik ===
    
    // /**
     // * Laesst dieses Grafik-Objekt springen, wenn es selbst aktiv ist und 
     // * es gleichzeitig auf einem passiven Grafik-Objekt steht
     // *
     // * @param   staerke     grosse Werte fuer hohe Spruenge (Werte vermutlich > 1.0)
     // */
    // public void sprung( float staerke )
    // {
        // super.getActor().physics.applyImpulse( new ea.Vector( 0 , 1000 * staerke) );
    // }
    
    
    // /**
     // * Gibt an, ob dieses Grafik-Objekt auf einem anderen steht. 
     // * <b>!!! Wissenswert !!!</b> Funktioniert nur, wenn dieses Grafik-Objekt aktiv und das andere passiv ist.
     // *
     // * @return  'true' wenn dieses aktive Objekt auf einem anderen passiven Objekt steht, sonst 'false'
     // */
    // public boolean steht()
    // {
        // return super.steht();
    // }
    
    
    // /**
     // * Gibt an, ob dieses Grafik-Objekt auf einem bestimmten anderen steht.
     // *
     // * @param   go  Das andere Grafik-Objekt
     // * @return  'true' = steht drauf , 'false' = steht nicht drauf
     // */
    // public boolean stehtAuf( EduActor ea )
    // {
        // return super.stehtAuf( ea.getActor() );
    // }
    
    
    // /**
     // * Macht dieses Grafik-Objekt im Sinne der Jump'n'Run-Physik aktiv. 
     // * Es unterliegt nun der Schwerkraft und kann auf passiven Objekten 
     // * stehen und von ihnen abspringen.
     // *
     // */
    // public void macheAktiv()
    // {
        // super.macheAktiv();
    // }
    
    
    // /**
     // * Macht dieses Grafik-Objekt im Sinne der Jump'n'Run-Physik passiv. 
     // * Es kann nun nicht mehr von aktiven Objekten durchdrungen werden.  
     // * Aktive Objekte koennen nun davon abspringen.
     // *
     // */
    // public void machePassiv()
    // {
        // super.machePassiv();
    // }
    
    
    // /**
     // * Macht dieses Grafik-Objekt im Sinne der Jump'n'Run-Physik neutral. 
     // * Dieses Objekt nimmt nun nicht mehr an der Jump'n'Run-Physik Teil!
     // *
     // */
    // public void macheNeutral()
    // {
        // super.macheNeutral();
    // }
    
    
    
    // // === Animationen ===
    
    // public void animationGerade( float ziel_x , float ziel_y , int milliSec , boolean pingpong )
    // {
        // super.geradenAnimation( ziel_x , ziel_y , milliSec , pingpong );
    // }
    
    
    // public void animationKreis( float zentrum_x , float zentrum_y , int milliSec , boolean uhrzeigersinn , boolean rotation )
    // {
        // super.kreisAnimation( zentrum_x , zentrum_y , milliSec , uhrzeigersinn , rotation );
    // }
    
    
}
