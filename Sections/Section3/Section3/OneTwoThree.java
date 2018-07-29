/*
 * File: OneTwoThree.java
 * -------------------
 * This program is designed to test your understanding of parameters, 
 * return values, and instance variables.
 */

import acm.program.*;

public class OneTwoThree extends ConsoleProgram {

	public void run() {
		int a = 100;
		addOne();
		addTwo(a);
		a = addThreeAndReturnResult(a);
		println("run: a = " + a + ", b = " + b);
	}

	private void addOne() {
		int a = 101;
		b++;
		println("addOne: a = " + a + ", b = " + b); 
	}

	private void addTwo(int a) {
		a += 2;
		b += 2;
		println("addTwo: a = " + a + ", b = " + b);
	}

	private int addThreeAndReturnResult(int a) {
		a += 3;
		b += 3;	
		println("addThreeAndReturnResult: a = " + a + ", b = " + b);
		return a;
	}

	/* Private instance variable */
	private int b = 200;	

}
