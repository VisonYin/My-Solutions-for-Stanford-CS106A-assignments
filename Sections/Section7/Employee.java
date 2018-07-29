/*
 * File: Employee.java
 * -------------------
 * Class which describes the Employee variable type.
 * An Employee has the following information:
 * 		- name
 * 		- title
 * 		- annual salary
 * 
 * They may be given a promotion, which adds the word "Senior"
 * to their job title and doubles their salary. 
 */
public class Employee {
	
	public Employee(String newName, String newTitle) {
		name = newName;
		title = newTitle;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	// Adds "Senior" to the front of our job title, and doubles our salary
	public void promote() {
		title = "Senior " + title;
		salary *= 2;
	}
	
	/* Employee instance variables */
	private String name;
	private String title;
	private int salary;
}
