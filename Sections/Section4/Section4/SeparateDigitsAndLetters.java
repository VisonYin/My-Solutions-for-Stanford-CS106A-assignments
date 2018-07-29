/*
 * File: SeparateDigitsAndLetters.java
 * --------------------
 * This is a test program for the separateDigitsAndLetters method,
 * which returns a string of all the numbers in the string in
 * their original order of appearance, followed by all the letters
 * in the string in their original order of appearance.  Non-letter
 * and non-digit characters are ignored.
 */

import acm.program.*;

public class SeparateDigitsAndLetters extends ConsoleProgram {

	public void run() {
		println(separateDigitsAndLetters("a1b2c3d4"));
		println(separateDigitsAndLetters("abc1def2g"));
		println(separateDigitsAndLetters("abc1??def2g!"));
		println(separateDigitsAndLetters("abcdefg"));
	}

	private String separateDigitsAndLetters(String str) {
		String numbers = "";
		String letters = "";
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isLetter(ch)) {
				letters += ch;
			} else if (Character.isDigit(ch)) {
				numbers += ch;
			}
		}

		return numbers + letters;
	}
}

