/*
 * File: GuessYourNumber.java
 * ---------------------
 * This program attempts to guess what number you are thinking of.
 * It asks for the range of your number, and then proceeds to make
 * guesses until it narrows in on your number!
 */

import acm.program.*;

public class GuessYourNumber extends ConsoleProgram {
	public void run() {
		println("I will guess your number!");
		int lowest = readInt("Lower bound (inclusive)? ");
		int highest = readInt("Upper bound (inclusive)? ");
		int answer = findNumber(lowest, highest);
		println("Ha!  I knew your number was " + answer + "!");
	}
	
	/*
	 * Returns the final computer guess after narrowing down the
	 * range of possible numbers the user is thinking of.  Takes
	 * as parameters the initial bounds of the user's number.
	 */
	private int findNumber(int lowerBound, int upperBound) {
		while (lowerBound != upperBound) {
			int guess = (upperBound + lowerBound) / 2;

			// Update our bounds depending on the result
			int check = readInt(guess + "? (0=yes, -1=low, 1=high)");
			if (check == -1) {
				lowerBound = guess + 1;
			} else if (check == 1) {
				upperBound = guess - 1;
			} else {
				lowerBound = guess;
				upperBound = guess;
			}
		}
		
		return lowerBound;
	}
}


