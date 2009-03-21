package objectdraw1;
import java.awt.*;
import java.awt.event.*;

public class SelectTool implements Tool {
   
  protected DrawingCanvas canvas;
  protected Point startingMousePosition;
  protected Color saveColor;

  public SelectTool(DrawingCanvas c) {
   canvas = c;
  }
  
  protected void drawErasure(int x, int y, int width, int height) {
    Graphics iBGraphics = canvas.getimageBufferGraphics();
    iBGraphics.fillRect(x, y, width, height);
    canvas.repaint(x, y, width, height);
  }

 public void mousePressed(MouseEvent e)  {
   /* Establish starting point for next drawing */
   startingMousePosition = e.getPoint();
   Graphics iBGraphics = canvas.getimageBufferGraphics();
   saveColor = iBGraphics.getColor( );
   iBGraphics.setColor(Color.white);
 }

 public void mouseDragged(MouseEvent e)  {
   /* erase  */
   Point newMousePosition = e.getPoint();
   int x0 = Math.min(startingMousePosition.x, newMousePosition.x)-2;
   int y0= Math.min(startingMousePosition.y, newMousePosition.y)-2;
   int dx = Math.abs(newMousePosition.x - startingMousePosition.x) + 5;
   int dy = Math.abs(newMousePosition.y - startingMousePosition.y) + 5;
   drawErasure(x0, y0, dx, dy);
   /* update current mouse coordinates */
   startingMousePosition = newMousePosition;
 }


   public void mouseReleased(MouseEvent e) { 
     Graphics iBGraphics = canvas.getimageBufferGraphics();
     /* restore pen color  */
     iBGraphics.setColor(saveColor);
  }
}
