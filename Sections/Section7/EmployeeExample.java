/*
 * File: EmployeeExample.java
 * --------------------------
 * This file contains a sample program using the Employee class.  It reads in   
 * employee information until we read in the empty string, randomly promotes 
 * one of the employees  entered, and then prints out all employees.
 */
import java.util.*;
import acm.program.*;
import acm.util.*;

public class EmployeeExample extends ConsoleProgram {

	public void run() {
		ArrayList<Employee> employees = readInEmployees();
		
		// Randomly promote a single employee
		int randomEmployeeNum = rgen.nextInt(employees.size());
		Employee employeeToPromote = employees.get(randomEmployeeNum);
		employeeToPromote.promote();
		println(employeeToPromote.getName() + " was promoted!\n\n");
		
		printEmployees(employees);
	}
	
	/*
	 * Reads in a list of employees until the empty string is entered.
	 * Returns an ArrayList of all Employees entered.
	 */
	private ArrayList<Employee> readInEmployees() {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		while (true) {
			String name = readLine("---\nName: ");
			if (name.equals("")) {
				break;
			}
			String title = readLine("Title: ");
			int salary = readInt("Salary ($): ");

			Employee newEmployee = new Employee(name, title);
			newEmployee.setSalary(salary);
			employees.add(newEmployee);
		}
		return employees;
	}
	
	/* Prints the name, title and salary for each of the given employees. */
	private void printEmployees(ArrayList<Employee> employees) {
		for (int i = 0; i < employees.size(); i++) {
			Employee currentEmployee = employees.get(i);
			println("--- " + currentEmployee.getName() + " (" +
					currentEmployee.getTitle() + ") ---");
			println("Salary: $" + currentEmployee.getSalary());
		}
	}
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
}
