package objectdraw1;
import java.awt.*;
import java.awt.event.*;

public class SelectTool implements Tool {
   
  protected DrawingCanvas canvas;
  protected Point startingMousePosition;
  protected Color saveColor;
  
  protected TwoEndShapeObj obj = null; 

  public SelectTool(DrawingCanvas c) {
   canvas = c;
  }
  
 public void mousePressed(MouseEvent e)  {

	 // Highlight OR Un-highlight on button-press
	 int i;
	 boolean foundAnObject = false;
	 
	 for (i = canvas.objsOnCanvas.size()-1 ; i >= 0 ; i-- ) {
		 // Cannot use iterator to iterate backwards
		 //	 for (TwoEndShapeObj obj : canvas.objsOnCanvas) {	

		 obj = canvas.objsOnCanvas.get(i);
		 
		 // TODO: reverse the order here
		 System.out.println("From for loop in SelectTool: " + canvas.objsOnCanvas.size());
			 
	 	if (obj.isPointInObject(e.getPoint()) == true) {
//			  canvas.objsOnCanvas.remove(obj);
	 		  foundAnObject = true;

			  canvas.clearCanvas();
			  canvas.redrawObjs();	
			  
			  if (canvas.highlightedObj == obj) {
				  canvas.highlightedObj = null;
				  canvas.isAnObjectHighlighted = false;
			  }
			  else {
				  obj.drawObjBoundingBox(canvas.getimageBufferGraphics());
				  //System.out.println("Break");
				  canvas.highlightedObj = obj;
				  canvas.isAnObjectHighlighted = true;
			  }
			  
			  break;
		  }
	  }
	 
	 // Code for if clicked outside of all objects' boundary
	 if (foundAnObject == false) { 
		  canvas.highlightedObj = null; 
		  canvas.isAnObjectHighlighted = false;
		  canvas.clearCanvas();
		  canvas.redrawObjs();
	  }
 }

 public void mouseDragged(MouseEvent e)  { 
 }

 public void mouseReleased(MouseEvent e) { 
 }

}
