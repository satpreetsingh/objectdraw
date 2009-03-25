package objectdraw2;


import java.awt.*;

public class RectangleShape implements TwoEndShape {

 public void draw(Graphics g, int x0, int y0, int x1, int y1) {
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
    g.drawRect(shapeX, shapeY, shapeWidth, shapeHeight);
  }
  
  public void drawOutline(Graphics g, int x0, int y0,
                                        int x1, int y1) {
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
      shapeWidth = (x0 -x1)+1;
    }
    if (y0 <= y1) {
      shapeY = y0;
      shapeHeight = (y1-y0)+1;
    }
    else {
      shapeY = y1;
      shapeHeight = (y0-y1)+1;
    }
    g.drawRect(shapeX, shapeY, shapeWidth, shapeHeight);
  }

}
