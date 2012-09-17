package storage;

/*
 * Filename:	storage.java
 * Author:	Brandon Higashi
 * Mod:		September 14, 2012
 *
 * This is the storage class for the ee260 debugger
 */

public class Storage {

    private byte[] memory;
    private byte[] registers;
    private int PC;

    // Initializes the memory to be used
    public void initStorage() {
	registers = new byte[2];
	memory = new byte[256];
	PC = 0;
    }

    // Increases the PC counter by 1
    public void progressPC() {
	PC++;
	if (PC > 63)
	    System.out.println("Program counter exceeding storage");
    }

    // Offsets the PC counter by the specified amount
    // Note: parameter offset can be positive or negative
    public void offsetPC(int offset) {
	PC = PC + offset;
    }

    // Returns the counter value
    public int readPC() {
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
	}
    }

    // Returns a char value of what is in a specified register
    // Note: parameter aRegister can be upper or lowercase
    public char readRegister(char aRegister) {
	aRegister = Character.toLowerCase(aRegister);
	switch (aRegister) {
	case 'a':
	    return (char) registers[0];
	case 'b':
	    return (char) registers[1];
	default:
	    System.out.println(aRegister + " is not a register.");
	    return 0;
	}
    }

    /**
     * Writes a byte of data to an address in memory Note: parameter address
     * must be in hex form and have 0x in front of the address to be accessed.
     * Can be upper or lowercase. Ex. 0x4f 0xA3
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
    public char readMemory(String address) {
	int decimalAddress = Integer.decode(address);
	byte mem = memory[decimalAddress];
	char memry = (char) mem;
	return memry;
    }
}
