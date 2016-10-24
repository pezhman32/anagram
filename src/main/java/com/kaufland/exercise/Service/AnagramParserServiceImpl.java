package com.kaufland.exercise.Service;

import com.kaufland.exercise.Model.Anagram;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * This service is supposed to parse anagrams from given input file
 *
 * @author Pezhman Jahanmard
 */
public class AnagramParserServiceImpl implements AnagramParserService {
	private static final Logger LOGGER = Logger.getLogger(AnagramParserServiceImpl.class.getName());
	private List<Anagram> anagrams;

	public AnagramParserServiceImpl(URI uri) throws IOException {
		readFile(uri);
	}

	/**
	 * Reads file line by line and calls parseAnagram() for each word in the line
	 * @param uri file URI
	 */
	private void readFile(URI uri) throws IOException {
		LOGGER.info("Reading input file started...");

		anagrams = new ArrayList<>();
		long lines = 0;
		//using Stream, simple, beautiful and more memory efficient than loading whole file into memory!
		try (Stream<String> stream = Files.lines(Paths.get(uri))) {
			stream.forEach(this::parseAnagram);
			lines++;
		}

		LOGGER.info("Total of " + lines + " has been parsed.");
	}

	/**
	 * Iterate through our anagrams list and find anagrams with more than 1 words inside
	 *
	 * @return final result set
	 * @throws AnagramServiceException if no words added yet
	 */
	@Override
	public List<List<String>> getAnagrams() throws AnagramServiceException {
		if (anagrams == null) {
			throw new AnagramServiceException("Lets add some words first!");
		}

		List<List<String>> result = new ArrayList<>();
		//using parallelStream, add anagrams with more than 1 word inside to result set
		anagrams.parallelStream().forEach(anagram -> {
			if (anagram.getSize() > 1) {
				result.add(anagram.getAnagrams());
			}
		});

		LOGGER.info("Total of " + result.size() + " anagrams found.");
		return result;
	}

	/**
	 * Adds to already existing anagram or create new one into list
	 *
	 * @param word to lookup
	 */
	private void parseAnagram(String word) {
		for (Anagram anagram : anagrams) {
			if (anagram.doesFit(word)) {
				//if found, add to to existing list
				anagram.addWord(word);
				return;
			}
		}

		//else create new anagram union
		anagrams.add(new Anagram(word));
	}
}
