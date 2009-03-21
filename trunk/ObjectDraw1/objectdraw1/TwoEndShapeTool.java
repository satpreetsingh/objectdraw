package objectdraw1;


import java.awt.*;
import java.awt.event.*;

public class TwoEndShapeTool implements Tool {
   
  protected DrawingCanvas canvas;
  protected Point startingMousePosition;
  protected Point currentMousePosition;
  protected Color saveColor;
  protected TwoEndShape shape;
 
  protected LineShapeObj line = new LineShapeObj(); 
  
  public TwoEndShapeTool(DrawingCanvas c, TwoEndShape s) {
   canvas = c;
   shape = s;
  }
  
 public void mousePressed(MouseEvent e)  {
   /* Establish starting point for next drawing */
   startingMousePosition = e.getPoint();
   currentMousePosition = startingMousePosition;
   Graphics iBGraphics = canvas.getimageBufferGraphics();
   saveColor = iBGraphics.getColor( );
   iBGraphics.setXORMode(Color.lightGray);
   iBGraphics.setColor(Color.white);
   shape.drawOutline(iBGraphics,
                     startingMousePosition.x,
                     startingMousePosition.y,
                     startingMousePosition.x,
		     startingMousePosition.y);

   canvas.repaint();
 }

 public void mouseDragged(MouseEvent e)  {
   Point newMousePosition = e.getPoint();
   Graphics iBGraphics = canvas.getimageBufferGraphics();

   /* erase previous temporary figure by redrawing it */

   shape.drawOutline(iBGraphics,
                     startingMousePosition.x,
		     startingMousePosition.y,
                     currentMousePosition.x, 
		     currentMousePosition.y);

   /* draw new temporary figure */

   shape.drawOutline(iBGraphics,
                     startingMousePosition.x,
		     startingMousePosition.y,
                     newMousePosition.x,
		     newMousePosition.y);

   /* update current mouse coordinates */

   currentMousePosition = newMousePosition;

   canvas.repaint();
 }


  public void mouseReleased(MouseEvent e) { 
    Graphics iBGraphics = canvas.getimageBufferGraphics();

    /* Erase final temporary figure  */

    shape.drawOutline(iBGraphics,
                      startingMousePosition.x, 
		      startingMousePosition.y,
		      currentMousePosition.x, 
		      currentMousePosition.y); 
    /* Return graphics context to normal drawing mode and color */

    iBGraphics.setPaintMode();
    iBGraphics.setColor(saveColor);

    /* Draw final"permanent" figure */
    shape.draw(iBGraphics,
               startingMousePosition.x +10 , 
	       startingMousePosition.y,
               e.getPoint().x +10, 
	       e.getPoint().y);
    
    line.setPoints(startingMousePosition, e.getPoint());
    line.drawObj(iBGraphics);
    //iBGraphics.drawLine(startingMousePosition.x + 10, startingMousePosition.y, e.getPoint().x + 10, e.getPoint().y);
    
    canvas.repaint();   

  }
}
