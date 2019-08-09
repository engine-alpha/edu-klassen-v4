import ea.edu.*;
import ea.*;

/**
 * Klasse TEXT zum Darstellen von Texten in EDU-Engine
 * 
 * @author      mike_gans@yahoo.de  and  michael andonie
 * 
 * @version     4.0 (2018-08-06)
 * 
 * @changelog   4.0 Umstieg auf EA 4
 * 
 *              
 */
public class TEXT
extends Text 
{
    private double M_x;
    
    private double M_y;
    
    
    /**
     * Konstruktor der Klasse Text.
     *
     * @param   x           x-Koordinate im Fenster
     * @param   y           y-Koordinate im Fenster
     * @param   textHoehe   Hoehe des Texts in Bildschirm-Metern
     * @param   text    anzuzeigender Text
     */
    public TEXT( double x , double y , double textHoehe , String text ) 
    {
        super( text , textHoehe );
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * TEXT Konstruktor
     *
     * @param   x           x-Koordinate im Fenster
     * @param   y           y-Koordinate im Fenster
     * @param   textHoehe   Hoehe des Texts in Bildschirm-Metern
     * @param   zahl        anzuzeigende Zahl
     */
    public TEXT( double x , double y , double textHoehe , int zahl ) 
    {
        super( ""+zahl , textHoehe );
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
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
    public void setzeInhalt( double zahl )
    {
        super.setzeInhalt( "" + zahl );
    }
    
    
    /**
     * Setzt die Schriftgroesse auf einen neuen Wert.
     *
     * @param   schriftHoehe    neue Schrifthoehe in Bildschirm-Metern
     */
    public void setzeSchriftHoehe( double schriftHoehe )
    {
        super.setzeHoehe( schriftHoehe );
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
        getActor().setFont( ttfDatei );
    }
    
    
    /**
     * Setzt den Schriftstil (fett, kursiv) des Textes. 
     *
     * @param   schriftStil     0=normal , 1=fett , 2=kursiv , 3=fett+kursiv 
     */
    public void setzeStil( int schriftStil )
    {
        getActor().setStyle( schriftStil );
    }
    
    
    /**
     * Verschiebt das Objekt um die angegebenen Pixel. 
     *
     * @param   deltaX  Bildschirm-Meter in x-Richtung
     * @param   deltaY  Bildschirm-Meter in y-Richtung
     */
    public void verschiebenUm( double deltaX , double deltaY ) 
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
        return super.beinhaltetPunkt( x  ,y );
    }
    
    
    /**
     * Setzt den Mittelpunkt des Objekts auf einen anzugebenden Punkt. 
     *
     * @param   x   x-Koordinate des Mittelpunkts
     * @param   y   y-Koordinate des Mittelpunkts
     */
    public void setzeMittelpunkt( double x , double y ) 
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
    public int nenneEbenenposition()
    {
        return super.nenneEbenenposition();
    }
    
    
    /**
     * Nennt die x-Koordinate des Mittelpunkts dieses Objekts. 
     *
     * @return  x-Koordinate des Mittelpunkts
     */
    public double nenneMx() 
    {
        return this.M_x;
    }
    
    
    /**
     * Nennt die y-Koordinate des Mittelpunkts dieses Objekts. 
     *
     * @return  y-Koordinate des Mittelpunkts
     */
    public double nenneMy() 
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
        return super.istSichtbar();
    }
    
    
    /**
     * Dreht die Grafik um einen Winkel
     *
     * @param   winkelAenderung     +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void drehenUm( double winkelAenderung )
    {
        double x = this.nenneMx();
        double y = this.nenneMy();
        super.setzeSichtbar( false );
        super.drehen( winkelAenderung );
        this.setzeMittelpunkt( x , y );
        super.setzeSichtbar( true );
    }
    
    
    /**
     * Setzt den Drehwinkel auf eine absoluten neuen Wert
     *
     * @param   neuerDrehwinkel     der neue Drehwinkel
     *                              +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void setzeDrehwinkel( double neuerDrehwinkel )
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
    public double nenneDrehwinkel()
    {
        return super.nenneDrehwinkel();
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
    public double berechneAbstandX( EduActor ea )
    {
        return this.M_x - ea.nenneMittelpunktX();
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
    public double berechneAbstandY( EduActor ea )
    {
        return this.M_y - ea.nenneMittelpunktY();
    }
    
    
}
