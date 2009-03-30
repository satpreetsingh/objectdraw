

import java.awt.*;
import java.util.*;
import java.awt.FontMetrics;


public class TextObj extends ShapeObj{

	private Point startp;
	private Point endp;
	// Note: startp and endp have a different meaning here compared to TwoEndShapeObjs:
	// They are the ends of the bounding-box of the text shape and are
	// calculated when the calcBB function is called from mouseReleased is 
	// Also, startp here is bottom-left corner, endp is top-right corner
	
	private Color objColor;	
	private StringBuffer textObjBuffer;
	
	FontMetrics fontMetrics;
	private Font objFont = new Font("Serif", Font.BOLD, 24);;


	public TextObj() {
		System.out.println("Constructor of TextObj");	
		this.textObjBuffer = new StringBuffer();
//		this.textObjBuffer.append("");
		this.startp = new Point();
		this.endp = new Point();
	}

	public void setFont(Font objFont, Graphics g) {
		this.objFont = objFont;	
		fontMetrics = g.getFontMetrics();
	}
	
	public void setColor(Color objColor) {
		this.objColor = objColor;	
	}

	public void setStartPoint(Point startp) {
		this.startp = (Point)startp.clone();
	}
	
	public void addChar(char c) {
		this.textObjBuffer.append(c);
		System.out.println("addChar in TextObj. String is now: " + textObjBuffer.toString());	
		this.calcBB();
	}
		
	public void drawObj(Graphics g) {
//		System.out.println("drawObj in TextObj");
		Color saveColor = g.getColor();
		g.setColor(this.objColor);

		Font saveFont = g.getFont();
		g.setFont(objFont);
		//fontMetrics = g.getFontMetrics();

		g.drawString(textObjBuffer.toString(), startp.x, startp.y); 
		
		g.setColor(saveColor);
		g.setFont(saveFont);
	}  

	public void drawOutline(Graphics g, int x0, int y0, int x1, int y1) {
		System.out.println("drawOutline in TextObj");
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
		this.endp = new Point();
		this.endp.x = this.startp.x + fontMetrics.stringWidth(textObjBuffer.toString());
		this.endp.y = this.startp.y - fontMetrics.getHeight();
	}

	@Override
	int resizeCornerSelected(Point p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	void resize(int corner, int delX, int delY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void toggleFill() {
		// TODO Auto-generated method stub
		
	}

}
