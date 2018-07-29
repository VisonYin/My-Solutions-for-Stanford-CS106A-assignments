/*
 * File: PigLatin.java
 * --------------------
 * This is a test program for the pigLatin method,
 * which converts a word to Pig Latin.
 */

import acm.program.*;

public class PigLatin extends ConsoleProgram {

	public void run() {
		// You can also use pigLatinAlternate here
		println(pigLatin("elephant"));
		println(pigLatin("aardvark"));
		println(pigLatin("switch"));
		println(pigLatin("string"));
	}

	private String pigLatin(String word) {
		if (word.length() == 0) {
			return "";
		}

		// Words starting with vowels
		if (isVowel(word.charAt(0))) {
			return word + "yay";
		}

		// Words starting with consonants
		int firstVowelIndex = 1;
		for (int i = 1; i < word.length(); i++) {
			if (!isVowel(word.charAt(i))) {
				firstVowelIndex++;
			} else {
				break;
			}
		}

		return word.substring(firstVowelIndex) + 
				word.substring(0, firstVowelIndex) + "ay";
	}

	/* An alternate implementation */
	private String pigLatin2(String word) {
		if (word.length() == 0) {
			return "";
		}

		// Words starting with vowels
		if (isVowel(word.charAt(0))) {
			return word + "yay";
		}

		/* Word starting with consonants:
		 * increment firstVowelIndex while we have not gotten
		 * to the end of the string, and have not seen a vowel.
		 */
		int firstVowelIndex = 1;
		while (firstVowelIndex < word.length() && 
				!isVowel(word.charAt(firstVowelIndex))) {
			firstVowelIndex++;
		}

		return word.substring(firstVowelIndex) + 
				word.substring(0, firstVowelIndex) + "ay";
	}

	/* This is a helper method that returns true if ch is a vowel,
	 * and false otherwise.
	 */
	private boolean isVowel(char ch) {
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' 
				|| ch == 'u';  
	}

}
