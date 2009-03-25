package objectdraw2;


import java.awt.*;
import java.awt.event.*;

public class TwoEndShapeTool implements Tool {
   
  protected DrawingCanvas canvas;
  protected Point startingMousePosition;
  protected Point currentMousePosition;
  protected Color saveColor;
//  protected TwoEndShape shape;
  
  protected TwoEndShapeObj shapeObj;
  protected LineShapeObj line = new LineShapeObj(); 
  
//  public TwoEndShapeTool(DrawingCanvas c, TwoEndShape s) {
//   canvas = c;
//   shape = s;
//  }
  
  public TwoEndShapeTool(DrawingCanvas c, TwoEndShapeObj so) {
	   canvas = c;
	   shapeObj = so;
	   canvas.objsOnCanvas.add(so);
  }
  
  public void mousePressed(MouseEvent e)  {
   /* Establish starting point for next drawing */
   startingMousePosition = e.getPoint();
   currentMousePosition = startingMousePosition;
   Graphics iBGraphics = canvas.getimageBufferGraphics();
   saveColor = iBGraphics.getColor( );
   iBGraphics.setXORMode(Color.lightGray);
   iBGraphics.setColor(Color.white);

   shapeObj.drawOutline(iBGraphics,
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
   shapeObj.drawOutline(iBGraphics,
                     startingMousePosition.x,
		     startingMousePosition.y,
                     currentMousePosition.x, 
		     currentMousePosition.y);

   /* draw new temporary figure */
   shapeObj.drawOutline(iBGraphics,
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
    shapeObj.drawOutline(iBGraphics,
                      startingMousePosition.x, 
		      startingMousePosition.y,
		      currentMousePosition.x, 
		      currentMousePosition.y);
    
    /* Return graphics context to normal drawing mode and color */
    iBGraphics.setPaintMode();
//    iBGraphics.setColor(saveColor);

    /* Draw final"permanent" figure */

//	    shape.draw(iBGraphics,
//               startingMousePosition.x +10 , 
//	       startingMousePosition.y,
//               e.getPoint().x +10, 
//	       e.getPoint().y);
    
    shapeObj.setColor(saveColor);
    shapeObj.setEndPoints(startingMousePosition, e.getPoint());
    shapeObj.drawObj(iBGraphics);
    //iBGraphics.drawLine(startingMousePosition.x + 10, startingMousePosition.y, e.getPoint().x + 10, e.getPoint().y);
    
    canvas.repaint();   

  }
}
