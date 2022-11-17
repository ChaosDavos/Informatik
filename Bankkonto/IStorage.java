
/**
 * Tragen Sie hier eine Beschreibung des Interface IStorage ein.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */

public interface IStorage
{
    /**
     * Ein Beispiel eines Methodenkops - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     * 
     * @param  y    ein Beispielparameter für eine Methode
     * @return        das Ergebnis von beispMethode
     */
    
    public int getBalance();
    public String[] getPersonalInfo(Object caller);
    public void deposit(int money);
    public void withdraw(int money);
    public void lock(Object caller);
    public void unlock(Object caller);
    
}
