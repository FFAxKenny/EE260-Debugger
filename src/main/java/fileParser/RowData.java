package fileParser;

import javax.management.openmbean.InvalidKeyException;

public class RowData {

    private String rowAddress;
    private String label;
    private String mneumonic;
    private String operand;

    private final static String[] mneumonicsList = { "LDA", "LDIA", "LDB",
	    "LDIB", "STA", "STB", "TAB", "TBA", "ADDA", "ADDIA", "ADDB",
	    "ADDIB", "ADDB", "ADDIB", "SUBA", "SUBB", "SUBIB", "ANDA", "ANDIA",
	    "ANDB", "ANDIB", "ORA", "ORIA", "ORB", "ORIB", "JMP", "JBZ", "JBNZ" };

    private final int min = Integer.parseInt("00");

    private final int max = Integer.parseInt("FF");

    public RowData(String lbl, String nmnc, String oper, String rowAdd) {
	label = checkLabel(lbl);
	mneumonic = checkMneumonic(nmnc);
	operand = checkOperand(oper);
	rowAddress = rowAdd;

    }

    public RowData(String nmnc, String oper, String rowAdd) {
	label = null;
	mneumonic = checkMneumonic(nmnc);
	operand = checkOperand(oper);
	rowAddress = rowAdd;
    }

    public String getLabel() {
	return label;
    }

    public String getMneumonic() {
	return mneumonic;
    }

    public String getOperand() {
	return operand;
    }

    public String getRowAddress() {
	return rowAddress;
    }

    public String checkLabel(String label) {
	if (label.charAt(label.length() - 1) != ':') {
	    throw new InvalidKeyException(
		    "Invalid Label. Probably missing a ':' at the end of the label");
	}
	return label;
    }

    public String checkMneumonic(String nmnc) throws InvalidKeyException {
	boolean check = false;
	for (int i = 0; i < mneumonicsList.length; i++) {
	    if (nmnc == mneumonicsList[i]) {
		check = true;
	    }
	}
	if (!check) {
	    throw new InvalidKeyException(
		    "Enter a valid mneumonic, refer to help");
	}
	return nmnc;
    }

    public String checkOperand(String add) {
	int address = Integer.parseInt(add);
	if (address <= min && address >= max) {
	    throw new InvalidKeyException(
		    "Enter a valid address in between 0x00 and 0xFF");
	}
	return add;
    }

}
