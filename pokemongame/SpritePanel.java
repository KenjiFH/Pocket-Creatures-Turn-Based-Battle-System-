
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * A spritePanel is a subclass of JPanel that contains the sprites of the player,
 * as well as the enemy which it draws at runtime. It also contains the healthbars of 
 * the 2 and the logic/drawing animations for the attacks
 */

public class SpritePanel extends JPanel {
    final int PANEL_WIDTH = 300;
    final int PANEL_HEIGHT = 400;
    protected Image player;
    protected Image enemy;
    protected Image resizedImage;
    protected HealthBar playerHealthBar;
    protected HealthBar enemyHealthBar;
    protected BattleWindow Bwindow;
 
    
  
    //animation components
    protected boolean isAnimating = false;

    private BufferedImage spriteSheet;
    private BufferedImage[] frames;
    private int currentFrame = 0;
    

    private int frameWidth = 64;   // Example width of each frame in the sprite sheet
    private int frameHeight = 64;  // Example height of each frame in the sprite sheet
    private int frameCount = 4;    // Example total number of frames

    private int drawAnimationAtX;
    private int drawAnimationAtY;

    
    private Timer animationTimer;

    



 
    

    SpritePanel(String imagePathPlayer, String imagePathEnemy, BattleWindow Bwindow){
     this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
     this.setSize(200,200);
     this.player = new ImageIcon(getClass().getResource(imagePathPlayer)).getImage();
     this.enemy = new ImageIcon(getClass().getResource(imagePathEnemy)).getImage();
     this.Bwindow = Bwindow;
     this.setLayout(null); // No layout manager
      
     
     String pathToAttackPng = "images/attacks/fire.png";
     this.setNewSpriteSheet(pathToAttackPng);
    
   
    //attackAnimations.setBackground(Color.orange);
   
 
    
 

     //heathbar class is set to player/enemy HP value
     this.playerHealthBar = new HealthBar(Bwindow.battleSystem.playerTeam[0].getHp());



     this.enemyHealthBar = new HealthBar(Bwindow.battleSystem.enemyTeam[0].getHp());

    

     this.playerHealthBar.setBounds(150,100,100,20);


     this.enemyHealthBar.setBounds(990,100,100,20);
     

     this.add(playerHealthBar);
     this.add(enemyHealthBar);
     
     
     this.paintAnimation();
     
     
        

    }
    public void paint(Graphics g){
       
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(player,130,150, null);
        g2D.drawImage(enemy, 1100,70,null);
   
  
    
       if(this.isAnimating){
        
        g.drawImage(frames[currentFrame], drawAnimationAtX, drawAnimationAtY, this);  // Drawing the animation at (100, 100) if isanimating is true
       
       }
      //System.out.println("painting " +  "animiating is " + isAnimating);
        
   
    }
    //resets sprite in SPrite panel to refeclt current mon
    
    public void resetPlayerSprite(String filePath){
        
        this.player = new ImageIcon(getClass().getResource(filePath)).getImage();
        repaint(); // Repaint the panel to show the updated sprite

     
    }
    
    //resets enemey sprite, calls repaint to update
    
    public void resetEnemySprite(String filePath){
        
        this.enemy = new ImageIcon(getClass().getResource(filePath)).getImage();
        repaint(); // Repaint the panel to show the updated sprite

     
    }


        //sets new sprite sheet for new attack sheet
    public void setNewSpriteSheet(String spriteSheetPath){
         // Load the sprite sheet
         try {
            
            spriteSheet = ImageIO.read(getClass().getResource(spriteSheetPath));

            
            frameWidth = spriteSheet.getWidth() / frameCount;
            frameHeight = spriteSheet.getHeight();

             
            
      
      
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        // Extract the individual frames from the sprite sheet
        frames = new BufferedImage[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }
    }






   public void paintAnimation(){
    // Timer to control the animation speed
    
    animationTimer = new Timer(200, new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
        
           currentFrame = (currentFrame + 1) % frameCount;  // Move to the next frame           
     
           repaint();  // Redraw the JPanel to show the next frame
           
       }
   });

   animationTimer.start();  // Start the animation
  // animationTimer.setRepeats(false);
}
   

 
    
     //ADD boolean for player turn or not
    //plays an attack animation, animation varies based on the attack type
    public void playAnimation(String pathToSpriteSheet,int x, int y){
        
        this.drawAnimationAtX = x;
        this.drawAnimationAtY = y;


        setNewSpriteSheet(pathToSpriteSheet);

        
         
        /* 
        this.isAnimating = true;
       
       
       
        animationTimer2 = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        
            
             isAnimating = false;
            
            }
        });
        this.animationTimer2.start();  // Start the animation

         */
 

        
    }


    public void setAnimation(boolean animationStatus){
        this.isAnimating = animationStatus;
    }
   
 
  



}
