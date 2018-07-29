
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.*;

import acm.program.*;

public class BouncingBalls extends GraphicsProgram {

	private static final int DELAY = 3;
	Ball ball1;
	Ball ball2;
	Ball ball3;
		
	public void init() {
		JButton ballButton = new JButton("Add ball");
		add(ballButton, SOUTH);
		addActionListeners();
	}
	
	public void run() {
		// TODO: your code here		
		ball1 = new Ball(getWidth(), getHeight());
		ball2 = new Ball(getWidth(), getHeight());
		ball3 = new Ball(getWidth(), getHeight());
		while (true)	{	
			ball1.heartbeat(getWidth(), getHeight());
			ball2.heartbeat(getWidth(), getHeight());
			ball3.heartbeat(getWidth(), getHeight());
			pause(DELAY);
		}		
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO: your code here
		String cmd = e.getActionCommand();
		if (cmd.equals("Add ball"))	{
			add(ball1.getGOval());
			add(ball2.getGOval());
			add(ball3.getGOval());
		}
	}

}