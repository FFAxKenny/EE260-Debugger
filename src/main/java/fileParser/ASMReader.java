package fileParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsible for reading in files and then converting the lines of assembly
 * code to usable data for the system. The processor will then make sense of
 * what is going on inside code.
 * 
 * @author ejay
 */

public class ASMReader {

    private File inputFile;
    private final static Logger log = LoggerFactory.getLogger(ASMReader.class);

    public ASMReader(File file) {
	inputFile = file;
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
    public List<RowData> read() throws IOException {
	// TODO: modify so that it also checks if the parameters coming from
	// the row before it loads it into the rowData.
	BufferedReader reader = new BufferedReader(new InputStreamReader(
		new FileInputStream(inputFile)));
	String line;
	String lbl;
	String nmnc;
	String add;
	List<RowData> listOfRawData = new LinkedList<RowData>();
	RowData rowData;
	try {
	    int i = 0;
	    while ((line = reader.readLine()) != null) {
		log.info("Line: " + line);
		line = line.trim();
		String[] elements = line.split("\\s+", 3);
		log.info(elements[0]);
		if (elements[0].contains(":")) {
		    lbl = elements[0];
		    nmnc = elements[1];
		    add = elements[2];
		    log.info("Label: " + lbl + "Mneumonic: " + nmnc + "Addr: "
			    + add);
		    rowData = new RowData(lbl, nmnc, add,
			    Integer.toHexString(i));
		} else {
		    nmnc = elements[0];
		    add = elements[1];
		    log.info("Mneumonic: " + nmnc + "Addr: " + add);
		    rowData = new RowData(nmnc, add, Integer.toHexString(i));
		}
		listOfRawData.add(rowData);
		i += 2;
	    }
	    if (listOfRawData.size() > 63) {
		throw new ArrayIndexOutOfBoundsException("Exceeded Code Space");
	    }
	    return listOfRawData;
	} finally {
	    reader.close();
	}
    }
}
