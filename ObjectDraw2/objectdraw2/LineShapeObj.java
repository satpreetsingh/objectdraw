package objectdraw2;

import java.awt.*;
import java.util.*;

public class LineShapeObj implements TwoEndShapeObj{

   private Point startp = new Point();
   private Point endp = new Point();
   private Color objColor;
   
   public void setColor(Color objColor) {
		  this.objColor = objColor;	
	  }
    
  public void setEndPoints(Point startp,Point endp) {
      this.startp=startp;
      this.endp=endp;
  }
  
//  public void draw(Graphics g, int x0, int y0,
//			      int x1, int y1) {
//    g.drawLine(x0, y0, x1, y1);
//  }

  public void drawObj(Graphics g) {
	    g.setColor(this.objColor);
	  	g.drawLine(startp.x, startp.y, endp.x, endp.y);
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
  }


	public boolean isPointInObject(Point p) {
			
		return true;
	}

  
}
