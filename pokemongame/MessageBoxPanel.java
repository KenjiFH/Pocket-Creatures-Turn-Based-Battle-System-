/*
 * i have a game in java and i want to display a message contained in a rectuanglar messagebox. How would i implement this
 * 
 * 
 * i have a messagbox class that is the same as the first provided answer to my prompt for messageboxes, but instead its width is 900 and its height is 100 and is at 
 *  pos (45,200) on a 1000 x 500 Jframe. i need the messagebox to stay the same size but wrap text when it is about to go over the border, how could i do this?
 * 
 * 
 * messagebox is created and then displayed for displayTimeMillis amount of seconds
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.font.FontRenderContext;
 import java.awt.font.LineBreakMeasurer;
 import java.awt.font.TextAttribute;
 import java.awt.font.TextLayout;
 import java.text.AttributedCharacterIterator;
 import java.text.AttributedString;
 
 public class MessageBoxPanel extends JPanel {
     private String message;
     private boolean isVisable;
     private int padding = 10; // Padding around the text
     private static final int WIDTH = 500;
     private static final int HEIGHT = 100;
     private static final int X_POS = 500;
     private static final int Y_POS = 100;
     private Font gameFont = new Font("Courier",Font.PLAIN,17);
     private Timer timer = null;
 
     public MessageBoxPanel(String message, int displayTimeMillis) {
         this.message = message;
         this.isVisable = true;
         this.setVisible(true);

 
         // Timer to hide the message box after a certain amount of time
         this.timer = new Timer(displayTimeMillis, e -> {
             this.isVisable = false;
             this.setVisible(false);
             timer.stop();
           
         });
         
         timer.start();
     }
 
     @Override
     protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         if (!isVisable) {
             return; // Do not draw anything if the message box is not isVisable
         }
 
         Graphics2D g2d = (Graphics2D) g;
         
         // Enable anti-aliasing for smoother text
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         
         // Define font and color
         Font font = gameFont;
         g2d.setFont(font);
         g2d.setColor(Color.BLACK);
 
         // Create an attributed string with the message
         AttributedString attributedString = new AttributedString(message);
         attributedString.addAttribute(TextAttribute.FONT, font);
         AttributedCharacterIterator iterator = attributedString.getIterator();
 
         // Use LineBreakMeasurer to wrap text
         FontRenderContext frc = g2d.getFontRenderContext();
         LineBreakMeasurer measurer = new LineBreakMeasurer(iterator, frc);
 
         // Define the position of the rectangle
         int rectX = X_POS;
         int rectY = Y_POS;
         int rectWidth = WIDTH;
         int rectHeight = HEIGHT;
 
         // Draw the rectangle
         g2d.setColor(Color.LIGHT_GRAY);
         g2d.fillRect(rectX, rectY, rectWidth, rectHeight);
         g2d.setColor(Color.BLACK);
         g2d.drawRect(rectX, rectY, rectWidth, rectHeight);
 
         // Draw the wrapped text
         int textX = rectX + padding;
         int textY = rectY + padding;
         int maxWidth = rectWidth - 2 * padding;
         
         measurer.setPosition(0);
         while (measurer.getPosition() < iterator.getEndIndex()) {
             TextLayout layout = measurer.nextLayout(maxWidth);
             textY += layout.getAscent();
             layout.draw(g2d, textX, textY);
             textY += layout.getDescent() + layout.getLeading();
 
             // Stop drawing text if it goes outside the rectangle's height
             if (textY > rectY + rectHeight - padding) {
                 break;
             }
         }
     }

     public void makeNewMessage(String message) {
        this.message = message;
        
        this.isVisable = true;
        this.setVisible(true);
       

        // Timer to hide the message box after a certain amount of time
         this.timer = new Timer(2000, e -> {
            this.isVisable = false;
            this.setVisible(false);
            timer.stop();
         
        });
   
        timer.start();
     }
 }
 
