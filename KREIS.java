/** @author     mike_gans@yahoo.de  and  michael andonie
 * 
 *  @Version    4.1 (2020-01-04)
 *  
 *  @Changelog  4.1 float durch double ersetzt
 *                  Mittelpunkt stimmt nun auch, wenn aktiv
 *                  setzeDichte, nenneDichte und nenneMasse hinzugefuegt
 *                  diverse kleinere Optimierungen
 *              4.0 Umstieg auf EA 4
 *  
*/

import ea.edu.Kreis;
import ea.edu.EduActor;

/**
 * Diese Klasse stellt einen einfachen Kreis dar. 
 */
public class KREIS
extends Kreis 
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
    private double radius;
    
    /**
     * x-Koordinate des Mittelpunkts
     */
    private double M_x;
    
    /**
     * y-Koordinate des Mittelpunkts
     */
    private double M_y;
    
    
    /**
     * Konstruktor der Klasse <code>KREIS</code>. Erstellt einen neuen Standard-Kreis.
     */
    public KREIS() 
    {
        this( 2 );
    }
    
    
    /**
     * Konstruktor der Klasse <code>KREIS</code>. Erstellt einen neuen Kreis mit gegebenem Radius (in Bildschirm-Metern).
     *
     * @param   rNeu    Der radius des Kreises (in Bildschirm-Metern)
     */
    public KREIS( double rNeu ) 
    {
        super(rNeu);
        //this.kreis = new Kreis( rNeu );
        this.sichtbar = true;
        super.setzeSichtbar( true );
        this.farbe = "Blau";
        super.setzeFarbe( this.farbe );
        this.radius = rNeu;
        this.M_x = 0;
        this.M_y = 0;
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
     * Setzt die Farbe dieses Kreises neu.
     * 
     * @param   farbeNeu    Diese Farbe erhaelt der Kreis (z.B. "Rot") 
     *                      Farbnamen koennen in der Readme.txt im BlueJ-Projekt nachgelesen werden.
     *                      
     */
    public void setzeFarbe( String farbeNeu ) 
    {
        this.farbe = farbeNeu;
        super.setzeFarbe( farbe );
    }
   
    
    /**
     * Setzt den Mittelpunkt dieses Kreises neu.
     * 
     * @param x   Die X-Koordinate des Mittelpunktes (in Bildschirm-Metern)
     * 
     * @param y   Die Y-Koordinate des Mittelpunktes (in Bildschirm-Metern)
     */
    public void setzeMittelpunkt( double x , double y ) 
    {
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Setzt den Radius dieses Kreises (in Bildschirm-Metern) neu.
     * 
     * @param   radius  Der neue Radius (in Bildschirm-Metern)
     */
    public void setzeRadius( double radius ) 
    {
        //super.entfernen();
        double x = this.nenneMx();
        double y = this.nenneMy();
        //this.radius = radius;
        //this.kreis = new Kreis( radius );
        //super.setzeMittelpunkt( x , y );
        //super.setzeFarbe( this.farbe )*/
        ((ea.actor.Circle)getActor()).resetRadius((float)radius);
        super.setzeMittelpunkt( x , y );
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
        super.setzeSichtbar( sichtbarNeu );
    }
    
    
    /**
     * Verschiebt diesen Kreis um eine Strecke - angegeben durch ein "Delta X" und "Delta Y".
     * 
     * @param   deltaX  Der X Anteil (in Bildschirm-Metern) dieser Verschiebung. Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY  Der Y Anteil (in Bildschirm-Metern) dieser Verschiebung. Positive Werte verschieben nach unten, negative nach oben.
     */
    public void verschiebenUm( double deltaX , double deltaY ) 
    {
        this.M_x = this.M_x + deltaX;
        this.M_y = this.M_y + deltaY;
        super.verschieben( deltaX , deltaY );
    }
    
    
    /**
     * Methode beruehrt
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
     * Diese Methode gibt die x-Koordinate des Mittelpunkts dieses Kreises (in Bildschrim-Metern) zurueck.
     * 
     * @return  Die aktuelle x-Koordinate des Mittelpunktes dieses Kreises (in Bildschrim-Metern)
     */
    public double nenneMx()
    {
        return super.nenneMittelpunktX();
    }
    
    
    /**
     * Diese Methode gibt die y-Koordinate des Mittelpunkts dieses Kreises (in Bildschrim-Metern) zurueck.
     * 
     * @return  Die aktuelle y-Koordinate des Mittelpunktes dieses Kreises (in Bildschrim-Metern)
     */
    public double nenneMy()
    {
        return super.nenneMittelpunktY();
    }
    
    
    /**
     * Diese Methode gibt den Radius dieses Kreises (in Bildschrim-Metern) zurueck.
     * 
     * @return  Der aktuelle Radius dieses Kreises (in Bildschrim-Metern)
     */
    public double nenneRadius()
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
        return super.nenneFarbe();
    }
    
    
    /**
     * Diese Methode gibt die Sichtbarkeit dieses Kreises zurueck.
     * 
     * @return  Die aktuelle Sichtbarkeit dieses Kreises
     */
    public boolean istSichtbar()
    {
        return super.istSichtbar();
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Kreises vom Mittelpunkt 
     * eines anderen EduActors in x-Richtung (in Bildschrim-Metern) entfernt ist.
     * 
     * @param   ea    Der andere EduActor
     * 
     * @return  Abstand (in Bildschrim-Metern) dieses Rechtecks vom anderen EduActor in x-Richtung (>0, wenn dieses Rechteck rechts des anderen EduActors liegt)
     */
    public double berechneAbstandX( EduActor ea )
    {
        return super.nenneMittelpunktX() - ea.nenneMittelpunktX();
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Kreises vom Mittelpunkt 
     * eines anderen EduActors in y-Richtung entfernt ist.
     * 
     * @param   ea    Der andere EduActor
     * 
     * @return  Abstand (in Pixeln) dieses Kreises vom anderen EduActor in y-Richtung (>0, wenn dieser Kreis unterhalb des anderen EduActors liegt)
     */
    public double berechneAbstandY( EduActor ea )
    {
        return super.nenneMittelpunktY() - ea.nenneMittelpunktY();
    }
    
    
    /**
     * Dreht die Grafik um einen Winkel (in Grad)
     *
     * @param   winkelAenderungInGrad     +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                                    -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void drehenUm( double winkelAenderungInGrad )
    {
        double x = super.nenneMittelpunktX();
        double y = super.nenneMittelpunktY();
        super.setzeSichtbar( false );
        super.drehen( winkelAenderungInGrad );
        super.setzeMittelpunkt( x , y );
        super.setzeSichtbar( true );
    }
    
    
    /**
     * Setzt den Drehwinkel auf einen absoluten neuen Wert (in Grad)
     *
     * @param   neuerDrehwinkelInGrad     der neue Drehwinkel (in Grad)
     *                                    +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                                    -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void setzeDrehwinkel( double neuerDrehwinkelInGrad )
    {
        this.drehenUm( neuerDrehwinkelInGrad - this.nenneDrehwinkel() );
    }
    
    
    /**
     * Nennt den Winkel (in Grad), um den die Grafik gedreht wurde
     *
     * @return      der Winkel (in Grad), um den die Grafik gedreht wurde
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
    
    
 