package fileParser;

import java.util.HashMap;

/**
 * This class contains the entire source code. It stores the lines of the source
 * into a HashMap where the key is the RowAddress, and the value is the
 * @{RowData}. Also stores the pairing of Labels to RowAddresses in another
 * separate HashMap. RowAddress is in a hex string.
 * 
 * @author ejay
 * 
 */

public class SourceData {
    private HashMap<String, RowData> sourceCode = new HashMap<String, RowData>();
    private HashMap<String, String> labelAddressMap = new HashMap<String, String>();

    public SourceData(HashMap<String, RowData> listOfRowData,
	    HashMap<String, String> lblAddMap) {
	sourceCode = listOfRowData;
	labelAddressMap = lblAddMap;
    }

    public HashMap<String, RowData> getSourceCode() {
	return sourceCode;
    }

    public HashMap<String, String> getLabelAddressMap() {
	return labelAddressMap;
    }

}
