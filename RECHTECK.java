/** @author     mike ganshorn + michael andonie
 * 
 *  @Version    4.0 (2018-08-06)
 *  
 *  
 *  @changelog  4.0 Umstieg auf EA 4
 *  
 *              2.4 WECHSELBILD erbt von Knoten und damit von Raum
 *                  verschiebenUm greift auf bewegen zurueck
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

import ea.edu.Rechteck;
import ea.edu.EduActor;

/**
 * Diese Klasse stellt ein einfaches Rechteck dar.
 */
public class RECHTECK 
implements EduActor
{
        
    /**
     * Die Farbe dieses Rechtecks
     */
    private String farbe;
    
    /**
     * Grafische Repraesentation des Rechtecks
     */
    private Rechteck rechteck;
    
    /**
     * Gibt an, ob dieses Rechteck sichtbar ist.
     */
    private boolean sichtbar;
    
    /**
     * Breite des Rechtecks
     */
    private float breite;
    
    /**
     * Hoehe des Rechtecks
     */
    private float hoehe;
    
    /**
     * x-Koordinate ds Mittelpunkts
     */
    private float M_x;
    
    /**
     * y-Koordinate des Mittelpunkts
     */
    private float M_y;
    
    
    /**
     * Konstruktor der Klasse <code>RECHTECK</code>. Erstellt ein neues Standard-Rechteck.
     */
    public RECHTECK() 
    {
        this( 200 , 130 );
    }
    
    
    /**
     * Konstruktor der Klasse <code>RECHTECK</code>. Erstellt ein neues Rechteck mit gegebenen Massen.
     *
     * @param   breite  Breite des Rechtecks
     * 
     * @param   hoehe   Hoehe des Rechtecks
     */
    public RECHTECK( float breite , float hoehe ) 
    {
        this.rechteck = new Rechteck( breite, hoehe);
        this.sichtbar = true;
        this.rechteck.setzeSichtbar( true );
        this.farbe = "Rot";
        this.rechteck.setzeFarbe( this.farbe );
        this.breite = breite;
        this.hoehe = hoehe;
        this.M_x = 250;
        this.M_y = 150;
        this.rechteck.setzeMittelpunkt( this.M_x , this.M_y );
        
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
        this.rechteck.setLayer( ebenenNummer );
    }
    
    
    /**
     * Setzt die Hoehe und Breite dieses Rechtecks neu.
     * 
     * @param   breite  Die neue Breite dieses Rechtecks
     * 
     * @param   hoehe   Die neue Hoehe dieses Rechtecks
     */
    public void setzeGroesse( float breite , float hoehe ) 
    {
        float x = this.nenneMx();
        float y = this.nenneMy();
        float winkel = this.nenneDrehwinkel();
        this.rechteck.entfernen();
        this.breite = breite;
        this.hoehe = hoehe;
        this.rechteck = new Rechteck( breite , hoehe );
        this.rechteck.setzeMittelpunkt( x , y );
        this.rechteck.setzeFarbe( this.farbe );
        this.setzeDrehwinkel( winkel );
    }
    
    
    /**
     * Setzt die Farbe dieses Rechtecks neu.
     * 
     * @param   farbeNeu    Diese Farbe erhaelt das Rechteck (z.B. "Rot")
     */
    public void setzeFarbe( String farbeNeu ) 
    {
        this.farbe = farbeNeu;
        this.rechteck.setzeFarbe( farbeNeu );
    }
    
    
    /**
     * Setzt den Mittelpunkt dieses Rechtecks neu.
     * 
     * @param   x   Die X-Koordinate des neuen Mittelpunktes
     * 
     * @param   y   Die Y-Koordinate des neuen Mittelpunktes
     */
    public void setzeMittelpunkt( float x , float y ) 
    {
        this.M_x = x;
        this.M_y = y;
        this.rechteck.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Setzt, ob dieses Rechteck sichtbar sein soll.
     * 
     * @param   sichtbarNeu Ist dieser Wert <code>true</code>, ist nach dem Aufruf dieser 
     *          Methode dieses Rechteck sichtbar. Ist dieser Wert <code>false</code>, so ist 
     *          nach dem Aufruf dieser Methode dieses Rechteck unsichtbar.
     */
    public void setzeSichtbar( boolean sichtbarNeu ) 
    {
        this.sichtbar = sichtbarNeu;
        this.rechteck.setzeSichtbar( sichtbarNeu );
    }    
    
    /**
     * Verschiebt dieses Rechteck um eine Verschiebung - angegeben durch ein "Delta X" und "Delta Y".
     * 
     * @param   deltaX  Der X Anteil dieser Verschiebung. Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY  Der Y Anteil dieser Verschiebung. Positive Werte verschieben nach unten, negative nach oben.
     */
    public void verschiebenUm( float deltaX , float deltaY ) 
    {
        this.M_x = this.M_x + deltaX;
        this.M_y = this.M_y + deltaY;
        this.rechteck.verschieben( deltaX , deltaY );
    }
    
    
    /**
     * Testet, ob ein anderes Grafik-Objekt beruehrt wird.
     *
     * @param   r   Ein anderes BILD, RECHTECK, KREIS, DREIECK, ...
     * 
     * @return  true, wenn sich die beiden Objekte ueberschneiden
     */
    public boolean beruehrt( EduActor ea ) 
    {
        return this.rechteck.schneidet( ea.getActor() );
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
        return this.rechteck.beinhaltetPunkt( x , y );
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
        return this.rechteck.getLayer();
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
     * Diese Methode gibt die Breite dieses Rechtecks zurueck.
     * 
     * @return  Die aktuelle Breite dieses Rechtecks
     */
    public float nenneBreite()
    {
        return this.breite;
    }
    
    
    /**
     * Diese Methode gibt die Hoehe dieses Rechtecks zurueck.
     * 
     * @return  Die aktuelle Hoehe dieses Rechtecks
     */
    public float nenneHoehe()
    {
        return this.hoehe;
    }
    
    
    /**
     * Diese Methode gibt die Farbe dieses Rechtecks zurueck.
     * 
     * @return  Die aktuelle Farbe dieses Rechtecks
     */
    public String nenneFarbe()
    {
        return this.farbe;
    }
    
    
    /**
     * Diese Methode gibt die Sichtbarkeit dieses Rechtecks zurueck.
     * 
     * @return  Die aktuelle Sichtbarkeit dieses Rechtecks
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
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Rechtecks vom Mittelpunkt 
     * eines anderen Grfik-Objekts in y-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Rechtecks vom anderen Grafik-Objekt in y-Richtung (>0, wenn dieses Rechteck unterhalb des anderen Grafik-Objekts liegt)
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
        float x = this.rechteck.nenneMx();
        float y = this.rechteck.nenneMy();
        this.rechteck.drehen( (float)Math.toRadians(winkelAenderung)  );
        this.rechteck.setzeMittelpunkt( x , y );
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
        float x = this.M_x;
        float y = this.M_y;
        this.drehenUm( neuerDrehwinkel - this.nenneDrehwinkel() );
        this.setzeMittelpunkt( x , y );
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
        return (float)Math.toDegrees( this.rechteck.nenneWinkel() );
    }
    
    
    
    

    
    
    
    
    @Override
    /**
     * Methode des Interfaces 'GrafikObjekt'
     */
    public ea.actor.Actor getActor() {
        return this.rechteck;
    }
    
}