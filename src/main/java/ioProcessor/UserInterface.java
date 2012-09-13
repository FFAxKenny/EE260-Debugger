package ioProcessor;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * User input processor
 * 
 * @author boka
 */
public class UserInterface {
	private InputStreamReader mInputReader;

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
		mInputReader = new InputStreamReader(input);
	}

	/**
	 * Get a command from the user
	 */
	public void getCommand(){};
}
