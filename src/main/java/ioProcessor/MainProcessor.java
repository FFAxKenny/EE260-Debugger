package ioProcessor;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;

import display.Display;

import fileParser.ASMReader;
import fileParser.SourceData;

/**
 * The main process for the EE260 debugger
 * 
 * @author boka
 */
public class MainProcessor {

	/**
	 * @param args to run the program
	 */
	public static void main(String[] args) {
		try {

			// Create file
			File inputFile;

			// Process arguments
			if (args.length == 1) {
				if (args[0].endsWith(".s")) {
					System.out.println("Running with input file: " + args[0]);
					inputFile = new File(args[0]);
				}
				else if (args[0].equals("-h")) {
					System.out.println("Help menu:");
					System.out.println("\tsyntax:");
					System.out
							.println("\t\tee260debugger <file>\n\t\t\tto run a file");
					System.out
							.println("\t\tor\n\t\tee260debugger -h\n\t\t\tto show this help menu");
					return;
				}
				else {
					System.out.println("Use command -h for help.");
					return;
				}
			}
			else {
				System.out.println("Use command -h for help.");
				return;
			}

			// Set up code space and initialize classes
			ASMReader asmReader = new ASMReader(inputFile);
			SourceData sourceData = asmReader.read();
			// TODO send sourceData to Code Processor
			// CodeProcessor codeProcessor = new CodeProcessor(sourceData);
			
			// Set up user interface
			UserInterface userInterface = new UserInterface();

			// Set up Display
			Display display = new Display();
			
			// Main loop
			boolean done = false; // loop condition
			while (!done) {
				String[] command = userInterface.getCommand();
				// TODO send command to codeProcessor
				// String[] updates = codeProcessor.run(command);
				// display.update(updates);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Something bad happened :(");
		}
	}
}
