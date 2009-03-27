package objectdraw1;


import java.awt.*;

public class OvalShapeObj extends TwoEndShapeObj {

	private Point startp = new Point();
	private Point endp = new Point();
	private Color objColor;
	private boolean isHighlighted = false;

	public OvalShapeObj() {
		System.out.println("Constructor of OvalShapeObj");	   
		this.isHighlighted = false;
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

		g.setColor(this.objColor);
		g.drawOval(shapeX, shapeY, shapeWidth, shapeHeight);

		if (this.isHighlighted == true) {
			g.setColor(Color.GRAY);
			g.drawRect(shapeX - 2, shapeY - 2, shapeWidth + 4, shapeHeight + 4);
		}

	}

	public void drawOutline(Graphics g, int x0, int y0,
			int x1, int y1) {
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
			shapeWidth = (x0 -x1)+1;
		}
		if (y0 <= y1) {
			shapeY = y0;
			shapeHeight = (y1-y0)+1;
		}
		else {
			shapeY = y1;
			shapeHeight = (y0-y1)+1;
		}

		g.drawOval(shapeX, shapeY, shapeWidth, shapeHeight);

//		this.startp.x = x0;
//this.startp.y = y0;
//this.endp.x   = x1;
//this.endp.y   = y1;

	}

	public void move(int delX, int delY) {
		this.startp.x += delX;	    this.startp.y += delY;
		this.endp.x   += delX;	    this.endp.y   += delY;
		// Add routines here for redrawing canvas
		// Make sure that object being moved (which is also selected), shows bounding box movement
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

		g.drawRect(shapeX - 1 , shapeY -1, shapeWidth + 2, shapeHeight + 2);		

	}

}
