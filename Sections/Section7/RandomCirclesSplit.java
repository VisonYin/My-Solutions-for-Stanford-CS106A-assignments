/*
 * File: RandomCirclesSplit.java
 * ------------------------
 * This program draws a set of circles with random sizes,
 * positions, and colors.  The number of circles drawn is
 * given by the user.
 */

import acm.program.*;

public class RandomCirclesSplit extends ConsoleProgram {
	
	RandomCirclesCanvas canvas;

	public void init() {
		canvas = new RandomCirclesCanvas();
		add(canvas);
	}
	
	public void run() {
		int numCircles = readInt("# random circles: ");
		for (int i = 0; i < numCircles; i++) {
			canvas.drawRandomCircle();
		}
	}
}
