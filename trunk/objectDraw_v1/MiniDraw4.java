package objectdraw.MiniDraw4;

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;
import javax.swing.*;
import java.net.*;

public class MiniDraw4 extends JApplet  {

  protected DrawingCanvas canvas;
  protected ControlPanelView controlPanel;
  protected ToolBarView toolBar;
  protected MenuBarView menuBar;
  protected ToolList toolList;
  protected boolean isApplet = false;

  /* Constructors  */

  public MiniDraw4(boolean isApplet) { 
    this.isApplet = isApplet;
    if (!isApplet) {
      init();
    }
  }

  public MiniDraw4() {
    /* invoked as Applet */
    this(true);
  }

  public void init() {
    getContentPane().setLayout(new BorderLayout());
    canvas = createDrawingCanvas();
    getContentPane().add(canvas, BorderLayout.CENTER);
    controlPanel = createControlPanelView();
    getContentPane().add(controlPanel, BorderLayout.SOUTH);
    toolList = createToolList();
    toolBar = createToolBarView(toolList);
    getContentPane().add(toolBar, BorderLayout.WEST);
    menuBar = createMenuBarView(toolList);
    getContentPane().add (menuBar, BorderLayout.NORTH);
  }


  /*Factory methods  */
  protected DrawingCanvas createDrawingCanvas() {
    return new DrawingCanvas();
  }
  
  protected ControlPanelView createControlPanelView() {
    return new ControlPanelView(canvas); 
  }
  
 
  protected ToolBarView createToolBarView(ToolList toolList) {
    return new ToolBarView(toolList);
  }
  
  protected MenuBarView createMenuBarView(ToolList toolList) {
    return new MenuBarView(toolList);
  }
 
  
  /* Configure tool list used for ToolBar and MenuBar constructon */
  
  protected ToolList createToolList() {
    ToolList actions = new ToolList();

    actions.add(
                new ToolController("Freehand",
  	        getImageIcon("freehand.jpg"),
  	        "freehand drawing tool",
  	        canvas,
  		new FreehandTool(canvas)));

    actions.add(
  		new ToolController("Line",
  		getImageIcon("line.jpg"),
  		"Line drawing tool",
  		canvas,
  		new TwoEndShapeTool(canvas, new LineShape())));
  
    actions.add(
  		new ToolController("Rectangle",
  		getImageIcon("rectangle.jpg"),
  		"Rectangle drawing tool",
  		canvas,
  		new TwoEndShapeTool(canvas, new RectangleShape())));
  		
    actions.add(
  	        new ToolController("Oval",
  	        getImageIcon("oval.jpg"),
  		"Oval drawing tool",
  		canvas,
  		new TwoEndShapeTool(canvas, new OvalShape())));
    actions.add(
  		new ToolController("Text",
  		getImageIcon("text.jpg"),
  		"text drawing tool",
          	canvas,
  		new TextTool(canvas)));		
    actions.add(
  		new ToolController("Eraser",
  		getImageIcon("eraser.jpg"),
  		"Eraser drawing tool",
  		canvas,
  		new EraserTool(canvas)));
  
    return actions;
  }
  
  
  protected ImageIcon getImageIcon(String fileName) {
    if(isApplet) {
      try {
        URL url = new URL(getCodeBase(), fileName);
        return new ImageIcon(url);
      }
      catch(MalformedURLException e) {
        return null;
      }
    }
    else {
      return new ImageIcon(fileName);
    }
  }


  /* Main method  */

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("MiniDraw Fourth Iteration");
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(new MiniDraw4(false),
			      BorderLayout.CENTER);
    frame.addWindowListener(new AppCloser());
    frame.pack();
    frame.setSize(600, 400);
    frame.setVisible(true);
  }

  /* Inner class AppCLoser for terminating application  */
  /* when Close Window button of frame is clicked       */
   
  static class AppCloser extends WindowAdapter  {
    public void windowClosing(WindowEvent e) {
       System.exit(0);
    }
  }
}
