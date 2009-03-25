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
  
 public void mousePressed(MouseEvent e)  {

	 // Highlight OR Un-highlight on button-press
	 //for (TwoEndShapeObj obj : (canvas.objsOnCanvas)) {	
	 	 int i;
	 	 TwoEndShapeObj obj;
	 	//if (obj.isPointInObject(e.getPoint()) == true){
		 for (i = 0; i < canvas.objsOnCanvas.size(); i++) {
		// TODO: reverse the order here
			 obj = canvas.objsOnCanvas.get(i);
			 System.out.println("From for loop: " + canvas.objsOnCanvas.size());
			 System.out.println(i);
			  if (obj.isPointInObject(e.getPoint()) == true) {
	//			  canvas.objsOnCanvas.remove(obj);
				  if (obj.getHighlighted() ==  true) {
					  obj.clearHighlighted();
				  } else {
					  obj.setHighlighted();
				  }
					 			  
	//			  canvas.repaint();
				  canvas.clearCanvas();
				  canvas.redrawObjs();			  
	//			  canvas.repaint();
				  System.out.println("Break");
				  break;
			  }
	  }
 }

 public void mouseDragged(MouseEvent e)  {

	 
 }

 public void mouseReleased(MouseEvent e) { 
 }

}
