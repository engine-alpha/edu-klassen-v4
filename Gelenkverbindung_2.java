/**
 * Klasse Gelengverbindung_2 zur Demonstration Kette aus Grafik-Objekten.
 * Dabei sind die Objekte durch drehbare Gelenke miteinander verbunden.
 * 
 * @author      mike_gans@yahoo.de
 * 
 * @version     2019-08-08
 */
public class Gelenkverbindung_2
{
    private KREIS hindernis;
    private int anzahl = 12;
    private RECHTECK[] kette;
    
    public Gelenkverbindung_2()
    {
        SPIEL.zeigeKoordinatensystem( true );
        
        this.hindernis = new KREIS( 1 );
        this.hindernis.setzeMittelpunkt( 0 , 0 );
        this.hindernis.setzeFarbe( "rot" );
        this.hindernis.machePassiv();
        
        this.kette = new RECHTECK[this.anzahl];
        for ( int i=0 ; i<this.anzahl ; i++ )
        {
            this.kette[i] = new RECHTECK( 1 , 0.4f );
            this.kette[i].setzeMittelpunkt( -4+0.8f*i , 5 );
            this.kette[i].setzeFarbe( "gruen" );
            this.kette[i].setzeRotationBlockiert( false );
        }
        
        for ( int i=0 ; i<this.anzahl-1 ; i++ )
        {
            this.kette[i].erzeugeGelenkverbindung( this.kette[i+1] , 0.9f , 0.2f );
        }
        
        for ( int i=0 ; i<this.anzahl ; i++ )
        {
            this.kette[i].macheAktiv();
        }
        
        
    }
}