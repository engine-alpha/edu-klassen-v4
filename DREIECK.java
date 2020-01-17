/** @author     mike_gans@yahoo.de  and  michael andonie
 * 
 *  @Version    4.1 (2020-01-09)
 *  
 *  @changelog  4.1 Mittelpunkt stimmt nun auch, wenn aktiv
 *                  Konstruktor mit Parametern und setzeEcken funktionieren richtig
 *                  setzeDichte, nenneDichte und nenneMasse hinzugefuegt
 *                  diverse kleinere Optimierungen
 *              4.0 Umstieg auf EA 4
 *  
*/

import ea.edu.Dreieck;
import ea.edu.EduActor;

/**
 * Diese Klasse stellt ein einfaches Dreieck dar. 
 */
public class DREIECK
extends Dreieck
{
    
    /**
     * Die Farbe dieses Dreiecks
     */
    private String farbe;
    
    /**
     * Grafische Repraesentation des Dreiecks
     */
    private Dreieck dreieck;
    
    /**
     * Gibt an, ob dieses Dreieck sichtbar ist.
     */
    private boolean sichtbar;
    
    /**
     * x-Koordinate des Eckpunkts A
     */
    private double A_x;
    
    /**
     * y-Koordinate des Eckpunkts A
     */
    private double A_y;
    
    /**
     * x-Koordinate des Eckpunkts B
     */
    private double B_x;
    
    /**
     * y-Koordinate des Eckpunkts B
     */
    private double B_y;
    
    /**
     * x-Koordinate des Eckpunkts C
     */
    private double C_x;
    
    /**
     * y-Koordinate des Eckpunkts C
     */
    private double C_y;
    
     /**
     * x-Koordinate des Mittelpunkts des umschliessenden Rechtecks
     */
    private double M_x;
    
    /**
     * y-Koordinate des Mittelpunkts des umschliessenden Rechtecks
     */
    private double M_y;
    
    
    /**
     * Konstruktor der Klasse <code>DREIECK</code>. Erstellt ein neues Standard-Dreieck.
     */
    public DREIECK() 
    {
        this( 3 , 6 , 5 , 2 , 7 , 6 );
    }
    
    
    /**
     * Konstruktor der Klasse <code>DREIECK</code>. Erstellt ein neues Dreieck mit gegebenen Eck-Koordinaten. 
     * Die Reihenfolge der Ecken ist egal. 
     *
     * @param   Ax  Die x-Koordinate der ersten Ecke
     * @param   Ay  Die y-Koordinate der ersten Ecke
     * @param   Bx  Die x-Koordinate der zweiten Ecke
     * @param   By  Die y-Koordinate der zeiten Ecke
     * @param   Cx  Die x-Koordinate der dritten Ecke
     * @param   Cy  Die y-Koordinate der dritten Ecke
     */
    public DREIECK( double Ax , double Ay , double Bx , double By , double Cx , double Cy ) 
    {
        super( Ax , Ay , Bx , By , Cx , Cy );
        this.setzeEcken( Ax , Ay , Bx , By , Cx , Cy );
        this.A_x = Ax;
        this.A_y = Ay;
        this.B_x = Bx;
        this.B_y = By;
        this.C_x = Cx;
        this.C_y = Cy;
        this.M_x = ( Math.min(Math.min(Ax,Bx),Cx) + Math.max(Math.max(Ax,Bx),Cx) ) / 2 ;
        this.M_y = ( Math.min(Math.min(Ay,By),Cy) + Math.max(Math.max(Ay,By),Cy) ) / 2 ;
        this.sichtbar = true;
        super.setzeSichtbar( true);
        this.farbe = "Gruen";
        super.setzeFarbe( this.farbe );
    }
    
    
    /**
     * Legt die Ebene fest, in der das Objekt gezeichnet wird. 
     * Ebenen mit grossen Nummern ueberdecken Ebenen mit kleineren Nummern. 
     * Der Hintergrund ist -1. Jedes Objekt wird zunaechst in Ebene 0 erzeugt. 
     * 
     *
     * @param   ebenenNummer    -1=Hintergrund ; 0=Standard (ueberdeckt Hintergrund) ; 1=weiter vorne (ueberdeckt Hintergrund und Ebene 0) ; ...
     */
    public void setzeEbene( int ebenenNummer )
    {
        super.getActor().setLayerPosition( ebenenNummer );
    }
    
    
    /**
     * Setzt die Ecken dieses Dreiecks (A, B, C) neu.
     * 
     * @param   Ax  Die X-Koordinate des Punktes A
     * @param   Ay  Die Y-Koordinate des Punktes A
     * @param   Bx  Die X-Koordinate des Punktes B
     * @param   By  Die Y-Koordinate des Punktes B
     * @param   Cx  Die X-Koordinate des Punktes C
     * @param   Cy  Die Y-Koordinate des Punktes C
     */
    public void setzeEcken( double Ax , double Ay , double Bx , double By , double Cx , double Cy ) 
    {
        //this.dreieck.entfernen();
        //super.setzeEcken( Ax , Ay , Bx , By , Cx , Cy );
        this.A_x = Ax;
        this.A_y = Ay;
        this.B_x = Bx;
        this.B_y = By;
        this.C_x = Cx;
        this.C_y = Cy;
        this.M_x = ( Math.min(Math.min(Ax,Bx),Cx) + Math.max(Math.max(Ax,Bx),Cx) ) / 2 ;
        this.M_y = ( Math.min(Math.min(Ay,By),Cy) + Math.max(Math.max(Ay,By),Cy) ) / 2 ;
        //dreieck = new Dreieck( Ax , Ay , Bx , By , Cx , Cy );
        //dreieck.setzeFarbe( this.farbe );
        
        ea.Vector[] points = {new ea.Vector(Ax, Ay), new ea.Vector(Bx, By), new ea.Vector(Cx, Cy)};
        ((ea.actor.Polygon)getActor()).resetPoints(points);
        
        super.setzeMittelpunkt( this.M_x , this.M_y );
    }
    
    
    /**
     * Setzt die Farbe dieses Dreiecks neu.
     * 
     * @param   farbeNeu    Diese Farbe erhaelt das Dreieck (z.B. "Rot"). 
     */
    public void setzeFarbe( String farbeNeu ) 
    {
        this.farbe = farbeNeu;
        super.setzeFarbe( this.farbe );
    }
    
    
    /**
     * Setzt den Mittelpunkt dieses Dreieck neu.<br />
     * <b>ACHTUNG!</b><br />
     * Dies ist <i>nicht</i> der geometrische Mittelpunkt. Denkt man sich ein Rechteck, 
     * das man "genau ueber das Dreieck" spannt, sodass es dieses gerade von allen Seiten umschliesst, 
     * so ist der Mittelpunkt <b>dieses Rechtecks</b> der, der hier neu gesetzt wird.
     * 
     * @param   x Die X-Koordinate des neuen Mittelpunktes
     * 
     * @param   y Die Y-Koordinate des neuen Mittelpunktes
     */
    public void setzeMittelpunkt( double x , double y ) 
    {
        double deltaX = x - this.M_x;
        double deltaY = y - this.M_y;
        this.A_x = this.A_x + deltaX;
        this.A_y = this.A_y + deltaY;
        this.B_x = this.B_x + deltaX;
        this.B_y = this.B_y + deltaY;
        this.C_x = this.C_x + deltaX;
        this.C_y = this.C_y + deltaY;
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Setzt, ob dieses Dreieck sichtbar sein soll.
     * 
     * @param   sichtbarNeu Ist dieser Wert <code>true</code>, ist nach dem Aufruf dieser Methode 
     *          dieses Dreieck sichtbar. Ist dieser Wert <code>false</code>, so ist nach dem Aufruf 
     *          dieser Methode dieses Dreieck unsichtbar.
     */
    public void setzeSichtbar( boolean sichtbarNeu ) 
    {
        this.sichtbar = sichtbarNeu;
        super.setzeSichtbar( sichtbarNeu );
    }
    
    
    /**
     * Verschiebt dieses Dreieck um eine gewisse Strecke - angegeben durch ein "Delta X" und "Delta Y" (in Bildschirm-Metern).
     * 
     * @param   deltaX      Der X Anteil dieser Verschiebung (in Bildschirm-Metern). 
     *                      Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY      Der Y Anteil dieser Verschiebung (in Bildschirm-Metern). 
     *                      Positive Werte verschieben nach oben, negative nach unten.
     */
    public void verschiebenUm( double deltaX , double deltaY ) 
    {
        this.A_x = this.A_x + deltaX;
        this.A_y = this.A_y + deltaY;
        this.B_x = this.B_x + deltaX;
        this.B_y = this.B_y + deltaY;
        this.C_x = this.C_x + deltaX;
        this.C_y = this.C_y + deltaY;
        this.M_x = this.M_x + deltaX;
        this.M_y = this.M_y + deltaY;
        super.verschieben( deltaX , deltaY );
    }
    
    
    /**
     * Testet, ob ein anderes Grafik-Objekt beruehrt wird.
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
     * Diese Methode gibt die x-Koordinate des Mittelpunkts dieses Dreiecks (in Bildschrim-Metern) zurueck.
     * 
     * @return  Die aktuelle x-Koordinate des Mittelpunktes dieses Dreiecks (in Bildschrim-Metern)
     */
    public double nenneMx()
    {
        return super.nenneMittelpunktX();
    }
    
    
    /**
     * Diese Methode gibt die y-Koordinate des Mittelpunkts dieses Dreiecks (in Bildschrim-Metern) zurueck.
     * 
     * @return  Die aktuelle y-Koordinate des Mittelpunktes dieses Dreiecks (in Bildschrim-Metern)
     */
    public double nenneMy()
    {
        return super.nenneMittelpunktY();
    }
    
    
    /**
     * Diese Methode gibt die Farbe dieses Dreiecks zurueck.
     * 
     * @return  Die aktuelle Farbe dieses Dreiecks
     */
    public String nenneFarbe()
    {
        return super.nenneFarbe();
    }
    
    
    /**
     * Diese Methode gibt die Sichtbarkeit dieses Dreiecks zurueck.
     * 
     * @return  Die aktuelle Sichtbarkeit dieses Dreiecks
     */
    public boolean istSichtbar()
    {
        return super.istSichtbar();
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Dreiecks vom Mittelpunkt 
     * eines anderen Grafik-Objekts in x-Richtung (in Bildschrim-Metern) entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Bildschrim-Metern) dieses Dreiecks vom anderen Grafik-Objekt in x-Richtung (>0, wenn dieses Rechteck rechts des anderen Grafik-Objekts liegt)
     */
    public double berechneAbstandX( EduActor ea )
    {
        return  super.nenneMittelpunktX() - ea.nenneMittelpunktX() ;
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Dreiecks vom Mittelpunkt 
     * eines anderen Grafik-Objekts in y-Richtung (in Bildschrim-Metern) entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Bildschrim-Metern) dieses Dreiecks vom anderen Grafik-Objekt in y-Richtung (>0, wenn dieses Dreieck unterhalb des anderen Grafik-Objekts liegt)
     */
    public double berechneAbstandY( EduActor ea )
    {
        return  super.nenneMittelpunktY() - ea.nenneMittelpunktY();
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
        super.setzeSichtbar( false );
        super.drehen( winkelInGrad );
        super.setzeMittelpunkt( x , y );
        super.setzeSichtbar( true );
    }
    
    
    /**
     * Setzt den Drehwinkel (in Grad) auf eine absoluten neuen Wert. 
     *
     * @param   neuerDrehwinkelInGrad     der neue Drehwinkel
     *                                    +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                                    -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void setzeDrehwinkel( double neuerDrehwinkelInGrad )
    {
        this.drehenUm( neuerDrehwinkelInGrad - super.nenneDrehwinkel() );
    }
    
    
    /**
     * Nennt den Winkel (in Grad), um den die Grafik gedreht wurde. 
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