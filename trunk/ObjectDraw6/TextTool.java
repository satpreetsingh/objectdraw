


import java.awt.*;
import java.awt.event.*;

public class TextTool implements KeyboardTool {

	protected DrawingCanvas canvas;
	protected Point startingPosition;
	protected StringBuffer bufferText;
	protected Font font = new Font("Serif", Font.BOLD, 24);

	protected TextObj textObj;

	public TextTool(DrawingCanvas c) {
		canvas = c;
		System.out.println("Constructor of TextTool");	   
	}

	public void mousePressed(MouseEvent e)  {
		/* Establish Focus and starting position for text display */
		System.out.println("mousePressed of TextTool");	   

		canvas.requestFocus();
		startingPosition = e.getPoint();
		Graphics iBGraphics = canvas.getimageBufferGraphics();
		iBGraphics.setFont(font);
		bufferText = new StringBuffer();

		this.textObj = new TextObj();
		this.textObj.setColor(iBGraphics.getColor());
		this.textObj.setStartPoint(startingPosition);
		
		this.textObj.setFont(font, iBGraphics);
		
	}

	public void keyPressed(KeyEvent e)  {
		/* add character to string buffer */
		char nextChar = e.getKeyChar(); 
		bufferText.append(nextChar); 
		Graphics iBGraphics = canvas.getimageBufferGraphics();
		iBGraphics.drawString(bufferText.toString(), startingPosition.x,
				startingPosition.y); 

		this.textObj.addChar(nextChar);
//		System.out.println("Adding char to StringBuffer of textObj");	   

		canvas.repaint();
	}


	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased of TextTool");	   
		canvas.objsOnCanvas.add(textObj);
	} 


	public void mouseDragged(MouseEvent e) { }
	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }
}
