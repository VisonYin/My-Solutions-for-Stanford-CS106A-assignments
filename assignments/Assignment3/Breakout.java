/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

import javax.swing.*;

public class Breakout extends GraphicsProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;
	

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 4;

	// Width of each brick, in pixels
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;

	// Offset of the top brick row from the top, in pixels
	public static final double BRICK_Y_OFFSET = 70;

	// Dimensions of the paddle
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	// Offset of the paddle up from the bottom 
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels
	public static final double BALL_RADIUS = 10;

	// The ball's vertical velocity.
	public static final double VELOCITY_Y = 5.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)
	public static final double DELAY = 1000.0 / 60.0;

	// Number of turns 
	public static final int NTURNS = 3;
	
	//球的运动速度
	private double vx, vy;
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private GRect paddle = null;
	
	private int numOfLeftBricks = NBRICK_COLUMNS * NBRICK_ROWS;
	
	private int turns = NTURNS;

	public void run() {
	
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);

		/* You fill this in, along with any subsidiary methods */
		
		//搭建砖块
		setUp();
		//制造挡块，并让它随鼠标移动而移动
		paddle = createPaddle();
		
		addMouseListeners();
		
		//回合开始
		while (turns > 0)	{
			if (numOfLeftBricks == 0)	{
				JOptionPane.showMessageDialog(null, "You win!Click to end the game.");
				break;
			}
			
			begin();
			turns --;
		}	
		
		if (turns == 0 && numOfLeftBricks > 0)
			JOptionPane.showMessageDialog(null, "You lose!");
		
	}
	
	//挡块随鼠标移动
	public void mouseMoved(MouseEvent e)	{
		double x = e.getX();
		double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT; 
		if (x <= 0)
			x = 0;
		if (x >= getWidth() - PADDLE_WIDTH)
			x = getWidth() - PADDLE_WIDTH;
		paddle.setLocation(x, y);
		
	}
	
	public void setUp()	{
		//搭建砖块
		double xStart = (getWidth() - (NBRICK_COLUMNS - 1.0) * BRICK_SEP - NBRICK_COLUMNS * BRICK_WIDTH)/2;
		for (int i = 0; i < 10; i++)	{
			double x =xStart + i * (BRICK_SEP + BRICK_WIDTH);
			drawOneColumn(x);
		}
	}
	
	public void begin()	{
		
		JOptionPane.showMessageDialog(null, "You have " + turns + " left.Click the screen to go on!");
		
		GOval ball = createBall();
		waitForClick();
		vx = rgen.nextDouble(VELOCITY_X_MIN, VELOCITY_X_MAX);
		if (rgen.nextBoolean(0.5))
			vx = -vx;
		
		vy = VELOCITY_Y;
		
		while(! turnOver(ball))	{
			//球若碰到墙壁则反弹
			if (hitLeftWall(ball) || hitRightWall(ball))
				vx = - vx;
			if (hitBottomWall(ball) || hitTopWall(ball))
				vy = - vy;
			
			GObject collider = getCollidingObject(ball);
			if (collider != null)	{
				AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
				if (collider != paddle)	{
					vy = - vy;
					remove(collider);
					numOfLeftBricks -= 1;
					bounceClip.play();
				}
				else	{
					//碰撞paddle下侧时不反弹,改善粘球bug
					if (vy > 0)	{
						vy = - vy;
						bounceClip.play();
					}
				}
							
			}
			
			//球每秒运动量
			ball.move(vx,  vy);
			
			pause(DELAY);
		}	
	}

	//搭建一列的砖块，参数x为这一列砖块的x坐标
	public void drawOneColumn(double x)	{
		for (int i = 0; i < 10; i++)  {
			double y = BRICK_Y_OFFSET + i * (BRICK_SEP + BRICK_HEIGHT);
			GRect rec = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			rec.setFilled(true);
			if (i == 0 || i == 1)
				rec.setColor(Color.RED);
			else if (i == 2 || i == 3)
				rec.setColor(Color.ORANGE);
			else if (i == 4 || i == 5)
				rec.setColor(Color.YELLOW);
			else if (i == 6 || i == 7)
				rec.setColor(Color.GREEN);
			else
				rec.setColor(Color.CYAN);
			add (rec, x, y);
		}
	}
	
	private GRect createPaddle() {
		double y = getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
		double x = (getWidth() - PADDLE_WIDTH)/2;
		GRect rec = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		rec.setFilled(true);
		rec.setColor(Color.BLACK);
		add(rec, x, y);
		return rec;
	}
	
	private GOval createBall()	{
		GOval ball = new GOval(2*BALL_RADIUS, 2*BALL_RADIUS);
		double x = getWidth()/2 - BALL_RADIUS;
		double y = getHeight()/2 - BALL_RADIUS;
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball, x, y);
		return ball;
	}
	
	private boolean hitLeftWall(GOval ball)	{
		return ball.getX() <= 0;
	}
	
	private boolean hitRightWall(GOval ball)	{
		return getWidth() - ball.getX() <= ball.getWidth();
	}
	
	private boolean hitTopWall(GOval ball)	{
		return ball.getY() <= 0;
	}
	
	private boolean hitBottomWall(GOval ball)	{
		return getHeight() - ball.getY() <= ball.getHeight();				
	}
	
	//获取与球发生碰撞的物体
	private GObject getCollidingObject(GOval ball)	{
		GObject obj1 = getElementAt(ball.getX(), ball.getY());
		GObject obj2 = getElementAt(ball.getX() + ball.getWidth(), ball.getY() );
		GObject obj3 = getElementAt(ball.getX(), ball.getY()+ball.getHeight());
		GObject obj4 = getElementAt(ball.getX() + ball.getWidth(), ball.getY() + ball.getHeight());
		if (obj1 != null)
			return obj1;
		else if (obj2 != null)
			return obj2;
		else if (obj3 != null) 
			return obj3;
		else if (obj4 != null)
			return obj4;
		else
			return null;
	}
	
	//回合结束条件：未接到小球或者所有砖块清除
	private boolean turnOver(GOval ball)	{
		if (hitBottomWall(ball) || numOfLeftBricks == 0)	{
			remove(ball);
			return true;
		}
			
		else
			return false;
	}
	
}