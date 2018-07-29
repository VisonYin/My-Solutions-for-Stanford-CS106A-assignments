/*
 * File: RandomCircles.java
 * ------------------------
 * This program draws a set of 10 circles with different sizes,
 * positions, and colors.  Each circle has a randomly chosen
 * color, a randomly chosen radius between 5 and 50 pixels,
 * and a randomly chosen position on the canvas, subject to
 * the condition that the entire circle must fit inside the
 * canvas without extending past the edge.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
	private final int MIN_RADIUS = 5;
	private final int MAX_RADIUS = 50;
	private final int NUMS_CIRCLE = 10;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	public void run ()	{
		for (int i = 0; i < NUMS_CIRCLE; i ++)	{
			GOval circle = drawACircle();
		}
	}
	
	public GOval drawACircle()	{
		int r = rgen.nextInt(MIN_RADIUS, MAX_RADIUS);
		int x = rgen.nextInt(0, getWidth() - 2 * r);
		int y = rgen.nextInt(0, getHeight() - 2 * r);
		GOval cir = new GOval (2 * r, 2 * r);
		cir.setColor(rgen.nextColor());
		cir.setFilled(true);
		add(cir, x ,y);
		return cir;
	}
}
