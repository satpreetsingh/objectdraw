package objectdraw.MiniDraw4;

import java.awt.*;
import java.awt.event.*;

public class DrawingCanvasController 
     implements MouseListener, MouseMotionListener {

  protected DrawingCanvas canvas;
  
  /* Constructor */
  public DrawingCanvasController(DrawingCanvas c) {
    canvas=c;
  }
  
  public void mousePressed(MouseEvent e) {
    Tool tool = canvas.getcurrentTool();
    if (tool != null) {
      tool.mousePressed(e);
    }
  }
  
  public void mouseReleased(MouseEvent e) { 
    Tool tool = canvas.getcurrentTool();
    if(tool != null) {
      tool.mouseReleased(e);
    }
   }
  
    public void mouseDragged(MouseEvent e) {
      Tool tool = canvas.getcurrentTool();
      if(tool != null) {
        tool.mouseDragged(e);
    }
  }

  /* Null listener methods */

  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mouseMoved(MouseEvent e) {}
  
} /* end of class DrawingCanvasController */
