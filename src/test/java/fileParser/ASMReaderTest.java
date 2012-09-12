package fileParser;

import java.io.IOException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import fileParser.ASMReader;
import fileParser.RowData;

public class ASMReaderTest {

    @Test
    public void readTest() throws IOException {
	ASMReader reader = new ASMReader("SampleData/AsmTiny.s");
	List<RowData> convertedAsmCode = reader.read();
	RowData convertedRow0 = convertedAsmCode.get(0);
	RowData convertedRow1 = convertedAsmCode.get(1);
	Assert.assertEquals("LDB", convertedRow0.getMneumonic());
	Assert.assertEquals("0x40", convertedRow0.getAddress());
	Assert.assertEquals("LOOP:", convertedRow1.getLabel());
	Assert.assertEquals("ADDIA", convertedRow1.getMneumonic());
	Assert.assertEquals("10", convertedRow1.getAddress());
    }
}
