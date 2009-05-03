


import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

public class KeyboardController  
     implements KeyListener { 

  protected DrawingCanvas canvas;

  public KeyboardController(DrawingCanvas c) {
    canvas = c;
  }

  public void keyPressed(KeyEvent e) {
    KeyboardTool tool = (KeyboardTool)canvas.getcurrentTool();
    if (tool != null) {
      tool.keyPressed(e);
    }
  }

  public void keyReleased(KeyEvent e) { 
    KeyboardTool tool = (KeyboardTool)canvas.getcurrentTool();
    if(tool != null) {
      tool.keyReleased(e);
    }
  }

  public void keyTyped(KeyEvent e) {
    KeyboardTool tool = (KeyboardTool)canvas.getcurrentTool();
    if(tool != null) {
      tool.keyTyped(e);
    }
  }

}
