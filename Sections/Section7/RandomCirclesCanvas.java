/*
* File: RandomCirclesCanvas.java
* ------------------------
* This GCanvas subclass adds the ability to also draw random circles.
* Each circle has a randomly chosen color, a randomly chosen
* radius between 5 and 50 pixels, and a randomly chosen
* position on the canvas, subject to the condition that
* the entire circle must fit inside the canvas without
* extending past the edge.
*/

import acm.graphics.*;
import acm.util.*;

public class RandomCirclesCanvas extends GCanvas {
	
	/** Minimum radius */
	private static final double MIN_RADIUS = 5;
	
	/** Maximum radius */
	private static final double MAX_RADIUS = 50;
	
	public void drawRandomCircle() {
		double r = rgen.nextDouble(MIN_RADIUS, MAX_RADIUS);
		double x = rgen.nextDouble(0, getWidth() - 2 * r);
		double y = rgen.nextDouble(0, getHeight() - 2 * r);
		GOval circle = new GOval(x, y, 2 * r, 2 * r);
		circle.setFilled(true);
		circle.setColor(rgen.nextColor());
		add(circle);	// adds it to ourself!
	}
	
	/* Private instance variable */
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
