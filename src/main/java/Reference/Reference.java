package Reference;

import java.util.HashMap;

/**
 * A reference card that has data about the instruction set, and the EE260
 * Computer.
 * 
 * @author ejay
 * 
 */

public class Reference {
    public static final HashMap<String, String> MNEUMONIC_MAP = new HashMap<String, String>() {
	/**
	 * A HashMap that represents, opcode to mneumonic pairing.
	 */
	private static final long serialVersionUID = 1L;

	{
	    put("LDA", "3A");
	    put("LDIA", "3E");
	    put("LDB", "39");
	    put("LDIB", "3D");
	    put("STA", "1A");
	    put("STB", "19");
	    put("TAB", "21");
	    put("TBA", "22");
	    put("ADDA", "62");
	    put("ADDIA", "66");
	    put("ADDB", "61");
	    put("ADDIB", "65");
	    put("SUBA", "6A");
	    put("SUBIA", "6E");
	    put("SUBB", "69");
	    put("SUBIB", "6D");
	    put("ANDA", "72");
	    put("ANDIA", "76");
	    put("ANDB", "71");
	    put("ANDIB", "75");
	    put("ORA", "7A");
	    put("ORIA", "7E");
	    put("ORB", "79");
	    put("ORIB", "7D");
	    put("ORB", "79");
	    put("ORIB", "7D");
	}
    };
}
