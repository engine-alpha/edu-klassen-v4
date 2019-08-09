/**
 * Klasse Seilverbindung zur Demonstration einer Seilverbindung 
 * zweier Grafik-Objekte.
 * Dabei bleibt der maximale Abstand der Objekte beschraenkt und sie 
 * ziehen wie an einem undurchsichtigen Seil aneinander.
 * 
 * @author      mike_gans@yahoo.de
 * 
 * @version     2019-08-08
 */
public class Seilverbindung
{
    public KREIS hindernis_1;
    public KREIS hindernis_2;
    
    public KREIS ball_1;
    public KREIS ball_2;
    
    public Seilverbindung()
    {
        SPIEL.zeigeKoordinatensystem( true );
        
        this.hindernis_1 = new KREIS( 1.5f );
        this.hindernis_1.setzeMittelpunkt( 3.3f , 3.5f );
        this.hindernis_1.setzeFarbe( "rot" );
        this.hindernis_1.setzeElastizitaet( 0.5f );
        this.hindernis_1.machePassiv();
        
        this.hindernis_2 = new KREIS( 2 );
        this.hindernis_2.setzeMittelpunkt( -1.5f , -2.5f );
        this.hindernis_2.setzeFarbe( "rot" );
        this.hindernis_2.setzeElastizitaet( 0.8f );
        this.hindernis_2.machePassiv();
        
        this.ball_1 = new KREIS( 1.5f );
        this.ball_1.setzeMittelpunkt( -1.5f , 7 );
        this.ball_1.setzeElastizitaet( 0.8f );
        this.ball_1.setzeFarbe( "blau" );
        this.ball_1.macheAktiv();
        
        this.ball_2 = new KREIS( 1 );
        this.ball_2.setzeMittelpunkt( 2 , 7 );
        this.ball_2.setzeElastizitaet( 0.8f );
        this.ball_2.setzeFarbe( "gelb" );
        this.ball_2.macheAktiv();
        
        // Seil-Verbindung erstellen
        // Syntax: Objekt1.erzeugeSeilverbindung( Objekt2 , Seillaenge , x1 , y1 , x2 , y2 )
        // erster Punkt relativ zur linken unteren Ecke des Huell-Rechtecks von Objekt1
        // zweiter Punkt leativ zur linken unteren Ecke des Huell-Rechtecks von Objekt2
        this.ball_1.erzeugeSeilverbindung( this.ball_2 , 5 , 1.5f , 1.5f , 1 , 1 );
        
        
        
    }
}