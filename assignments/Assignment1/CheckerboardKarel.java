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
	//除了世界大小为1*n的特殊情形，都可以使用以下算法：
	//在一行每间隔一个放上警报器，并保证放置完后机器人朝北
	//此时若北面无障碍，则向北前进一格，并转向至无障碍且东西朝向的方向，开始在下一行放置警报器，以此循环;
	//若北面有障碍，退出循环，任务完成
	public void run() {
		//处理世界大小为1*n的特殊情形
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
		//一般情形
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
