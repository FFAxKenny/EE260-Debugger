package ioProcessor;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;

import CodeProcessor.CodeProcessor;

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
			CodeProcessor codeProcessor = new CodeProcessor(sourceData);

			// Set up user interface
			UserInterface userInterface = new UserInterface();

			// Set up Display
			Display display = new Display();

			// Main loop
			while (true) {
				String[] command = userInterface.getCommand();

				// If command is to quit
				if (command[0] == UserInterface.QUIT) {
					System.out.println("Terminating.");
					return;
				}

				// If command is for help
				else if (command[0] == UserInterface.HELP) {
					System.out.println("Commands:");
					System.out.println("quit (exits the program)");
					System.out.println("help (shows this help menu)");
					System.out.println("step <int>");
					System.out.println("\t(steps the program int times)");
					System.out.println("io <hex1> <hex2>");
					System.out.println("\t(changes io at hex1 to value hex2)");
					System.out.println("get \"gpr\" <char>");
					System.out.println("\t(view value at gpr 'a' or 'b'");
					System.out.println("get \"mem\" <hex>");
					System.out.println("\t(view value at address <hex>");
				}

				// If there was an error
				else if (command[0] == UserInterface.ERROR) {
					System.out.println("Error processing command.");
					System.out.println("For instructions enter command: help");
				}
				// If command is to step
				else if (command[0] == UserInterface.STEP) {
					int steps = Integer.parseInt(command[1]);
					display.update(codeProcessor.step(steps));
				}

				else {
					display.update(codeProcessor.runCommand(command));
				}
				// Separate lines
				System.out.println();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (InvalidKeyException e) {
			// TODO Fix the error in the project caused by this
			e.printStackTrace();
		}
		finally {
			System.out.println("Terminating.");
		}
	}
}
