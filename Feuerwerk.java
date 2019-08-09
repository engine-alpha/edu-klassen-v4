/**
 * Klasse Feuerwerk - Demo
 * 
 * @author      mike_gans@yahoo.de  and  bernd wagner
 * 
 * @version     2019-08-07
 */
public class Feuerwerk 
extends SPIEL 
{
    
    private PARTIKEL partikel;
    
    public Feuerwerk() 
    {
        super();
        super.tickerNeuStarten( 1.2f );
    }
    
    @Override
    public void tick() 
    {
        int x = super.zufallszahlVonBis(-12, 12);
            int y = super.zufallszahlVonBis(-8, 8);
            int z = super.zufallszahlVonBis(1200,2000);
            //int dauer = super.zufallszahlVonBis(1000, 10000);
            //int radius = super.zufallszahlVonBis(500, 5000);
            //partikel = new PARTIKEL(radius, dauer);
            partikel.particleFun( x , y , 1.0f/1000*z , this );
            
    }
    
}
