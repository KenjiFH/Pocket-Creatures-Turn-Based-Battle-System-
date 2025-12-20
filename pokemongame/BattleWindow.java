//https://introcs.cs.princeton.edu/java/15inout/GUI.java.html




//GREEN = MSGPANEL
//MAGENTA = SPRITEPANEL

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * 
 * BattleWindow IS a JFRAME (extends Jframe),
 *
 * 
 *  A battle window is created when a battle starts, it creates a JFRAME gui that allows the user
 * to make choices in a battle, it implements the actionListner Class to listen for user 
 * events, or the user pressing a button. To add an object you first have to make a panel, then add your object the panel,
 *  then add the panel to your frame. and orginze with LayoutManager. Contains logic for fight seqeunces and which screen of each respective panel
 * should display based on user input
 */
public class BattleWindow extends JFrame{
  
    protected JFrame frame = new JFrame();

    
   
   
  
    private JPanel buttonPanel = new JPanel(new GridLayout(2,2,5,2));
    private Font gameFont = new Font("Courier",Font.PLAIN,25);
    protected SpritePanel Spanel;
    protected BattleSystem battleSystem;
    private String enemySpritePath;
    private String playerSpritePath;
    protected MessageBoxPanel messageBoxPanel;

    protected int currentPlayerIndex = 0;
    protected int currentEnemyIndex = 0;


    //buttons for attackPanel
    protected JButton abutton1 = new JButton("fight 1"); 
    protected JButton abutton2 = new JButton("fight 2");
    protected JButton abutton3 = new JButton("fight 3");
    protected JButton abutton4 = new JButton("fight 4");


    //buttons for switchPanel

    // Create buttons with unique actions
     protected JButton sbutton1 = new JButton("Action 1"); 
     protected JButton sbutton2 = new JButton("Action 2");
     protected JButton sbutton3 = new JButton("Action 3");
     protected JButton sbutton4 = new JButton("Action 4");
     protected JButton sbutton5 = new JButton("Action 5");


    //mainPAnel uses cardlayout to switch between screens add desired screens to mainpanel to switch, just make 
    //sure to use a back button
    private JPanel mainPanel;
    private CardLayout cardLayout;


    //boolean to make sure player doesnt move out of turn TODO: change to factor speed var in
    protected boolean inTurn = true;
    
    
    
    
   
 

    
    //creates battlewindow and assigns sprite paths of player and opponent 
    
    public BattleWindow(BattleSystem battleSystem){

        // Initialize the CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

    this.battleSystem = battleSystem;

     this.enemySpritePath = battleSystem.enemyTeam[0].getFilePath();
     this.playerSpritePath = battleSystem.playerTeam[0].getFilePath();

    this.Spanel = new SpritePanel(playerSpritePath, enemySpritePath,this);


    this.Spanel.setBackground(Color.CYAN);
  
    this.battleSystem = battleSystem;
    
    //frame.getContentPane().setBackground(Color.green);
    //panel styling
    buttonPanel.setPreferredSize(new Dimension(300, 200));
    buttonPanel.setBackground(Color.black);

    mainPanel.setPreferredSize(new Dimension(300, 200));
   
    //when you make a new mssgbox it displays this mssg and for displaytimeMs amount of sec
    String message = "Battle start: ";

   

    this.messageBoxPanel = new MessageBoxPanel(message, 2000);
    
    this.messageBoxPanel.setBackground(Color.GREEN);

         
       // creates grid panel for battle options
       JPanel gridPanelFight = createGridPanelAttack("Screen 2", "Screen 1");
       gridPanelFight.setBackground(Color.ORANGE);
      
       
   

       //creates gridPanel for switching options
       JPanel gridPanelSwitch = createGridPanelSwitch("Screen 3", "Screen 1");
       gridPanelSwitch.setBackground(Color.CYAN);
    

       //creates GridPanel for item usage
       JPanel gridPanelBag = createGridPanelBag("Screen 4", "Screen 1");
       gridPanelBag.setBackground(Color.PINK);
       
       
       //GPT: how can i create a scrolling menu that scrolls down and has buttons as options to press in a JFrame? 
       //wraps frame in a scrollpane
       JScrollPane scrollPaneBag = new JScrollPane(gridPanelBag);


   
   
    //Buttons in panel for player choices
    JButton attackButton = new JButton("fight");
    JButton bagButton = new JButton("bag");
    JButton switchButton = new JButton("switch");
    JButton runButton = new JButton("run");

    
    //styling
    
    attackButton.setFont(gameFont);
    bagButton.setFont(gameFont);
    switchButton.setFont(gameFont);
    runButton.setFont(gameFont);

   
    //sets fight option to current mon moveset
    abutton1.setText(battleSystem.playerTeam[currentPlayerIndex].moveset[0].getName());
    abutton2.setText(battleSystem.playerTeam[currentPlayerIndex].moveset[1].getName());
    abutton3.setText(battleSystem.playerTeam[currentPlayerIndex].moveset[2].getName());
    abutton4.setText(battleSystem.playerTeam[currentPlayerIndex].moveset[3].getName());
    

 

    //Action listners for buttons
    attackButton.addActionListener(new AttackButtonListener());
    bagButton.addActionListener(new BagButtonListener(this.messageBoxPanel));
    switchButton.addActionListener(new SwitchButtonListener());
    runButton.addActionListener(new RunButtonListener(frame));
   
    
  

        
    /*A JPanel acts like a container for elements or objects, and in this case these objects 
    are the buttons that are added to a grid in the container 
    */
    buttonPanel.add(attackButton);
    buttonPanel.add(bagButton);
    buttonPanel.add(switchButton);
    buttonPanel.add(runButton);
            
    
          // Add action listeners to buttons to switch screens
            attackButton.addActionListener(e -> cardLayout.show(mainPanel, "Screen 2"));
            switchButton.addActionListener(e -> cardLayout.show(mainPanel, "Screen 3"));
            bagButton.addActionListener(e -> cardLayout.show(mainPanel, "Screen 4"));
          

   
     
    Spanel.setBounds(50, 50, 500, 350);
    Spanel.setBackground(Color.MAGENTA);
    frame.add(Spanel,BorderLayout.NORTH);
    //adds panels to frame and aligns formatting
 
    frame.add(mainPanel, BorderLayout.SOUTH);
    
    buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    
        


       

        // Add the grid panels to the main panel
        mainPanel.add(buttonPanel, "Screen 1");
        mainPanel.add(gridPanelFight, "Screen 2");
        mainPanel.add(gridPanelSwitch, "Screen 3");
        mainPanel.add(scrollPaneBag,"Screen 4");
 







    // Add the message box panel to the center of the frame
    frame.add(this.messageBoxPanel,BorderLayout.CENTER);
   
   
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Pokemon Game");
    frame.setSize(1500,1000);
   
   
    
    
    
   //frame.setVisible(true);
        
    
    
    }



    
    //Seperate classes for Each Button Listner

     class AttackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("attack pressed");
            
        
            
        }
    }
    class BagButtonListener implements ActionListener {
        protected MessageBoxPanel messageBoxPanel;
        public BagButtonListener(MessageBoxPanel messageBoxPanel2){
            this.messageBoxPanel = messageBoxPanel2;
            
           
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            
           
            System.out.println("Bag pressed");
            
          
        }
    }
    class RunButtonListener implements ActionListener {
       JFrame frame;
       public RunButtonListener(JFrame frame){
       this.frame = frame;
       }
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("run performed");
            endBattle();
            
        }
    }
    class SwitchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("switch pressed");
        }
    }



    //attack panel
    
    //creates a panel with buttons that perform a certain action
    //as well as a back button to return to the previous screen
    protected JPanel createGridPanelAttack(String currentScreen, String nextScreen){
        // GPT : Create a panel with GridBagLayout to arrange buttons and back button

        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

       
        // Create buttons with unique actions
      

        abutton1.addActionListener(e -> performActionFight("Attk 1 executed",0));
        abutton2.addActionListener(e -> performActionFight("Attk 2 executed",1));
        abutton3.addActionListener(e -> performActionFight("Attk 3 executed",2));
        abutton4.addActionListener(e -> performActionFight("Attk 4 executed",3));

        // Add buttons to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(abutton1, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(abutton2, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(abutton3, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(abutton4, gbc);

        // Create a small "Back" button and add it to the panel
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, nextScreen));

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        panel.add(backButton, gbc);

        return panel;
    }

    
    protected JPanel createGridPanelSwitch(String currentScreen, String nextScreen){
        //PROMPT
        // Create a panel with GridBagLayout to arrange buttons, including a back button that takes you back to the previous panel screen

        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
       
       sbutton5.setText(battleSystem.playerTeam[0].getName());
       sbutton1.setText(battleSystem.playerTeam[1].getName());
       sbutton2.setText(battleSystem.playerTeam[2].getName());
       sbutton3.setText(battleSystem.playerTeam[3].getName());
       sbutton4.setText(battleSystem.playerTeam[4].getName());
       

       
        sbutton5.addActionListener(e -> performSwitchAction("switched to" + battleSystem.playerTeam[0].getName(),0));
        sbutton1.addActionListener(e -> performSwitchAction("switched to" + battleSystem.playerTeam[1].getName(),1));
        sbutton2.addActionListener(e -> performSwitchAction("switched to" + battleSystem.playerTeam[2].getName(),2));
        sbutton3.addActionListener(e -> performSwitchAction("switched to" + battleSystem.playerTeam[3].getName(),3));
        sbutton4.addActionListener(e -> performSwitchAction("switched to" + battleSystem.playerTeam[4].getName(),4));
       

        // Add buttons to the panel
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(sbutton1, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(sbutton2, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(sbutton3, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(sbutton4, gbc);

        // Add the new button (sbutton5)
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(sbutton5, gbc);

        // Create a small "Back" button and add it to the panel
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, nextScreen));

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.SOUTH;
        panel.add(backButton, gbc);

        return panel;
    }
    


    
    
    
    //methof to make gridPanel for items
    //GPT: how can i create a scrolling menu that scrolls down and has buttons as options to press in a JFrame? 


    protected JPanel createGridPanelBag(String currentScreen, String nextScreen){
        //PROMPT
        // Create a panel with GridBagLayout to arrange buttons, including a back button that takes you back to the previous panel screen

        


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
       
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical alignment
     
        // Add buttons to the panel
        /* 
        for (int i = 1; i <= 10; i++) {  // Add 20 buttons as an example
            JButton button = new JButton("Option " + i);
            panel.add(button);
        }
            */

            //adds the item and the quantity of the item to the inventory
            for (ItemBase key : battleSystem.inventoryMap.keySet()) {
                JButton itemButton = new JButton(key.toString() + " x " + battleSystem.inventoryMap.get(key));
                
                itemButton.addActionListener(e -> useItem(key,itemButton));
        
                panel.add(itemButton);
            }
        
   
      

        

        // Create a small "Back" button and add it to the panel
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 10));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, nextScreen));

       
        panel.add(backButton, gbc);

        return panel;
    }

   
    //TODO make items take a turn and deplete
    public void useItem(ItemBase item, JButton itemButton){
        
        //first check to maken sure youre not out of turn
        if(!this.inTurn){
            cardLayout.show(mainPanel, "Screen 1");
            return;
        }
        

        //second check to make sure you cant use the item if you are out
        if(battleSystem.inventoryMap.get(item) == 0){
            itemButton.setForeground(Color.red);
            cardLayout.show(mainPanel, "Screen 1");
            return;
        }
        
      System.out.println(item +  " used");

      Pokemon currentMon = (battleSystem.playerTeam[currentPlayerIndex]);

      item.useItem(currentMon);

      System.out.println(currentMon);

      Spanel.playerHealthBar.setHealthBarNS(currentMon.getHp());
      
      //uses one item
      battleSystem.inventoryMap.put(item, battleSystem.inventoryMap.get(item) - 1);


      itemButton.setText(item.toString() + " x " + battleSystem.inventoryMap.get(item));

      if(battleSystem.inventoryMap.get(item) == 0){
        itemButton.setForeground(Color.red);
      }

      messageBoxPanel.makeNewMessage(item.toString() +   " used");
      
      cardLayout.show(mainPanel, "Screen 1");
      
  
      //check for sysout
      System.out.println(battleSystem.inventoryMap);
      
      //enemy turn after you
      enemyAttacks();

    }






    
  
    //ATTACK func passes in a message and thee index of the moveset of the move that is being used
    private void performActionFight(String message, int moveSetIndex) {


        

        

        if(!this.inTurn){
            cardLayout.show(mainPanel, "Screen 1");
            return;
        }
        //player cant attack if their current mon is down
        if(battleSystem.playerTeam[currentPlayerIndex].getHp() <= 0){
            return;
        }
        
        
       this.inTurn = false;
       // System.out.println(message);
       
      
        
       
       
       
        this.messageBoxPanel.makeNewMessage(battleSystem.playerTeam[currentPlayerIndex].getName() + " uses " + battleSystem.playerTeam[currentPlayerIndex].moveset[moveSetIndex].getName());  //include stats on damage done
        
         
        //PLAYS ANIMATION
        Spanel.playAnimation(battleSystem.playerTeam[currentPlayerIndex].moveset[moveSetIndex].getAnimationPath(),900,0);
        Spanel.setAnimation(true);
        Timer animtimer = new Timer(2000, new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent e) {
              Spanel.setAnimation(false);
               
            }
        });
        
        animtimer.setRepeats(false); // Execute only once
        animtimer.start(); // Start the timer

        
       
       
       //amount of dmg is calculated bu adding player atk value to the power of the move and then subtracting it from the enemy HP
        int dmgToEnemy = battleSystem.playerTeam[currentPlayerIndex].getAtk() + battleSystem.playerTeam[currentPlayerIndex].moveset[moveSetIndex].getPower();

         
        
       
        
        
        
         Spanel.enemyHealthBar.decreaseHealth(dmgToEnemy); 
        
        
        battleSystem.enemyTeam[currentEnemyIndex].setHp(battleSystem.enemyTeam[currentEnemyIndex].getHp()-dmgToEnemy);

        //change to screen 2 for switch 
        cardLayout.show(mainPanel, "Screen 1");
        



        if(battleSystem.enemyTeam[currentEnemyIndex].getHp() <= 0){
        
         
         enemySwitch();
         return;
        }
        if(!this.inTurn){
            enemyAttacks();
        }
       
       
       
        


        
        
        
        /*  TIMER
        Timer timer = new Timer(3000, new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent e) {
               
               
            }
        });
        
        timer.setRepeats(false); // Execute only once
        timer.start(); // Start the timer


       //send message to main for enemy to attack player
       Timer timer1 = new Timer(6000, new ActionListener() {
            

        @Override
        public void actionPerformed(ActionEvent e) {
           
           battleSystem.updateGame(); 
           messageBoxPanel.makeNewMessage("player turn: Turn number: " + battleSystem.turn);
           
           inTurn = true;
          
           
        }
    });
    
    timer1.setRepeats(false); // Execute only once
    timer1.start(); // Start the timer

       */

       
        
       
         
    }
   
    public void performSwitchAction(String message, int swappedIndex){
     
      

       Pokemon lastMon = new Pokemon(battleSystem.playerTeam[currentPlayerIndex]);

        System.out.println("last mon is " + lastMon);
        //first check cannot perform if playerturn is not true (cannot swap out of turn)
        if(!this.inTurn){
            cardLayout.show(mainPanel, "Screen 1");
            return;
        }
      currentPlayerIndex = swappedIndex;

      //second check is to make sure that you cant switch to the same mon that youre currently on (this will use a turn and you dont want that)
     
      


      //CC to avoid shallow copies (the HP WILL CARRY OVER SWITCHES)
      
      Pokemon currentMon = new Pokemon(battleSystem.playerTeam[swappedIndex]);

      //changes attack buttons to refect moveds of mon you just changed to
       
      abutton1.setText(currentMon.moveset[0].getName());
      abutton2.setText(currentMon.moveset[1].getName());
      abutton3.setText(currentMon.moveset[2].getName());
      abutton4.setText(currentMon.moveset[3].getName());
      //

      //IF mon is fainted you cant switch
      if(currentMon.getFntStatus()){
        return;
      }
      //if mon is same as last mon, you cannot switch(cant switch into same mon as last switch)
      if(currentMon.getName().equals(lastMon.getName())){
        cardLayout.show(mainPanel, "Screen 1");
        return;
      }

      
      System.out.println("current mon is :  " + currentMon);

      messageBoxPanel.makeNewMessage("switched to " + currentMon.getName());
      
      Spanel.resetPlayerSprite(battleSystem.playerTeam[swappedIndex].getFilePath());

      Spanel.playerHealthBar.setHealthBar(currentMon.getHp(),currentMon.getMaxHP());
      
      
      cardLayout.show(mainPanel, "Screen 1");
     
      enemyAttacks();

      System.out.println("switch performed");
    


    }

  
    
    //enemy only switches when its current pokemons HP = 0; 
    protected void enemySwitch(){
        this.inTurn = false;
        
        currentEnemyIndex++;
        System.out.println("current index is " + currentEnemyIndex);
      
        if(currentEnemyIndex >= 5){
        currentEnemyIndex = 4;


        endBattle();
        //end game
       // return;
       } 

       Timer Etimer = new Timer(5500, new ActionListener() {
            

        @Override
        public void actionPerformed(ActionEvent e) {
           
            int swappedIndex = currentEnemyIndex;
            Pokemon currentEnemy = new Pokemon(battleSystem.enemyTeam[swappedIndex]);
            System.out.println("current enemy is :  " + currentEnemy);
     
            messageBoxPanel.makeNewMessage("enemy switched to " + currentEnemy.getName());
     
            Spanel.resetEnemySprite(battleSystem.enemyTeam[swappedIndex].getFilePath());
     
            Spanel.enemyHealthBar.setHealthBar(currentEnemy.getHp(),currentEnemy.getMaxHP());
            
            battleSystem.turn++;

            Timer timer = new Timer(3000, new ActionListener() {
            

                @Override
                public void actionPerformed(ActionEvent e) {
                   
                    messageBoxPanel.makeNewMessage("player turn: Turn number: " + battleSystem.turn);
                    inTurn = true;
                }
            });
            timer.setRepeats(false); // Execute only once
            timer.start(); // Start the timer
           
           
        }
    });
    
    Etimer.setRepeats(false); // Execute only once
    Etimer.start(); // Start the timer
      
  
  
      

    
      
      
      
       

    }


  //method for enemy to do damage based on their damage stat 
    protected void enemyAttacks(){

        this.inTurn = false;

      


        
        
        
        
        Timer timer = new Timer(3000, new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent e) {

                Random rand = new Random();
                //bound 0-3
                int randomEnemyAttackIndex = rand.nextInt(4);
               
               messageBoxPanel.makeNewMessage("enemy " + battleSystem.enemyTeam[currentEnemyIndex].getName() + " uses " + (battleSystem.enemyTeam[currentEnemyIndex].moveset[randomEnemyAttackIndex].getName()));
               
               battleSystem.playerTeam[currentPlayerIndex].setHp((battleSystem.playerTeam[currentPlayerIndex].getHp()-battleSystem.enemyTeam[currentEnemyIndex].getAtk()));
               
               Spanel.playerHealthBar.setHealthBarNS(battleSystem.playerTeam[currentPlayerIndex].getHp());     //setHpBarPlayer(battleSystem.playerTeam[currentPlayerIndex].getHp());
               Spanel.setAnimation(true);
               
               //PLAYS ANIMATION
               Timer animTImer = new Timer(2000, new ActionListener() {
                   
       
                   @Override
                   public void actionPerformed(ActionEvent e) {
                     Spanel.setAnimation(false);
                      
                   }
               });
              
               animTImer.setRepeats(false); // Execute only once
               animTImer.start(); // Start the timer
               
               
               
               //ADDED animation
               Spanel.playAnimation(battleSystem.enemyTeam[currentEnemyIndex].moveset[randomEnemyAttackIndex].getAnimationPath(),100,100);
               
               //if player HP is zero set fnt status to true
               if(battleSystem.playerTeam[currentPlayerIndex].getHp() <= 0){
                battleSystem.playerTeam[currentPlayerIndex].setFntStatus(true);
               }
              

               //checks team status after attack
               checkTeamFntStatus();
            }
        });
        
        timer.setRepeats(false); // Execute only once
        timer.start(); // Start the timer


       //send message to main for enemy to attack player
       Timer timer1 = new Timer(6000, new ActionListener() {
            

        @Override
        public void actionPerformed(ActionEvent e) {
           
           

           battleSystem.updateGame(); 
           messageBoxPanel.makeNewMessage("player turn: Turn number: " + battleSystem.turn);
           
           inTurn = true;
          
           
        }
    });
    
    timer1.setRepeats(false); // Execute only once
    timer1.start(); // Start the timer
    }

     //exits system and disposes frame, ending battle
    public void endBattle(){
        System.out.println("game over");
        this.frame.dispose();
        System.exit(0);
    }



    //checks player team HP, if all players have zero or less HP then the game ends and you lose
    //also updates buttons to check if the mon they switch into has fainted
    public void checkTeamFntStatus(){

        
        //sbutton 5 = 0
        //if KO player count = 0, or all players are KO, then the game ends
        int KOplayerCount = 0;
        
        for (int index = 0; index < battleSystem.playerTeam.length; index++) {
            if((battleSystem.playerTeam[index].getHp()) <= 0){

                
                KOplayerCount++;

                switch(index){
                   case 0:
                    sbutton5.setForeground(Color.RED);
                    
                    break;
                   case 1:
                    sbutton1.setForeground(Color.RED);
                
                    break;
                   case 2:
                    sbutton2.setForeground(Color.RED);
                 
                    break;
                   case 3:
                    sbutton3.setForeground(Color.RED);
         
                    break;
                   case 4:
                    sbutton4.setForeground(Color.RED);
                   
                    break;
                }
                

            } 
        }

        if(KOplayerCount == 5){
            endBattle();
        } 
        return;
    }

    
  

  
  
    

}

