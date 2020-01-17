/**
 * Klasse Collider - Demo  &  Scene - Demo
 * 
 * @author      mike_gans@yahoo.de  and  michael andonie
 * 
 * @version     2019-08-07
 */
public class Collider
extends SPIEL
implements ea.edu.event.KollisionsReagierbar {
    public RECHTECK boden;
    public FIGUR figur;
    public KREIS ball;
    
    private boolean istPause = false;
    
    public Collider()
    {
        SPIEL.zeigeKoordinatensystem( true );
        
        machePausenMenue();
        
        this.neueSzene();
        this.benenneSzene("Pickle Rick");
        this.registriereTastenReagierbar(this);
        
        this.boden = new RECHTECK( 25 , 1 );
        this.boden.setzeMittelpunkt( 0 , -8 );
        this.boden.machePassiv();
        
        this.figur = new FIGUR( "standard" , "gurke_2.gif" );
        this.figur.skaliere( 0.5f );
        this.figur.setzeRotationBlockiert( false );
        this.figur.setzeMittelpunkt( 0 , 5 );
        this.figur.setzeAnimationsGeschwindigkeitVon( "standard" , 0.2f );
        this.figur.macheAktiv();
        
        // Collider fuer die Figur anpassen
        
        //Formate
        //KREIS :    C mx , my , r
        //RECHTECK:  R basis_x, basis_y, hoehe , breite
        //Polygon:   P a_x , a_y , b_x , b_y , c_x , c_y , d_x , d_y , ...
        //Mehrere Shapes verbinden mit "&"
        // nach dem & KEIN Leerzeichen !!!
        // vor dem & KEIN Komma !!!
        this.figur.setzeKollisionsformen( "C 2.5f,1.1f,1.1f  &C 5f,1.1f,1.1f  &C 3.8f,4f,3.2f  &C 3.7f,6f,2f" );
        
        this.ball = new KREIS(1);
        this.ball.setzeFarbe("gelb");
        this.ball.setzeMittelpunkt( 12 , -1 );
        this.ball.registriereKollisionsReagierbar(figur, this);
    }
    
    public void machePausenMenue() {
        //
        this.neueSzene();
        this.benenneSzene("Pause");
        this.registriereTastenReagierbar(this);
        
        RECHTECK rechteck = new RECHTECK(5, 2);
        rechteck.setzeMittelpunkt(0, 0);
        rechteck.setzeFarbe("Rot");
        TEXT text = new TEXT(0,0, 1, "Pause");
        text.setzeMittelpunkt(0, 0);
    }
    
    @Override
    public boolean kollisionReagieren(ea.edu.EduActor anderer) {
        this.setzeEbenenzeitverzerrung("Hauptebene", 0.4f);
        anderer.verzoegere( 2f , () -> this.setzeEbenenzeitverzerrung("Hauptebene", 1f) );
        return true;    // bei false wird diese Kollision NICHT aufgelöst !!!
    }
    
    @Override
    public void tasteReagieren( int taste )
    {
        if ( taste == TASTE.S )
        {
            this.ball.macheAktiv();
            this.ball.wirkeImpuls( -20000, 0 );
        }
        
        else if ( taste == TASTE.A )
        {
            this.figur.verschiebenUm(-0.5f, 0);
        }
        
        if (taste == TASTE.P ) 
        {
            if(istPause) 
            {
                this.setzeSzene("Pickle Rick");
                istPause = false;
            } 
            else 
            {
                this.setzeSzene("Pause");
                istPause = true;
            }
        }
    }
}