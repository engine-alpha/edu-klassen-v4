
/**
 * Klasse LayerTest zur Demonstration von Layern
 * 
 * @author      mike_gans@yahoo.de   and   michael andonie 
 * @version     2019-08-08
 */
public class LayerTest
extends SPIEL 
{
    private KREIS ball;
    private RECHTECK[] hindernisse;
    
    public LayerTest()
    {
        this.ball = new KREIS(1);
        this.ball.setzeMittelpunkt( 1 , 10 );
        this.ball.setzeElastizitaet( 0.3f );
        this.ball.macheAktiv();
        
        this.hindernisse = new RECHTECK[100];
        int v=1;
        for ( int i = 0 ; i<100 ; i++ )
        {
            this.hindernisse[i] = new RECHTECK( 3 , 0.5f );
            this.hindernisse[i].setzeMittelpunkt( v*2.5f , 8-2*i );
            this.hindernisse[i].drehenUm( v*30 );
            this.hindernisse[i].setzeElastizitaet( 0.3f );
            this.hindernisse[i].machePassiv();
            v = v*-1;
        }
        
        this.setzeKamerafokus(ball);
        erzeugeScoreAnzeige();
    }
    
    public void erzeugeScoreAnzeige() 
    {
        //Hauptebene ist 0
        //Größere Ebenennummern werden weiter vorne angezeigt
        this.erzeugeNeueEbene("Score", 1);
        this.setzeAktiveEbene("Score");
        RECHTECK anzeige = new RECHTECK(5,2);
        anzeige.setzeFarbe("hellblau");
        anzeige.setzeMittelpunkt(-9, 7);
        // keine x-Verschiebung (0), keine y-Verschiebung (0), beim Zoomen normal (1)
        // fuer x/y: 1 = normal, 0<...<1 = langsamer, >1 = schneller als Kamera
        this.setzeEbenenparallaxe("Score", 0, 0, 1);
    }
    
    
    // Zeitlupe / Zeitraffer
    public void setzeZeitVerzerrung(double zeitfaktor) 
    {
        this.setzeEbenenzeitverzerrung("Hauptebene",zeitfaktor);
    }
}
