package CodeProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fileParser.RowData;
import fileParser.SourceData;
import storage.Storage;

/*
 * Filename:	codeProcessor.java
 * Author:
 * Mod:		September 16, 2012
 *
 * This is the code processor for the ee260 debugger
 */

public class CodeProcessor {

    private Storage mStorage;
    int mPC;
    
    /**
     * Constructor for the CodeProcessor class.
     * @param source A SourceData object gives the codeProcessor the data it needs
     */
    public CodeProcessor(SourceData source) {
	this.importSourceToStorage(source);
	mPC = 0;
    }
    
    /**
     * Import source to storage within the code space.
     * @param source
     */
    public void importSourceToStorage(SourceData source) {
	HashMap<String, RowData> sourceData = source.getSourceCode();
	for(int i = 0; i < source.getSize(); i += 2) {
	    RowData row = sourceData.get(Integer.toHexString(i));
	    if(row.getSize() == 2) {
		mStorage.writeMemory(Integer.toHexString(i), 
			Byte.parseByte(row.getMneumonic()));
		mStorage.writeMemory(Integer.toHexString(i+1), 
			Byte.parseByte(row.getOperand()));
	    }
	    else {
		mStorage.writeMemory(Integer.toHexString(i), 
			Byte.parseByte(row.getMneumonic()));
	    }
	}
    }
    
    /**
     * Runs a command and returns the String Array
     * Commands are anything except stepping the program
     * @param command
     * @return string array of completed operation
     */
    public String[] runCommand(String[] command) {
    	// TODO figure out what to do from the command
    	//		Use UserInterface class' static variables
    	//		To check for commands
    	//		ex:  case UserInterface.
    	
    	// TODO call the correct method with interpreted parameters
    	//		and return a string array of the completed command
    	//		if command can't be completed, print an error
    }
    
    /**
     * Steps the program
     * @param steps
     * @return string array list of completed operations
     */
    public List<String[]> step(int steps) {
    	List<String[]> operations = new ArrayList<String[]>();
    	for (int i = 0 ; i < steps ; i++) {
    		// TODO get code to run from program counter
    		
    		// TODO run code
    		
    		// TODO add operations to actions string list
    		// String[] operation;
    		// operation = { "update", "gpu", "a", "value" }
    		// actions.add(String[]);
    	}
    }
    
    /**
     * @param register
     * @return List<String[]> of GPRA or GPRB
     */
    private String[] getGPR(char register) {
    	String[] gpr = { "gpr", "a", mStorage.readRegister(register) };
       	return gpr;
    }

    /**
     * @param address
     * @return List<String[]> of register value
     */
    private String[] getRegister(String address) {
    	String[] gpr = { "register", "a", mStorage.readMemory(address) };
       	return gpr;
    }
    
    /**
     * Writes to IO Space (0x40 to 0x7F)
     * @param hexAddress
     * @param hexValue
     */
    private String[] writeToIOSpace(String hexAddress, String hexValue) {
	// TODO: Tiffany - please implement this
    }
    
    /**
     * Writes to Data Space (0x80 to 0xFF)
     * @param hexAddress
     * @param hexValue
     */
    private String[] writeToDataSpace(String hexAddress, String hexValue) {
	// TODO: Tiffany - please implement this
    }
    
    // Executes the code according to the opCode parameter
    // If parameter data is an address then must be in hex format Eg. 0x4f 0xF3
    // If using TAB or TBA then parameterdata can be set to anything
    private void instruction(String opCode, String data) {
	// TODO: Tiff - you have to finish what Brandon was doing here. This is where
	// we simulate the behavior of the code. The behaviors are accessed through
	// the opcode since that is how it is stored in storage.
	
    
	String OP = opCode.toLowerCase();
	String DATA = data.toLowerCase();
	switch(OP) {
	    // Data transfer instructions 	
/* LDA   */    	case "3a" :	char temp = mStorage.readMemory(DATA);
				mStorage.writeRegister('a', (byte) temp);
/* LDIA  */   	case "3e" :	int tempa = Integer.parseInt(DATA);
				byte tempb = Integer.toByte(tempa); Byte.
				mStorage.writeRegister('a', tempb);
/* LDB   */     case "39" :	char temp = mStorage.readMemory(DATA);
				mStorage.writeRegister('b', (byte) temp);
/* LDIB  */     case "3d" :	int tempa = Integer.parseInt(DATA);
				byte tempb = Integer.toHexString(tempa);
				mStorage.writeRegister('b'. tempb);
/* STA   */     case "1a" :	char temp = readRegister('a');
				mStorage.writeMemory(DATA, (byte) temp);
/* STB   */     case "19" :	char temp = readRegister('b');
				mStorage.writeMemory(DATA, (byte) temp);
/* TAB   */ 	case "21" :	char temp = readRegister('a');
				mStorage.writeRegister('b');
/* TBA   */     case "22" :	char temp = readRegister('b');
				mStorage.writeRegister('a');
	    // Data manipulation instructions
/* ADDA  */  	case "62" :	char tempa = mStorage.readMemory(DATA);
				char tempb = mStorage.readRegister('a');
				int sum = Character.toNumericValue(tempa) + Character.toNumericValue(tempb);
				writeRegister('a', (byte) sum);
/* ADDIA */     case "66" :	
/* ADDB  */     case "61" :	
/* ADDIB */     case "65" :	
/* SUBA  */     case "6a" :	
/* SUBIA */     case "6e" :	
/* SUBB  */     case "69" :	
/* SUBIB */     case "6d" :	
/* ANDA  */     case "72" :	
/* ANDIA */     case "76" :	
/* ANDB  */     case "71" :	
/* ANDIB */     case "75" :	
/* ORA   */     case "7a" :	
/* ORIA  */     case "7e" :	
/* ORB   */     case "79" :	
/* ORIB  */     case "7d" :	
	    // Program control instruction
/* JMP   */     case "80" :	
/* JBZ   */     case "89" :	
/* JBNZ  */     case "99" :	
    }
}

