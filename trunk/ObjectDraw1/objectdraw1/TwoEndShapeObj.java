package objectdraw1;


import java.awt.*;

public interface TwoEndShapeObj {

  void setColor(Color objColor);	

  void setEndPoints(Point startp, Point endp);
		
  void drawObj(Graphics g);

  void drawOutline(Graphics g, int x0, int y0,
		      int x1, int y1);
  
  void move(int delX, int delY);

  void pointIsInObject(int x, int y);

}
