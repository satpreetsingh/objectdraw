package objectdraw2;
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
	  for (TwoEndShapeObj obj : canvas.objsOnCanvas) {	
		  
		  if (obj.isPointInObject(e.getPoint())) {
			  canvas.objsOnCanvas.remove(obj);
			  break;
		  }
	  }
	  canvas.clearCanvas();
	  canvas.redrawObjs();
 }

 public void mouseDragged(MouseEvent e)  {
 }

 public void mouseReleased(MouseEvent e) { 
 }

}
