import java.util.*;
import javax.swing.*;

/** Spielleiter Vorlage zu Projekt/Klausur Jan.2023
 *  Theo Heußer;    2.1.2023
 *  >>> fuer Spiel17u4, noch ohne Gewinnentscheid bei mehreren Spielern 
 *  >>> nutzt die Objekt-Wiederherstellung aus SpielSpeicher
 */

public class Spielleiter
{
    // --- Objektvariable 
    private ArrayList<Spieler17u4> spielerliste; 
    private int maxAnzahl;
    private boolean spielBeendet;

    // --- Konstruktoren --- 
    /** Konstruktor, um ein Spiel mit einigen Spielern vorzubereiten.  
     *  Die maximale Anzahl der Spieler ist einzugeben!  */
    public Spielleiter(int anzahl) {
        spielerliste = new ArrayList<Spieler17u4>();  
        maxAnzahl    = anzahl;
        spielBeendet = false;
        for (int i=0; i<maxAnzahl; i++) {
            Spieler17u4 sp = new Spieler17u4("Sp"+i, this);  //this hinzugefügt, um den neuen Konbbstruktor aufzurufen
            spielerliste.add(sp);
        }
    }  

    /** Parameterloser Konstruktor für ein Spiel mit max. 20 Spielern.  
     *  >> wird  u.a. von der Wiederherstellung verwendet.    */
    public Spielleiter() {
        spielerliste = new ArrayList<Spieler17u4>();  
        maxAnzahl    = 20;
        spielBeendet = false;
        //daran schließt sich an, was durch die Wiederherstellung geleistet wird.
    }
    /*# wird von der Objekt-Wiederherstellung durch die Speicherklasse verwendet, um
     *  eine komplette Situation mit mehreren vorgegebenen Spielern zu haben, in der ggf.
     *  die Methoden getestet werden können, die im Projekt programmiert werden sollen.  */

    /** trägt einen Spieler in die Teilnehmerliste ein. 
     *  nur möglich, wenn  spielerliste  noch nicht voll ist. */
    public boolean eintragen(Spieler17u4 teilnehmer)   {
        if(spielerliste.size() >= maxAnzahl) {  
            melde("Es sind schon genug Teilnehmer.\nKeine Teilnahme mehr m\u00F6glich.",
                "WARNUNG!");
            return false;
        }
        else {
            spielerliste.add(teilnehmer);
            return true;
        }
    }

    /** zeigt die aktuelle Liste der Mitspieler an. 
     */
    public void zeigeAlleSpieler()   {
        schreibe("----- Logging all Players------");
        for (Spieler17u4 sp : spielerliste) {
            String zusatz = "";
            if (sp.isFertig()) {
                zusatz = " fertig!";
            }
            schreibe(sp.getName()+": "+sp.getStand()+zusatz); 
        }
        schreibe("----- End of Logging ------");
    }    

    /** prüft, ob Spiel zu Ende ist, ändert die Variable spielBeendet
     *  und meldet ggf. das Spielergebnis;
     *  andernfalls ergebnislos und kommentarlos weiter   */
    public void pruefeEnde()  {
        spielBeendet = true;     
        for (Spieler17u4 sp : spielerliste) {       
            if (sp.getStand()<=21 && !sp.isFertig()) {
                spielBeendet = false; 
            }            
        }

        if (spielBeendet) {
            meldeSieger();
        } else { 
            schreibe("Runde beendet. H\u00F6rt einer auf?"); 
        }
    }

    /** eine weitere Runde 
     *  für alle Spieler spielen lassen */
    public void lasseRundeSpielen()   {
        for (Spieler17u4 sp : spielerliste) {
            sp.spieleRunde(); 
            if(sp.getStand() > 21){
                sp.aufhoeren(); //Den Spieler als fertig ansehen, da er nicht mehr gewinnen kann
            }
        }
        //schreibe("Gesamtstand: ");
        zeigeAlleSpieler();
        pruefeEnde();       
    }

    // ++++++++++++++++    Aufgaben Klausur     +++++++++++++++++++++++++ 
    // Bereich A: Funktionen, welche genau den Wortlaut der Aufgaben erfüllen. Benötigen z.T. Funktionen aus Bereich B
    /** Entfernt einen Spieler per Index aus der Liste.'
     *  Überprüft zuerst, ob der Index innerhalb der Länge der Liste liegt.
     *  Ist dies der Fall, wird der Spieler am gegebenen Index gelöscht.
     *  
     *  Eingabe: Nichts
     *  Rückgabe: boolean: True: erfolgreich gelöscht; False: Fehler
     *  Laufzeit: O(1) (konst.)
     *  Space-Complexitiy: O(1) (konst.) */
    public boolean removePlayerFour(){
        return removePlayerByIndex(3);
    }

    /** Sucht nach einem Spieler mit dem Namen "name" und entfernt diesen aus der ArrayList aller Spieler.
     *  Dabei wird mit Hilfe einer for-Schleife über alle Spieler17u4 Objekte iteriert, wobei die Namen abgeglichen werden.
     *  Der Name ist Case-Insensitive d.h. Groß- und Kleinschreibung wird nicht beachtet.
     *  
     *  Eingabe: name (String): Der Name des zu entfernenden Spielers
     *  Rückgabe: boolean: True: erfolgreich gelöscht; False: Fehler
     *  Laufzeit: O(n) (linear)
     *  Space-Complexitiy: O(1) (konst.) */
    public boolean removePlayerByName(String name){
        for(int i = 0; i < spielerliste.size(); i++){
            if(spielerliste.get(i).getName().toLowerCase().equals(name.toLowerCase())){
                schreibe("Kein Spieler names: " + spielerliste.get(i).getName());
                spielerliste.get(i).austragen(this); //Spielleiter beim Spieler austragen
                spielerliste.remove(i);
                return true;
            }
        }
        schreibe("Kein Spieler namens: " + name);
        return false;
    }

    /** 
     *  Alternative zu "removePlayerByName". Funktioniert gleich, nur,
     *  dass mit Hilfe einer foreach-Schleife über die Objekte iteriert wird.
     * 
     *  Eingabe: name (String): Der Name des zu entfernenden Spielers
     *  Rückgabe: boolean: True: erfolgreich gelöscht; False: Fehler
     *  Laufzeit: O(n) (linear)
     *  Space-Complexitiy: O(1) (konst.) */
    public boolean removePlayerByNameAlternative(String name){
        for(Spieler17u4 player: spielerliste){
            if(player.getName().toLowerCase().equals(name.toLowerCase())){
                schreibe("Kein Spieler names: " + player.getName());
                player.austragen(this); //Spielleiter beim Spieler austragen
                spielerliste.remove(player);
                return true;
            }
        }
        schreibe("Kein Spieler namens: " + name);
        return false;
    }

    /** 
     *  Alternative zu "removePlayerByName".
     *  Löscht das Objekt basierend auf der build-in Methode ArrayList<T>.remove(T obj), mit T = Spieler17u4
     * 
     *  Eingabe: player (Spieler17u4): Der zu entfernende Spieler
     *  Rückgabe: boolean: True: erfolgreich gelöscht; False: Fehler
     *  Laufzeit: O(n) (linear) (aufgrund der Funktionsweise von ArrayList<T>.remove(T obj))
     *  Space-Complexitiy: O(1) (konst.) */
    public boolean removePlayerByObject(Spieler17u4 player){
        if(!spielerliste.contains(player)){
            schreibe("Kein Spieler namens: " + player.getName());
            return false;
        }
        schreibe("Spieler " + player.getName() + " entfernt");
        return true;
    }

    /** Gibt allen Spielern mit 12 Punkten 1 Punkt mehr.
     *  Dafür wird über alle Objekte der Spielerlsite iteriert
     *  
     *  Eingabe: Nichts
     *  Rückgabe: Nichts
     *  Laufzeit: O(n) (linear)
     *  Space-Complexitiy: O(1) (konst.) */
    public void incrementOnTwelve(){
        alterAllByPoints(12,1);
    }

    /** Ersetzt den Spieler am 5. Index mit einem anderen Spieler.
     *  Der neue Spieler wird durch die Funktion ergaenzeKA23(5) erstellt (siehe deren Beschreibung)
     *  Der erstzte Spieler wird zurückgegeben, damit mit dem Objekt noch weiter gearbeitet werden kann, wenn du Funktion von anderen Funktionen aufgerufen wird.
     *  Die Listengröße wird bereits in ergaenzeKA23 überprüft.
     *  
     *  Eingabe: Nichts
     *  Rückgabe: Spieler17u4|null: Spieler17u4 obj: erfolgreich ersetzt, obj ersetzter Spieler; null: Fehler (Liste zu kurz)
     *  Laufzeit: O(1) (konst.)
     *  Space-Complexitiy: O(1) (konst.) */
    public Spieler17u4 insertPlayerAtIndexFive(){
        Spieler17u4 player = ergaenzeKA23(5);
        if(player == null){
            return null;
        }
        Spieler17u4 oldPlayer = spielerliste.get(5);
        spielerliste.set(5, player);
        schreibe("Replaced " + oldPlayer.getName() +" with " + player.getName());
        oldPlayer.austragen(this); //den Spielleiter austragen, da dieser Spieler nun keinen mehr hat;
        return oldPlayer;

    }

    //Bereich B: Allegemeinere Funktionen, welche genutzt werden, um die Aufgaben zu erfüllen

    /** Entfernt einen Spieler per Index aus der Liste.'
     *  Überprüft zuerst, ob der Index innerhalb der Länge der Liste liegt.
     *  Ist dies der Fall, wird der Spieler am gegebenen Index gelöscht.
     * 
     *  Eingabe: i (int): Index des zu entfernenden Spielers
     *  Rückgabe: boolean: True: erfolgreich gelöscht; False: Fehler
     *  Laufzeit: O(1) (konst.)
     *  Space-Complexitiy: O(1) (konst.) */
    private boolean removePlayerByIndex(int i){
        if(spielerliste.size() <= i){
            return false;
        }
        spielerliste.get(i).austragen(this); //Spielleiter beim Spieler austragen
        spielerliste.remove(i);
        return true;
    }

    /** Gibt allen Spielern mit "points" Punkten "alteration" mehr bzw. weniger Punkte.
     *  Dafür wird über alle Objekte der Spielerlsite iteriert.
     *  
     *  Eingabe:    points (int): der Punktestand, bei welchem Eine veränderung eintreten soll
     *              alteration (int): die Veränderung (positiv oder negativ)        
     *  Rückgabe: Nichts
     *  Laufzeit: O(n) (linear)
     *  Space-Complexitiy: O(1) (konst.) */
    private void alterAllByPoints(int points, int alteration){
        schreibe("HINWEIS: "+ alteration + " Punkte für alle, die " + points + " Punkte haben");
        for(Spieler17u4 player: spielerliste){
            if(player.getStand() == points){
                player.setStand(player.getStand() + alteration);
            }
        }
    }

    // ++++++++++++++++    Hilfsroutinen     +++++++++++++++++++++++++
    /** die veränderlichen Werte zurücksetzen 
     *  der Reihe nach für alle Spieler der  spielerliste  */
    public void neuesSpiel() {
        for (Spieler17u4 sp : spielerliste) {       
            sp.setStartwerte();   
        }
    } 

    /** Feststellen, wer Sieger/ die Sieger (bei gleicher Punktzahl) ist. 
     *  Sortiert die Spieler den Punkten nach. Sucht den Spieler mit der höchsten Punktzahl <= 21.
     *  Fügt alle anderen Spieler mit gleicher Punktzahl ebenso der Siegerliste zu
     *  
     *  Eingabe: Nichts
     *  Rückgabe: ArrayList<Spieler17u4>|null:  ArrayList: Die Gewinner als Objekte; null: keine Gewinner 
     *  Laufzeit: O(n^3 log n) (Allerschlechtester Fall: jeder die gleiche Punktzahl <= 21)
     *  Space-Complexity O(n) */
    private ArrayList<Spieler17u4> ermittleSieger()   {
        //ArrayList in ein Array kopieren
        Spieler17u4[] arr = cloneArray(spielerliste);

        //Array sortieren mit Merge-Sort
        split(arr, arr.length);

        //Stelle finden, ab welcher Spieler mehr als 21 Punkte haben
        int i = 0;
        while(arr[i].getStand() <= 21 && i < arr.length){
            i++;
        }

        ArrayList<Spieler17u4> winner = new ArrayList<Spieler17u4>();
        if(i != 0){
            //Index des letzten gültigen Spielers (Punkte <=21)
            int k = i- 1;
            winner.add(arr[k]); //Garantierter Gewinner

            //Andere Spieler mit der gleichen Punktzahl
            while(arr[k].getStand() == arr[k-1].getStand() && k != -1){
                winner.add(arr[k-1]);
                k--;
            }
        } else {
            winner = null;
        }
        return winner;
    }

    /** Schreibe den Sieger vorerst auf die Konsole.
     * Erhält die Sieger durch ermittleSieger()
     *
     *  Eingabe: Nichts
     *  Rückgabe: Nichts 
     *  Laufzeit: O(n^3 log n)
     *  Space-Complexity O(n) */
    public void meldeSieger()   {
        ArrayList<Spieler17u4> winner = ermittleSieger();
        //Gibt es keine Gewinner?
        if(winner == null){
            schreibe("Es gibt keinen Gewinner, jeder hat mehr als 21 Punkte");
            return; //Funktionsende
        } 
        schreibe("-----Gewinner------");
        for(Spieler17u4 player : winner){
            schreibe("Herzlichen Glückwunsch " + player);
        }
        schreibe("-----Rundenende------");
        neuesSpiel();
    }   

    // -- Hilfsroutinen
    /** zeilenweise schreiben, Ausgabe auf Konsole 
     *  nur um nicht immer wieder den langen Namen:   System.out.println 
     *  dieser Java-System-Methode schreiben zu müssen.   */
    private void schreibe(String text) {
        System.out.println(text);
    }

    /** VORSICHT !! Spielleiter hat Macht!!! 
     *  Dadurch kann der Spielleiter den Spielstand des Mitspielers mit der Nr. nr  
     *  auf einen neuen Wert setzen, der in der Variable   wert  genannt wird.
     *  Dies dient vor allem der Fehlersuche */
    public void setze(int nr, int wert) {   
        Spieler17u4 sp = spielerliste.get(nr);
        sp.setStand(wert);
    }

    /** VORSICHT !! Spielleiter hat dadurch Macht!!! 
     *  Dadurch lässt der Spielleiter den Mitspieler mit der Nr. nr aufhören. 
     *  Dies dient vor allem der Fehlersuche */
    public void aufhoeren(int nr) {  
        Spieler17u4 sp = spielerliste.get(nr);
        sp.aufhoeren();
    }  

    /** Erstellt ein Spieler17u4-Objekt mit dem Namen "Reservix_", gefolgt von einer Nummer zwischen
     *  0 und 100. Für Aufgabe f() wird 5 übergeben, um "Reservix_5" als name zu erhalten.
     *  Der Stand des  Speilers,wird auf 13 gesetzt, bevor dieser zurückgegeben wird.
     *  
     *  Eingabe: nr (int): Die Nummer, welche den Spielernamen "Reservix_" angehängt wird. (wird in den Ganzzahlenbereich 0-100 umgewandelt)
     *  Rückgabe: Spieler17u4|null: Spieler17u4 obj: Erfolgreich erstellter Spieler; null: Fehler (Liste zu kurz)
     *  Laufzeit: O(1) (konst.)
     *  Space-Complexitiy: O(1) (konst.) */

    public Spieler17u4 ergaenzeKA23(int nr) {  
        if (nr < 0) {nr = -1*nr; }
        if (nr>100) {nr = nr%100; }
        Spieler17u4 spNeu = new Spieler17u4("Reservix_"+nr, this);  //this, um den Spielleiter zu übergeben
        spNeu.setStand(13);
        //Einzige Änderung: size() >= 6, damit es für immer geht, wenn ein 5. Index exisitert, 
        //anonsten würde die Methode für den Fall, dass die List 6 Objekte lang ist nicht funktionieren, obwohl es einen 5. Index gäbe
        if ( spielerliste.size()>=6) { return spNeu; }
        else {return null;}
    }                

    /** Kopiert eine ArrayList in ein Array
     *  
     *  Eingabe: in (ArrayList<Spieler17u4>): Die zu kopierende ArrayList
     *  Rückgabe: Spieler17u4[]: Die ArrayList als Array (kopiert)
     *  Laufzeit: O(n) (linear)
     *  Space-Complexitiy: O(n) (linear) */
    private Spieler17u4[] cloneArray(ArrayList<Spieler17u4> in){
        Spieler17u4[] clone  = new Spieler17u4[in.size()];
        for(int i = 0; i < in.size(); i++){
            clone[i] = spielerliste.get(i);
        }
        return clone;
    }
    // -- Hilfsroutinen
    // -- solange nicht in eine GUI eingebunden.
    /** gibt den uebergebenen String text in einer Dialogbox auf dem Bildschirm 
     *  unter der Ueberschrift  titel  aus  */
    protected void melde(String text, String titel) {
        JOptionPane.showMessageDialog(null, text, titel, JOptionPane.INFORMATION_MESSAGE);
    }

    //------------------- Merge-Sort-------------------------------
    /** Sortieralgorithmus 
     *  Laufzeit: O(n log n)
     *  Space-Complexity: O(n) (linear)*/
    private void split(Spieler17u4[] arr, int n){
        //Rekursionsbruch: Länge 1 kann nicht weiter unterteilt werden
        if(n == 1){
            return;
        }

        //Array in zwei Unterarrays aufteilen
        int middle =  n/2;
        Spieler17u4[] left = new Spieler17u4[middle];
        Spieler17u4[] right = new Spieler17u4[n - middle];

        //Werte aus Ursprungsarray in die Hälfen übertragen
        for (int i = 0; i < middle; i++) {
            left[i] = arr[i];
        }

        for (int i = middle; i < n; i++) {
            right[i - middle] = arr[i];
        }

        //Rekursives Aufteilen bis die Länge 1 erreicht ist
        split(left, middle);
        split(right, n - middle);

        // Zusammensetzen der Arrayteile
        merge(arr, left, right, middle, n-middle);
    }

    
    private void merge(Spieler17u4[] arr, Spieler17u4[] firstArr, Spieler17u4[] secondArr, int leftLen, int rightLen){
        int i = 0; //linkes Array: Index
        int j = 0; //rechtes Array: Index
        int k = 0; //finales Array: Index

        //Sortierung der sortierten Teilarrays in das Hauptarray
        
        //Solange beide Teilarrays noch Elemente beinhalten
        while(i < leftLen && j < rightLen){
            
            //Element des l. Arrays kleiner als das Element des r. Arrays?
            if(firstArr[i].getStand() <= secondArr[j].getStand()){
                arr[k] = firstArr[i];
                k++;
                i++;
            } else {
                arr[k] = secondArr[j];
                k++;
                j++;
            }
        }

        // Nur eine Schleife wird durchlaufen. Restelemente werden in das Hauptarray übertragen
        while(i < leftLen){
            arr[k] = firstArr[i];
            k++;
            i++;
        }

        while(j < rightLen){
            arr[k] = secondArr[j];
            k++;
            j++;
        }
    }
}

/* ++++++++++++++       ++++++++++++++++++
 * 
 * Etliches darin benötigen Sie nicht für die Projektarbeit.
 * Das Projekt ist korrekt und lauffähig, aber noch nicht vollständig. 
 * Die Siegerermittlung ist noch nicht implementiert. 
 * Ihrer Projektarbeit Januar 2023 wird dadurch NICHT berührt.
 *     
 *   ++++++++++++>>>   zu ArrayList< >   siehe z.B. unser Infoblatt
 * 
 *    
 */

