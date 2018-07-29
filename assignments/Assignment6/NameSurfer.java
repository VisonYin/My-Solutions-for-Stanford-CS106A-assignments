/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {
	
	JTextField textField;
	NameSurferGraph nsg;
	NameSurferDataBase nsdb;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		
		JLabel label = new JLabel("Name:");
		add(label,NORTH);
		textField = new JTextField(TEXT_FIELD_WIDTH);
		textField.setActionCommand("Graph");
		add(textField, NORTH);
		JButton button1 = new JButton("Graph");
		JButton button2 = new JButton("Clear");
		add(button1, NORTH);
		add(button2, NORTH);	
		nsg = new NameSurferGraph();
		add(nsg);		
		nsdb = new NameSurferDataBase(NAMES_DATA_FILE);	
		addActionListeners();
		textField.addActionListener(this);

	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// You fill this in //
		String str = e.getActionCommand();
		if (str.equals("Graph"))	{
			if (nsdb.findEntry(textField.getText()) != null)
				nsg.addEntry(nsdb.findEntry(textField.getText()));	
		}
		else
			nsg.clear();
	}
}
