import java.util.Dictionary;
import java.util.HashMap;
import javax.swing.Spring;

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
    
    public void createAccount( String[] people, int money, int disp){
        Konto acc = new Konto( people, money, disp, this, generateIBAN(), getBIC());
    }
    public Konto getAccount(String IBAN){
        return IBANtoAccount.get(IBAN);
    }
    
    public int transfer(String myIBAN, String IBAN, int amount){
        Konto acc = IBANtoAccount.get(myIBAN);
        String[] to = new String[3];
        to[0] = IBAN;
        if(IBAN != myIBAN){
            to[1] = "";
            to[2] = "";
            Transfer transfer = acc.createTransfer(amount, to);
        }
        return 0;
    }
    private String generateIBAN(){
        folgeNr++;
        int zwiNr = 23456789 + folgeNr;    //<-- 5a.
        return "31"+zwiNr;
    }

    private String getBIC(){
        return "HELADEFXX";
    }

}
