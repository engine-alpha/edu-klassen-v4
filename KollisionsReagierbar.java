
/**
 * Interface KollisionReagierbar. Fordert auf zum Ueberschreiben der Methode 'boolean kollisionReagieren( EduActor ea )'. 
 * Die Methode wird automatisch aufgerufen, sobald ein anderes Objekt beruehrt wird. 
 * Ihr wird automatisch eine Referenz auf das beruehrte Objekt uebergeben.
 * Dieses Interface sollte nicht in SPIEL (bzw. einer Unterklasse) 
 * sondern in einer Unterklasse von EduActor implementiert werden.
 * !!! Beachte hierzu die von EduActor geerbten Methoden registriereKollisionsReagierbar(...) !!!
 * 
 * @author      Mike Ganshorn   (Mike_Gans@yahoo.de)
 * 
 * @version     4.0 (2020-01-04)
 */

public interface KollisionsReagierbar
extends ea.edu.event.KollisionsReagierbar
{
    
}
