
/**
 * Beschreiben Sie hier die Klasse PhysikTest.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PhysikTest
extends SPIEL
{
    private RECHTECK boden;
    private RECHTECK ding;
    
    public PhysikTest()
    {
        this.boden = new RECHTECK(15,1);
        this.boden.setzeMittelpunkt(0,-8);
        this.boden.setzeElastizitaet(0.9);
        this.boden.setzeReibung(0.2);
        this.boden.machePassiv();
        
        this.ding = new RECHTECK(2,2);
        this.ding.setzeMittelpunkt(0, 5);
        this.ding.setzeFarbe("blau");
        this.ding.setzeElastizitaet(0.8);
        this.ding.setzeReibung(0.2);
        this.ding.drehenUm(SPIEL.zufallszahlVonBis(0, 90));
        this.ding.setzeRotationBlockiert(false);
        this.ding.macheAktiv();
    }
    
    @Override
    public void tasteReagieren( int taste )
    {
        this.ding.setzeMittelpunkt(0,0);
        this.ding.setzeGeschwindigkeit(0,0);
        this.ding.drehenUm(SPIEL.zufallszahlVonBis(0, 90));
        this.ding.setzeWinkelgeschwindigkeit(0);
    }
}
