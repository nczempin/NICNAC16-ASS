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
		File file = new File("test1.asm");
		Charset charset = Charset.defaultCharset();
		ImmutableList<String> lines = Files.asCharSource(file, charset).readLines();
		short[] rom = new short[PAGE_SIZE];
		short[] ram = new short[PAGE_SIZE];

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
				if (token.startsWith("*")) {
					String address = token.substring(3);
					currentAddress = Integer.parseInt(address, 16);
					System.out.println("decimal address: "+currentAddress);
					break; // TODO ignore for now.
				} else if (token.startsWith(";")) {
					break; // ignore comments
				} else if (token.startsWith(".")) {
					break; // TODO ignore for now
				}
				Integer opcode = opcodes.get(token);
				if (opcode != null) {
					operand = st.nextToken().substring(1);
					String s = String.format("%x%s", opcode, operand);
					int decimal = Integer.parseInt(s,16);
					
					String binary = String.format("%16s",Integer.toBinaryString(decimal)).replace(" ", "0");;
					System.out.println(currentAddress+": "+s + "->" + binary);
					currentAddress++;
				}
			}
		}

	}

}
