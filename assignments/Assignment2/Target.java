/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	
	public static final int MAX_RADIUS=72;
	public static final int MID_RADIUS=(int)(0.65*MAX_RADIUS+0.5);
	public static final int MIN_RADIUS=(int)(0.3*MAX_RADIUS+0.5);
	
	public void run() {
		/* You fill this in. */
		makeCircle(MAX_RADIUS,Color.RED);
		GOval midCircle= makeCircle(MID_RADIUS,Color.WHITE);
		midCircle.sendToFront();
		GOval minCircle= makeCircle(MIN_RADIUS,Color.RED);
		minCircle.sendToFront();
	}
	
	public GOval makeCircle(int r,Color color) {
		int d=2*r;
		GOval c=new GOval(d,d);
		int x=getWidth()/2-r;
		int y=getHeight()/2-r;
		c.setFilled(true);
		c.setColor(color);
		add(c,x,y);
		return c;
	}
}
