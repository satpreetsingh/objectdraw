

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
		Color saveColor = g.getColor();
		g.setColor(Color.gray);
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
		
		// Main bounding box
		g.drawRect(shapeX - 2, shapeY - 2, shapeWidth + 4, shapeHeight + 4);

		// Extra boxes on bounding-box corners for resize function
		g.drawRect(shapeX - 2, shapeY - 2, 6, 6);
		g.drawRect(shapeX + shapeWidth - 4, shapeY - 2, 6, 6);
		g.drawRect(shapeX - 2, shapeY + shapeHeight -4, 6, 6);
		g.drawRect(shapeX + shapeWidth - 4, shapeY + shapeHeight - 4, 6, 6);	
		
		g.setColor(saveColor);		
	}

	int resizeCornerSelected(Point p) {
		// Returns 
		// 0 if No resize-box selected
		// 1 if TOP-LEFT resize-box selected
		// 2 if TOP-RIGHT resize-box selected
		// 3 if BOTTOM-RIGHT resize-box selected
		// 4 if BOTTOM-LEFT resize-box selected		
		
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
		
		
		if (p.x >= shapeX - 2 && p.x <= shapeX - 2 + 6) {
			if (p.y >= shapeY - 2 && p.y <= shapeY - 2 + 6) {
				System.out.println("ResizeCorner: TOP-LEFT");				
				return 1;
			}
			if (p.y >= shapeY + shapeHeight - 4 && p.y <= shapeY + shapeHeight - 4 + 6) {
				System.out.println("ResizeCorner: BOTTOM-LEFT");				
				return 4;
			}		
		}

		if (p.x >= shapeX + shapeWidth - 4 && p.x <= shapeX + shapeWidth - 4 + 6) {
			if (p.y >= shapeY - 2 && p.y <= shapeY - 2 + 6) {
				System.out.println("ResizeCorner: TOP-RIGHT");				
				return 2;
			}
			if (p.y >= shapeY + shapeHeight - 4 && p.y <= shapeY + shapeHeight - 4 + 6) {
				System.out.println("ResizeCorner: BOTTOM-RIGHT");				
				return 3;
			}		
		}
		System.out.println("ResizeCorner: NONE");
		return 0;		
	}

	public void resize(int corner, int delX, int delY) {
		switch (corner) {
        case 1:  
        	System.out.println("Resizing TOP-LEFT corner"); 
        	this.startp.x += delX; 		this.startp.y += delY;
        	break;
        
        case 2:  
        	System.out.println("Resizing TOP-RIGHT corner"); 
        	this.endp.x += delX; 		this.startp.y += delY;        	
        	break;
        
        case 3:  
        	System.out.println("Resizing BOTTOM-RIGHT corner");         	
        	this.endp.x   += delX;		this.endp.y   += delY; 
        	break;
        
        case 4:  
        	System.out.println("TODO: Resize for BOTTOM-LEFT"); 
        	this.startp.x += delX; 		this.endp.y += delY;        	
        	break;
        
        default: 
        	System.out.println("Invalid Corner?");
        	break;        
		}
		
		
		// Re-calc startp & endp
		// This is a startp/endp correction routine IN CASE the startp/endp need to be interchanged
		
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
		
		startp.x = shapeX;
		startp.y = shapeY;
		endp.x = shapeX + shapeWidth - 1;
		endp.y = shapeY + shapeHeight - 1;
					
	}

	@Override
	void toggleFill() {
		// TODO Auto-generated method stub
		
	}

}
