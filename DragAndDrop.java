
/**
 * Klasse DRAGANDDROP - Demo
 * 
 * @author      mike_gans@yahoo.de  and  michael andonie
 * 
 * @version     2019-08-07
 */
public class DragAndDrop
extends SPIEL
{
    KREIS objekt;
    
    boolean mausGedrueckt = false;
    
    //Mauskoordinaten des letzten Klick
    double maus_x = 0;
    double maus_y = 0;

    //Kreismittelpunkt beim letzten Klick
    double kreis_m_x = 0;
    double kreis_m_y = 0;

    /**
     * Konstruktor für Objekte der Klasse DRAGANDDROP
     */
    public DragAndDrop()
    {
        super( 800 , 600 , true );
        objekt = new KREIS();
    }
    
    @Override
    public void klickReagieren( double mx , double my ) 
    {
        if ( objekt.beinhaltetPunkt( mx , my ) ) 
        {
            maus_x = mx;
            maus_y = my;
            
            kreis_m_x = objekt.nenneMx();
            kreis_m_y = objekt.nenneMy();
            
            mausGedrueckt = true;
        }
    }
    
    @Override
    public void klickLosgelassenReagieren( double x , double y )
    {
        mausGedrueckt = false;
    }
    
    @Override
    public void bildAktualisierungReagieren( double ms ) 
    {
        if ( !mausGedrueckt ) 
        {
            return;
        }
        else
        {
            double aktuelleMausX = super.nenneMausPositionX();
            double aktuelleMausY = super.nenneMausPositionY();
            
            double delta_x = aktuelleMausX - maus_x;
            double delta_y = aktuelleMausY - maus_y;
            
            objekt.setzeMittelpunkt(kreis_m_x + delta_x, kreis_m_y + delta_y);
        }
    }
}
