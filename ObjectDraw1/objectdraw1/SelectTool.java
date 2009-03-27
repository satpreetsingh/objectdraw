package objectdraw1;
import java.awt.*;
import java.awt.event.*;

public class SelectTool implements Tool {

	protected DrawingCanvas canvas;
	protected Point startingMousePosition;
	protected Point currentMousePosition;
	protected Point oldStartingMousePosition;
	protected Color saveColor;

	protected TwoEndShapeObj shapeObj = null; 

	public SelectTool(DrawingCanvas c) {
		canvas = c;
	}

	public void mousePressed(MouseEvent e)  {
		System.out.println("\n**mousePressed**");

		Graphics iBGraphics = canvas.getimageBufferGraphics();		
		startingMousePosition = e.getPoint();
		currentMousePosition = (Point)startingMousePosition.clone();
		oldStartingMousePosition = (Point)startingMousePosition.clone();
		saveColor = iBGraphics.getColor( );
		
		iBGraphics.setXORMode(Color.lightGray);
		iBGraphics.setColor(Color.white);

		
		// Set/Clear Canvas highlightedObj on button-press
		int i;
		boolean foundAnObject = false;
		for (i = canvas.objsOnCanvas.size()-1 ; i >= 0 ; i-- ) {
			System.out.println("From for loop in SelectTool. Object ["+i+"/"+(canvas.objsOnCanvas.size()-1)+"]");
			shapeObj = canvas.objsOnCanvas.get(i);
			if (shapeObj.isPointInObject(startingMousePosition) == true) {
				foundAnObject = true;
				System.out.println("SelectTool found object: " + shapeObj.toString());
				if (canvas.highlightedObj == shapeObj) {
					System.out.println("Un-Highlighting! " + shapeObj.toString());					
					canvas.highlightedObj = null;
					canvas.isAnObjectHighlighted = false; // TODO: Is a redundant variable -- replace with just canvas.highlightedObj
				}
				else {
					// This draws a highlight/bounding-box around object (if found) immediately on mousePress
					System.out.println("Highlighting! " + shapeObj.toString());					
					iBGraphics.setPaintMode();
					System.out.println("(mousePressed) Drawing bounding box on: " + shapeObj.toString());
					shapeObj.drawObjBoundingBox(iBGraphics);
					iBGraphics.setXORMode(Color.lightGray);					
					canvas.highlightedObj = shapeObj;
					canvas.isAnObjectHighlighted = true;
				}
				break;
			}
		}

		// Code for if clicked outside of all objects' boundary
		if (foundAnObject == false) { 
			canvas.highlightedObj = null; 
			canvas.isAnObjectHighlighted = false;
//			canvas.clearCanvas();
//			canvas.redrawObjs();
		}
		
		canvas.repaint(); // Puts buffer to screen
	}



	public void mouseDragged(MouseEvent e)  { 
		System.out.println("**mouseDragged**");
		Point newMousePosition = e.getPoint();
		Graphics iBGraphics = canvas.getimageBufferGraphics();

		iBGraphics.setXORMode(Color.lightGray);
		iBGraphics.setColor(Color.black); 
		// So paint in white and alternate with light-gray
		
		/* erase previous temporary figure by redrawing it */
		shapeObj.drawOutline(iBGraphics,
				oldStartingMousePosition.x,
				oldStartingMousePosition.y,
				currentMousePosition.x, 
				currentMousePosition.y);
		
		int dX = newMousePosition.x - currentMousePosition.x;
		int dY = newMousePosition.y - currentMousePosition.y;
		System.out.println("Dragged by ("+dX+", "+dY+")");
		
		oldStartingMousePosition.x += dX;
		oldStartingMousePosition.y += dY;
				
		/* draw new temporary figure */
		shapeObj.drawOutline(iBGraphics,
				oldStartingMousePosition.x,
				oldStartingMousePosition.y,
				newMousePosition.x,
				newMousePosition.y);

		/* update current mouse coordinates */
		currentMousePosition = (Point)newMousePosition.clone();

		canvas.repaint(); // Puts buffer to screen
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("**mouseReleased**");
		Graphics iBGraphics = canvas.getimageBufferGraphics();
		currentMousePosition = e.getPoint();
		
//		// Still in XOR Mode
//		/* Erase final temporary figure  */
//		shapeObj.drawOutline(iBGraphics,
//				startingMousePosition.x, 
//				startingMousePosition.y,
//				currentMousePosition.x, 
//				currentMousePosition.y);
		
		/* Return graphics context to Normal drawing mode and color */
		iBGraphics.setColor(saveColor);
		iBGraphics.setPaintMode();

		// If found an object and dragged mouse, then this will be the final position drawing routine.
		// After drawing at final position, highlight (highlight already done in mousePressed)
		if ((canvas.highlightedObj != null) && (currentMousePosition != startingMousePosition)) {
			shapeObj = canvas.highlightedObj;
			int dX = currentMousePosition.x - startingMousePosition.x;
			int dY = currentMousePosition.y - startingMousePosition.y;
			System.out.println("Moving by ("+dX+", "+dY+")");
//			System.out.println("End   Point: " + currentMousePosition.toString());
//			System.out.println("Start Point: " + startingMousePosition.toString());			
			shapeObj.move(dX, dY);		
//			System.out.println("(mouseReleased) Drawing bounding box on: " + shapeObj.toString());
//			shapeObj.drawObjBoundingBox(iBGraphics);
		}
		
		// If didn't find an object then just de-highlight all objects (done in mousePressed) and redraw canvas
		// i.e. do nothing special
		
		// Common
		canvas.clearCanvas();
		canvas.redrawObjs();
		if (canvas.highlightedObj != null) { canvas.highlightedObj.drawObjBoundingBox(iBGraphics); }
		
		// If found an object and not dragged mouse, then toggle highlight for that object
		if ((canvas.highlightedObj != null) && (currentMousePosition == startingMousePosition)) {
//			shapeObj = canvas.highlightedObj;
//			System.out.println("(mouseReleased)Drawing bounding box on: " + shapeObj.toString());					
//			shapeObj.drawObjBoundingBox(iBGraphics); // Drawing toggles highlight
		}
		canvas.repaint(); // Puts buffer to screen		
	}

}
