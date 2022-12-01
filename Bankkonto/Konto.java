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
    private String owner;
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
    public Konto( String person, int money, int disp, Object caller, String _IBAN, String _BIC, int _maxTransfer, String code)
    {
        // Instanzvariable initialisieren
        maxTransfer = _maxTransfer;
        owner = person;
        amount = money;
        isLocked = false;
        maxDispo = disp;
        IBAN = _IBAN;
        BIC = _BIC;
        password = code;
    }

    public void withdraw(int money)
    {
        if((amount + maxDispo) >= money || (money > maxTransfer && maxTransfer != 0)){
            amount -= money;
        }
    }

    public void deposit(int money){
        if((money > maxTransfer && maxTransfer != 0)){
            amount += money; 
        }
    }

    public Transfer createTransfer(int money, String[] to){
        try {
            if(to.length == 3){
                String[] personal = new String[3];
                personal[0]= IBAN;
                personal[1] = BIC;
                personal[2] = owner;
                if( ((amount + maxDispo) < money) || (money > maxTransfer && maxTransfer != 0)){
                    throw new Exception("Insufficient funds or amount too large");
                }
                Transfer transfer = new Transfer(money, personal, to);
                amount -= transfer.getAmount();
                transferHistory.add(transfer);
                return transfer;
            }
            throw new Exception("Invalid sender data");
        } catch(Exception ex){
            CentralServer.sendDialogue(ex.getMessage());
        }
        String[] ex = new String[3];
        return new Transfer(0, ex, ex);
    }

    public void lock(Object caller){
        if(caller instanceof CentralServer){

        } else {
            CentralServer.sendDialogue("No permission!");
        }
    }

    public void unlock(Object caller){
        if(caller instanceof CentralServer){

        } else {
            CentralServer.sendDialogue("No permission!");
        }

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
    
    public boolean isCorrectPwd(String pwd){
        if(pwd == password){
            return true;
        }
        return false;
    }
    public void setMaxTransfer(int amount, Object caller){
        if(caller instanceof CentralServer){
            maxTransfer = amount;
        }
    }
    
    public boolean isLocked(){
        return isLocked;
    }
}
