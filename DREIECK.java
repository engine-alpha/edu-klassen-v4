/** @author     mike ganshorn
 * 
 *  @Version    4.0 (2018-08-06)
 *  
 *  @changelog  4.0 Umstieg auf EA 4
 *  
 *              2.4 WECHSELBILD erbt von Knoten und damit von Raum
 *                  verschiebenUm greift auf bewegen zurueck
 *                  Methoden in allen Klassen vereinheitlicht (bis auf indiv. Methoden)
 *  
 *              2.3 Methode beruehrt(WECHSELBILD) hinzugefuegt
 *  
 *              2.2 Jump'n'Run-Physik hinzu gefuegt.
 *  
 *              2.1 keine Abhaengigkeit mehr zwischen den alpha-Formen
 *                  Der Mittelpunkt des Dreiecks ist hier der Mittelpunkt des umhuellenden Rechtecks !!!
 * 
 *              
*/

import ea.edu.Dreieck;
import ea.edu.EduActor;

/**
 * Diese Klasse stellt ein einfaches Dreieck dar.
 */
public class DREIECK
implements EduActor
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
    private float A_x;
    
    /**
     * y-Koordinate des Eckpunkts A
     */
    private float A_y;
    
    /**
     * x-Koordinate des Eckpunkts B
     */
    private float B_x;
    
    /**
     * y-Koordinate des Eckpunkts B
     */
    private float B_y;
    
    /**
     * x-Koordinate des Eckpunkts C
     */
    private float C_x;
    
    /**
     * y-Koordinate des Eckpunkts C
     */
    private float C_y;
    
     /**
     * x-Koordinate des Mittelpunkts des umschliessenden Rechtecks
     */
    private float M_x;
    
    /**
     * y-Koordinate des Mittelpunkts des umschliessenden Rechtecks
     */
    private float M_y;
    
    
    /**
     * Konstruktor der Klasse <code>DREIECK</code>. Erstellt ein neues Standard-Dreieck.
     */
    public DREIECK() 
    {
        this( 80 , 190 , 150 , 70 , 220 , 190 );
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
    public DREIECK( float Ax , float Ay , float Bx , float By , float Cx , float Cy ) 
    {
        this.dreieck = new Dreieck( Ax , Ay , Bx , By , Cx , Cy );
        this.A_x = Ax;
        this.A_y = Ay;
        this.B_x = Bx;
        this.B_y = By;
        this.C_x = Cx;
        this.C_y = 50;
        this.M_x = ( Math.min(Math.min(Ax,Bx),Cx) + Math.max(Math.max(Ax,Bx),Cx) ) / 2 ;
        this.M_y = ( Math.min(Math.min(Ay,By),Cy) + Math.max(Math.max(Ay,By),Cy) ) / 2 ;
        this.sichtbar = true;
        this.dreieck.setzeSichtbar( true);
        this.farbe = "Gruen";
        this.dreieck.setzeFarbe( this.farbe );
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
        this.dreieck.setLayer( ebenenNummer );
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
    public void setzeEcken( float Ax , float Ay , float Bx , float By , float Cx , float Cy ) 
    {
        this.dreieck.entfernen();
        this.A_x = Ax;
        this.A_y = Ay;
        this.B_x = Bx;
        this.B_y = By;
        this.C_x = Cx;
        this.C_y = Cy;
        this.M_x = ( Math.min(Math.min(Ax,Bx),Cx) + Math.max(Math.max(Ax,Bx),Cx) ) / 2 ;
        this.M_y = ( Math.min(Math.min(Ay,By),Cy) + Math.max(Math.max(Ay,By),Cy) ) / 2 ;
        dreieck = new Dreieck( Ax , Ay , Bx , By , Cx , Cy );
        dreieck.setzeFarbe( this.farbe );
    }
    
    
    /**
     * Setzt die Farbe dieses Dreiecks neu.
     * 
     * @param   farbeNeu    Diese Farbe erhaelt das Dreieck (z.B. "Rot")
     */
    public void setzeFarbe( String farbeNeu ) 
    {
        this.farbe = farbeNeu;
        this.dreieck.setzeFarbe( this.farbe );
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
    public void setzeMittelpunkt( float x , float y ) 
    {
        float deltaX = x - this.M_x;
        float deltaY = y - this.M_y;
        this.A_x = this.A_x + deltaX;
        this.A_y = this.A_y + deltaY;
        this.B_x = this.B_x + deltaX;
        this.B_y = this.B_y + deltaY;
        this.C_x = this.C_x + deltaX;
        this.C_y = this.C_y + deltaY;
        this.M_x = x;
        this.M_y = y;
        this.dreieck.setzeMittelpunkt( x , y );
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
        this.dreieck.setzeSichtbar( sichtbarNeu );
    }
    
    
    /**
     * Verschiebt dieses Dreieck um eine Verschiebung - angegeben durch ein "Delta X" und "Delta Y".
     * 
     * @param   deltaX  Der X Anteil dieser Verschiebung. Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY  Der Y Anteil dieser Verschiebung. Positive Werte verschieben nach unten, negative nach oben.
     */
    public void verschiebenUm( float deltaX , float deltaY ) 
    {
        this.A_x = this.A_x + deltaX;
        this.A_y = this.A_y + deltaY;
        this.B_x = this.B_x + deltaX;
        this.B_y = this.B_y + deltaY;
        this.C_x = this.C_x + deltaX;
        this.C_y = this.C_y + deltaY;
        this.M_x = this.M_x + deltaX;
        this.M_y = this.M_y + deltaY;
        this.dreieck.verschieben( deltaX , deltaY );
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
        return this.dreieck.schneidet( ea.getActor() );
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
        return this.dreieck.beinhaltetPunkt( x , y );
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
        return this.dreieck.getLayer();
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
     * Diese Methode gibt die Farbe dieses Dreiecks zurueck.
     * 
     * @return  Die aktuelle Farbe dieses Dreiecks
     */
    public String nenneFarbe()
    {
        return this.farbe;
    }
    
    
    /**
     * Diese Methode gibt die Sichtbarkeit dieses Dreiecks zurueck.
     * 
     * @return  Die aktuelle Sichtbarkeit dieses Dreiecks
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
        return  this.M_x - ea.mittelPunkt().x ;
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Dreiecks vom Mittelpunkt 
     * eines anderen Grfik-Objekts in y-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Dreiecks vom anderen Grafik-Objekt in y-Richtung (>0, wenn dieses Dreieck unterhalb des anderen Grafik-Objekts liegt)
     */
    public float berechneAbstandY( EduActor ea )
    {
        return  this.M_y - ea.mittelPunkt().y;
    }
    
    
    /**
     * Dreht die Grafik um einen Winkel
     *
     * @param   winkelAenderung     +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void drehenUm( float winkelAenderung )
    {
        float x = this.dreieck.nenneMx();
        float y = this.dreieck.nenneMy();
        this.dreieck.setzeSichtbar( false );
        this.dreieck.drehen( (float)Math.toRadians(winkelAenderung)  );
        this.dreieck.setzeMittelpunkt( x , y );
        this.dreieck.setzeSichtbar( true );
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
        return (float)Math.toDegrees( this.dreieck.nenneWinkel() );
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
        // this.dreieck.getActor().physics.applyImpulse( new ea.Vector( 0 , 1000 * staerke) );
    // }
    
    
    // /**
     // * Gibt an, ob dieses Grafik-Objekt auf einem anderen steht. 
     // * <b>!!! Wissenswert !!!</b> Funktioniert nur, wenn dieses Grafik-Objekt aktiv und das andere passiv ist.
     // *
     // * @return  'true' wenn dieses aktive Objekt auf einem anderen passiven Objekt steht, sonst 'false'
     // */
    // public boolean steht()
    // {
        // return this.dreieck.steht();
    // }
    
    
    // /**
     // * Gibt an, ob dieses Grafik-Objekt auf einem bestimmten anderen steht.
     // *
     // * @param   go  Das andere Grafik-Objekt
     // * @return  'true' = steht drauf , 'false' = steht nicht drauf
     // */
    // public boolean stehtAuf( EduActor ea )
    // {
        // return this.dreieck.stehtAuf( ea.getActor() );
    // }
    
    
    // /**
     // * Macht dieses Grafik-Objekt im Sinne der Jump'n'Run-Physik aktiv. 
     // * Es unterliegt nun der Schwerkraft und kann auf passiven Objekten 
     // * stehen und von ihnen abspringen.
     // *
     // */
    // public void macheAktiv()
    // {
        // this.dreieck.macheAktiv();
    // }
    
    
    // /**
     // * Macht dieses Grafik-Objekt im Sinne der Jump'n'Run-Physik passiv. 
     // * Es kann nun nicht mehr von aktiven Objekten durchdrungen werden.  
     // * Aktive Objekte koennen nun davon abspringen.
     // *
     // */
    // public void machePassiv()
    // {
        // this.dreieck.machePassiv();
    // }
    
    
    // /**
     // * Macht dieses Grafik-Objekt im Sinne der Jump'n'Run-Physik neutral. 
     // * Dieses Objekt nimmt nun nicht mehr an der Jump'n'Run-Physik Teil!
     // *
     // */
    // public void macheNeutral()
    // {
        // this.dreieck.macheNeutral();
    // }
    
    
    
    // // === Animationen ===
    
    // public void animationGerade( float ziel_x , float ziel_y , int milliSec , boolean pingpong )
    // {
        // this.dreieck.geradenAnimation( ziel_x , ziel_y , milliSec , pingpong );
    // }
    
    
    // public void animationKreis( float zentrum_x , float zentrum_y , int milliSec , boolean uhrzeigersinn , boolean rotation )
    // {
        // this.dreieck.kreisAnimation( zentrum_x , zentrum_y , milliSec , uhrzeigersinn , rotation );
    // }
    
    
    
    
    @Override
    /**
     * Methode des Interfaces 'GrafikObjekt'
     */
    public ea.actor.Actor getActor() {
        return this.dreieck;
    }
}