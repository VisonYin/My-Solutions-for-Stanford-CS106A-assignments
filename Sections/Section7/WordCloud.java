/*
 * File: WordCloud.java
 * --------------------
 * This program allows the user to create a set of labels and then drag
 * them around in the window.
 */

import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class WordCloud extends GraphicsProgram {

	public void init() {
		contents = new HashMap<String,GLabel>();
		createController();
		addActionListeners();
		addMouseListeners();
	}

	/* Creates the control strip at the bottom of the window */
	private void createController() {
		nameField = new JTextField(MAX_NAME);
		nameField.addActionListener(this); 	 // Detects ENTER key pressed
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		add(new JLabel("Name"), SOUTH);
		add(nameField, SOUTH);
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
	}

	/* Adds a label with the given name at the center of the window */
	private void addLabel(String name) {
		GLabel label = new GLabel(name);
		double labelX = getWidth() / 2.0 - label.getWidth() / 2.0;
		double labelY = getHeight() / 2 + label.getAscent() / 2.0;
		add(label, labelX, labelY);
		contents.put(name, label);
	}

	/* Removes all labels in the contents table */
	private void removeContents() {
		for (String labelName : contents.keySet()) {
			remove(contents.get(labelName));
		}
		contents.clear();		// Clear all entries in the hashmap
	}

	/* Called in response to button actions */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		// Detect both clicks and ENTER for adding a new label
		if (source == addButton || source == nameField) {
			addLabel(nameField.getText());
		} else if (source == removeButton) {
			String text = nameField.getText();
			if (contents.containsKey(text)) {
				remove(contents.get(text));
				contents.remove(text);
			}
		} else if (source == clearButton) {
			removeContents();
		}
	}

	/* Called on mouse press to record the coordinates of the click */
	public void mousePressed(MouseEvent e) {
		last = new GPoint(e.getPoint());
		currentLabel = (GLabel)getElementAt(last);
	}

	/* Called on mouse drag to reposition the object */
	public void mouseDragged(MouseEvent e) {
		if (currentLabel != null) {
			currentLabel.move(e.getX() - last.getX(),
				e.getY() - last.getY());
			last = new GPoint(e.getPoint());
		}
	}

	/* Private constants */
	private static final int MAX_NAME = 25;

	/* Private instance variables */
	private HashMap<String,GLabel> contents;
	private JTextField nameField;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private GLabel currentLabel;
	private GPoint last;
}
