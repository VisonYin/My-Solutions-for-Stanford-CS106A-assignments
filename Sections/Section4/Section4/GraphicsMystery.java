/*
 * File: GraphicsMystery.java
 * ------------------
 * This program doesn't do anything useful and exists only to test
 * your understanding of method calls and parameter passing with
 * objects and primitives.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class GraphicsMystery extends GraphicsProgram {
	public void run() {
		double size = 100;
      int x = mystery(50, 50);
      int y = mystery(x, -25);
      GRect r = new GRect(size, size/3);
      paintShop(r);
      add(r, x, y);
      System.out.println(r);
	}

   private void paintShop(GRect r) {
      r.setFilled(true);
      r.setColor(Color.BLUE);
   }

   private int mystery(int var, int delta) {
      unknown(var);
      for (int i = 0; i < 4; i++) {
         if (i * 100 <= var) {
            var += delta;
         }
      }
      return var;
   }

   private void unknown(int var) {
      var += 100;
   }  
}
