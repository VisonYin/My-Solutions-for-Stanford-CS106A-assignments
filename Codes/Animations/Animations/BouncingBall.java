/*
 * File: GravityBall.java
 * -----------------------------
 * Has a ball bounce around the screen and applies a downward
 * force on the ball (gravity).
 */

import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class BouncingBall extends GraphicsProgram	{
	
	//每一帧的延迟（单位：毫秒）
	private static final int DELAY = 2;
	//小球在Y方向上速度
	private static final double INITIAL_VY = 2;
	//小球在X方向上速度
	private static final double INITIAL_VX = 2;
	//小球半径
	private static final int BALL_RADIUS = 15;
	//小球颜色
	private static final Color BALL_COLOR = Color.RED;
	
	public void run() {
		//Set up
		GOval ball =makeBall();
		double vx=INITIAL_VX;
		double vy=INITIAL_VY;
		
		//小球运动
		waitForClick();
		while(true) {
			makeShadow(ball);
			
			if (hitLeftWall(ball)||hitRightWall(ball))
				vx=-vx;
			if (hitTopWall(ball)||hitBottomWall(ball))
				vy=-vy;
			
			ball.move(vx, vy);
			
			pause(DELAY);
		}
	}
	
	public GOval makeBall() {
		double diamete=2*BALL_RADIUS;
		GOval b=new GOval(1,1,diamete,diamete);
		b.setColor(BALL_COLOR);
		b.setFilled(true);
		add(b);
		return b;
	}
	
	public void makeShadow(GOval ball) {
		GOval s=new GOval(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight());
		s.setColor(Color.LIGHT_GRAY);
		s.setFilled(true);
		add(s);
		s.sendToBack();
	}
	
	public boolean hitLeftWall(GOval ball) {
		return ball.getX()<=0;
	}
	
	public boolean hitRightWall(GOval ball) {
		return ball.getX()>=getWidth()-ball.getWidth();
	}
	
	public boolean hitTopWall(GOval ball) {
		return ball.getY()<=0;
	}
	
	public boolean hitBottomWall(GOval ball) {
		return ball.getY()>=getHeight()-ball.getHeight();
	}	
}	

