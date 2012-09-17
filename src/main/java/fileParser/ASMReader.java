package fileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Reference.Reference;

/**
 * Responsible for reading in files and then converting the lines of assembly
 * code to usable data for the system. The processor will then make sense of
 * what is going on inside code.
 * 
 * @author ejay
 */

public class ASMReader {

    private File mInputFile;
    private final static Logger mLog = LoggerFactory.getLogger(ASMReader.class);

    public ASMReader(File file) {
	mInputFile = file;
    }

    /**
     * Reads the inputFile and converts it into useful data that the Code
     * Processor will need.
     * 
     * @param inputFile
     *            The assembly file
     * @return a List of RowData that represents the elements in the row. Row
     *         sometimes contain a Label, but always contains a mneumonic and
     *         address.
     * @throws IOException
     *             if it fails to read the file initially or fails to read the
     *             line.
     */
    public SourceData read() throws IOException, IllegalArgumentException,
	    InvalidKeyException {
	// TODO: modify so that it also checks if the parameters coming from
	// the row before it loads it into the rowData.
	BufferedReader reader = new BufferedReader(new InputStreamReader(
		new FileInputStream(mInputFile)));
	String line;
	String lbl;
	String nmnc;
	String value;
	HashMap<String, RowData> rowMap = new HashMap<String, RowData>();
	HashMap<String, String> lblAddMap = new HashMap<String, String>();
	RowData rowData = new RowData();
	try {
	    int i = 0;
	    while ((line = reader.readLine()) != null) {
		mLog.info("Line: " + line);
		line = line.trim();
		String[] elements = line.split("\\s+", 3);
		mLog.info(elements[0]);
		if (elements.length == 3) {
		    lbl = elements[0];
		    nmnc = elements[1];
		    value = elements[2];
		    checkUsage(nmnc, value);
		    lblAddMap.put(lbl, Integer.toHexString(i));
		    mLog.info("Label: " + lbl + "Mneumonic: "
			    + Reference.MNEUMONIC_MAP.get(nmnc) + "Addr: "
			    + value);
		    rowData = new RowData(lbl,
			    Reference.MNEUMONIC_MAP.get(nmnc), value);
		} else if (elements.length == 2) {
		    if (elements[0].contains(":")) {
			// it's starts with a label
			lbl = elements[0];
			nmnc = elements[1];
			mLog.info("Label: " + lbl + " Mneumonic: "
				+ Reference.MNEUMONIC_MAP.get(nmnc));
			rowData = new RowData(lbl,
				Reference.MNEUMONIC_MAP.get(nmnc), true);

		    } else {
			nmnc = elements[0];
			value = elements[1];
			checkUsage(nmnc, value);
			mLog.info("Mneumonic: "
				+ Reference.MNEUMONIC_MAP.get(nmnc) + " Addr: "
				+ value);
			rowData = new RowData(
				Reference.MNEUMONIC_MAP.get(nmnc), value, false);
		    }
		} else if (elements.length == 1) {
		    nmnc = elements[0];
		    mLog.info("Mneumonic: " + Reference.MNEUMONIC_MAP.get(nmnc));
		    rowData = new RowData(Reference.MNEUMONIC_MAP.get(nmnc));
		}
		rowMap.put(Integer.toHexString(i), rowData);
		i += 2;
	    }
	    if (rowMap.size() > 64) {
		throw new ArrayIndexOutOfBoundsException("Exceeded Code Space");
	    }
	    SourceData sourceData = new SourceData(rowMap, lblAddMap);
	    return sourceData;
	} finally {
	    reader.close();
	}
    }

    /**
     * Checks if the mneumonics are being used properly. Immediates -> Value,
     * Non-Immediates -> Address.
     * 
     * @param mneumonic
     * @param value
     */
    public void checkUsage(String mneumonic, String value)
	    throws IllegalArgumentException {
	if (mneumonic.contains("I")) {
	    // it's an immediate and needs a data value
	    // therefore the corresponding value cannot have "0x"
	    if (value.contains("0x")) {
		throw new IllegalArgumentException(
			"Expected a value instead of an address");
	    }
	} else {
	    if (!value.contains("0x")) {
		throw new IllegalArgumentException(
			"Expected an address instead of a value");
	    }
	}
    }
}
