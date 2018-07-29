/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// You fill in this part
	//���������СΪ1*n���������Σ�������ʹ�������㷨��
	//��һ��ÿ���һ�����Ͼ�����������֤�����������˳���
	//��ʱ���������ϰ�������ǰ��һ�񣬲�ת�������ϰ��Ҷ�������ķ��򣬿�ʼ����һ�з��þ��������Դ�ѭ��;
	//���������ϰ����˳�ѭ�����������
	public void run() {
		//���������СΪ1*n����������
		if (!frontIsClear()) {
			putBeeper();
			turnLeft();
			while(frontIsClear()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
				}
				else
					break;
			}
		}
		//һ������
		else {
			while(true) {
				putBeeper();
				while(frontIsClear()) {
					move();
					if (frontIsClear()) {
						move();
						putBeeper();
					}
					else
						break;
				}
				while(!facingNorth())
					turnLeft();
				if(frontIsClear())
					move();
				else
					break;
				while(facingNorth()||facingSouth()||(!frontIsClear())) {
					turnLeft();
				}
			}
		}
	}
}
