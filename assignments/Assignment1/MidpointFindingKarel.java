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
	
	//���������˶�������һ��ÿһ����þ�����
	public void putWholeRow() {
		putBeeper();
		while (frontIsClear()) {
			move();
			putBeeper();
		}
	}
	
	//�������ɶ�ת��������������������
	public void returnOrigin() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnAround();
	}
	
	//���������˶������ռ����������������
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
	
	//�������ռ�������������ת�������򷵻أ�����û�о��������������ߵĸ����Ϸ��þ�����
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
	
	//�������ռ��������о�������ֻ���´������м��һ��
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
