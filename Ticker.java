
/**
 * Interface Ticker. Fordert zum Ueberschreiben der Methode tick() auf.
 * Der Ticker muss erst gestartet werden mit der Methode starteTickerNeu( double sekunden ).
 * Die Methode tick() wird von nun an regelmaessig automatisch aufgerufen. 
 * Der Ticker kann mit stoppeTicker() gestartet werden.
 * Schnellere Ticker als 0.05 Sekunden sind nicht sinnvoll !!! (aber moeglich)
 * Anstatt dessen sollte man dann BildAktualisierungReagierbar nutzen !!!
 * 
 * @author      mike_gans@yahoo.de  and michael andonie
 * 
 * @version     4.0 (2020-01-04)
 */

public interface Ticker
extends ea.edu.event.Ticker 
{
    
}
