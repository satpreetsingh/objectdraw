package objectdraw1;

import java.awt.*;
import java.util.*;

public class LineShapeObj implements TwoEndShapeObj{

   private Point startp = new Point();
   private Point endp = new Point();
   private Color objColor;
   private boolean isHighlighted;
   

   public LineShapeObj() {
	   System.out.println("Constructor of LineShapeObj");	   
	   this.isHighlighted = false;
   }
   
   public void setColor(Color objColor) {
		  this.objColor = objColor;	
	  }
    
  public void setEndPoints(Point startp, Point endp) {
      this.startp=startp;
      this.endp=endp;
  }
  
  public void drawObj(Graphics g) {
		System.out.println("drawObj in LineShapeObj");
	    g.setColor(this.objColor);
	  	g.drawLine(startp.x, startp.y, endp.x, endp.y);
	  	
	  	if (this.isHighlighted == true) {
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
		    
		    g.drawRect(shapeX - 2, shapeY - 2, shapeWidth + 4, shapeHeight + 4);
//		    g.drawLine(startp.x + 2, startp.y, endp.x + 2, endp.y);	  		
//		  	g.drawLine(startp.x - 2, startp.y, endp.x - 2, endp.y);	  		
	  	}
	  }  

  public void drawOutline(Graphics g, int x0, int y0,
			      int x1, int y1) {
    g.drawLine(x0, y0, x1, y1);

//    this.startp.x = x0;
//    this.startp.y = y0;
//    this.endp.x   = x1;
//    this.endp.y   = y1;
    //System.out.println(x0 + "," + y0 + " " + x1 + "," + y1 );
  }

  public void move(int delX, int delY) {
	  this.startp.x += 20;	  
  }


	public boolean isPointInObject(Point p) {		
		return true;
	}

	public void setHighlighted() {
		System.out.println("setHighlighted in LineShapeObj");
		this.isHighlighted = true;
	}

	public void clearHighlighted() {
		System.out.println("clearHighlighted in LineShapeObj");
		this.isHighlighted = false;
	}
	
	public boolean getHighlighted() {
		return this.isHighlighted;
	}
	

  
}
