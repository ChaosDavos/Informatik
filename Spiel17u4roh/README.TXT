PROJEKT: Spiel17u4roh
Klasse:  Spieler17u4
ZWECK:   erstes Programm
DATUM:   25.9.2021/ 2.2.19/...
AUTOR:   Theo Heußer

Hinweise:
Spieler erzeugen und spielen, indem die Spieler reihum wuerfeln.
Dem Spieler17u4 in dieser Rohversion fehlen entscheidende Dinge.
Ergaenzen Sie sie.


Spieler17u4: (EinfachVersion)

charakterisierende Eigenschaften: 
aktWurf;    (eine Zahl von 1..6)
aktStand;   (eine Zahl von 0..21 bzw. 25)
fertig;     (ja/nein) 

Faehigkeiten:
getWurf();      : Wurfergebnis nennen
getStand();     : akt. Stand nennen
aufhoeren();    : sagen, dass er aufhoert
istFertig();    : Angabe, ob aufgehoert wurde
spieleRunde();  : eine Runde spielen, d.h.: wuerfeln und aufaddieren
wuerfle();      : wuerfeln und das Wurfergebnis zurueckmelden


// --------------------------

Spieler17u4: (erweitert)

charakterisierende Eigenschaften: 
name;       (ein Wort)  
aktWurf;    (eine Zahl von 1..6)
aktStand;   (eine Zahl von 0..21 bzw. 25)
fertig;     (ja/nein) 

Faehigkeiten:
getName();      :Namen nennen
setzeName(String neuName)  : neuen Namen festsetzen;
getWurf();      :akt. Wurfergebnis nennen
getStand();     :akt. Stand nennen
aufhoeren();    :festlegen, dass er aufhoert
istFertig();    :melden, ob aufgehoert wurde
spieleRunde();  :eine Runde spielen, d.h. wuerfeln und aufaddieren
wuerfle();      :nur wuerfeln und Wurfergebnis melden; sonst nichts

