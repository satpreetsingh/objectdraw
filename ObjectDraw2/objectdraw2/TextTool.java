package objectdraw2;


import java.awt.*;
import java.awt.event.*;

public class TextTool implements KeyboardTool {
   
  protected DrawingCanvas canvas;
  protected Point startingPosition;
  protected StringBuffer text;
  protected Font font = new Font("Serif", Font.BOLD, 24);

  public TextTool(DrawingCanvas c) {
   canvas = c;
  }
  
 public void mousePressed(MouseEvent e)  {
   /* Establish Focus and starting position for text display */
   canvas.requestFocus();
   startingPosition = e.getPoint();
   Graphics iBGraphics = canvas.getimageBufferGraphics();
   iBGraphics.setFont(font);
   text = new StringBuffer();
 }

  public void keyPressed(KeyEvent e)  {
   /* add character to string buffer */
   char nextChar = e.getKeyChar(); 
   text.append(nextChar); 
   Graphics iBGraphics = canvas.getimageBufferGraphics();
   iBGraphics.drawString(text.toString(), startingPosition.x,
					  startingPosition.y); 
   canvas.repaint();
  }


   public void mouseReleased(MouseEvent e) { } 
   public void mouseDragged(MouseEvent e) { }
   public void keyReleased(KeyEvent e) { }
   public void keyTyped(KeyEvent e) { }
}
