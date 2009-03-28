package objectdraw1;


import java.awt.*;

public abstract class ShapeObj {

	protected Point startp;
	protected Color objColor;

	abstract void setColor(Color objColor);	
	abstract void setStartPoint(Point startp);		

	abstract void drawObj(Graphics g);
	abstract void drawOutline(Graphics g, int x0, int y0, int x1, int y1);
	abstract void move(int delX, int delY);

	abstract boolean isPointInObject(Point p);
	abstract void drawObjBoundingBox(Graphics g);

}
