/** Projekt: Spiel17u4roh
 *  Klasse   Spieler17u4 
 *  @author  Info1; thh
 *  @version 221010 - roh
 */
public class Spieler17u4
{
    // Objektvariable; 
    // sie beschreiben Eigenschaften eines jeden Spielers dieses Spiels
    private int aktStand;       // merkt sich ...
    private int aktWurf;        // merkt sich ...
    private boolean fertig;     // merkt sich, ob ...
    
    /** Konstruktor: erzeugt neue Objekte der Klasse Spieler17u4 
     *  dabei werden die Anfangswerte festgelegt */
    public Spieler17u4()    { 
        aktStand = 0; 
        aktWurf  = 0;           // <-- 4.
        fertig   = false;
    }
    
    /** den Wert des aktuellen Wurfes nennen 
     */
    public int getWurf() {
        return aktWurf;
    }
    
    public int getStand(){
        return aktStand;
    }
        
    /** den Wert des aktuellen Spielstandes nennen   */
    // Ihre 2. Aufgabe im Programm       // <-- 5.
    
    /** eine Runde spielen; das bedeutet: wuerfeln mit normalem W6, 
     *  Wurfergebnis merken, den Stand erhöhen, ebenfalls merken, .... 
     *  Tragen Sie die korrekten Zeilen ein. vgl. Aufgabe 7;  
     *  Allerdings: danach fehlt noch etliches !  */
    public void spieleRunde() {
        // unvollständig  !!                   // <-- 8.
            // [wm] Wurfergebnis unter den Namen aktWurf merken.
            // [se] aktStand erhöhen um aktWurf
            aktWurf = wuerfle();
            aktStand += aktWurf;
        // das ist nicht alles; nicht für alle Fälle
    }   

    /** interne Routine: das Wurfergebnis eines 
     *  Wurfes mit einem normalen W6 zurückmelden */
    private int wuerfle() {                    // <-- 0.
        Dice dice = new Dice();
        return dice.roll();
        //return  1 + (int) (Math.random()*6); 
        //<-- Z*.
    }    
        
    /** zurückmelden, 
     *  ob der Spieler aufgehört hat oder nicht */
    public boolean istFertig()  {
        return fertig;
    }   
    
    public void hoereAuf(){
        fertig = true;  
    }
    
    /** der Spieler entscheidet für sich, dass er nicht mehr weiterwürfeln will;
     *  er notiert es sich, aber teilt dies niemandem mit. */
    // Ihre 3.Aufgabe  im Programm          //<-- 6.
    
}

/*# Aufgaben: 
 *  (Vgl. Arbeitsblatt AB1 zu Spiel17u4, dort 1., 2.)
 *  
 * 0. Als Methodenname verwende ich wuerfle() , reine ASCII-Zeichen, und nicht würfle().
 *    Sicherist sicher! Ich weiß nicht, ob jeder eine UTF-8-Kodierung verwendet. Innerhalb Javas wird 
 *    sie i.d.R. verwendet.
 *    
 *  
 * 3. Erproben Sie, was diese Spieler schon können.
 * 
 * 4. Erläutern Sie die Anfangsfestlegungen in Worten.
 *    Der aktuelle Wurf beträgt den Wert 0, die GEsamtanzahl aller Würfe ebenso. 
 *    Der Spieler ist zu begin noch nicht fertig mit dem Spiel.
 * 5. Ein Spieler kann auf Anfrage seinen aktuellen Stand nennen. Erstellen 
 *    Sie die Methode für diese Fähigkeit. Welchen Namen geben Sie ihr?  
 *    Das ist eine Anfrage-Methode, die eine ganzzahlige Antwort zurückgibt 
 *    wie bei getWurf().
 *    
 * 6. Wo können Sie am Objektdiagramm eines Spielers ablesen, ob er 
 *    noch weiter würfeln will oder bereits aufgehört hat?
 *    Sorgen Sie dafür, dass die Spieler die Fähigkeit haben, von sich aus 
 *    mit dem Würfeln aufzuhören.
 *    
 * 7. --siehe AB--
 * 
 * 8. Ergänzen Sie in der Methode spieleRunde() die zwei Anweisungen
 *    für diese Aktionen  Z.38,39 
 *    Erproben Sie Ihre veränderten Spieler.
 *    Welche Schwächen hat die Methode danach noch? Notieren Sie sie. 
 *    Geben Sie Ideen an, diese Schwächen zu beseitigen. 
 *    Verbessern Sie Ihre Spieler.
 *    Implementieren Sie diese Ideen.
 *    
 *    
 *   
 * 9.-12. Arbeitsblatt zu Spiel17u4; AB1
 * 
 * Z*. Immer, wenn man wuerfle() ausführen lässt, wird ein Wurfergebnis von 1..6 
 *    zurückgemeldet.  Wie dies im Detail geht, müssen Sie nicht beachten. 
 *   ++ Es wird durch Math.random() mit mathematischen Hilfsmitteln eine Zahl im
 *      Bereich 0,0 bis 0,9999 erzeugt. Nach Multiplikation mit 6 wird diese Zahl
 *      durch (int) auf ihren ganzzahligen Anteil reduziert, aus z.B.: 
 *      3,876502 wird 3.  So kommen nur ganzzahlige Ergebnisse von 0..5 heraus.
 *      Durch die Addition: 1 + ...  werden so die Wurfergebnisse 1..6 erreicht.
 *      
 */