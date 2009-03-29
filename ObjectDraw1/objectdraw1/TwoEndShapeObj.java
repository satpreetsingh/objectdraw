package objectdraw1;


import java.awt.*;

public abstract class TwoEndShapeObj extends ShapeObj {

	private Point startp;
	private Point endp; 
	private Color objColor;

	public void setColor(Color objColor) {
		this.objColor = objColor;	
	}

	public void setStartPoint(Point startp) {
		this.startp=startp;
	}

	public void setEndPoint(Point endp) {
		this.endp=endp;
	}

	abstract void drawObj(Graphics g);
	abstract void drawOutline(Graphics g, int x0, int y0, int x1, int y1);
	abstract void move(int delX, int delY);
	abstract boolean isPointInObject(Point p);
	abstract void drawObjBoundingBox(Graphics g);	

}
