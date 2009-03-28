package objectdraw1;


import java.awt.*;
import java.awt.event.*;

public class FreehandTool implements Tool {

	protected DrawingCanvas canvas;
	protected Point startingMousePosition;
	protected FreehandShapeObj FHShapeObj; 

	public FreehandTool(DrawingCanvas c) {
		canvas = c;
		System.out.println("Constructor of FreehandTool");	   
	}

	protected void drawLineSegment(Point p1, Point p2) {
		canvas.getimageBufferGraphics().drawLine(p1.x,p1.y,
				p2.x, p2.y);
		/* redraw only the small rectangle  */
		/* containing the new line segment  */
		int x0 = Math.min(p1.x, p2.x);
		int y0 = Math.min(p1.y, p2.y);
		int dx = Math.abs(p2.x - p1.x)+ 1;
		int dy = Math.abs(p2.y - p1.y) + 1;
		canvas.repaint(x0, y0, dx, dy);
	}

	public void mousePressed(MouseEvent e)  {
		/* Establish starting point for next drawing */
		startingMousePosition = e.getPoint();

		FHShapeObj = new FreehandShapeObj();
		FHShapeObj.setStartPoint(startingMousePosition);
		FHShapeObj.setColor(canvas.getimageBufferGraphics().getColor());
	}

	public void mouseDragged(MouseEvent e)  {
		Point newMousePosition = e.getPoint();
		drawLineSegment(startingMousePosition,
				newMousePosition);
		/* update current mouse coordinates */
		startingMousePosition = newMousePosition;

		FHShapeObj.addFreehandPoint(newMousePosition);
	}

	/*null methods*/

	public void mouseReleased(MouseEvent e) {
		FHShapeObj.calcBB();
		canvas.objsOnCanvas.add(FHShapeObj);
	}
}
