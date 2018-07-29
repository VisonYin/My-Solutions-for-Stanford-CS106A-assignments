/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// You fill in this part
	public void run() {
		putWholeRow();
		returnOrigin();
		pickTwo();
		while(frontIsClear()) {
			backToPut();
			pickTwo();
		}
		backToPut();
		oneLeft();
	}
	
	//机器人向东运动并将第一行每一格放置警报器
	public void putWholeRow() {
		putBeeper();
		while (frontIsClear()) {
			move();
			putBeeper();
		}
	}
	
	//机器人由东转向至西，并返回最西边
	public void returnOrigin() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnAround();
	}
	
	//机器人向东运动，并收集最靠西的两个警报器
	public void pickTwo() {
		move();
		while(!beepersPresent()) {
			if(frontIsClear()) 
				move();
			else
				break;
			
		}
		pickBeeper();
		if (frontIsClear()) {
			move();
			pickBeeper();
		}
	}
	
	//机器人收集两个警报器后，转向西方向返回，并在没有警报器的且最西边的格子上放置警报器
	public void backToPut() {
		turnAround();
		while(!beepersPresent()) {
			if (frontIsClear())
				move();
			else
				break;
			
		}
		turnAround();
		if (!beepersPresent())
			putBeeper();
		else {
			move();
			putBeeper();
		}
	}
	
	//机器人收集余下所有警报器，只留下处于最中间的一个
	public void oneLeft() {
		returnOrigin();
		while(beepersPresent()) {
			pickBeeper();
			move();
		}
		turnAround();
		move();
		putBeeper();		
	}
}
