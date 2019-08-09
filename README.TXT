Engine-Alpha     EDU-Klasse     Version 4.0

 = Kurz-Erklaerung =

Es gibt ein (unsichtbares) Interface 'EduActor', das man als eine gemeinsame 
'Super-Klasse' (Ober-Klasse) aller grafisch sichtbaren Objekte ansehen kann.

Man findet dieses Interface als Typ in Methoden, die ein anderes Grafik-Objekt uebergeben bekommen.
z.B. beruehrt(...) , berechneAbstandX(...) , berechneAbstandY(...)

In BlueJ werden in der Klassen-Karte nur die unmittelbaren Methoden der Objekte angezeigt. 
Auch bei Rechtsklick auf die rote Objekt-Karte erscheinen zunaechst nur diese Methoden.
Dort kann man aber auch nach oben gehen zu 'geeerbt von EduActor'.
Hier befinden sich nun weitere Methoden z.B. fuer die Jump'n'Run-Physik oder die Newton-Physik.

    Dort verbergen sich  V I E L E   N E U E   F E A T U R E S  ! ! ! 

    V I E L   S P A S S   B E I M   E N T D E C K E N   :-)
    
vordefinierte Farbnamen:
-------------------------
gelb
weiss, weiß
orange
grau
gruen, grün
hellgruen, hellgrün
dunkelgruen, dunkelgrün
blau
hellblau
dunkelblau
rot
pink
magenta,
lila, purple
braun
cyan, tuerkis, türkis
dunkelgrau
hellgrau
grau
schwarz
--> unbekannte Farbnamen fuehren nun zu einer Exception :-)
--> es gibt auch eine neue Methode setzeFarbe( rot , gruen , blau ) :-)

Michael Ganshorn:   mike_gans@yahoo.de