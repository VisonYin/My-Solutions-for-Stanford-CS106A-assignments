/*
 * File: Countdown.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the Countdown problem.
 */

import acm.program.*;

public class Countdown extends ConsoleProgram {
	
	/** Count down to 0 from this number */
	private static final int START = 10;

	public void run() {
		/* You fill this in. */
		setFont("Courier-24");
		
		for (int i=START;i>0;i--) {
			println(i);
		}
		
		println("Liftoff");
	}
}

