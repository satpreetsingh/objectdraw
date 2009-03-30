package objectdraw1;
import java.awt.*;
import java.awt.event.*;

public class SelectTool implements Tool {

	protected DrawingCanvas canvas;
	protected Point startingMousePosition;
	protected Point currentMousePosition;
	protected Point oldStartingMousePosition;
	protected Color saveColor;
	protected int resizeCorner;

	protected boolean toggleHighlight;


	protected ShapeObj shapeObj = null; 

	public SelectTool(DrawingCanvas c) {
		canvas = c;
		System.out.println("Constructor of SelectTool");	   
	}

	public void mousePressed(MouseEvent e)  {
		System.out.println("\n**mousePressed**");

		startingMousePosition = e.getPoint();
		currentMousePosition = (Point)startingMousePosition.clone();
		oldStartingMousePosition = (Point)startingMousePosition.clone();

		Graphics iBGraphics = canvas.getimageBufferGraphics();		
		saveColor = iBGraphics.getColor();
		iBGraphics.setPaintMode();

		canvas.clearCanvas();
		canvas.redrawObjs();

		// Set/Clear Canvas highlightedObj on button-press
		int i;
		boolean foundAnObject = false;
		this.toggleHighlight = false;				

		for (i = canvas.objsOnCanvas.size()-1 ; i >= 0 ; i-- ) {
			System.out.println("From for loop in SelectTool. Object ["+i+"/"+(canvas.objsOnCanvas.size()-1)+"]");
			shapeObj = canvas.objsOnCanvas.get(i);
			if (shapeObj.isPointInObject(startingMousePosition) == true) {
				foundAnObject = true;
				System.out.println("SelectTool found object: " + shapeObj.toString());
				if (canvas.highlightedObj == shapeObj) { this.toggleHighlight = true; }
				System.out.println("Highlighting! " + shapeObj.toString());					
				System.out.println("(mousePressed) Drawing bounding box on: " + shapeObj.toString());
				shapeObj.drawObjBoundingBox(iBGraphics);
				canvas.highlightedObj = shapeObj;
				this.resizeCorner = shapeObj.resizeCornerSelected(startingMousePosition);
				break;
			}
		}

		// Code for if clicked outside of all objects' boundary
		if (foundAnObject == false) { 
			canvas.highlightedObj = null; 
		}

		iBGraphics.setColor(saveColor);		
		canvas.repaint(); // Puts buffer to screen
	}

	public void mouseDragged(MouseEvent e)  { 
		System.out.println("**mouseDragged -- ALT**");
		Graphics iBGraphics = canvas.getimageBufferGraphics();
		Point newMousePosition = e.getPoint();

		int dX = newMousePosition.x - currentMousePosition.x;
		int dY = newMousePosition.y - currentMousePosition.y;
		System.out.println("Dragged by ("+dX+", "+dY+")");

		oldStartingMousePosition.x += dX;
		oldStartingMousePosition.y += dY;

		/* Return graphics context to Normal drawing mode and color */
		iBGraphics.setColor(shapeObj.objColor);
		iBGraphics.setPaintMode();

		// MOVE
		if ((this.resizeCorner == 0) && 
				(canvas.highlightedObj != null) && 
				((dX != 0 || dY != 0))) {
			System.out.println("Moving by ("+dX+", "+dY+")");
			shapeObj = canvas.highlightedObj;
			shapeObj.move(dX, dY);	
			this.toggleHighlight = false;
		}

		// RESIZE
		if ((this.resizeCorner != 0) && 
				(canvas.highlightedObj != null) && 
				((dX != 0 || dY != 0))) {
			System.out.println("Resizing Corner: " + this.resizeCorner + " by ("+dX+", "+dY+")");
			shapeObj = canvas.highlightedObj;
			shapeObj.resize(this.resizeCorner, dX, dY);
			this.toggleHighlight = false;
		}

		// Canvas Repainting after Move/Resize/Nothing
		canvas.clearCanvas();
		canvas.redrawObjs();

		// Draw Bounding box if MOVED, RESIZED or SELECTED
		if ((canvas.highlightedObj != null) && (this.toggleHighlight == false)) { 
			shapeObj = canvas.highlightedObj;
			shapeObj.drawObjBoundingBox(iBGraphics); 
		}

		// If found an object and not dragged mouse, then toggle highlight for that object
		if ((this.toggleHighlight == true) && (dX == 0 && dY == 0)) {
			canvas.highlightedObj = null; 
		}

		currentMousePosition = (Point)newMousePosition.clone();
		canvas.repaint(); // Puts buffer to screen		
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("**mouseReleased**");
		Graphics iBGraphics = canvas.getimageBufferGraphics();
		currentMousePosition = e.getPoint();

		/* Return graphics context to Normal drawing mode and color */
		iBGraphics.setColor(saveColor);
		iBGraphics.setPaintMode();

		int dX = currentMousePosition.x - startingMousePosition.x;
		int dY = currentMousePosition.y - startingMousePosition.y;

		// Canvas Repainting after Move/Resize/Nothing
		canvas.clearCanvas();
		canvas.redrawObjs();

		// Draw Bounding box if MOVED, RESIZED or SELECTED
		if ((canvas.highlightedObj != null) && (this.toggleHighlight == false)) { 
		shapeObj = canvas.highlightedObj;
		shapeObj.drawObjBoundingBox(iBGraphics); 
		}

		// If found an object and not dragged mouse, then toggle highlight for that object
		if ((this.toggleHighlight == true) && (dX == 0 && dY == 0)) {
		canvas.highlightedObj = null; 
		}

		canvas.repaint(); // Puts buffer to screen		
	}

}
