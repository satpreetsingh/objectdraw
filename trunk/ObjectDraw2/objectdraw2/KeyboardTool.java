package objectdraw2;


import java.awt.*;
import java.awt.event.*;

public interface KeyboardTool
      extends Tool{
  
  void keyPressed(KeyEvent e);
  void keyReleased(KeyEvent e);
  void keyTyped(KeyEvent e);
}
