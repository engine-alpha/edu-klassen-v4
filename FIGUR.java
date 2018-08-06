
import ea.edu.*;

/**
 * Klasse FIGUR repreasentiert eine Spielfigur, deren Grafik animiert ist. 
 * Die Animationen koennen durch ein animiertes GIF, durch Einzelbilder 
 * oder durch Spritesheets erzeugt werden. 
 * Jede Figur kann mehrere Zustaende annehmen: Je einen fuer eine Animationsfolge. 
 * So kann man z.B. eine andere Animationsfolge fuer 'laufen' verwenden als fuer 'springen' ...
 * 
 * @author      mike_gans@yahoo.de
 * 
 * @version     4.0 (2018-08-06)
 *                  
 * @changelog   4.0 Umstieg auf EA 4
 *                  Nicht vergleichbar mit alter Klasse FIGUR (kein Figuren-Editor mehr).
 *                  Vereint die alten Klassen FIGUR, BILD und WECHSELBILD.
 *                  Kann ein Bild, mehrere Bilder, Sprite-Sheets und animierte GIFs einlesen.
 */
public class FIGUR
extends Figur 
{
    private float M_x;
    
    private float M_y;
    
    

    /**
     * Konstruktor der Klasse FIGUR. 
     * Es wird ein erster Zustand angelegt mit der Animation des GIF.
     * 
     * @param   zustandName     Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   gifPfad         Datei-Pfad zu einem animierten GIF. (Liegt dieses direkt im Projekt-Ordner, so reicht der Datei-Name)
     */
    public FIGUR( String zustandName , String gifPfad )
    {
        super( zustandName , gifPfad );
    }
    
    
    
    /**
     * Konstruktor der Klasse FIGUR. 
     * Die Sprites muessen alle gleich gross sein !!!
     * Es wird ein erster Zustand angelegt mit der Animation des Spritesheets. 
     * Einfache Bilder koennen mit x=1 und y=1 geladen werden
     *
     * @param   zustandName         Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   spritesheetPfad     Datei-Pfad zu einem Spritesheet. (Liegt dieses direkt im Projekt-Ordner, so reicht der Datei-Name)
     * @param   x                   Anzahl der Sprites in x-Richtung
     * @param   y                   Anzahl der Sprites in y-Richtung
     */
        public FIGUR( String zustandName , String spritesheetPfad , int x , int y )
    {
        super( zustandName , spritesheetPfad , x , y );
    }
    
    
    
    /**
     * Konstruktor der Klasse FIGUR. 
     * Laedt dazu alle Bilder in einem Verzeichnis ein, die zu einem bestimmten Praefix passen.
     * Es wird ein erster Zustand angelegt mit der Animation der Bilder-Folge.
     * 
     * @param zustandName       Name für den ersten Zustand.
     * @param verzeichnisPfad   Pfad zum Verzeichnis, in dem alle einzuladenden Bilder liegen.
     * @param praefix           Das Praefix, das alle einzuladenden Bilder haben müssen.
     */
    public FIGUR( String zustandName , String verzeichnisPfad , String praefix )
    {
        super( zustandName , verzeichnisPfad , praefix);
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
     * Fuegt einen neuen Zustand mit GIF-Animation ein. 
     * 
     * @param   zustandsName    Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   bildpfad        Pfad zum animierten GIF fuer diesen Zustand.
     */
    public void zustandHinzufuegenVonGIF( String zustandsName , String bildpfad )
    {
        super.zustandHinzufuegenVonGIF( zustandsName , bildpfad );
    }
    
    
    /**
     * Fuegt einen neuen Zustand mit Spritesheet-Animation ein. 
     * Das Spritesheet muss <b>aus Kacheln gleicher Größe</b> bestehen.
     * "leere" Kacheln werden als leere Animationsframes mit einbezogen.
     * 
     * @param   zustandsName  Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   bildpfad      Pfad zum Spritesheet. (Liegt diese im Projekt-Ordner, so reicht der Datei-Name)
     * @param   anzahlX       Anzahl der Spritesheet-Kacheln in die X-Richtung.
     * @param   anzahlY       Anzahl der Spritesheet-Kacheln in die Y-Richtung.
     */
    public void zustandHinzufuegenVonSpritesheet( String zustandsName , String bildpfad , int anzahlX , int anzahlY )
    {
        super.zustandHinzufuegenVonSpritesheet( zustandsName , bildpfad , anzahlX , anzahlY );
    }
    
    
    /**
     * Fuegt einen neuen Zustand durch einzelne Bilder ein. 
     * Die Bilder werden automatisch der Reihe nach durchgewechselt.
     * 
     * @param   zustandsName    Frei waehlbarer Name des Zustands. (Wird zum Umschalten verwendet)
     * @param   bildpfade       Die Pfade der einzelnen Bilder in korrekter Reihenfolge.
     */
    public void zustandHinzufuegenVonBildern( String zustandsName , String... bildpfade )
    {
        zustandHinzufuegenVonBildern( zustandsName , bildpfade );
    }
    
    
    /**
     * Setzt den Zustand der Figur neu. 
     * In jedem Fall wird dabei der Animationsloop zurueckgesetzt.
     * 
     * @param   zustandsName    Der Name des zu setzenden Zustands. 
     *                          Unter diesem Namen muss ein Zustand in dieser Figur existieren.
     */
    public void setzeZustand( String zustandsName )
    {
        super.zustandSetzen( zustandsName )   ;
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
    public void automatischenUebergangSetzen( String zustandVon , String zustandNach )
    {
        super.automatischenUebergangSetzen( zustandVon , zustandNach );
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
     * Nennt den aktuellen Zustand.
     * 
     * @return      Der Name des aktuellen Zustands.
     */
    public String nenneAktuellenZustand()
    {
        return super.nenneAktuellenZustand();
    }
    
    
    /**
     * Setzt die Animationsgeschwindigkeit eines zustands neu. 
     *
     * @param   zustandName     Name des Zustands, dessen Animationsgeschwindigkeit man aendern moechte
     * @param   frameDauerInMS  Dauer in Millisekunden fuer ein Bild
     */
    public void setzeAnimationsGeschwindigkeitVon( String zustandName , int frameDauerInMS )
    {
        super.setzeAnimationsGeschwindigkeitVon( zustandName , frameDauerInMS );
    }
    
    
    /**
     * Setzt den Mittelpunkt der Figur neu. 
     *
     * @param   x   Die x-Koordinate des neuen Mittelpunkts
     * @param   y   Die y-Koordinate des neuen Mittelpunkts
     */
    public void setzeMittelpunkt( float x , float y )
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
    public float nenneMx()
    {
        return this.M_x;
    }
    
    
    /**
     * Nennt die y-Koordinate des Mittelpunkts. 
     *
     * @return   y-Koordinate des Mittelpunkts
     */
    public float nenneMy()
    {
        return this.M_y;
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
    public boolean nenneSichtbar()
    {
        return super.nenneSichtbar();
    }
    
    
    /**
     * Verschiebt diese Figur um eine Verschiebung - angegeben durch ein "Delta X" und "Delta Y".
     * 
     * @param   deltaX  Der X Anteil dieser Verschiebung. Positive Werte verschieben nach rechts, negative nach links.
     * 
     * @param   deltaY  Der Y Anteil dieser Verschiebung. Positive Werte verschieben nach unten, negative nach oben.
     */
    public void verschiebenUm( float deltaX , float deltaY ) 
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
        return super.schneidet( ea.getActor() );
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
     * Nennt den Winkel, um den die Grafik gedreht wurde
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
     * Spiegelt die Grafik an der senkrechten Achse
     *
     * @param   gespiegelt  'true' spiegeln, 'false' nicht spiegeln
     */
    public void spiegelnHorizontal( boolean gespiegelt ) {
        this.setFlipHorizontal( gespiegelt );
    }
    
    
    /**
     * Spiegelt die Grafik an der waagerechten Achse
     *
     * @param   gespiegelt  'true' spiegeln, 'false' nicht spiegeln
     */
    public void spiegelnVertikal( boolean gespiegelt ) {
        super.setFlipVertical( gespiegelt );
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
