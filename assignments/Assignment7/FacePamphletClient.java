/* 
 * File: FacePamphletClient.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management client.  Remember to update this comment!
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.*;

public class FacePamphletClient extends GraphicsProgram {

	/** Number of characters for each of the text input fields */
	public static final int TEXT_FIELD_SIZE = 15;

	/** Name of font used to display the application message at the
	 *  bottom of the display canvas */ 
	public static final String MESSAGE_FONT = "Dialog-18";

	/** Name of font used to display the name in a user's profile */
	public static final String PROFILE_NAME_FONT = "Dialog-24";

	/** The number of pixels in the vertical margin between the bottom 
	 *  of the canvas display area and the baseline for the message 
	 *  text that appears near the bottom of the display */
	public static final double BOTTOM_MESSAGE_MARGIN = 20;

	/** The number of pixels in the hortizontal margin between the 
	 *  left side of the canvas display area and the Name, Image, and 
	 *  Status components that are display in the profile */	
	public static final double LEFT_MARGIN = 20;	

	/** The number of pixels in the vertical margin between the top 
	 *  of the canvas display area and the top (NOT the baseline) of 
	 *  the Name component that is displayed in the profile */	
	public static final double TOP_MARGIN = 20;	

	/** The address of the server that should be contacted when sending
	 * any Requests. */
	private static final String HOST = "http://localhost:8000/";
	
	private JTextField nameField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField statusField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField pictureField = new JTextField(TEXT_FIELD_SIZE);
	private JTextField friendField = new JTextField(TEXT_FIELD_SIZE);
	
	private GLabel currentMessage;
	private GLabel profileName;

	/** 
	 * Init is called before the window is created 
	 */
	public void init() {
		// your code here
		add(new JLabel("Name"), NORTH);
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		add(pictureField, WEST);
		add(new JButton("Change Picture"), WEST);
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
		addActionListeners();
	}
	
	/** 
	 * Run is called after the window is created 
	 */
	public void run() {
		// your code here
	}

	/**
	 * This method is an example of sending a request to the server.
	 * You can delete it when you are ready.
	 */
		
	private void addProfileRequest()	{
		try	{
			Request add = new Request("addProfile");
			add.addParam("name", nameField.getText());
			SimpleClient.makeRequest(HOST, add);
			String result ="New profile created";
			drawMessage(result);
		}	catch (IOException e)	{
			String errorMessage = e.getMessage();
			drawMessage(errorMessage);
		}
	}
	
	private void deleteProfileRequest()	{
		try	{
			Request delete = new Request("deleteProfile");
			delete.addParam("name", nameField.getText());
			SimpleClient.makeRequest(HOST, delete);
			String result = "Profile of " + nameField.getText() + " deleted";
			removeAll();
			drawMessage(result);
		}	catch (IOException e)	{
			String errorMessage = e.getMessage();
			removeAll();
			drawMessage(errorMessage);
		}
	}
	
	private void lookupProfileRequest()	{
		try	{
			Request contains = new Request("containsProfile");
			contains.addParam("name", nameField.getText());
			String result = SimpleClient.makeRequest(HOST, contains);
			String display;
			if (result.equals("true"))	{
				display = "Displaying " + nameField.getText();
				drawProfileName(nameField.getText());
				drawMessage(display);
			}
			if (result.equals("false")){
				display = "A profile with the name " + nameField.getText() + " does not exist";
				removeAll();
				drawMessage(display);
			}
			
				
		}	catch (IOException e)	{
			String errorMessage = e.getMessage();
			removeAll();
			drawMessage(errorMessage);
			
		}
	}
	
	public void actionPerformed(ActionEvent actionEvent)	{
		String buttonStr = actionEvent.getActionCommand();
		
		if (buttonStr.equals("Add"))	{
			if (nameField.getText() != null)
				addProfileRequest();
		}
		
		if (buttonStr.equals("Delete"))	{
			if (nameField.getText() != null)	{
				deleteProfileRequest();
			}	
		}
		
		if (buttonStr.equals("Lookup"))	{
			if (nameField.getText() != null)
				lookupProfileRequest();
		}
	}

	/**
	 * Again, this is a helper method that we wrote for the "pingTheServer"
	 * example (above). You should not include it in your final submission.
	 */
	
	private void drawMessage(String text)	{
		if (currentMessage != null)
			remove(currentMessage);
		currentMessage = new GLabel(text);
		currentMessage.setFont(MESSAGE_FONT);
		double x = (getWidth() - currentMessage.getWidth())/2;
		double y = getHeight() - BOTTOM_MESSAGE_MARGIN;
		add(currentMessage, x, y);
	}
	
	private void drawProfileName(String name)	{
		if (profileName != null)
			remove(profileName);
		profileName = new GLabel(name);
		profileName.setFont(PROFILE_NAME_FONT);
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN + profileName.getAscent()/2;
		add(profileName, x, y);
	}
}
