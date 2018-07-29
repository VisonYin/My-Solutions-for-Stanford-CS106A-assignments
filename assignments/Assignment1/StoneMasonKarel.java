/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// You fill in this part
	public void run() {
		fillColumn();
		backToOrigin();
		while(frontIsClear()) {
			goToNextColumn();
			fillColumn();
			backToOrigin();
		}
		
	}
	
	public void fillColumn () {
		if (!beepersPresent())
			putBeeper();
		turnLeft();
		while (frontIsClear()) {
			move();
			if (!beepersPresent())
				putBeeper();
		}
	}
	
	public void backToOrigin() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}
	
	public void goToNextColumn() {
		for (int i=0;i<4;i++) {
			move();
		}
	}
}