
/**
 * Interface BildAktualisierungReagierbar. Fordert zum Ueberschreiben der Methode bildAktualisierungReagieren(double zeitIntervall) auf.
 * Sie wird nach jedem Frame-Update automatisch aufgerufen. 
 * Ihr wird automatisch as Zeitintervall (in Sekunden) seit dem letzten Frame-Update uebergeben.
 * Das Zeitintervall kann genutzt werden, um exakte Physik zu betreiben: 
 * z.B. verschiebenUm( zeitIntervall*nenneGeschwindigkeitX() , zeitIntervall*nenneGeschwindigkeitY() )
 * 
 * @author      mike_gans@yahoo.de  and michael andonie
 * 
 * @version     4.0 (2020-01-04)
 */

public interface BildAktualisierungReagierbar
extends ea.edu.event.BildAktualisierungReagierbar
{
    
}
