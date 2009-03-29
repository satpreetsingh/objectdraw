package objectdraw1;
import java.awt.*;
import java.awt.event.*;

public class FillTool implements Tool {

	protected DrawingCanvas canvas;
	protected Point startingMousePosition;
	protected Color saveColor;

	public FillTool(DrawingCanvas c) {
		System.out.println("Constructor of FillTool");  
		canvas = c;
	}  

	public String toString() {
		return "FillTool";
	}

	public void mousePressed(MouseEvent e)  {

//		if (canvas.highlightedObj != null) {
//			System.out.println("Toggling highlighted object fill");
//			canvas.highlightedObj.toggleFill(); 
//			canvas.clearCanvas();
//			canvas.redrawObjs();
//			//canvas.highlightedObj.drawObjBoundingBox(canvas.getimageBufferGraphics());
//		}

//		for (ShapeObj obj : canvas.objsOnCanvas) {			  
//		if (obj.isPointInObject(e.getPoint()) == true) {
//		canvas.objsOnCanvas.remove(obj);				 			  
//		canvas.clearCanvas();
//		canvas.redrawObjs();			  
//		break;
//		}
		canvas.repaint();

	}

	public void mouseDragged(MouseEvent e)  { }
	public void mouseReleased(MouseEvent e) { }
}
