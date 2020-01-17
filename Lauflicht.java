
/**
 * Klasse Lauflicht als Demo fuer Nebenlaeufige Prozesse
 * mit der Methode parallel(...) der Klasse SPIEL.
 * 
 * @author      mike_gans@yahoo.de   and   nick keller
 * 
 * @version     2019-08-08
 */
public class Lauflicht
extends SPIEL
{
    private KREIS[] lichter;
    
    public Lauflicht()
    {
        this.lichter = new KREIS[8];
        for ( int i=0 ; i<8 ; i++ )
        {
            this.lichter[i] = new KREIS(1);
            this.lichter[i].setzeMittelpunkt( -8+2.5f*i, 0);
            this.lichter[i].setzeFarbe("dunkelgrau");
        }
        
    }
    
    public void lauflichtNachRechts()
    {
        for ( int i=0 ; i<8 ; i++ )
        {
            lichter[i].setzeFarbe("gelb");
            super.warte( 0.5 );
            lichter[i].setzeFarbe("dunkelgrau");
        }
    }
    
    public void flash( int xmal )
    {
        for ( int i=0 ; i<xmal ; i++ )
        {
            lichter[i].setzeFarbe("gelb");
            super.warte( 0.5 );
            lichter[i].setzeFarbe("dunkelgrau");
        }
    }
    
    @Override
    public void tasteReagieren( int taste )
    {
        if ( taste == TASTE.A )
        {
            // Methodenreferenz reicht ohne Parameter
            SPIEL.parallel( this::lauflichtNachRechts );
        }
        else if ( taste == TASTE.S )
        {
            // Lambda (mit Paramerer noetig)
            SPIEL.parallel( () -> this.flash(5) );
        }
        else if ( taste == TASTE.G )
        {
            // Lambda (mit Paramerer noetig)
            SPIEL.parallel( () -> {this.flash(5);flash(3);} );
        }
        
        // else if ( taste == TASTE.D )
        // {
            // // anderer Weg mit Consumer fuer Parameter Me
            // // zuegehoerige Methode ist in SPIEL standardmaessig auskommentiert
            // SPIEL.parallel( this::flash , 5 );
            // // fuer mehrere Parameter die Methode in SPIEL ueberladen mit entspr. mehr argumenten
        // }
        
    }
}
