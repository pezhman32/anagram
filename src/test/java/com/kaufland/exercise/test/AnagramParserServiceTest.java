package com.kaufland.exercise.test;

import com.kaufland.exercise.Service.AnagramParserService;
import com.kaufland.exercise.Service.AnagramParserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for AnagramParserServiceImpl
 *
 * @author Pezhman Jahanmard
 */
public class AnagramParserServiceTest {
	private AnagramParserService anagramParserService;
	private final String TEST_FILE_ORIGINAL = "/testInput-original.txt";
	private final String TEST_FILE_ANOTHER = "/testInput-another.txt";

	@Before
	public void before() {
		//make sure we are creating new instance on every test
		anagramParserService = null;
	}

	/**
	 * Tests default example
	 */
	@Test
	public void testOriginalDataSaet() throws Exception {
		List<List<String>> expectedResult = new ArrayList<List<String>>(){{
			add(Arrays.asList("act", "cat"));
			add(Arrays.asList("race", "care", "acre"));
		}};

		URL url = this.getClass().getResource(TEST_FILE_ORIGINAL);
		File file = new File(url.getFile());

		anagramParserService = new AnagramParserServiceImpl(file.toURI());
		List<List<String>> anagrams = anagramParserService.getAnagrams();
		assertListEquals(expectedResult, anagrams);
	}

	/**
	 * Tests another small data set
	 */
	@Test
	public void testJustAnotherDataSet() throws Exception {
		List<List<String>> expectedResult = new ArrayList<List<String>>(){{
			add(Arrays.asList("open", "nope"));
			add(Arrays.asList("yes", "sey"));
			add(Arrays.asList("no", "on"));
		}};

		URL url = this.getClass().getResource(TEST_FILE_ANOTHER);
		File file = new File(url.getFile());

		anagramParserService = new AnagramParserServiceImpl(file.toURI());
		List<List<String>> anagrams = anagramParserService.getAnagrams();
		assertListEquals(expectedResult, anagrams);
	}

	/**
	 * We expect an exception to be thrown here because of wrong result
	 */
	@Test(expected = Exception.class)
	public void testJustAnotherDataSetMustThrowException() throws Exception {
		List<List<String>> expectedResult = new ArrayList<List<String>>(){{
			add(Arrays.asList("must", "not"));
			add(Arrays.asList("match", "this"));
			add(Arrays.asList("another", "test"));
		}};

		URL url = this.getClass().getResource(TEST_FILE_ANOTHER);
		File file = new File(url.getFile());

		anagramParserService = new AnagramParserServiceImpl(file.toURI());
		List<List<String>> anagrams = anagramParserService.getAnagrams();
		assertListEquals(expectedResult, anagrams);
	}

	/**
	 * Compares two (unordered) lists to see if they are equal
	 */
	private void assertListEquals(List<List<String>> expected, List<List<String>> actual) throws Exception {
		if (expected.size() != actual.size()) {
			throw new Exception("wrong result");
		}

		for (List<String> strings : expected) {
			boolean found = false;
			for (List<String> parsed : actual) {
				if (parsed.containsAll(strings)) {
					found = true;
					break;
				}
			}

			if (!found) {
				throw new Exception("Wrong answer, not found in expected answer: " + strings);
			}
		}
	}
}
