package objectdraw1;


import java.awt.*;

public abstract class TwoEndShapeObj {

	private Point startp = new Point();
	private Point endp = new Point(); 
	private Color objColor;
	private boolean isHighlighted = false;

	abstract void setColor(Color objColor);	
	abstract void setEndPoints(Point startp, Point endp);		
	abstract void drawObj(Graphics g);
	abstract void drawOutline(Graphics g, int x0, int y0, int x1, int y1);
	abstract void move(int delX, int delY);
	abstract boolean isPointInObject(Point p);
	abstract void drawObjBoundingBox(Graphics g);

	//abstract void resize();  
	//int isPointInObjectCorner(Point p);

}
