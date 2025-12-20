

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class HealthBar extends JPanel {
    private JProgressBar progressBar;
    private int maxHealth;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        progressBar = new JProgressBar(0, maxHealth);
        progressBar.setValue(maxHealth);
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        progressBar.setBackground(Color.BLACK);
        progressBar.setUI(new CustomProgressBarUI());
        progressBar.setBounds(0, 0, 50, 50);
        
        setLayout(new BorderLayout());
        add(progressBar, BorderLayout.CENTER);
    }
    

    public void decreaseHealth(int amount) {
        int newHealth = Math.max(progressBar.getValue() - amount, 0);
        progressBar.setValue(newHealth);
    }

    public void increaseHealth(int amount) {
        int newHealth = Math.min(progressBar.getValue() + amount, maxHealth);
        progressBar.setValue(newHealth);
    }
   
     
    //has to change to be out of MAX of CUrrent mon
    public void setHealthBar(int currentHP,int MaxHP){
        //System.out.println(MaxHP);
        //System.out.println(currentHP);
        progressBar.setMinimum(0);
        
        progressBar.setMaximum(MaxHP);
        progressBar.setValue(currentHP);
    }
    //sets heath bar when no switch happens 
    public void setHealthBarNS(int currentHP){
        progressBar.setValue(currentHP);
    }
        

    private static class CustomProgressBarUI extends BasicProgressBarUI {
        @Override
        protected Color getSelectionBackground() {
            return Color.BLACK;
        }

        @Override
        protected Color getSelectionForeground() {
            return Color.BLACK;
        }

        @Override
        protected void paintDeterminate(Graphics g, JComponent c) {
            super.paintDeterminate(g, c);
            // Custom drawing for the progress bar can be added here
        }
    }

    
    
          /* how to use
    
            public static void main(String[] args) {
        
            JFrame frame = new JFrame("Custom Health Bar Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 500);

            JPanel panel = new JPanel();
            panel.setSize(100,80);
            panel.setBackground(Color.GREEN);
           

            HealthBar healthBar = new HealthBar(100);
            healthBar.setBounds(0,200,200,200);
            panel.add(healthBar, BorderLayout.SOUTH);

            frame.add(panel,BorderLayout.NORTH);

           

      

            frame.setVisible(true);
      
    } */
            
}
