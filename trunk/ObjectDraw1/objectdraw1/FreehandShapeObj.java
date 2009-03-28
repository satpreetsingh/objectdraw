package objectdraw1;

import java.awt.*;
import java.util.*;

public class FreehandShapeObj extends ShapeObj{

	private Point startp;
	private Point endp;
	// Note: startp and endp have a different meaning here compared to TwoEndShapeObjs:
	// They are the ends of the bounding-box of the free-hand shape and are
	// calculated when the calcBB function is called from mouseReleased is 
	
	private Color objColor;
	
	private Vector<Point> FreehandPoints;

	public FreehandShapeObj() {
		FreehandPoints = new Vector<Point>(); 
		System.out.println("Constructor of FreehandShapeObj");	   
	}

	public void setColor(Color objColor) {
		this.objColor = objColor;	
	}

	public void setStartPoint(Point startp) {
		this.FreehandPoints.add(startp);
		this.startp = startp;
	}

	public void addFreehandPoint(Point newpoint) {
		this.FreehandPoints.add(newpoint);
		this.endp = newpoint;
	}
	
	public void drawObj(Graphics g) {
		System.out.println("drawObj in FreehandShapeObj");
		Color saveColor = g.getColor();	
		g.setColor(this.objColor);
		
		if (FreehandPoints.size() == 1) { 
			Point solePoint = FreehandPoints.get(0);
			g.drawLine(solePoint.x, solePoint.y, solePoint.x, solePoint.y);
			return;
		}
		
		int i;
		Point oldPoint = FreehandPoints.get(0);
		Point newPoint;
		for (i = 1; i < FreehandPoints.size()-1; i++ ) {
			newPoint = FreehandPoints.get(i);
			g.drawLine(oldPoint.x, oldPoint.y, newPoint.x, newPoint.y);
			oldPoint = newPoint;
		}
		g.setColor(saveColor);
	}  

	public void drawOutline(Graphics g, int x0, int y0, int x1, int y1) {
		System.out.println("drawOutline in FreehandShapeObj");
		g.drawLine(x0, y0, x1, y1);
	}

	public void move(int delX, int delY) {
		for (Point freehandPoint: FreehandPoints) {
			freehandPoint.x += delX; 	    freehandPoint.y += delY;
		}	
		//this.recalc_endp();
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
		Color saveColor = g.getColor();	

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

		g.drawRect(shapeX - 1, shapeY - 1, shapeWidth + 2, shapeHeight + 2);		
		g.setColor(saveColor);
	}
	
	public void calcBB() {
		startp = (Point)FreehandPoints.get(0).clone();
		endp = (Point)FreehandPoints.get(0).clone();	
		
		for (Point freehandPoint: FreehandPoints) {
			if (freehandPoint.x <= startp.x) 	{ startp.x = freehandPoint.x; }
			if (freehandPoint.y >= startp.y) 	{ startp.y = freehandPoint.y; }

			if (freehandPoint.x >= endp.x) 		{ endp.x = freehandPoint.x; }
			if (freehandPoint.y <= endp.y) 		{ endp.y = freehandPoint.y; }
		}	
	}

}
