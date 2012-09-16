package fileParser;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

public class ASMReaderTest {

    @Test
    @Ignore
    public void readTest() throws IOException {
	ASMReader reader = new ASMReader(new File("SampleData/AsmTiny.s"));
	SourceData convertedAsmCode = reader.read();
	RowData convertedRow0 = convertedAsmCode.getSourceCode().get(0);
	RowData convertedRow1 = convertedAsmCode.getSourceCode().get(1);
	Assert.assertEquals("LDB", convertedRow0.getMneumonic());
	Assert.assertEquals("0x40", convertedRow0.getOperand());
	Assert.assertEquals("LOOP:", convertedRow1.getLabel());
	Assert.assertEquals("ADDIA", convertedRow1.getMneumonic());
	Assert.assertEquals("10", convertedRow1.getOperand());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    @Ignore
    public void codeSpaceTest() throws IOException {
	ASMReader reader = new ASMReader(new File("SampleData/AsmSize.s"));
	@SuppressWarnings("unused")
	SourceData tooBig = reader.read();
	Assert.fail();
    }

    @Test
    public void Test() {
	String i = "0x55";
	String[] i2 = i.split("x");
	System.out.println(Integer.parseInt(i2[1], 16));
    }
}
