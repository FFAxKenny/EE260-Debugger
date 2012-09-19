package ioProcessor;

import org.junit.Assert;
import org.junit.Test;

public class TestUserInterface {

	@Test
	public void testProcessCommand() {
		UserInterface ui = new UserInterface();

		String[] bad = new String[] {};
		String[] quit = new String[] { "quit", "yo" };
		String[] help = new String[] { "help", "yo" };
		String[] step = new String[] { "step" };
		String[] stepNum = new String[] { "step", "1000", "hello" };
		String[] io = new String[] { "io", "from", "to" };
		String[] badIo = new String[] { "io", "from" };
		String[] get = new String[] { "get", "type", "addr" };
		String[] badGet = new String[] { "get", "type" };

		Assert.assertArrayEquals(new String[] { "error" },
				ui.processCommand(bad));
		Assert.assertArrayEquals(new String[] { "quit" },
				ui.processCommand(quit));
		Assert.assertArrayEquals(new String[] { "help" },
				ui.processCommand(help));
		Assert.assertArrayEquals(new String[] { "step", "1" },
				ui.processCommand(step));
		Assert.assertArrayEquals(new String[] { "step", "1000" },
				ui.processCommand(stepNum));
		Assert.assertArrayEquals(new String[] { "io", "from", "to" },
				ui.processCommand(io));
		Assert.assertArrayEquals(new String[] { "error" },
				ui.processCommand(badIo));
		Assert.assertArrayEquals(new String[] { "get", "type", "addr" },
				ui.processCommand(get));
		Assert.assertArrayEquals(new String[] { "error" },
				ui.processCommand(badGet));
	}
}
