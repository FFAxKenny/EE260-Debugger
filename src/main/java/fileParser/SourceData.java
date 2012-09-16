package fileParser;

import java.util.HashMap;
import java.util.List;

public class SourceData {
    private List<RowData> sourceCode;
    private HashMap<String, String> labelAddressMap = new HashMap<String, String>();

    public SourceData(List<RowData> listOfRowData,
	    HashMap<String, String> lblAddMap) {
	sourceCode = listOfRowData;
	labelAddressMap = lblAddMap;
    }

    public List<RowData> getSourceCode() {
	return sourceCode;
    }

    public HashMap<String, String> getLabelAddressMap() {
	return labelAddressMap;
    }

}
