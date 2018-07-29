/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	private ArrayList<NameSurferEntry> entryList = new ArrayList<NameSurferEntry>();
	private ArrayList<Color> colorList = new ArrayList<Color>();
	
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
		this.colorList.add(Color.BLACK);
		this.colorList.add(Color.RED);
		this.colorList.add(Color.BLUE);
		this.colorList.add(Color.MAGENTA);
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		// You fill this in //
		this.removeAll();
		this.entryList.clear();
		//重绘初始线条
		this.update();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		entryList.add(entry);
		//entryList增加新元素后，重新绘制
		this.update();
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		// You fill this in //
		//绘制初始线条
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, this.getWidth(), GRAPH_MARGIN_SIZE);
		GLine bottomLine = new GLine(0, this.getHeight()-GRAPH_MARGIN_SIZE, this.getWidth(), this.getHeight()-GRAPH_MARGIN_SIZE);
		this.add(topLine);
		this.add(bottomLine);
		for (int i = 0;i < NDECADES; i++)	{
			int d = this.getWidth()/NDECADES;
			GLine verticalLine = new GLine(i*d, 0, i*d, this.getHeight());
			this.add(verticalLine);
			String year = Integer.toString(START_DECADE + i*10);
			GLabel yearLabel = new GLabel(year);
			this.add(yearLabel, i*d, this.getHeight()-DECADE_LABEL_MARGIN_SIZE);
		}
		//绘制entryList
		for (int i = 0;i < entryList.size();i ++)	{
			if (i%4 == 1)
				this.draw(entryList.get(i), colorList.get(1));
			else if (i%4 == 2)
				this.draw(entryList.get(i), colorList.get(2));
			else if (i%4 == 3)
				this.draw(entryList.get(i), colorList.get(3));
			else
				this.draw(entryList.get(i), colorList.get(0));
				
		}
	}
	
	//用color颜色画某名字的排名图表
	public void draw(NameSurferEntry nse, Color color)	{
		GCompound objects = new GCompound();
		int yBegin = GRAPH_MARGIN_SIZE;
		double yd = 1.0*(this.getHeight() - 2*GRAPH_MARGIN_SIZE)/( MAX_RANK-1);
		double xd = 1.0*this.getWidth()/NDECADES;
		for (int i = 1;i < NDECADES;i ++)	{
			double x1 = (i-1)*xd;
			double x2 = i*xd;
			double y1 = yBegin + yd*(nse.getRank(i-1)-1);
			double y2 = yBegin + yd*(nse.getRank(i)-1);
			if (nse.getRank(i-1) == 0)
				y1 = this.getHeight() - GRAPH_MARGIN_SIZE;
			if (nse.getRank(i) == 0)
				y2 = this.getHeight() - GRAPH_MARGIN_SIZE;
			GLine line = new GLine(x1, y1, x2, y2);
			String str = "";
			if (nse.getRank(i-1) == 0)
				str += nse.getName() + " " + "*";
			else
				str += nse.getName() + " " + Integer.toString(nse.getRank(i-1));
			GLabel label = new GLabel(str);
			label.setLocation(x1, y1);
			objects.add(line);
			objects.add(label);
			
		}
		String lastLabel = "";
		double x = (NDECADES-1)*xd;
		double y = yBegin + yd*(nse.getRank(NDECADES-1)-1);
		if (nse.getRank(NDECADES-1) == 0)	{
			lastLabel += nse.getName() + " " + "*";
			y = this.getHeight() - GRAPH_MARGIN_SIZE;
		}
		else
			lastLabel += nse.getName() + " " + Integer.toString(nse.getRank(NDECADES-1));
		GLabel last = new GLabel(lastLabel);
		last.setLocation(x, y);
		objects.add(last);
		objects.setColor(color);
		this.add(objects);
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
