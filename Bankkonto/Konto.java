import java.util.List;
import java.util.LinkedList;
/**
 * Beschreiben Sie hier die Klasse Bankkonto.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Konto implements IStorage
{
    private int amount = 0;
    private boolean isLocked= false;
    private int maxDispo;
    private String[] owners;
    private int maxWithdraw;
    private int maxTransfer;
    private String password;
    public String IBAN;
    public String BIC;
    private LinkedList<Transfer> transferHistory = new LinkedList<Transfer>();

    private String country;
    private String street;
    private int houseNumber;
    private int plz;
    private String city;

    /**
     * Konstruktor für Objekte der Klasse Bankkonto
     */
    public Konto( String[] people, int money, int disp, Object caller, String _IBAN, String _BIC)
    {
        // Instanzvariable initialisieren
        owners = people;
        amount = money;
        isLocked = false;
        maxDispo = disp;
        IBAN = _IBAN;
        BIC = _BIC;
    }

    public void withdraw(int money)
    {
        if(amount >= money){
            amount -= money;
        }
    }

    public void deposit(int money){
        amount += money;
    }
    
    public Transfer createTransfer(int money, String[] to){
        try {
            if(to.length == 3){
            String[] personal = new String[3];
            personal[0]= IBAN;
            personal[1] = BIC;
            personal[2] = owners[0];
            if(amount < money){
                throw new Exception();
            }
            Transfer transfer = new Transfer(money, personal, to);
            amount -= transfer.getAmount();
            return transfer;
            }
            throw new Exception();
        } catch(Exception ex){
            
        }
        String[] ex = new String[3];
        return new Transfer(0, ex, ex);
    }
    
    /**private boolean transfer(Transfer transfer){
        if(transfer.getReceiverIBAN() == IBAN) {
            amount += transfer.getAmount();
            return true;
        } else if(transfer.getSenderIBAN() == IBAN) {
            if (amount >= transfer.getAmount()){
               amount -= transfer.getAmount();
               return true;
            }
            return false;
        }
        return false;
    }**/
    
    public void lock(Object caller){
        
    }
    
    public void unlock(Object caller){
        
    }
    public String[] getPersonalInfo(Object caller){
        return new String[2];
    }
    
    public String[] getKontoauszug(){
        return this.getPersonalInfo(this);
    }
    
    public int getBalance(){
        return amount;
    }
}
