/**
 * Klasse Gelengverbindung zur Demonstration einer GElenkverbindung 
 * von zwei Grafik-Objekten.
 * Dabei sind die Objekte durch ein drehbares Gelenk miteinander verbunden. 
 * Setzt man zwei solcher Gelengverbindungen an unterschiedlichen Punkten, 
 * so sind die beiden Objekte starr miteinander verbunden. 
 * Bei sehr geringem Abstand der Gelenke kann es zu geringem Federn kommen.
 * 
 * @author      mike_gans@yahoo.de
 * 
 * @version     2019-08-08
 */
public class Gelenkverbindung
{
    public KREIS hindernis;
    
    public RECHTECK stab;
    public KREIS ball;
    
    public Gelenkverbindung()
    {
        SPIEL.zeigeKoordinatensystem( true );
        
        this.hindernis = new KREIS( 1 );
        this.hindernis.setzeMittelpunkt( 0 , 0 );
        this.hindernis.setzeFarbe( "rot" );
        this.hindernis.machePassiv();
        
        this.ball = new KREIS( 3 );
        this.ball.verschiebenUm( -3 , 6 );
        this.ball.setzeFarbe( "blau" );
        this.ball.setzeRotationBlockiert( false );
        this.ball.macheAktiv();
        
        this.stab = new RECHTECK( 18 , 1 );
        this.stab.verschiebenUm( 0.5f , 1 );
        this.stab.setzeFarbe( "gelb" );
        this.stab.setzeRotationBlockiert( false );
        this.stab.macheAktiv();
        
        // Gelenk-Verbindung erstellen
        // --> gemeinsamer Punkt der Objekte: in relativen Koordinaten 
        // bezogen auf die linke untere Ecke des Huell-Rechtecks des aufrufenden Objekts
        this.stab.erzeugeGelenkverbindung( this.ball , 0.2f , 0.5f );
        // zweite Verbindung an anderem Punkt = fest verbunden
        //this.stab.erzeugeGelenkVerbindung( this.ball , -0.6f , 0.5f );
        
    }
}