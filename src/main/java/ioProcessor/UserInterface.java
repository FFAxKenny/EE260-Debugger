package ioProcessor;

import java.io.InputStream;
import java.util.Scanner;

/**
 * User input processor
 * 
 * @author boka
 */
public class UserInterface {
	private static final String DEFAULT_STEP = "1";
	public static final String QUIT = "quit";
	public static final String HELP = "help";
	public static final String STEP = "step";
	public static final String ERROR = "error";
	public static final String CHANGE_IO = "io";
	public static final String GET = "get";

	private Scanner mScanner;

	/**
	 * Default constructor
	 */
	public UserInterface() {
		this(System.in);
	}

	/**
	 * Constructor from input stream
	 * 
	 * @param input
	 */
	private UserInterface(InputStream input) {
		mScanner = new Scanner(input);
	}

	/**
	 * Get a command from user input
	 */
	public String[] getCommand() {
		return processCommand(getSplitLine());
	}

	/**
	 * Process a command from split line
	 */
	public String[] processCommand(String[] splitLine) {
		String[] command;

		if (splitLine.length >= 1) {
			switch (splitLine[0].toLowerCase()) {
				case "quit":
					command = new String[] { QUIT };
					break;
				case "help":
					command = new String[] { HELP };
					break;
				case "step":
					if (splitLine.length == 1) {
						command = new String[] { STEP, DEFAULT_STEP };
					}
					else {
						command = new String[] { STEP, splitLine[1] };
					}
					break;
				case "io":
					if (splitLine.length >= 3) {
						command = new String[] { CHANGE_IO, splitLine[1],
								splitLine[2] };
					}
					else {
						command = new String[] { ERROR };
					}
					break;
				case "get":
					if (splitLine.length >= 3) {
						command = new String[] { GET, splitLine[1],
								splitLine[2] };
					}
					else {
						command = new String[] { ERROR };
					}
					break;

				default:
					command = new String[] { ERROR };
					break;
			}
		}
		else {
			command = new String[] { ERROR };
		}
		return command;
	}

	/**
	 * Get a line from the user input and split
	 */
	private String[] getSplitLine() {
		return mScanner.nextLine().trim().split("\\s");
	}
}
