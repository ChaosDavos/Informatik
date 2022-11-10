
/**
 * Write a description of class Gamemanager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Gamemanager
{
    // instance variables - replace the example below with your own
    public Spieler17u4[] player;

    /**
     * Constructor for objects of class Gamemanager
     */
    public Gamemanager(int count)
    {
        init(count);
        System.out.println("---------------------------------------");
        // initialise instance variables
    }

    
    public void init(int count){
        player = new Spieler17u4[count];
        for(int i = 0; i < player.length; i++){
            player[i] = new Spieler17u4();
        }
    }
    
    public void playround(){
        for(int i = 0; i< player.length ; i++){
            player[i].spieleRunde();
            System.out.println(player[i].getStand());
        }
        System.out.println("---");
    }
}
