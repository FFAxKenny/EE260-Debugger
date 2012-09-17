package fileParser;

import javax.management.openmbean.InvalidKeyException;

import Reference.Reference;

/**
 * This contains the RowData of one row in the source code. Stores the opcode of
 * the mneumonic, and the hex of the value.
 * 
 * @author ejay
 * 
 */
public class RowData {

    private String label;
    private String mneumonic;
    private String operand;

    private final int min = Integer.parseInt("00", 16);

    private final int max = Integer.parseInt("FF", 16);

    /**
     * For a row with 3 arguments, always (Label Mneumonic Value).
     * 
     * @param lbl
     * @param nmnc
     * @param oper
     */
    public RowData(String lbl, String nmnc, String oper)
	    throws InvalidKeyException {
	// TODO: if there is a label we need to create a hashmap to key the
	// label, and value the rowAddress
	label = checkLabel(lbl);
	mneumonic = checkMneumonic(nmnc);
	operand = checkOperand(oper);

    }

    /**
     * For a row with 2 arguments, it's either (Label Mneumonic) or (Mneumonic
     * Value). A boolean specifies which type.
     * 
     * @param nmncOrLabel
     * @param nmncOrValue
     * @param labelDefined
     */
    public RowData(String nmncOrLabel, String nmncOrValue, boolean labelDefined)
	    throws InvalidKeyException {
	if (labelDefined) {
	    label = nmncOrLabel;
	    mneumonic = checkMneumonic(nmncOrValue);
	} else {
	    mneumonic = checkMneumonic(nmncOrLabel);
	    operand = checkOperand(nmncOrValue);
	}
    }

    /**
     * For a row with only 1 argument, only one possibility is available.
     * 
     * @param nmnc
     */
    public RowData(String nmnc) throws InvalidKeyException {
	label = null;
	operand = null;
	mneumonic = checkMneumonic(nmnc);
    }

    /**
     * Empty constructor
     */
    public RowData() {
	label = null;
	operand = null;
	mneumonic = null;
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

    /**
     * Checks if the label is okay to use.
     * 
     * @param label
     *            the label that needs to be checked
     * @return the same label if it passes
     */
    public String checkLabel(String label) throws InvalidKeyException {
	if (label.charAt(label.length() - 1) != ':') {
	    throw new InvalidKeyException(
		    "Invalid Label. Probably missing a ':' at the end of the label");
	}
	return label;
    }

    /**
     * Checks if the mneumonic is okay to use.
     * 
     * @param nmnc
     * @return returns it back if it passes
     * @throws InvalidKeyException
     *             if it doesn't pass it throws this
     */
    public String checkMneumonic(String nmnc) throws InvalidKeyException {
	for (int i = 0; i < Reference.MNEUMONIC_MAP.keySet().size(); i++) {
	    if (!Reference.MNEUMONIC_MAP.containsValue(nmnc)) {
		throw new InvalidKeyException(
			"Enter a valid mneumonic, refer to help");
	    }
	}
	return nmnc;
    }

    public String checkOperand(String value) throws InvalidKeyException {
	// figure out if it's data(integers) or an address(hex)
	if (value.contains("0x")) {
	    // it's an address
	    String[] modValue = value.split("x");
	    value = modValue[1];
	}
	int val = Integer.parseInt(value);
	if (val <= min && val >= max) {
	    throw new InvalidKeyException(
		    "Enter a valid address in between 0x00 and 0xFF");
	}
	return Integer.toHexString(val);
    }
}
