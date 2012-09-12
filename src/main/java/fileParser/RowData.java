package fileParser;

public class RowData {

    private String label;
    private String mneumonic;
    private String address;
    private String description;

    public RowData(String lbl, String nmnc, String add) {
	label = lbl;
	mneumonic = nmnc;
	address = add;
    }

    public RowData(String nmnc, String add) {
	label = null;
	mneumonic = nmnc;
	address = add;
    }

    public String getLabel() {
	return label;
    }

    public String getMneumonic() {
	return mneumonic;
    }

    public String getAddress() {
	return address;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }
}
