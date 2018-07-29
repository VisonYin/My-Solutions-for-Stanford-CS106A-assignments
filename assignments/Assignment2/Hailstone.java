/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		int cnt = 0;
		int a = readInt("Enter a number:");
		int b = a;
	
		while(b!=1) {
			if (a%2==0) {
				b = a/2;
				println(a+" is even,so I make 3n + 1: "+b);
				a = b;
				cnt++;
		}
			else {
				b = 3*a+1;
			    println(a+" is odd ,so I take half: "+b);
			    a = b;
			    cnt++;
		}
		}
	
	println("The process took "+cnt+" to reach 1");
	}
}
