package com.kaufland.exercise.Service;

import java.util.List;

/**
 * This service is supposed to parse anagrams from given input file
 *
 * @author Pezhman Jahanmard
 */
public interface AnagramParserService {
	List<List<String>> getAnagrams() throws AnagramServiceException;
}
