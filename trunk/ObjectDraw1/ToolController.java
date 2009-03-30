


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.EventListener;

public class ToolController extends AbstractAction {

  protected DrawingCanvas canvas;
  protected Tool tool;
  
  public ToolController(String name, Icon icon, String tip,
		        DrawingCanvas c, Tool t) {
    super(name, icon);
    tool = t;
    putValue(Action.DEFAULT, icon);
    putValue(Action.SHORT_DESCRIPTION, tip);
    setEnabled(tool != null);
    canvas = c;
    
  } 

  public void actionPerformed(ActionEvent e) {
    canvas.setcurrentTool(tool);
    System.out.println("ToolSelected: " + canvas.getcurrentTool().toString());

    if ("EraserTool".equals(canvas.getcurrentTool().toString())) {
		  System.out.println("Deleting highlighted object");
		  canvas.objsOnCanvas.remove(canvas.highlightedObj); 
		  canvas.highlightedObj = null; 
		  canvas.clearCanvas();
		  canvas.redrawObjs();
  }

    if (("FillTool".equals(canvas.getcurrentTool().toString())) && (canvas.highlightedObj != null)) {
		  System.out.println("Toggling object fill");
			System.out.println("Toggling highlighted object fill");
			canvas.highlightedObj.toggleFill(); 
			canvas.clearCanvas();
			canvas.redrawObjs();
			canvas.highlightedObj.drawObjBoundingBox(canvas.getimageBufferGraphics());
  }
    
    
  }

}
