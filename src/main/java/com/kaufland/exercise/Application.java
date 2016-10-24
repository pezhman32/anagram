package com.kaufland.exercise;

import com.kaufland.exercise.Service.AnagramParserService;
import com.kaufland.exercise.Service.AnagramParserServiceImpl;

import java.io.File;

/**
 * Main class to start our application
 *
 * @author Pezhman Jahanmard
 */
public class Application {

	public static void main(String[] args) throws Exception {
		//our only argument is the file path which must be exists
		if (args == null || args.length == 0) {
			throw new Exception("Please provide an input");
		}

		File input = new File(args[0]);
		//check for existence
		if (!input.exists()) {
			throw new Exception("Input file not exists!");
		}

		AnagramParserService parserService = new AnagramParserServiceImpl(input.toURI());
		parserService.getAnagrams().forEach(System.out::println);
	}
}
