package hw13;

/*
 * GrepPatternMatch.java
 * 
 * @version 
 * $Id: GrepPatternMatch.java , Version 1.0 11/23/2014 $ 
 * 
 * @revision 
 * $Log Initial Version $  
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

/**
 * This class mimics the functionality of the command grep 'pattern' file
 * It searches for the input pattern specified by the user as argument.
 * 
 * @author Uday Vilas Wadhone
 * 
 *
 */
public class GrepPatternMatch {
	//objects of Pattern and Matcher class
	Pattern patterninit;
	Matcher matcher;
	//boolean variable to notify if pattern is found
	boolean isFound;

	/**
	 * Constructor for class that initializes class variables
	 * @param	 pattern	pattern input by user
	 * 	
	 */
	public GrepPatternMatch(String pattern) {
		//compile pattern passed by user using Pattern class 
		patterninit = Pattern.compile(pattern);
		//initialize boolean variable to false
		isFound = false;
	}
	
	/**
	 * Main method. 
	 * Takes input from user and scans the file specified by user
	 * 
	 * @param	 args						take command from user from args
	 * @throws	 FileNotFoundException		exception thrown if file not found
	 */
	public static void main(String args[]) throws FileNotFoundException {
		//make scanner object to read the file specified by user
		Scanner sc = new Scanner(new File(args[2]));
		//initialize line variable used to process each line
		String line = "";
        int count = 0;
		//class object to call required methods and initialize class variables
		GrepPatternMatch patternMatch = new GrepPatternMatch(args[1]);
		//while loop to read each line and see if pattern exists in the file 
		while (sc.hasNext()) {
			//assign line variable to the next line in specified file
			line = sc.nextLine();
            count++;
			//pass the current line to pattern matching method
			patternMatch.matchPattern(line);
			//if match is found, print out the line
			if (patternMatch.isFound) {
				System.out.println("Pattern found at line :" +count+" "+ line);
			}
		}
		//close the scanner
		sc.close();
	}

	/**
	 * The method takes each line in specified file and checks if pattern is
	 * present in that line
	 * 
	 * @param	 line	each line of the file passed by user
	 */
	public void matchPattern(String line) {
		//initialize Matcher class variable by passing the line being processed
		matcher = patterninit.matcher(line);
		/*
		 * check if pattern exists in the particular line and change value of
		 * boolean variable accordingly.
		 * 
		 */
		isFound = matcher.matches();
	}
}
