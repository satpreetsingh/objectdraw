package objectdraw1;
import java.awt.*;
import java.awt.event.*;

public class EraserTool implements Tool {
   
  protected DrawingCanvas canvas;
  protected Point startingMousePosition;
  protected Color saveColor;
  
  public EraserTool(DrawingCanvas c) {
   System.out.println("Constructor of EraserTool");  
   canvas = c;
  }  

 public String toString() {
	 return "EraserTool";
 }
  
 public void mousePressed(MouseEvent e)  {

	 if (canvas.isAnObjectHighlighted == true) {
		  System.out.println("Deleting highlighted object");
		  canvas.objsOnCanvas.remove(canvas.highlightedObj); 
		  canvas.highlightedObj = null; 
		  canvas.isAnObjectHighlighted = false;
		  canvas.clearCanvas();
		  canvas.redrawObjs();
	 }
	 
	 for (TwoEndShapeObj obj : canvas.objsOnCanvas) {			  
		  if (obj.isPointInObject(e.getPoint()) == true) {
			  canvas.objsOnCanvas.remove(obj);				 			  
			  canvas.clearCanvas();
			  canvas.redrawObjs();			  
			  break;
		  }
	  }

	 
	 /* Establish starting point for next drawing */
   startingMousePosition = e.getPoint();
//   Graphics iBGraphics = canvas.getimageBufferGraphics();
//   saveColor = iBGraphics.getColor( );
//   iBGraphics.setColor(Color.white);
 }

 public void mouseDragged(MouseEvent e)  {
   /* erase  */
//   Point newMousePosition = e.getPoint();
//   int x0 = Math.min(startingMousePosition.x, newMousePosition.x)-2;
//   int y0= Math.min(startingMousePosition.y, newMousePosition.y)-2;
//   int dx = Math.abs(newMousePosition.x - startingMousePosition.x) + 5;
//   int dy = Math.abs(newMousePosition.y - startingMousePosition.y) + 5;
//   drawErasure(x0, y0, dx, dy);
//   /* update current mouse coordinates */
//   startingMousePosition = newMousePosition;
 }


   public void mouseReleased(MouseEvent e) { 
//     Graphics iBGraphics = canvas.getimageBufferGraphics();
//     /* restore pen color  */
//     iBGraphics.setColor(saveColor);
  }
}
