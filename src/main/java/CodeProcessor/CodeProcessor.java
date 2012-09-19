package CodeProcessor;

import ioProcessor.UserInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fileParser.RowData;
import fileParser.SourceData;
import storage.Storage;

/*
 * Filename:	codeProcessor.java
 * Author:	etumacder, boka
 * Mod:		September 18, 2012
 *
 * This is the code processor for the ee260 debugger
 */

public class CodeProcessor {
	private static final int MAX_OP_LEN = 4;
	private static final String OPCODE_ERROR = "error: no such opcode";
	private static final String COMMAND_ERROR = "error: invalid command";
	private static final String ADDR_ERROR = "error: address out of range";
	private static final String UPDATE = "update";
	private static final String GPR = "gpr";
	private static final String REG = "register";
	private static final String IO = "io";

	private Storage mStorage;

	/**
	 * Constructor for the CodeProcessor class.
	 * 
	 * @param source A SourceData object gives the codeProcessor the data it
	 *            needs
	 */
	public CodeProcessor(SourceData source) {
		mStorage = new Storage();
		this.importSourceToStorage(source);
	}

	/**
	 * Import source to storage within the code space.
	 * 
	 * @param source
	 */
	private void importSourceToStorage(SourceData source) {
		// TODO: this should use writeToDataSpace instead
		HashMap<String, RowData> sourceData = source.getSourceCode();
		for (int i = 0; i < source.getSize(); i += 2) {
			RowData row = sourceData.get(Integer.toHexString(i));
			if (row.getSize() == 2) {
				mStorage.writeMemory(Integer.toHexString(i),
						Byte.parseByte(row.getMneumonic()));
				mStorage.writeMemory(Integer.toHexString(i + 1),
						Byte.parseByte(row.getOperand()));
			}
			else {
				mStorage.writeMemory(Integer.toHexString(i),
						Byte.parseByte(row.getMneumonic()));
			}
		}
	}

	/**
	 * Runs a command and returns the String Array Commands are anything except
	 * stepping the program
	 * 
	 * @param command
	 * @return string array of completed operation
	 */
	public String[] runCommand(String[] command) {
		String[] operation;
		switch (command[0]) {
			case UserInterface.GET:
				switch (command[1]) {
					case GPR:
						operation = this.getGPR(command[2].charAt(0));
						break;
					case REG:
						operation = this.getRegister(command[2]);
						break;
					default:
						operation = new String[] { COMMAND_ERROR, command[0],
								command[1] };
						break;
				}
				break;
			case UserInterface.CHANGE_IO:
				operation = this.writeToIOSpace(command[1], command[2]);
				break;
			default:
				operation = new String[] { COMMAND_ERROR, command[0] };
				break;
		}
		return operation;
	}

	/**
	 * Steps the program
	 * 
	 * @param steps
	 * @return string array list of completed operations
	 */
	public List<String[]> step(int steps) {
		List<String[]> operations = new ArrayList<String[]>();
		for (int i = 0; i < steps; i++) {
			// TODO get code to run from program counter

			// TODO run code, use Reference class to extract hex command
			// TODO update program counter

			// TODO add operations to actions string list
			// String[] operation;
			// operation = { "update", "gpu", "a", "value" }
			// actions.add(String[]);
		}
		return operations;
	}

	/**
	 * @param register
	 * @return List<String[]> of GPRA or GPRB
	 */
	private String[] getGPR(char register) {
		String[] gpr = { GPR, String.valueOf(register),
				Byte.toString(mStorage.readRegister(register)) };
		return gpr;
	}

	/**
	 * @param address
	 * @return List<String[]> of register value
	 */
	private String[] getRegister(String address) {
		String[] gpr = { REG, address,
				Byte.toString(mStorage.readMemory(address)) };
		return gpr;
	}

	/**
	 * Writes to IO Space (0x40 to 0x7F)
	 * 
	 * @param hexAddress
	 * @param hexValue
	 */
	private String[] writeToIOSpace(String hexAddress, String hexValue) {
		String[] operation;

		// Make sure address is within range
		int address = Integer.decode(hexAddress);
		int value = Integer.decode(hexValue);

		if (address > 99 || address < 0) {
			operation = new String[] { ADDR_ERROR, hexAddress };
		}
		else {
			mStorage.writeMemory(hexAddress, (byte) value);
			operation = new String[] { UPDATE, IO,
					new Byte(mStorage.readMemory(hexAddress)).toString() };
		}
		return operation;
		// TODO: Use storage to save hexValue to hexAddress
	}

	/**
	 * Writes to Data Space (0x80 to 0xFF)
	 * 
	 * @param hexAddress
	 * @param hexValue
	 */
	private String[] writeToDataSpace(String hexAddress, String hexValue) {
		return null;
		// TODO: Use storage to save hexValue to hexAddress
		// TODO: Make sure address is within range
	}

	// Executes the code according to the opCode parameter
	// If parameter data is an address then must be in hex format Eg. 0x4f 0xF3
	// If using TAB or TBA then parameterdata can be set to anything
	private String[] instruction(String opCode, String data) {
		// TODO: pass instructions to storage class
		String OP = opCode.toLowerCase();

		String[] operation = new String[MAX_OP_LEN];
		// Data transfer instructions
		switch (OP) {
		// LDA
			case "3a":
				mStorage.writeRegister('a', mStorage.readMemory(data));
				operation[0] = UPDATE;
				operation[1] = GPR;
				operation[2] = "a";
				operation[3] = data;
				break;
			// LDIA
			case "3e":
				mStorage.writeRegister('a', new Byte(data));
				operation[0] = UPDATE;
				operation[1] = GPR;
				operation[2] = "a";
				operation[3] = data;
				break;
			// LDB
			case "39":
				mStorage.writeRegister('b', mStorage.readMemory(data));
				operation[0] = UPDATE;
				operation[1] = GPR;
				operation[2] = "b";
				operation[3] = data;
				break;

			// LDIB
			case "3d":
				mStorage.writeRegister('b', new Byte(data));
				operation[0] = UPDATE;
				operation[1] = GPR;
				operation[2] = "b";
				operation[3] = data;
				break;
			// STA
			case "1a":
				mStorage.writeMemory(data, mStorage.readRegister('a'));
				operation[0] = UPDATE;
				operation[1] = REG;
				operation[2] = data;
				operation[3] = new Byte(mStorage.readRegister('a')).toString();
				break;
			// STB
			case "19":
				mStorage.writeMemory(data, mStorage.readRegister('b'));
				operation[0] = UPDATE;
				operation[1] = REG;
				operation[2] = data;
				operation[3] = new Byte(mStorage.readRegister('b')).toString();
				break;
			// TAB
			case "21":
				mStorage.writeRegister('b', mStorage.readRegister('a'));
				operation[0] = UPDATE;
				operation[1] = GPR;
				operation[2] = "b";
				operation[3] = new Byte(mStorage.readRegister('a')).toString();
				break;
			// TBA
			case "22":
				mStorage.writeRegister('a', mStorage.readRegister('b'));
				operation[0] = UPDATE;
				operation[1] = GPR;
				operation[2] = "a";
				operation[3] = new Byte(mStorage.readRegister('b')).toString();
				break;
			// Data manipulation instructions
			/* ADDA */
			case "62":
				char tempa = mStorage.readMemory(data);
				char tempb = mStorage.readRegister('a');
				int sum = Character.toNumericValue(tempa)
						+ Character.toNumericValue(tempb);
				writeRegister('a', (byte) sum);
				/* ADDIA */
			case "66":
				break;
			/* ADDB */
			case "61":
				break;
			/* ADDIB */
			case "65":
				break;
			/* SUBA */
			case "6a":
				break;
			/* SUBIA */
			case "6e":
				break;
			/* SUBB */
			case "69":
				break;
			/* SUBIB */
			case "6d":
				break;
			/* ANDA */
			case "72":
				break;
			/* ANDIA */
			case "76":
				break;
			/* ANDB */
			case "71":
				break;
			/* ANDIB */
			case "75":
				break;
			/* ORA */
			case "7a":
				break;
			/* ORIA */
			case "7e":
				break;
			/* ORB */
			case "79":
				break;
			/* ORIB */
			case "7d":
				break;
			// Program control instruction
			/* JMP */
			case "80":
				break;
			/* JBZ */
			case "89":
				break;
			/* JBNZ */
			case "99":
				break;
			// Incorrect command
			default:
				operation[0] = OPCODE_ERROR;
				operation[1] = OP;
				break;
		}
		return operation;
	}
}
