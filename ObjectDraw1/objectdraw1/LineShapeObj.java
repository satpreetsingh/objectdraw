package objectdraw1;

import java.awt.*;
import java.util.*;

public class LineShapeObj implements TwoEndShape{
   private Point startp,endp;

  public void setPoints(Point startp,Point endp) {
      this.startp=startp;
      this.endp=endp;
  }

  public void draw(Graphics g, int x0, int y0,
			      int x1, int y1) {
    g.drawLine(x0, y0, x1, y1);
  }

  public void drawObj(Graphics g) {
	    g.drawLine(startp.x, startp.y, endp.x, endp.y);
	  }
  

  public void drawOutline(Graphics g, int x0, int y0,
			      int x1, int y1) {
    g.drawLine(x0, y0, x1, y1);
  }

}
