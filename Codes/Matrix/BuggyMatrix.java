/*
 * File: DrawFlag.java
 * -----------------------------
 * Draws various flags from around the world (that have
 * only horizontal bands)
 */

import java.awt.Color;

import acm.graphics.*;
import acm.program.*;
import acm.util.RandomGenerator;

public class BuggyMatrix extends ConsoleProgram {


	public void run() {
		int[][] matrix = new int[4][3];
		setValuesToTwo(matrix);
		printMatrix(matrix);
	}

	private void setValuesToTwo(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = 2;
			}
		}
	}
	
	private void printMatrix(int[][] matrix) {
		for(int r = 0; r < matrix.length; r++) {
			String line = "";
			for(int c = 0; c < matrix[0].length; c++) {
				line += matrix[r][c];
				if(c != matrix[0].length - 1) line += ", ";
				
			}
			println("[" + line + "]");
		}
	}



}
