/*
 * File: DeletingChars.java
 * --------------------
 * This is a test program for the removeAllOccurrences method,
 * which removes all instances of a particular character in a string.
 */

import acm.program.*;

public class DeletingChars extends ConsoleProgram {

	public void run() {
		println(removeAllOccurrences("This is a test", 't'));
		println(removeAllOccurrences("Summer is here!", 'e'));
		println(removeAllOccurrences("---0---", '-'));
	}

	private String removeAllOccurrences(String str, char ch) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ch) {
				result += str.charAt(i);
			}
		}
		return result;
	}

	/* Alternate implementation */
	private String removeAllOccurrences2(String str, char ch) {
		while (true) {
			int pos = str.indexOf(ch);
			if (pos >= 0) {
				str = str.substring(0, pos) + str.substring(pos + 1);
			} else {
				break;
			}
		}
		return str;
	}
}

