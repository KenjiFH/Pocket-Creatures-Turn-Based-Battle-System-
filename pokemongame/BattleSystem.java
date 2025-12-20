

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




/**
 * 
 * USed to run the combat system in the JFrame A pokemonBattle class is created when a battle between a player and the opponent is initiated. A battle is fought in turns, and who goes
 * first is decided by who has the greater SPE stat. as it is running, the player is prompted with decsions to make when it is their turn. Battle system should have control over the battlewindow. 
 * used to pass objects to control the game into the window, which contains the logic, playerTeam and enemyTeam can be customized so you can add you own monsters, you can also customize their moves
 * 
 * 
 * 
 * 
 */
public class BattleSystem {
    
   
    //a team contains 5 pokemon
    protected Pokemon [] playerTeam;
    protected Pokemon [] enemyTeam;

    //inventory
    //V = quantity K = item
    protected  Map<ItemBase, Integer> inventoryMap = new HashMap<>();



    protected int turn = 1;
    protected boolean playerTurn = true;
    
   
    //a moveset contains all the moves that a pokemon can use during a battle, you can add any move you want.
    //and then use the moveset as a paramater for the moves you want a pokemon to be able to use
    protected Move [] moveset;
    
    
    public BattleSystem(Pokemon [] enemy, Pokemon [] player, Map<ItemBase, Integer> inventoryMap){
     this.playerTeam = player;
     this.enemyTeam = enemy;
     this.inventoryMap = inventoryMap;
    }

   

    
    public void startBattle(BattleWindow b){
      
     
      
      b.frame.setVisible(true);
      System.out.println("{battle running}");
      

    }

    
    public void endBattle(BattleWindow b){
      
        b.frame.setVisible(false);
        System.exit(0);
        System.out.println("{battle no longer running}");
    }



    /**
     * updates the turn counter and displays status of player and opponent 
     */
     public void updateGame(){
    
        System.out.println("turn: " + this.turn);
      
        
        
        this.turn++;

    
    }

   

    
     
   



    

    


    public static void main(String[] args) {

        //each mon has a set of moves it can use that get put in an array of the corresponding type

        //normal
        Move tackle = new Move(20, "normal", "tackle");
        Move slash = new Move(35,"normal", "tackle");
        Move cut = new Move(45, "normal", "cut");
        Move hyperBeam = new Move(150, "normal", "hyperBeam");

        Move [] normalMoves = {tackle,slash,cut,hyperBeam};


        //water
        Move aquaJet = new Move (40,"water", "aquaJet");
        Move bubbleBeam = new Move(60, "water", "bubbleBeam");
        Move waterCannon = new Move(70, "water" , "waterCannon");
        Move maxGyser =new Move(200, "water", "maxGyser");

        Move [] waterMoves = {aquaJet,bubbleBeam,waterCannon,maxGyser};


        //fire
       
        Move flameTackle = new Move(40, "fire" , "flameTackle");
        Move flameThrower = new Move(60,"fire", "flameThrower");
        Move flameOut = new Move(70, "fire", "flameOut");
        Move maxEruption = new Move(200, "fire", "maxEruption");
       
       
        Move [] fireMoves = {flameTackle,flameThrower,flameOut,maxEruption};

        //grass
        Move leafBlade = new Move(40, "grass", "leafBlade");
        Move overGrow = new Move(60, "grass", "overGrow");
        Move terraBlast = new Move(70, "grass", "terraBlast");
        Move maxGrowth = new Move(200, "grass", "maxGrowth");


        Move [] grassMoves = {leafBlade,overGrow,terraBlast,maxGrowth};

        //add your own moves
       // Move [] customMoves = new Move[4];
        

        //INVENTORY
        Map<ItemBase, Integer> inventoryMap = new HashMap<>();
        AtkUp atkUpPotion = new AtkUp("atkUp",50);
        Potion hpPotion = new Potion("potion", 30);

        inventoryMap.put(atkUpPotion, 3);  //adds 3 atkUp items to inventory
        inventoryMap.put(hpPotion, 3);

       

        

        
        try {
            
            //player team
            Pokemon p1 =  new Pokemon(100, 20,20, "Sparchu", "fire","images/player/sback.png",fireMoves);
            Pokemon p2 =  new Pokemon(100, 20,20, "Atrox", "normal","images/player/Atrox.png",normalMoves);
            Pokemon p3 =  new Pokemon(200, 20,20, "Cleaf", "grass","images/player/Cleaf.png",grassMoves);
            Pokemon p4 =  new Pokemon(200, 20,20, "Finiette", "water","images/player/Finiette.png",waterMoves);
            Pokemon p5 =  new Pokemon(200, 20,20, "Pouch", "grass","images/player/Pouch.png",grassMoves);

            //enemy team
            Pokemon e1 = new Pokemon(400, 21, 20, "Sparchu", "fire","images/enemy/sfront.png",fireMoves);
            Pokemon e2 = new Pokemon(300, 21, 20, "Atrox", "normal","images/enemy/Atrox (1).png",normalMoves);
            Pokemon e3 = new Pokemon(200, 21, 20, "Friolera", "water","images/enemy/Friolera (1).png",waterMoves);
            Pokemon e4 = new Pokemon(200, 21, 20, "Finiette", "water","images/enemy/Finiette (1).png",waterMoves);
            Pokemon e5 = new Pokemon(200, 21, 20, "Ivieron", "grass","images/enemy/Ivieron (1).png",grassMoves);

           
            Pokemon [] playerTeam = {p1,p2,p3,p4,p5};
            
            Pokemon [] enemyTeam = {e1,e2,e3,e4,e5};


            //arrays of these teams gets passes into battlesystem
            BattleSystem bsMain = new BattleSystem(enemyTeam, playerTeam,inventoryMap);
            
            
            BattleWindow bWindowMain = new BattleWindow(bsMain);
       
       
       
            //bsMain.updateGame();
            bsMain.startBattle(bWindowMain);
            

            
          
          
             

            

            
            
           


        } catch (IOException e) {
            System.out.println("error image path not found");
        }
    


       
     
        
    
        
      
     
     
         
    
  

        
    }

}
