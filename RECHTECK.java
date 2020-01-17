/** @author     mike_gans@yahoo.de  and  michael andonie
 * 
 *  @Version    4.1 (2020-01-04)
 *  
 *  
 *  @changelog  4.1 Mittelpunkt stimmt nun auch, wenn aktiv
 *                  setzeDichte, nenneDichte und nenneMasse hinzugefuegt
 *                  diverse kleine Optimierungen
 *              4.0 Umstieg auf EA 4
 *  
*/

import ea.edu.Rechteck;
import ea.edu.EduActor;

/**
 * Diese Klasse stellt ein einfaches Rechteck dar. 
 */
public class RECHTECK 
extends Rechteck
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
    private double breite;
    
    /**
     * Hoehe des Rechtecks
     */
    private double hoehe;
    
    /**
     * x-Koordinate ds Mittelpunkts
     */
    private double M_x;
    
    /**
     * y-Koordinate des Mittelpunkts
     */
    private double M_y;
    
    
    /**
     * Konstruktor der Klasse <code>RECHTECK</code>. Erstellt ein neues Standard-Rechteck.
     */
    public RECHTECK() 
    {
        this( 170/30f , 110/30f );
    }
    
    
    /**
     * Konstruktor der Klasse <code>RECHTECK</code>. Erstellt ein neues Rechteck mit gegebenen Massen.
     *
     * @param   breite  Breite des Rechtecks (in Bildschirm-Metern)
     * 
     * @param   hoehe   Hoehe des Rechtecks (in Bildschirm-Metern)
     */
    public RECHTECK( double breite , double hoehe ) 
    {
        super( breite , hoehe );
        //this.rechteck = new Rechteck( breite, hoehe);
        this.sichtbar = true;
        super.setzeSichtbar( true );
        this.farbe = "Rot";
        super.setzeFarbe( this.farbe );
        this.breite = breite;
        this.hoehe = hoehe;
        this.M_x = 6;
        this.M_y = 4;
        super.setzeMittelpunkt( this.M_x , this.M_y );
        
    }
    
    
    /**
     * Legt die Ebene fest, in der das Objekt gezeichnet wird. 
     * Ebenen mit grossen Nummern ueberdecken Ebenen mit kleineren Nummern. 
     * Der Hintergrund ist -1. Jedes Objekt wird zunaechst in Ebene 0 erzeugt. 
     * Innerhalb derselben Ebene ueberdecken spaeter erzeugte Objekte die frueher erzeugten.
     *
     * @param   ebenenNummer    -1=Hintergrund ; 0=Standard (ueberdeckt Hintergrund) ; 1=weiter vorne (ueberdeckt Hintergrund und Ebene 0) ; ...
     */
    public void setzeEbene( int ebenenNummer )
    {
        super.getActor().setLayerPosition( ebenenNummer );
    }
    
    
    /**
     * Setzt die Hoehe und Breite dieses Rechtecks neu.
     * 
     * @param   breite  Die neue Breite dieses Rechtecks (in Bildschirm-Metern)
     * 
     * @param   hoehe   Die neue Hoehe dieses Rechtecks (in Bildschirm-Metern)
     */
    public void setzeGroesse( double breite , double hoehe ) 
    {
        double x = super.nenneMittelpunktX();
        double y = super.nenneMittelpunktY();
        //double winkel = this.nenneDrehwinkel();
        //this.rechteck.entfernen();
        //this.breite = breite;
        //this.hoehe = hoehe;
        //this.rechteck = new Rechteck( breite , hoehe );
        //this.rechteck.setzeFarbe( this.farbe );
        //this.setzeDrehwinkel( winkel );
        getActor().setSize((float)breite, (float)hoehe);
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Setzt die Farbe dieses Rechtecks neu.
     * 
     * @param   farbeNeu    Diese Farbe erhaelt das Rechteck (z.B. "Rot"). 
     *                      Farbnamen koennen in der Readme.txt im BlueJ-Projekt nachgelesen werden.
     */
    public void setzeFarbe( String farbeNeu ) 
    {
        this.farbe = farbeNeu;
        super.setzeFarbe( farbeNeu );
    }
    
    
    /**
     * Setzt den Mittelpunkt dieses Rechtecks neu.
     * 
     * @param   x   Die X-Koordinate des neuen Mittelpunktes (in Bildschirm-Metern)
     * 
     * @param   y   Die Y-Koordinate des neuen Mittelpunktes (in Bildschirm-Metern)
     */
    public void setzeMittelpunkt( double x , double y ) 
    {
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
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
        super.setzeSichtbar( sichtbarNeu );
    }    
    
    /**
     * Verschiebt dieses Rechteck um eine Verschiebung - angegeben durch ein "Delta X" und "Delta Y".
     * 
     * @param   deltaX      Der X Anteil dieser Verschiebung (in Bildschirm-Metern).  
     *                      Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY      Der Y Anteil dieser Verschiebung (in Bildschirm-Metern). 
     *                      Positive Werte verschieben nach oben, negative nach unten.
     */
    public void verschiebenUm( double deltaX , double deltaY ) 
    {
        this.M_x = this.M_x + deltaX;
        this.M_y = this.M_y + deltaY;
        super.verschieben( deltaX , deltaY );
    }
    
    
    /**
     * Testet, ob ein anderer EduActor beruehrt wird.
     *
     * @param   ea   Ein anderer EduActor, z.B. FIGUR, RECHTECK, KREIS, DREIECK, ...
     * 
     * @return  true, wenn sich die beiden Objekte ueberschneiden
     */
    public boolean beruehrt( EduActor ea ) 
    {
        return super.schneidet( ea );
    }
    
    
    
    
    /**
     * Diese Methode prueft, ob ein bestimmter Punkt in dem Objekt enthalten ist. <br />
     * z.B. interessant zum Auswerten von Maus-Klicks.
     *
     * @param   x   x-Koordinate des Punkts (in Bildschirm-Metern)
     * @param   y   x-Koordinate des Punkts (in Bildschirm-Metern)
     * 
     * @return      true, wenn Punkt innerhalb der Grafik
     */
    public boolean beinhaltetPunkt( double x , double y ) 
    {
        return super.beinhaltetPunkt( x , y );
    }
    
    
    /**
     * Nennt die Nummer der Ebene, in der dieses Objekt derzeit gezeichnet wird. 
     * Durch veraendern der Ebenen-Nummer kann man Objekte vor / hinter andere stellen. 
     * Ebenen mit groesserer Nummer verdecken Ebenen mit kleinerer Nummer. 
     * Innerhalb derselben Ebene ueberdecken spaeter erzeugte Objekte die frueher erzeugten.
     *
     * @return  Ebenen-Nummer: -1=Hintergrund ; 0=Standard (ueberdeckt Hintergrund) , 1=weiter vorne (ueberdeckt Hintergrund und Ebene 0) ; ...
     */
    public int nenneEbenenposition()
    {
        return super.nenneEbenenposition();
    }
    
       
    /**
     * Diese Methode gibt die x-Koordinate des Mittelpunkts dieses Objekts (in Bildschirm-Metern) zurueck.
     * 
     * @return  Die aktuelle x-Koordinate des Mittelpunktes dieses Objekts (in Bildschirm-Metern)
     */
    public double nenneMx()
    {
        return super.nenneMittelpunktX();
    }
    
    
    /**
     * Diese Methode gibt die y-Koordinate des Mittelpunkts dieses Objekts (in Bildschirm-Metern) zurueck.
     * 
     * @return  Die aktuelle y-Koordinate des Mittelpunktes dieses Objekts (in Bildschirm-Metern)
     */
    public double nenneMy()
    {
        return super.nenneMittelpunktY();
    }
    
    
    /**
     * Diese Methode gibt die Breite dieses Objekts (in Bildschirm-Metern) zurueck.
     * 
     * @return  Die aktuelle Breite dieses Objekts (in Bildschirm-Metern)
     */
    public double nenneBreite()
    {
        return this.breite;
    }
    
    
    /**
     * Diese Methode gibt die Hoehe dieses Objekts (in Bildschirm-Metern) zurueck.
     * 
     * @return  Die aktuelle Hoehe dieses Objekts (in Bildschirm-Metern)
     */
    public double nenneHoehe()
    {
        return this.hoehe;
    }
    
    
    /**
     * Diese Methode gibt die Farbe dieses Objekts zurueck.
     * 
     * @return  Die aktuelle Farbe dieses Objekts
     */
    public String nenneFarbe()
    {
        return super.nenneFarbe();
    }
    
    
    /**
     * Diese Methode gibt die Sichtbarkeit dieses Objekts zurueck. 
     * 
     * @return  Die aktuelle Sichtbarkeit dieses Objekts
     */
    public boolean istSichtbar()
    {
        return this.istSichtbar();
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Objekts vom Mittelpunkt 
     * eines anderen EduActors in x-Richtung (in Bildschirm-Metern) entfernt ist. 
     * 
     * @param   ea    Der andere EduActor
     * 
     * @return  Abstand (in Bildschirm-Metern) dieses Objekts vom anderen EduActor in x-Richtung (>0, wenn dieses Rechteck rechts des anderen EduActors liegt)
     */
    public double berechneAbstandX( EduActor ea )
    {
        return super.nenneMittelpunktX() - ea.nenneMittelpunktX();
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Objekts vom Mittelpunkt 
     * eines anderen EduActors in y-Richtung (in Bildschirm-Metern) entfernt ist. 
     * 
     * @param   ea    Der andere EduActor (in Bildschirm-Metern)
     * 
     * @return  Abstand (in Pixeln) dieses Rechtecks vom anderen EduActor in y-Richtung (>0, wenn dieses Rechteck unterhalb des anderen EduActors liegt)
     */
    public double berechneAbstandY( EduActor ea )
    {
        return super.nenneMittelpunktY() - ea.nenneMittelpunktY();
    }
    
    
    /**
     * Dreht die Grafik um einen Winkel (in Grad). 
     *
     * @param   winkelInGrad        +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void drehenUm( double winkelInGrad )
    {
        double x = super.nenneMittelpunktX();
        double y = super.nenneMittelpunktY();
        super.drehen( winkelInGrad );
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Setzt den Drehwinkel auf einen absoluten neuen Wert (in Grad). 
     *
     * @param   neuerDrehwinkelInGrad     der neue Drehwinkel
     *                                    +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                                    -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void setzeDrehwinkel( double neuerDrehwinkelInGrad )
    {
        double x = super.nenneMittelpunktX();
        double y = super.nenneMittelpunktY();
        this.drehenUm( neuerDrehwinkelInGrad - this.nenneDrehwinkel() );
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Nennt den Winkel (in Grad), um den die Grafik (im Vergleich zu ihrer Erzeugung) gedreht wurde
     *
     * @return      der Winkel in Grad, um den die Grafik gedreht wurde
     *              0: wenn nicht gedreht
     *              +: wenn mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *              -: wenn mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public double nenneDrehwinkel()
    {
        return super.nenneDrehwinkel();
    }
    
    
    /**
     * Gibt die aktuelle (Flaechen)Dichte des Koerpers zurueck. (Standard: 10kg/m2)
     *
     * @return  Die aktuelle Dichte in kg/m2
     */
    public double nenneDichte()
    {
        return super.nenneDichte();
    }
    
    
    /**
     * Gibt die aktuelle Masse des Koerpers zurueck. (Standard: 10kg/m2)
     *
     * @return  Die aktuelle Masse in kg
     */
    public double nenneMasse()
    {
        return super.nenneMasse();
    }
    
    
    /**
     * Setzt die (Flaechen)Dichte und damit die Masse des Koerpers. (Standard: 10kg/m2)
     *
     * @param   dichte  Die neue (Flaechen)Dichte in kg/m2
     */
    public void setzeDichte( double dichte )
    {
        super.setzeDichte( dichte );
    }
    
    
}