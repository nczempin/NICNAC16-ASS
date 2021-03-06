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

	private static final int PAGE_SIZE = 0x100;

	@SuppressWarnings("serial")
	public static void main(String[] args) throws IOException {
		SymbolTable symbols = new SymbolTable(){

		};

		Map<String, Integer> opcodes = new HashMap<String, Integer>() {
			{
				put("NOP", 0b0000);
				put("JMP", 0b0001);
				put("BL", 0b0010);
				put("RET", 0b0011);
				put("LDA", 0b0100);
				put("STA", 0b0101);
				put("ADD", 0b0110);
				put("BAN", 0b1000);
				put("DIO", 0b1111);
			}
		};
		File file = new File("testSymbols.asm");
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
		int maxAddress = 0;
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
					if (currentAddress > maxAddress) {
						maxAddress = currentAddress;
					}
					break;
				} else if (token.startsWith(";")) {
					break; // ignore comments
				} else if (token.startsWith(".word")) {
					String s = st.nextToken().substring(1);
					writeToRom(rom, currentAddress, s);
					currentAddress++;
					if (currentAddress > maxAddress) {
						maxAddress = currentAddress;
					}
					break;
				} else if (token.endsWith(":")) { //label
					String symbol = token.substring(0, token.length() - 1); // remove the ":"
					Integer value = symbols.get(symbol);
					if (value == null) {
						symbols.put(symbol, currentAddress);
					} else {
						System.out.println("Warning: redefining symbol " + symbol + " ignored");
					}
					System.out.println("Label: " + symbol + ", value: " + String.format("%x", currentAddress));
					break;
				}

				Integer opcode = opcodes.get(token);
				if (opcode != null) {
					System.out.println("opcode: " + token);
					if (st.hasMoreTokens()) {

						String rawOperand = st.nextToken();
						if (rawOperand.startsWith("$")) {
							operand = rawOperand.substring(1);
						} else {
							String symbol = rawOperand;
							System.out.println("new symbol: " + symbol);
							operand = symbols.handle(symbol,currentAddress);
						}
					} else {
						operand = "000"; // implicit address, for example for "NOP"
					}
					String s = String.format("%x%s", opcode, operand);
					System.out.println("operand: " + operand);
					System.out.println("opcode: " + opcode);
					System.out.println("writing: " + s);
					writeToRom(rom, currentAddress, s);
					currentAddress++;
					if (currentAddress > maxAddress) {
						maxAddress = currentAddress;
					}
				} else {
					throw new RuntimeException("unknown opcode:" + token);
				}
			}
		}
		System.out.println("********");
		symbols.print();
		
//		for (int i = 0; i < maxAddress; i++) {
//			String binary = convertTo16bitBinary(rom[i]);
//
//			System.out.println(binary);
//		}

	}

	private static void writeToRom(int[] rom, int currentAddress, String s) {
		int decimal = Integer.parseInt(s, 16);

		rom[currentAddress] = decimal;
	}

	private static String convertTo16bitBinary(int decimal) {
		return String.format("%16s", Integer.toBinaryString(decimal)).replace(" ", "0");
	}

}
