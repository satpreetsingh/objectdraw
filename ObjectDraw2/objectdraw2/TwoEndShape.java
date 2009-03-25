package objectdraw2;


import java.awt.*;

public interface TwoEndShape {

  void draw(Graphics g, int x0, int y0,
		int x1, int y1);
  void drawOutline(Graphics g, int x0, int y0,
		      int x1, int y1);
  
}
