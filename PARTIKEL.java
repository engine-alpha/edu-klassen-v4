
/**
 * Klasse PARTIKEL.
 * 
 * @author      mike_gans@yahoo.de  and  michael andonie 
 * 
 * @version     2019-08-07
 * 
 */
public class PARTIKEL
extends KREIS
{
    public PARTIKEL( float radius , float lebenszeitInSekunden )
    {
        super( radius*2 );
        machePartikel( lebenszeitInSekunden );
    }
    
    public static void particleFun(float mx, float my, float sekunden, SPIEL spiel) {
        //Stress-Test:
        for(int i = 0; i < 250; i++) {
            PARTIKEL p = new PARTIKEL(SPIEL.zufallszahlVonBis(1, 15)/200f, sekunden);
            p.setzeMittelpunkt(mx, my);
            ((ea.actor.Circle)p.getActor()).setColor(new java.awt.Color(
                SPIEL.zufallszahlVonBis(0, 255), 
                SPIEL.zufallszahlVonBis(0, 255), 
                SPIEL.zufallszahlVonBis(0, 255)));
            p.wirkeImpuls(
                (ea.Random.nextFloat()-.5f)*20,
                (ea.Random.nextFloat()-.5f)*20
            );
        }
    }
    
    public void frameUpdateReagieren(int ms) {
        this.getActor().applyForce(new ea.Vector(0,-9.81f));
    }
}
