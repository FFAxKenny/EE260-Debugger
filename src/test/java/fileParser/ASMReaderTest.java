package fileParser;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;

import junit.framework.Assert;

import org.junit.Test;

public class ASMReaderTest {

	@Test
	public void readTest() throws IOException, InvalidKeyException,
			IllegalArgumentException {
		ASMReader reader = new ASMReader(new File("SampleData/AsmTiny.s"));
		SourceData convertedAsmCode = reader.read();
		RowData convertedRow0 = convertedAsmCode.getSourceCode().get(0);
		RowData convertedRow1 = convertedAsmCode.getSourceCode().get(1);
		Assert.assertEquals("LDB", convertedRow0.getMneumonic());
		Assert.assertEquals("0x40", convertedRow0.getOperand());
		Assert.assertEquals("ADDIA", convertedRow1.getMneumonic());
		Assert.assertEquals("10", convertedRow1.getOperand());
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void codeSpaceTest() throws IOException, InvalidKeyException,
			IllegalArgumentException {
		ASMReader reader = new ASMReader(new File("SampleData/AsmSize.s"));
		@SuppressWarnings("unused")
		SourceData tooBig = reader.read();
		Assert.fail();
	}

}
