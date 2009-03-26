package objectdraw1;

import java.awt.*;
import java.awt.event.*;

public class ControlPanelController
implements ActionListener, ItemListener {
	;
	protected DrawingCanvas canvas;

	ControlPanelController(DrawingCanvas c) {
		canvas = c;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed in ControlPanelController -> [Clear]");  
		canvas.clearCanvas();
		canvas.clearObjs();
	}

	public void itemStateChanged(ItemEvent e)  {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			canvas.setpenColor(itemToColor(e.getItem()));

		if (canvas.highlightedObj != null) {
			System.out.println("From ControlPanelController: changing object color");
			canvas.highlightedObj.setColor(itemToColor(e.getItem())); 			 
			canvas.clearCanvas();
			canvas.redrawObjs();
			canvas.highlightedObj.drawObjBoundingBox(canvas.getimageBufferGraphics());
		}


		}
	}

	protected Color itemToColor(Object item) {

		if("black".equals(item)) {
			return Color.black;
		}
		else if("blue".equals(item)) {
			return Color.blue;
		}
		else if("green".equals(item)) {
			return Color.green;
		}
		else {
			return Color.red;
		}
	}

} 
