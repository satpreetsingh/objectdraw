package objectdraw1;

import java.awt.*;
import java.util.*;

public class LineShapeObj extends TwoEndShapeObj{

	private Point startp = new Point();
	private Point endp = new Point();
	private Color objColor;


	public LineShapeObj() {
		System.out.println("Constructor of LineShapeObj");	   
	}

	public void setColor(Color objColor) {
		this.objColor = objColor;	
	}

	public void setStartPoint(Point startp) {
		this.startp=startp;
	}

	public void setEndPoint(Point endp) {
		this.endp=endp;
	}


	public void drawObj(Graphics g) {
		System.out.println("drawObj in LineShapeObj");
		g.setColor(this.objColor);
		g.drawLine(startp.x, startp.y, endp.x, endp.y);
	}  

	public void drawOutline(Graphics g, int x0, int y0, int x1, int y1) {
		//System.out.println("drawOutline in LineShapeObj");
		g.drawLine(x0, y0, x1, y1);
	}

	public void move(int delX, int delY) {
		this.startp.x += delX; 	    this.startp.y += delY;
		this.endp.x   += delX;	    this.endp.y   += delY;
	}


	public boolean isPointInObject(Point p) {		
		boolean insideX = false;
		boolean insideY = false;

		if (startp.x < endp.x) {
			if (p.x <= endp.x && p.x >= startp.x) {
				insideX = true; 
			}
		}

		if (startp.x > endp.x) {
			if (p.x >= endp.x && p.x <= startp.x) {
				insideX = true; 
			}
		}

		if (startp.y < endp.y) {
			if (p.y <= endp.y && p.y >= startp.y) {
				insideY = true; 
			}
		}

		if (startp.y > endp.y) {
			if (p.y >= endp.y && p.y <= startp.y) {
				insideY = true; 
			}
		}

		return insideX && insideY;
	}


	public void drawObjBoundingBox(Graphics g){

		g.setColor(Color.lightGray);
		// Set up bounding box
		int x0 = this.startp.x;
		int y0 = this.startp.y;
		int x1 = this.endp.x;
		int y1 = this.endp.y;

		int shapeX;
		int shapeY;
		int shapeWidth;
		int shapeHeight;
		if (x0 <= x1) {
			shapeX = x0;
			shapeWidth = (x1-x0)+1;
		}
		else {
			shapeX = x1;
			shapeWidth = (x0-x1)+1;
		}

		if (y0 <= y1) {
			shapeY = y0;
			shapeHeight = (y1-y0)+1;
		}
		else {
			shapeY = y1;
			shapeHeight = (y0-y1)+1;
		}

		g.drawRect(shapeX, shapeY, shapeWidth, shapeHeight);		
	}

}
