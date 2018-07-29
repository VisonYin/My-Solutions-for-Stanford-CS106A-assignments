/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;

public class CS106ATiles extends GraphicsProgram {
	
	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	private static final int TILE_WIDTH = 100;
	private static final int TILE_HEIGHT = 60;

	public void run() {
		/* You fill this in. */
		int xCenter = getWidth()/2;
		int yCenter = getHeight()/2;
		
		makeRect(xCenter-TILE_SPACE/2-TILE_WIDTH,yCenter-TILE_SPACE/2-TILE_HEIGHT);
		GLabel Label1 = makeLabel(xCenter-TILE_SPACE/2-TILE_WIDTH/2,yCenter-TILE_SPACE/2-TILE_HEIGHT/2);
		double x = Label1.getWidth();
		double y = Label1.getAscent();
		Label1.move(-x/2, y/2);
		makeRect(xCenter+TILE_SPACE/2,yCenter-TILE_SPACE/2-TILE_HEIGHT);
		makeLabel(xCenter+TILE_SPACE/2+TILE_WIDTH/2-x/2,yCenter-TILE_SPACE/2-TILE_HEIGHT/2+y/2);
		makeRect(xCenter-TILE_SPACE/2-TILE_WIDTH,yCenter+TILE_SPACE/2);
		makeLabel(xCenter-TILE_SPACE/2-TILE_WIDTH/2-x/2,yCenter+TILE_SPACE/2+TILE_HEIGHT/2+y/2);
		makeRect(xCenter+TILE_SPACE/2,yCenter+TILE_SPACE/2);
		makeLabel(xCenter+TILE_SPACE/2+TILE_WIDTH/2-x/2,yCenter+TILE_SPACE/2+TILE_HEIGHT/2+y/2);
	}
	
	public void makeRect(int x,int y) {
		GRect r = new GRect(x,y,TILE_WIDTH,TILE_HEIGHT);
		r.setFilled(false);
		add(r);
	}
	
	public GLabel makeLabel(double x,double y) {
		GLabel l = new GLabel("CS106A",x,y);
		l.setFont("SansSerif-18");
		add(l);
		return l;
	}
	
	
	
	
	
	
	
	
	
	
}

