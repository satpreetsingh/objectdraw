package objectdraw1;


import java.awt.*;

public class RectangleShapeObj implements TwoEndShapeObj {

	   private Point startp = new Point();
	   private Point endp = new Point();
	   private Color objColor;
	   private boolean isHighlighted = false;

	   public RectangleShapeObj() {
		   System.out.println("Constructor of RectangleShapeObj");	   
		   this.isHighlighted = false;
	   }

	   
	   public void setColor(Color objColor) {
			  this.objColor = objColor;	
		  }
	 
	   public void setEndPoints(Point startp,Point endp) {
		     this.startp=startp;
		     this.endp=endp;
		 }
 
 public void drawObj(Graphics g) {
	System.out.println("drawObj in [RectangleShapeObj] + H: " + this.getHighlighted());
	 
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
    g.drawRect(shapeX, shapeY, shapeWidth, shapeHeight);
    
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
    g.drawRect(shapeX, shapeY, shapeWidth, shapeHeight);

    this.startp.x = x0;
    this.startp.y = y0;
    this.endp.x   = x1;
    this.endp.y   = y1;
    
  }
  
  public void move(int delX, int delY) {
  }

	public boolean isPointInObject(Point p) {
		return true;
	}
	
	public void setHighlighted() {
		System.out.println("setHighlighted in RectangleShapeObj");
		this.isHighlighted = true;
	}
	
	public void clearHighlighted() {
		System.out.println("clearHighlighted in RectangleShapeObj");
		this.isHighlighted = false;
	}

	public boolean getHighlighted() {
		return this.isHighlighted;
	}


}
