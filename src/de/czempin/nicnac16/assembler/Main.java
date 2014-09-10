package de.czempin.nicnac16.assembler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

public class Main {

	private static final int PAGE_SIZE = 0xff;

	public static void main(String[] args) throws IOException {
		Map<String, Integer> opcodes = new HashMap<String, Integer>() {
			{
				put("LDA", 0b0100);
				put("STA", 0b0101);
				put("ADD", 0b0110);
				put("DIO", 0b1111);
			}
		};
		File file = new File("test2.asm");
		Charset charset = Charset.defaultCharset();
		ImmutableList<String> lines = Files.asCharSource(file, charset).readLines();
		int[] rom = new int[PAGE_SIZE];
		int[] ram = new int[PAGE_SIZE];

		// not necessary IIRC :-)
		for (int i = 0; i < PAGE_SIZE; i++) {
			rom[i] = 0;
			ram[i] = 0;
		}
		int currentAddress = 0;
		for (String line : lines) {
			StringTokenizer st = new StringTokenizer(line);
			String token = null;
			String operand = null;
			while (st.hasMoreTokens()) {
				token = st.nextToken();
				token = token.trim();
				if (token.startsWith("*")) {
					String address = token.substring(3);
					currentAddress = Integer.parseInt(address, 16);
					break;
				} else if (token.startsWith(";")) {
					break; // ignore comments
				} else if (token.startsWith(".word")) {
					String s = st.nextToken().substring(1);;
					writeToRom(rom, currentAddress, s);
					currentAddress++;
					break;
				}

				Integer opcode = opcodes.get(token);
				if (opcode != null) {
					operand = st.nextToken().substring(1);
					String s = String.format("%x%s", opcode, operand);
					writeToRom(rom, currentAddress, s);
					currentAddress++;
				}else {
					throw new RuntimeException("unknown opcode:"+token);
				}
			}
		}
		for (int i = 0; i < currentAddress; i++) {
			String binary = convertTo16bitBinary(rom[i]);
			
			System.out.println(binary);
		}

	}

	private static void writeToRom(int[] rom, int currentAddress, String s) {
		int decimal = Integer.parseInt(s, 16);

		rom[currentAddress] = decimal;
	}

	private static String convertTo16bitBinary(int decimal) {
		return String.format("%16s", Integer.toBinaryString(decimal)).replace(" ", "0");
	}

}
