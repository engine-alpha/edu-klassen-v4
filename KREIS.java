/** @author     mike ganshorn + michael andonie
 * 
 *  @Version    4.0 (2018-08-06)
 *  
 *  @Changelog  4.0 Umstieg auf EA 4
 *  
 *              2.4 WECHSELBILD erbt von Knoten und damit von Raum
 *                  Methoden in allen Klassen vereinheitlicht (bis auf indiv. Methoden)
 *              
 *              2.3 Methode beruehrt(WECHSELBILD) hinzugefuegt
 *  
 *              2.2 Jump'n'Run-Physik hinzu gefuegt
 *  
 *              2.1 Bei Aenderung der Breite/Hoehe bleibt der Mittelpunkt erhalten
 *                  Keine Abhaengigkeit mehr zwischen den alpha-Formen
 *              
*/

import ea.edu.Kreis;
import ea.edu.EduActor;

/**
 * Diese Klasse stellt einen einfachen Kreis dar.
 */
public class KREIS 
implements EduActor
{
     
    /**
     * Die Farbe dieses Kreises
     */
    private String farbe;
    
    /**
     * Grafische Repraesentation des Kreises
     */
    private Kreis kreis;
    
    /**
     * Gibt an, ob dieser Kreis sichtbar ist.
     */
    private boolean sichtbar;
    
    /**
     * Radius dieses Kreises
     */
    private float radius;
    
    /**
     * x-Koordinate des Mittelpunkts
     */
    private float M_x;
    
    /**
     * y-Koordinate des Mittelpunkts
     */
    private float M_y;
    
    
    /**
     * Konstruktor der Klasse <code>KREIS</code>. Erstellt einen neuen Standard-Kreis.
     */
    public KREIS() 
    {
        this( 50 );
    }
    
    
    /**
     * Konstruktor der Klasse <code>KREIS</code>. Erstellt einen neuen Kreis mit gegebenem Radius.
     *
     * @param   rNeu    Der radius des Kreises
     */
    public KREIS( float rNeu ) 
    {
        this.kreis = new Kreis( rNeu );
        this.sichtbar = true;
        this.kreis.setzeSichtbar( true );
        this.farbe = "Blau";
        this.kreis.setzeFarbe( this.farbe );
        this.radius = rNeu;
        this.M_x = 0;
        this.M_y = 0;
        this.kreis.setzeMittelpunkt( this.M_x , this.M_y );
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
        this.kreis.setLayer( ebenenNummer );
    }
    
    
     /**
     * Setzt die Farbe dieses Kreises neu.
     * 
     * @param   farbeNeu    Diese Farbe erhaelt der Kreis (z.B. "Rot")
     */
    public void setzeFarbe( String farbeNeu ) 
    {
        this.farbe = farbeNeu;
        this.kreis.setzeFarbe( farbe );
    }
   
    
    /**
     * Setzt den Mittelpunkt dieses Kreises neu.
     * 
     * @param x   Die X-Koordinate des Mittelpunktes.
     * 
     * @param y   Die Y-Koordinate des Mittelpunktes.
     */
    public void setzeMittelpunkt( float x , float y ) 
    {
        this.M_x = x;
        this.M_y = y;
        this.kreis.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Setzt den Radius dieses Kreises neu.
     * 
     * @param   radius  Der neue Radius (in Pixel)
     */
    public void setzeRadius( float radius ) 
    {
        this.kreis.entfernen();
        float x = this.nenneMx();
        float y = this.nenneMy();
        this.radius = radius;
        this.kreis = new Kreis( radius );
        this.kreis.setzeMittelpunkt( x , y );
        this.kreis.setzeFarbe( this.farbe );
    }
    
    
    /**
     * Setzt, ob dieser Kreis sichtbar sein soll.
     * 
     * @param   sichtbarNeu Ist dieser Wert <code>true</code>, ist nach dem Aufruf dieser Methode dieser Kreis 
     *          sichtbar. Ist dieser Wert <code>false</code>, so ist nach dem Aufruf dieser Methode dieser Kreis unsichtbar.
     */
    public void setzeSichtbar( boolean sichtbarNeu ) 
    {
        this.sichtbar = sichtbarNeu;
        this.kreis.setzeSichtbar( sichtbarNeu );
    }
    
    
    /**
     * Verschiebt diesen Kreis um eine Verschiebung - angegeben durch ein "Delta X" und "Delta Y".
     * 
     * @param   deltaX  Der X Anteil dieser Verschiebung. Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY  Der Y Anteil dieser Verschiebung. Positive Werte verschieben nach unten, negative nach oben.
     */
    public void verschiebenUm( float deltaX , float deltaY ) 
    {
        this.M_x = this.M_x + deltaX;
        this.M_y = this.M_y + deltaY;
        this.kreis.verschieben( deltaX , deltaY );
    }
    
    
    /**
     * Methode beruehrt
     *
     * @param   r   Ein anderes BILD, RECHTECK, KREIS, DREIECK, ...
     * 
     * @return  true, wenn sich die beiden Objekte ueberschneiden
     */
    public boolean beruehrt( EduActor ea ) 
    {
        return this.kreis.schneidet( ea.getActor() );
    }
    
    
    /**
     * Methode beinhaltetPunkt
     *
     * @param   x   x-Koordinate des Punkts (Pixel)
     * @param   y   x-Koordinate des Punkts (Pixel)
     * @return      true, wenn Punkt innerhalb der Grafik
     */
    public boolean beinhaltetPunkt( float x , float y ) 
    {
        return this.kreis.beinhaltetPunkt( x , y );
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
        return this.kreis.getLayer();
    }
    
    
    /**
     * Diese Methode gibt die x-Koordinate des Mittelpunkts dieses Dreiecks zurueck.
     * 
     * @return  Die aktuelle x-Koordinate des Mittelpunktes dieses Dreiecks
     */
    public float nenneMx()
    {
        return this.M_x;
    }
    
    
    /**
     * Diese Methode gibt die y-Koordinate des Mittelpunkts dieses Kreises zurueck.
     * 
     * @return  Die aktuelle y-Koordinate des Mittelpunktes dieses Kreises
     */
    public float nenneMy()
    {
        return this.M_y;
    }
    
    
    /**
     * Diese Methode gibt den Radius dieses Kreises zurueck.
     * 
     * @return  Der aktuelle Radius dieses Kreises
     */
    public float nenneRadius()
    {
        return this.radius;
    }
    
    
    /**
     * Diese Methode gibt die Farbe dieses Kreises zurueck.
     * 
     * @return  Die aktuelle Farbe dieses Kreises
     */
    public String nenneFarbe()
    {
        return this.farbe;
    }
    
    
    /**
     * Diese Methode gibt die Sichtbarkeit dieses Kreises zurueck.
     * 
     * @return  Die aktuelle Sichtbarkeit dieses Kreises
     */
    public boolean nenneSichtbar()
    {
        return this.sichtbar;
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Rechtecks vom Mittelpunkt 
     * eines anderen Grfik-Objekts in x-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Rechtecks vom anderen Grafik-Objekt in x-Richtung (>0, wenn dieses Rechteck rechts des anderen Grafik-Objekts liegt)
     */
    public float berechneAbstandX( EduActor ea )
    {
        return this.M_x - ea.mittelPunkt().x;
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Kreises vom Mittelpunkt 
     * eines anderen Grfik-Objekts in y-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Kreises vom anderen Grafik-Objekt in y-Richtung (>0, wenn dieser Kreis unterhalb des anderen Grafik-Objekts liegt)
     */
    public float berechneAbstandY( EduActor ea )
    {
        return this.M_y - ea.mittelPunkt().y;
    }
    
    
    /**
     * Dreht die Grafik um einen Winkel
     *
     * @param   winkelAenderung     +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void drehenUm( float winkelAenderung )
    {
        float x = this.kreis.nenneMx();
        float y = this.kreis.nenneMy();
        this.kreis.setzeSichtbar( false );
        this.kreis.drehen( (float)Math.toRadians(winkelAenderung)  );
        this.kreis.setzeMittelpunkt( x , y );
        this.kreis.setzeSichtbar( true );
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
     * Nennt den Winkel, um den die Grafik gedreht wurde
     *
     * @return      der Winkel, um den die Grafik gedreht wurde
     *              0: wenn nicht gedreht
     *              +: wenn mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *              -: wenn mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public float nenneDrehwinkel()
    {
        return (float)Math.toDegrees( this.kreis.nenneWinkel() );
    }
    
    
    
    
    
    
    
    
    
    @Override
    /**
     * Methode des Interfaces 'GrafikObjekt'
     */
    public ea.actor.Actor getActor() 
    {
        return this.kreis;
    }
    
    
    
}