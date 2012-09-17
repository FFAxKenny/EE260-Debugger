package CodeProcessor;

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
    // Executes the code according to the opCode parameter
    // If parameter data is an address then must be in hex format Eg. 0x4f 0xF3
    // If using TAB or TBA then parameterdata can be set to anything
    public void instruction(String opCode, String data) {
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

