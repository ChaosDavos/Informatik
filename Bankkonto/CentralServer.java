import java.util.Dictionary;
import java.util.HashMap;
import javax.swing.Spring;
import javax.swing.JOptionPane;

/**
 * Beschreiben Sie hier die Klasse CentralServer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class CentralServer
{
    private int folgeNr = 2021;
    private HashMap<String, Konto> IBANtoAccount = new HashMap<String, Konto>();
    /**
     * Konstruktor für Objekte der Klasse CentralServer
     */
    public CentralServer()
    {
    }
    
    public void createAccount( String owner, int money, int disp){
        if(money >= 0){
            String acc_IBAN = generateIBAN();
            Konto acc = new Konto( owner, money, disp, this, acc_IBAN, getBIC());
            IBANtoAccount.put(acc_IBAN, acc);
            CentralServer.sendDialogue("Deine IBAN ist: " + acc_IBAN);
        }
    }
    private Konto getAccount(String IBAN){
        return IBANtoAccount.get(IBAN);
    }
    
    public void transfer(String myIBAN, String IBAN, int amount){
        Konto acc = IBANtoAccount.get(myIBAN);
        if(acc == null){
            CentralServer.sendDialogue("Sender IBAN invalid");
        }
        String[] to = new String[3];
        to[0] = IBAN;
        if(IBANtoAccount.containsKey(IBAN) ){
            if(IBAN != myIBAN){
            to[1] = "";
            to[2] = "";
            if(amount > 0){
                Transfer transfer = acc.createTransfer(amount, to);
                IBANtoAccount.get(transfer.getReceiverIBAN()).deposit(transfer.getAmount());
            }
            
        }
            
        }
        CentralServer.sendDialogue("Invalid operation");
    }
    
    public void getMoneyByIBAN(String IBAN){
        if(IBANtoAccount.containsKey(IBAN)){
            CentralServer.sendDialogue("Guthaben: " + (Integer.toString(IBANtoAccount.get(IBAN).getBalance())) + "€");
        } else {
            CentralServer.sendDialogue("IBAN not found!");
        }
    }
    
    public static void sendDialogue(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }
    private String generateIBAN(){
        folgeNr++;
        int zwiNr = 23456789 + folgeNr;    //<-- 5a.
        return "DE"+zwiNr;
    }

    private String getBIC(){
        return "HELADEFXX";
    }
    
    public void createTestAcc(){
        createAccount("Hans Peter", 10000, 500);
    }

}
