/*
 * File: InteractiveKarel.java
 * --------------------
 * This program lets the user control Karel as it moves and turns
 * within the canvas window.
 */

import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import javax.swing.*;

/* Simulates a simplified Karel the Robot through use of GUI interactors. */
public class InteractiveKarel extends GraphicsProgram {  
	
	/* The number of pixels wide/tall for the Karel images */
	private static final int KAREL_SIZE = 64;

	/* The image of Karel currently displayed on the canvas. */
	private GImage karel;
	
	/* The direction (NORTH, SOUTH, EAST, WEST) Karel is facing. */
	private String direction; 

	/* Sets up GUI components and Karel's initial image. */
	public void init() {    
		add(new JButton("move"), SOUTH); 
		add(new JButton("turnLeft"), SOUTH);
		addActionListeners(); 
	} 

	/* Add our graphics once the canvas is onscreen. */
	public void run() {
		karel = new GImage("KarelEast.jpg");
		direction = EAST;
		add(karel, 0, 0);
	}

	/* When we get an interaction, update Karel accordingly. */
	public void actionPerformed(ActionEvent event) { 
		String command = event.getActionCommand(); 
		if (command.equals("move")) { 
			moveKarel();
		} else if (command.equals("turnLeft")) { 
			turnLeftKarel(); 
		}
	}

	/* Moves Karel one step in the current direction. */
	private void moveKarel() { 
		double newX = karel.getX();
		double newY = karel.getY();
		if (direction.equals(NORTH)) {
			newY -= KAREL_SIZE;
		} else if (direction.equals(SOUTH)) {
			newY += KAREL_SIZE;
		} else if (direction.equals(EAST))  {
			newX += KAREL_SIZE;
		} else if (direction.equals(WEST))  {
			newX -= KAREL_SIZE;
		}

		if (isKarelOnScreen(newX, newY)) { 
			karel.setLocation(newX, newY); 
		} 
	}

	/* Causes Karel to turn 90 degrees to the left (counter-clockwise). */
	private void turnLeftKarel() { 
		if (direction.equals(NORTH)) {
			direction = EAST;
		} else if (direction.equals(EAST)) {
			direction = SOUTH;
		} else if (direction.equals(SOUTH))  {
			direction = WEST;
		} else if (direction.equals(WEST))  {
			direction = NORTH;
		} 

		karel.setImage("Karel" + direction + ".jpg");
	} 

	/* Returns whether Karel would be on-screen at the given x/y position. */
	private boolean isKarelOnScreen(double x, double y) { 
		return x >= 0 && y >= 0 && x + KAREL_SIZE <= getWidth() 
				&& y + KAREL_SIZE <= getHeight(); 
	}
}
