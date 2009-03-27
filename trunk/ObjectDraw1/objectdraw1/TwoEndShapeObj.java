package objectdraw1;


import java.awt.*;

public abstract class TwoEndShapeObj extends ShapeObj {

	private Point startp = new Point();
	private Point endp = new Point(); 
	private Color objColor;
	private boolean isHighlighted = false; // deprecated -- remove dependencies

	abstract void setColor(Color objColor);	
	abstract void setStartPoint(Point startp);		
	abstract void setEndPoint(Point endp);		

	abstract void drawObj(Graphics g);
	abstract void drawOutline(Graphics g, int x0, int y0, int x1, int y1);
	abstract void move(int delX, int delY);
	abstract boolean isPointInObject(Point p);
	abstract void drawObjBoundingBox(Graphics g);

	//abstract void resize();  
	//int isPointInObjectCorner(Point p);

}
