


import java.awt.*;
import java.awt.event.*;

public class TwoEndShapeTool implements Tool {

	protected DrawingCanvas canvas;
	protected Point startingMousePosition;
	protected Point currentMousePosition;
	protected Color saveColor;
	protected String twoEndShapeType;
	protected TwoEndShapeObj shapeObj;

	//  protected int preX,preY;
	//  protected int dx=0; protected int dy=0;
	//  protected TwoEndShapeObj clickedShape,draggedShape;
	//  protected int width,height;


	public TwoEndShapeTool(DrawingCanvas c, TwoEndShapeObj so, String twoEndShapeType) {
		canvas = c;
		shapeObj = so;
		this.twoEndShapeType = twoEndShapeType;
	}

	public void mousePressed(MouseEvent e)  {
		/* Establish starting point for next drawing */
		startingMousePosition = e.getPoint();
		currentMousePosition = (Point)startingMousePosition.clone();
		Graphics iBGraphics = canvas.getimageBufferGraphics();
		saveColor = iBGraphics.getColor( );
		iBGraphics.setXORMode(Color.lightGray);
		iBGraphics.setColor(Color.white);

		shapeObj.drawOutline(iBGraphics,
				startingMousePosition.x,
				startingMousePosition.y,
				startingMousePosition.x,
				startingMousePosition.y);


		//   preX= startingMousePosition.x;
		//   preY=startingMousePosition.y;
		//   draggedShape=shapeObj;

		canvas.repaint(); // Puts buffer to screen

	}

	public void mouseDragged(MouseEvent e)  {
		Point newMousePosition = e.getPoint();
		Graphics iBGraphics = canvas.getimageBufferGraphics();

		/* erase previous temporary figure by redrawing it */
		shapeObj.drawOutline(iBGraphics,
				startingMousePosition.x,
				startingMousePosition.y,
				currentMousePosition.x, 
				currentMousePosition.y);

		/* draw new temporary figure */
		shapeObj.drawOutline(iBGraphics,
				startingMousePosition.x,
				startingMousePosition.y,
				newMousePosition.x,
				newMousePosition.y);

		/* update current mouse coordinates */
		currentMousePosition = (Point)newMousePosition.clone();

		canvas.repaint(); // Puts buffer to screen
	}


	public void mouseReleased(MouseEvent e) { 
		Graphics iBGraphics = canvas.getimageBufferGraphics();

		/* Erase final temporary figure  */
		shapeObj.drawOutline(iBGraphics,
				startingMousePosition.x, 
				startingMousePosition.y,
				currentMousePosition.x, 
				currentMousePosition.y);

		/* Return graphics context to normal drawing mode and color */
		iBGraphics.setPaintMode();
		iBGraphics.setColor(saveColor);

		TwoEndShapeObj newTwoEndShapeObj = null;

		if ("Line".equals(twoEndShapeType)) 
		{ newTwoEndShapeObj = new LineShapeObj(); }
		else if ("Rectangle".equals(twoEndShapeType)) 
		{ newTwoEndShapeObj = new RectangleShapeObj(); }
		else if ("Oval".equals(twoEndShapeType))
		{ newTwoEndShapeObj = new OvalShapeObj(); }

		//TwoEndShapeObj newTwoEndShapeObj = new LineShapeObj();
		System.out.println("---> " + twoEndShapeType);
		///newTwoEndShapeObj = shapeObj;

		newTwoEndShapeObj.setColor(saveColor);
		newTwoEndShapeObj.setStartPoint(startingMousePosition);
		newTwoEndShapeObj.setEndPoint(e.getPoint());
		newTwoEndShapeObj.drawObj(iBGraphics);

		canvas.objsOnCanvas.add(newTwoEndShapeObj);
		canvas.highlightedObj = null;
		
		canvas.repaint();   // Puts buffer to screen

	}
}
