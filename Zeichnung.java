class Zeichnung
{
    private KREIS eisKugel, tropfen;
    private DREIECK eisWaffel;
    
    public Zeichnung()
    {
        this.eisKugel = new KREIS(3);
        this.eisKugel.setzeFarbe("lila");
        this.eisKugel.setzeMittelpunkt(0,0);
        
        this.eisWaffel = new DREIECK(-3,-1 , 0,-9 , 3,-1);
        this.eisWaffel.setzeFarbe("gelb");
        
        this.tropfen = new KREIS(1);
        this.tropfen.setzeMittelpunkt(3,-9);
        this.tropfen.setzeSichtbar(false);
    }
    
    public void schmelzen()
    {
        this.eisKugel.setzeRadius( 2.8 );
        this.tropfen.setzeFarbe("lila");
        this.tropfen.setzeSichtbar(true);
    }
}