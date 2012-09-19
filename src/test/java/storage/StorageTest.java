package storage;

import org.junit.Test;

public class StorageTest {

	@Test
	public static void storageTest(String[] args) {
		Storage storage = new Storage();
		storage.initStorage();

		System.out.println("Original PC:\t" + storage.readPC());
		storage.progressPC();
		System.out.println("Progressed PC by 1:\t" + storage.readPC());
		storage.offsetPC(3);
		System.out.println("Offset PC by 3\t" + storage.readPC());
		storage.writeRegister('a', (byte) 'l');
		System.out.println("Reading a, should be apple:\t"
				+ storage.readRegister('a'));
		storage.writeRegister('c', (byte) 'u');
		System.out.println("Wrote to wrong register");
		storage.writeRegister('B', (byte) 'o');
		System.out.println("Reading b, should be Pi3:\t"
				+ storage.readRegister('B'));
		storage.readRegister('c');
		System.out.println("Read from register c");
		storage.writeMemory("0x4f", (byte) 'v');
		storage.writeMemory("0X50", (byte) 'e');
		System.out
				.println("Reading from memory:\t" + storage.readMemory("0x4f")
						+ " " + storage.readMemory("0x50"));
	}
}
