


import java.awt.*;
import java.util.*;

public class ToolList {
   
  private Vector<ToolController> list;

  public ToolList() {
   list = new Vector<ToolController>(5,1);
  }
  
  public void add(ToolController item) {
    list.add(item);
 }

  public ToolListIterator iterator() {
    return new ToolListIterator(list);
  }

}
