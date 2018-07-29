/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		println("This program finds the largest and the smallest numbers");
		int c = readInt("?");
		if (c==0)
			println("You did not enter a valid num!");
		else
		{
			int max = c;
			int min = c;
			while (c!=0) {
				c = readInt("?");
				if (c==0)
					break;
				if (max<c)
					max = c;
				if (min>c)
					min = c;
			}
			println("smallest:"+min);
			println("largest:"+max);
		}
		
		
	}
	
	
}

