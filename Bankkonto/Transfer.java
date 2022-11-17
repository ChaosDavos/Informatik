import  java.time.LocalDate;
/**
 * Beschreiben Sie hier die Klasse Transfer.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Transfer
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    private String[] sender = new String[3];//IBAN; BIC; 1 Name
    private String[] receiver= new String[3];
    private int amount = 0;
    LocalDate date;

    /**
     * Konstruktor für Objekte der Klasse Transfer
     */
    public Transfer(int money, String[] from , String[] to)
    {
        // Instanzvariable initialisieren
        amount = money;
        sender = from;
        receiver = to;
        date = LocalDate.now();
    }
    public String getReceiverIBAN(){
        return receiver[0];
    }
    
    public String getSenderIBAN(){
        return sender[0];
    }
    
    public int getAmount(){
        return amount;
    }
}
