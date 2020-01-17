
import ea.edu.*;
import ea.actor.StatefulAnimation;

/**
 * Klasse FIGUR repreasentiert eine Spielfigur, deren Grafik animiert ist. 
 * Die Animationen koennen durch ein animiertes GIF, durch Einzelbilder 
 * oder durch Spritesheets erzeugt werden. 
 * Jede Figur kann mehrere Zustaende annehmen: Je einen fuer eine Animationsfolge. 
 * So kann man z.B. eine andere Animationsfolge fuer 'laufen' verwenden als fuer 'springen' ...
 * 
 * @author      mike_gans@yahoo.de  and  michael andonie
 * 
 * @version     4.1 (2020-01-04)
 *                  
 * @changelog   4.1 neuen Konstruktor Figur(String) fuer einfache Bilder
 *                  setzeDichte, nenneDichte und nenneMasse hinzugefuegt
 *                  setzeAnimationPausiert(boolean) hinzugefuegt
 *                  diverse kleine Bugs behoben
 *              4.0 Umstieg auf EA 4
 *                  Nicht vergleichbar mit alter Klasse FIGUR (kein Figuren-Editor mehr).
 *                  Vereint die alten Klassen FIGUR, BILD und WECHSELBILD.
 *                  Kann ein Bild, mehrere Bilder, Sprite-Sheets und animierte GIFs einlesen.
 */
public class FIGUR
extends Figur 
{
    private double M_x;
    
    private double M_y;
    
    
    // =====   K o n s t r u k t o r e n   ==============================================================

    /**
     * Konstruktor der Klasse FIGUR <b>fuer ein animiertes GIF</b>.  
     * Es wird ein erster Zustand angelegt mit der Animation des GIF.
     * 
     * @param   zustandName     Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   gifPfad         Datei-Pfad zu einem animierten GIF. 
     *                          (Liegt dieses direkt im Projekt-Ordner, so reicht der Datei-Name)
     */
    public FIGUR( String zustandName , String gifPfad )
    {
        super( zustandName , gifPfad );
    }
    
    
    /**
     * Konstruktor der Klasse FIGUR fuer <b>SpriteSheets</b> oder ein <b>einfaches Bild</b>.
     * Einfache Bilder koennen mit x=1 und y=1 geladen werden. 
     * Sprites muessen alle gleich gross sein !!!
     * Es wird ein erster Zustand angelegt mit der Animation des Spritesheets. 
     * 
     *
     * @param   zustandName         Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   spritesheetPfad     Datei-Pfad zu einem Spritesheet oder Bild. 
     *                              (Liegt dieses direkt im Projekt-Ordner, so reicht der Datei-Name)
     * @param   x                   Anzahl der Sprites in x-Richtung (bei einfachem Bild 1)
     * @param   y                   Anzahl der Sprites in y-Richtung (bei einfachem Bild 1)
     */
        public FIGUR( String zustandName , String spritesheetPfad , int x , int y )
    {
        super( zustandName , spritesheetPfad , x , y );
    }
    
    
    /**
     * Konstruktor der Klasse FIGUR fuer ein einfaches Bild.
     *
     * @param   bildName    Datei-Pfad zu einem Bild
     */
    public FIGUR( String bildName )
    {
        this( "standard" , bildName , 1 , 1 );
    }
    
    
    /**
     * Konstruktor der Klasse FIGUR fuer ausgewaehlte Bilder eines Ordners (im BlueJ-Projekt). 
     * Laedt dazu alle Bilder in einem Verzeichnis ein, die zu einem bestimmten Praefix passen.
     * Es wird ein erster Zustand angelegt mit der Animation der Bilder-Folge.
     * 
     * @param zustandName       Name für den ersten Zustand.
     * @param verzeichnisPfad   Pfad zum Verzeichnis, in dem alle einzuladenden Bilder liegen.
     * @param praefix           Das Praefix, das alle einzuladenden Bilder haben muessen: 
     *                          z.B. "affe" fuer affe1.jpg, affe2.jpg, ...
     */
    public FIGUR( String zustandName , String verzeichnisPfad , String praefix )
    {
        super( zustandName , verzeichnisPfad , praefix);
    }
    
    
    
    
    
    // =====   g e w o e h n l i c h e   M e t h o d e n   =============================================
    
    /**
     * Methode skalieren vergroessert / verkleinert die Figur. 
     *
     * @param   skalierungsFaktor   1 : normal ; 0<skalierungsfaktor<1 : kleiner ; >1 : groesser
     */
    public void skaliere( double skalierungsFaktor )
    {
        super.skaliere( skalierungsFaktor );
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
        this.getActor().setLayerPosition( ebenenNummer );
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
     * Nennt den aktuellen Zustand.
     * 
     * @return      Der Name des aktuellen Zustands.
     */
    public String nenneAktuellenZustand()
    {
        return super.nenneAktivenZustand();
    }
    
    
    /**
     * Setzt die Animationsgeschwindigkeit eines zustands neu. 
     *
     * @param   zustandName     Name des Zustands, dessen Animationsgeschwindigkeit man aendern moechte
     * @param   frameDauerInSek  Dauer in Sekunden fuer ein Bild
     */
    public void setzeAnimationsGeschwindigkeitVon( String zustandName , double frameDauerInSek )
    {
        super.setzeAnimationsgeschwindigkeit( zustandName , frameDauerInSek );
    }
    
    
    /**
     * Setzt den Mittelpunkt der Figur neu. 
     *
     * @param   x   Die x-Koordinate des neuen Mittelpunkts
     * @param   y   Die y-Koordinate des neuen Mittelpunkts
     */
    public void setzeMittelpunkt( double x , double y )
    {
        this.M_x = x;
        this.M_y = y;
        super.setzeMittelpunkt( x , y );
    }
    
    
    /**
     * Nennt die x-Koordinate des Mittelpunkts. 
     *
     * @return   x-Koordinate des Mittelpunkts
     */
    public double nenneMx()
    {
        return super.nenneMittelpunktX();
    }
    
    
    /**
     * Nennt die y-Koordinate des Mittelpunkts. 
     *
     * @return   y-Koordinate des Mittelpunkts
     */
    public double nenneMy()
    {
        return super.nenneMittelpunktY();
    }
    
    
    /**
     * Setzt die Figur sichtbar oder unsichtbar. 
     *
     * @param   sichtbarNeu     'true' fuer sichtbar, 'false' fuer unsichtbar
     */
    public void setzeSichtbar( boolean sichtbarNeu )
    {
        super.setzeSichtbar( sichtbarNeu );
    }
    
    
    /**
     * Gibt an, ob die Figur gerade sichtbar ist.
     *
     * @return  'true' wenn sichtbar, 'false' wenn unsichtbar
     */
    public boolean istSichtbar()
    {
        return super.istSichtbar();
    }
    
    
    /**
     * Verschiebt diese Figur um eine Verschiebung - angegeben durch ein "Delta X" und "Delta Y".
     * 
     * @param   deltaX  Der X Anteil dieser Verschiebung. Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY  Der Y Anteil dieser Verschiebung. Positive Werte verschieben nach unten, negative nach oben.
     */
    public void verschiebenUm( double deltaX , double deltaY ) 
    {
        this.M_x += deltaX;
        this.M_y += deltaY;
        super.verschieben( deltaX , deltaY );
    }
    
    
    /**
     * Testet, ob ein anderes Grafik-Objekt beruehrt wird.
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
        return super.beinhaltetPunkt( x , y );
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Rechtecks vom Mittelpunkt 
     * eines anderen Grfik-Objekts in x-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Rechtecks vom anderen Grafik-Objekt in x-Richtung (>0, wenn dieses Rechteck rechts des anderen Grafik-Objekts liegt)
     */
    public double berechneAbstandX( EduActor ea )
    {
        return super.nenneMittelpunktX() - ea.nenneMittelpunktX();
    }
    
    
    /**
     * Diese Methode prueft, wie weit der Mittelpunkt dieses Rechtecks vom Mittelpunkt 
     * eines anderen Grfik-Objekts in y-Richtung entfernt ist.
     * 
     * @param   grafikObjekt    Das andere Grafik-Objekt
     * 
     * @return  Abstand (in Pixeln) dieses Rechtecks vom anderen Grafik-Objekt in y-Richtung (>0, wenn dieses Rechteck unterhalb des anderen Grafik-Objekts liegt)
     */
    public double berechneAbstandY( EduActor ea )
    {
        return super.nenneMittelpunktY() - ea.nenneMittelpunktY();
    }
    
    
    /**
     * Dreht die Grafik um einen Winkel
     *
     * @param   winkelAenderung     +: mathematisch positiver Drehsinn (gegen den Uhrzeigersinn)
     *                              -: mathematisch negativer Drehsinn (im Uhrzeigersinn)
     */
    public void drehenUm( double winkelAenderung )
    {
        double x = super.nenneMittelpunktX();
        double y = super.nenneMittelpunktY();
        super.setzeSichtbar( false );
        super.drehen( winkelAenderung );
        super.setzeMittelpunkt( x , y );
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
        this.drehenUm( neuerDrehwinkel - super.nenneDrehwinkel() );
    }
    
    
    /**
     * Nennt den Winkel, um den die Grafik gedreht wurde
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
     * Spiegelt die Grafik an der senkrechten Achse
     *
     * @param   gespiegelt  'true' spiegeln, 'false' nicht spiegeln
     */
    public void spiegelnHorizontal( boolean gespiegelt ) {
        ((StatefulAnimation)this.getActor()).setFlipHorizontal( gespiegelt );
    }
    
    
    /**
     * Spiegelt die Grafik an der waagerechten Achse
     *
     * @param   gespiegelt  'true' spiegeln, 'false' nicht spiegeln
     */
    public void spiegelnVertikal( boolean gespiegelt ) {
        ((StatefulAnimation)super.getActor()).setFlipVertical( gespiegelt );
    }
    
    
    
    
    
    // =====   Z u s t a n d s - A u t o m a t   =============================================================
    
    /**
     * Fuegt einen neuen Zustand <b>mit GIF-Animation</b> ein. 
     * 
     * @param   zustandsName    Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   bildpfad        Pfad zum animierten GIF fuer diesen Zustand.
     */
    public void fuegeZustandVonGifHinzu( String zustandsName , String bildpfad )
    {
        super.fuegeZustandVonGifHinzu( zustandsName , bildpfad );
    }
    
    
    /**
     * Fuegt einen neuen Zustand <b>mit Spritesheet-Animation</b> ein. 
     * Das Spritesheet muss <b>aus Kacheln gleicher Größe</b> bestehen.
     * "leere" Kacheln werden als leere Animationsframes mit einbezogen.
     * 
     * @param   zustandsName  Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   bildpfad      Pfad zum Spritesheet. (Liegt diese im Projekt-Ordner, so reicht der Datei-Name)
     * @param   anzahlX       Anzahl der Spritesheet-Kacheln in die X-Richtung.
     * @param   anzahlY       Anzahl der Spritesheet-Kacheln in die Y-Richtung.
     */
    public void fuegeZustandVonSpritesheetHinzu( String zustandsName , String bildpfad , int anzahlX , int anzahlY )
    {
        super.fuegeZustandVonSpritesheetHinzu( zustandsName , bildpfad , anzahlX , anzahlY );
    }
    
    
    /**
     * Fuegt einen neuen Zustand <b>durch einzelne Bilder</b> ein. 
     * Die Bilder werden automatisch der Reihe nach durchgewechselt.
     * 
     * @param   zustandsName    Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   bildpfade       Die Pfade der einzelnen Bilder in korrekter Reihenfolge.
     */
    public void fuegeZustandVonEinzelbildernHinzu( String zustandsName , String... bildpfade )
    {
        super.fuegeZustandVonEinzelbildernHinzu( zustandsName , bildpfade );
    }
    
    
    /**
     * Fuegt einen neuen Zustand <b>durch ausgewaehlte Bilder eines Ordners</b> ein. 
     * Die Bilder werden automatisch der Reihe nach durchgewechselt.
     * 
     * @param   zustandsName    Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   verzeichnis     Der Name des Ordners (innerhalb des BlueJ-Projekts) in dem die Bilder liegen
     * @param   praefix         gemeinsamer Namensbestandteil: z.B. "affe" fuer affe1.jpg, affe2.jpg, ...
     */
    public void fuegeZustandVonPraefixHinzu( String zustandsName , String verzeichnis , String praefix )
    {
        super.fuegeZustandVonPraefixHinzu( zustandsName , verzeichnis , praefix );
    }
    
    
    /**
     * Versetzt die Figur in einen anderen (bereits existierenden) Zustand. 
     * In jedem Fall wird dabei der Animationsloop zurueckgesetzt.
     * 
     * @param   zustandsName    Der Name des zu setzenden Zustands. 
     *                          Unter diesem Namen muss ein Zustand in dieser Figur existieren.
     */
    public void setzeZustand( String zustandsName )
    {
        super.setzeZustand( zustandsName );
    }
    
    
    /**
     * Setzt einen automatischen Uebergang von einem Zustand zu einem anderen. 
     * Wird der erste Zustand aufgerufen, so wird erst <b>EIN MAL</b> die Animation dieses ersten Zustands 
     * aufgerufen und automatisch <b>anschliessend wiederholt</b> die Animation des zweiten Zustands.
     * 
     * @param   zustandVon      Der erste Zustand.
     * @param   zustandNach     Der zweite Zustand, zu dem die Figur automatisch uebergehen soll, 
     *                          nachdem die animation des ersten Zustands einmal bis zum Ende durchgelaufen ist.
     */
    public void setzeAutomatischenUebergang( String zustandVon , String zustandNach )
    {
        super.setzeAutomatischenUebergang( zustandVon , zustandNach );
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
    
    
    /**
     * Pausiert die Animation der Figur oder setzt sie wieder fort.
     *
     * @param   pausiert    true = Animation pausieren ; false = fortsetzen
     */
    public void setzeAnimationPausiert( boolean pausiert )
    {
        super.setzeAnimationPausiert( pausiert );
    }
}
