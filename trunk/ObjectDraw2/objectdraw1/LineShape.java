package objectdraw1;


import java.awt.*;

public class LineShape implements TwoEndShape {

  public void draw(Graphics g, int x0, int y0, 
		       int x1, int y1) {
    g.drawLine(x0, y0, x1, y1);
  }

  public void drawOutline(Graphics g, int x0, int y0,
			      int x1, int y1) {
    g.drawLine(x0, y0, x1, y1);
  }
}
