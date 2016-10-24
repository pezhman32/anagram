package com.kaufland.exercise.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This object will hold anagrams and some details about them
 *
 * @author Pezhman Jahanmard
 */
public class Anagram {
	private List<String> anagrams;
	private long size;

	public Anagram(String word) {
		anagrams = new ArrayList<>();
		anagrams.add(word);
		size = 1;
	}

	public List<String> getAnagrams() {
		return anagrams;
	}

	public long getSize() {
		return size;
	}

	public void addWord(String word) {
		size++;
		anagrams.add(word);
	}

	/**
	 * Checks to see if given word matches to first word (chosen as base word) as anagram
	 * Algorithm: Since we are supporting only english words, we convert words to int[26] array and compare them together
	 * This solution will result in O(n) which is acceptable.
	 *
	 * @param word to check
	 * @return true if we can add it to this anagram or false if it does not fits
	 */
	public boolean doesFit(String word) {
		String base = anagrams.get(0);
		char[] aArr = base.toLowerCase().toCharArray();
		char[] bArr = word.toLowerCase().toCharArray();
		if (aArr.length != bArr.length) {
			return false;
		}

		int[] counts = new int[26]; // An array to hold the number of occurrences of each character
		for (int i = 0; i < aArr.length; i++) {
			counts[aArr[i] - 97]++;  // Increment the count of the character at i
			counts[bArr[i] - 97]--;  // Decrement the count of the character at i
		}
		// If the strings are anagrams, the counts array will be full of zeros
		for (int i = 0; i < 26; i++) {
			if (counts[i] != 0) {
				return false;
			}
		}

		return true;
	}
}
