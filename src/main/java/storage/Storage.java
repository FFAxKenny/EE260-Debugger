package storage;

/*
 * Filename:	storage.java
 * Author:	Brandon Higashi, boka
 * Mod:		September 14, 2012
 *
 * This is the storage class for the ee260 debugger
 */

public class Storage {
	private static final int MAX_PC = 63;
	private static final byte ERR_BYTE = new Byte("0").byteValue();

	private byte[] memory;
	private byte[] registers;
	// The program counter
	private int PC;

	// Initializes the memory to be used
	public Storage() {
		registers = new byte[2];
		memory = new byte[256];
		PC = 0;
	}

	// Increases the PC counter by 1
	public void incrementPC() {
		PC++;
	}

	// Offsets the PC counter by the specified amount
	// Note: parameter offset can be positive or negative
	public void offsetPC(int offset) {
		PC = PC + offset;
	}

	// Returns the counter value
	public int getPC() {
		return PC;
	}

	// Writes a byte of data to a specified register
	// Note: parameter aRegister can be upper or lowercase
	public void writeRegister(char aRegister, byte data) {
		aRegister = Character.toLowerCase(aRegister);
		switch (aRegister) {
			case 'a':
				registers[0] = (byte) data;
				break;
			case 'b':
				registers[1] = (byte) data;
				break;
			default:
				System.out.println(aRegister + " is not a register.");
				break;
		}
	}

	// Returns a char value of what is in a specified register
	// Note: parameter aRegister can be upper or lowercase
	public byte readRegister(char aRegister) {
		aRegister = Character.toLowerCase(aRegister);
		switch (aRegister) {
			case 'a':
				return registers[0];
			case 'b':
				return registers[1];
			default:
				return ERR_BYTE;
		}
	}

	/**
	 * Writes a byte of data to an address in memory Note: parameter address
	 * must be in hex form. "0F" "00" etc.
	 * 
	 * @param address
	 * @param data
	 */
	public void writeMemory(String address, byte data) {
		int decimalAddress = Integer.decode(address);
		memory[decimalAddress] = (byte) data;
	}

	/**
	 * @return a char value of what is in a specified memory cell Note:
	 *         parameter address must be in hex form and have 0x in front of the
	 *         address to be accessed. Can be upper or lowercase. Ex. 0x4f 0xA3
	 * 
	 */
	public byte readMemory(String address) {
		return memory[Integer.decode(address)];
	}
}
