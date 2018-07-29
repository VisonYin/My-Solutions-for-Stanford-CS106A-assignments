/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;


public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 23;
	
	public void run() {
		/* You fill this in. */
		int rowsNum=BRICKS_IN_BASE;
		for (int i=0;i<rowsNum;i++) {
			buildSingleRow(i+1);
		}
	}
	
	public void buildSingleRow(int rowIndex) {
		double bricksNum= BRICKS_IN_BASE-rowIndex+1;
		
		int x=(int)((getWidth()-bricksNum*BRICK_WIDTH)/2);
		int y=getHeight()-BRICK_HEIGHT*rowIndex;
			
		for (int i=0;i<bricksNum;i++) {
			GRect rec=new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
			rec.setFilled(false);
			add(rec);
			x+=BRICK_WIDTH;
		}
	}
}

