package objectdraw1;
import java.awt.*;
import java.awt.event.*;

public class SelectTool implements Tool {
   
  protected DrawingCanvas canvas;
  protected Point startingMousePosition;
  protected Color saveColor;
  
  protected TwoEndShapeObj obj = null; 
  protected TwoEndShapeObj highlightedObj = null; 

  public SelectTool(DrawingCanvas c) {
   canvas = c;
  }
  
 public void mousePressed(MouseEvent e)  {

	 // Highlight OR Un-highlight on button-press
	 int i;
	 
	 for (i = canvas.objsOnCanvas.size()-1 ; i >= 0 ; i-- ) {
//	 for (TwoEndShapeObj obj : canvas.objsOnCanvas) {	

		 obj = canvas.objsOnCanvas.get(i);
		 
		 // TODO: reverse the order here
		 System.out.println("From for loop in SelectTool: " + canvas.objsOnCanvas.size());
			 
	 	if (obj.isPointInObject(e.getPoint()) == true) {
//			  canvas.objsOnCanvas.remove(obj);

			  canvas.clearCanvas();
			  canvas.redrawObjs();	
			  
			  if (highlightedObj == obj) {
				  highlightedObj = null;
			  }
			  else {
				  obj.drawObjBoundingBox(canvas.getimageBufferGraphics());
				  //System.out.println("Break");
				  highlightedObj = obj;
			  }
			  
			  break;
		  }
	  }
 }

 public void mouseDragged(MouseEvent e)  { 
 }

 public void mouseReleased(MouseEvent e) { 
 }

}
