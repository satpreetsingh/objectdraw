


import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.EventListener;

  public class DrawingCanvas extends JComponent {

  protected DrawingCanvasController DCcontroller;
  protected KeyboardController KBcontroller;
  protected Image imageBuffer;
  protected Graphics imageBufferGraphics;
  protected int canvasWidth;
  protected int canvasHeight;
  protected Color penColor = Color.black;
  protected Tool currentTool;
  
//  public boolean isAnObjectHighlighted = false; // Flag used by EraseTool, and set by SelectTool
  protected ShapeObj highlightedObj = null; // set by SelectTool, used by EraseTool

  
  protected Vector<ShapeObj> objsOnCanvas = new Vector<ShapeObj>();
  
    
  /* constructor */
  public DrawingCanvas() {
    setBackground(Color.white);
    DCcontroller = createDrawingCanvasController();
    addDrawingCanvasListener(DCcontroller);
    KBcontroller = createKeyboardController();
    addKeyboardListener(KBcontroller);
  }
  
  protected DrawingCanvasController
               createDrawingCanvasController() {
      return new DrawingCanvasController(this);
  }

  protected KeyboardController createKeyboardController() {
      return new KeyboardController(this);
  }

  protected void addDrawingCanvasListener(EventListener listener) {
    addMouseListener((MouseListener) listener);
    addMouseMotionListener((MouseMotionListener) listener);
  }
  
  protected void addKeyboardListener(EventListener listener) {
    addKeyListener( (KeyListener)listener);
  }
   
  public void update(Graphics g){
	  System.out.println("Update in DrawingCanvas");
	  paint(g);
  }

  public void paint(Graphics g) {
	  //System.out.println("(Re)Paint");
	  g.drawImage(imageBuffer, 0, 0, this);
  }
  
  public void drawLineSegment(Point p1, Point p2) {
   imageBufferGraphics.drawLine(p1.x,p1.y,
                	       p2.x, p2.y);
   repaint();
  }
  
  public void setpenColor(Color c) {
      penColor = c;
      imageBufferGraphics.setColor(c);
    }
    
  public Color getpenColor() {
    return penColor;
  }
  
  public void setcurrentTool(Tool t)  {
    currentTool = t;
  }
  
  public Tool getcurrentTool() {
    return currentTool;
  }
  
  public Graphics getimageBufferGraphics() {
    return imageBufferGraphics;
  }

  public void clearObjs() {
	  System.out.println("Clear Objs in DrawingCanvas");
	  objsOnCanvas.clear();
	  this.redrawObjs(); 	  
  }  
  
  public void clearCanvas() {
   System.out.println("Clear Canvas in DrawingCanvas");
   imageBufferGraphics.setColor(Color.white);
   imageBufferGraphics.fillRect(0, 0, canvasWidth, canvasHeight);
   imageBufferGraphics.setColor(penColor);
   repaint();   
  }
  
  
  public void setBounds(int x, int y, int width, int height) {
    Image newimageBuffer = createImage(width, height);
    imageBufferGraphics = newimageBuffer.getGraphics();
    if (imageBuffer != null) {
      imageBufferGraphics.drawImage(imageBuffer, 0, 0 ,this);
    }
    imageBuffer = newimageBuffer;
    setpenColor(penColor);
    super.setBounds(x, y, width, height);
    
	//canvas.clearCanvas();
	this.redrawObjs();
	if (this.highlightedObj != null) { this.highlightedObj.drawObjBoundingBox(imageBufferGraphics); }   
    repaint();
    canvasWidth = width;
    canvasHeight = height;    
  }
  
  public void redrawObjs() {
	  for (ShapeObj obj : objsOnCanvas) {
		  System.out.println("redrawObjs() in DrawingCanvas - Drawing Obj: "+ obj +" - " + objsOnCanvas.indexOf(obj));
		  obj.drawObj(imageBufferGraphics);
	  }
	  repaint();	  
  }
}
