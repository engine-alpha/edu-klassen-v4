/**
 * Klasse Stabverbindung zur Demonstration einer Stabverbindung 
 * zweier Grafik-Objekte.
 * Dabei bleibt der Abstand der Objekte konstant und sie 
 * sind durch eine unsichtbare Stange miteinander verbunden.
 * 
 * @author      mike_gans@yahoo.de
 * 
 * @version     2019-08-08
 */
public class Stabverbindung
{
    public KREIS hindernis;
    
    public KREIS ball_1;
    public KREIS ball_2;
    
    public Stabverbindung()
    {
        SPIEL.zeigeKoordinatensystem( true );
        
        this.hindernis = new KREIS( 1 );
        this.hindernis.setzeMittelpunkt( 0.3f , 3 );
        this.hindernis.setzeFarbe( "rot" );
        this.hindernis.setzeElastizitaet( 0.8f );
        this.hindernis.machePassiv();
        
        this.ball_1 = new KREIS( 1.5f );
        this.ball_1.setzeMittelpunkt( -3 , 7 );
        this.ball_1.setzeFarbe( "blau" );
        this.ball_1.macheAktiv();
        
        this.ball_2 = new KREIS( 1 );
        this.ball_2.setzeMittelpunkt( 0.5f , 7 );
        this.ball_2.setzeFarbe( "gelb" );
        this.ball_2.setzeElastizitaet( 0.8f );
        this.ball_2.macheAktiv();
        
        // Stangen-Verbindung erstellen
        // Syntax: Objekt1.erzeugeStabverbindung( Objekt2 , x1 , y1 , x2 , y2 )
        // erster Punkt relativ zur linken unteren Ecke des Huell-Rechtecks von Objekt1
        // zweiter Punkt leativ zur linken unteren Ecke des Huell-Rechtecks von Objekt2
        this.ball_1.erzeugeStabverbindung( this.ball_2 , 1.5f , 1.5f , 1 , 1 );
        
    }
}