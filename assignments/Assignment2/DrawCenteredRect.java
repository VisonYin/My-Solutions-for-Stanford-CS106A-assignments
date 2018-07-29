/*
 * File: DrawCenteredRect.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the DrawCenteredRect problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class DrawCenteredRect extends GraphicsProgram {
	
	/** Size of the centered rect */
	private static final int WIDTH = 350;
	private static final int HEIGHT = 270;

	public void run() {
		/* You fill this in. */
		makeRec();	
	}
	
	private GRect makeRec() {
		GRect r= new GRect(WIDTH,HEIGHT);
		int x= (getWidth()-WIDTH)/2;
		int y= (getHeight()-HEIGHT)/2;
		r.setColor(Color.BLUE);
		r.setFilled(true);
		add(r,x,y);
		return r;
	}
}

