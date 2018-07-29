/*
 * File: RubberBanding.java
 * ------------------------
 * This program allows users to create lines on the graphics
 * canvas by clicking and dragging with the mouse.  The line
 * is redrawn from the original point to the new endpoint, which
 * makes it look as if it is connected with a rubber band.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

/** This class allows users to drag lines on the canvas */
public class RubberBanding extends GraphicsProgram {
	
	private GLine line;
	
	public void run ()	{ 
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent e)	{
		int x = e.getX();
		int y = e.getY();
		line = new GLine(x, y, x, y);
		add(line);
	}
	
	public void mouseDragged(MouseEvent e)	{
		int x = e.getX();
		int y = e.getY();
		line.setEndPoint(x, y);
	}
}
