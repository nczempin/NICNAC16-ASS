package de.czempin.nicnac16.assembler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

public class Main {

	private static final int PAGE_SIZE = 0;

	public static void main(String[] args) throws IOException {
		File file = new File("test1.asm");
		Charset charset = Charset.defaultCharset();
		ImmutableList<String> lines = Files.asCharSource(file, charset).readLines();
		short [] rom = new short[PAGE_SIZE];
		short [] ram = new short[PAGE_SIZE];
		
		// not necessary IIRC :-)
		for (int i = 0; i < PAGE_SIZE; i++) {
			rom[i] = 0;
			ram[i] = 0;
		}
		
		for (String line : lines) {
			StringTokenizer st = new StringTokenizer(line);
			String token = null;
			while (st.hasMoreTokens()){
				token = st.nextToken();
				if (token.startsWith("*")){
					break; //TODO ignore for now.
				}else if (token.startsWith(";")){
					break; // ignore comments
				} else if (token.startsWith(".")){
					break; // TODO ignore for now
				}
				System.out.print(token+"-");
			}
			System.out.println();
		}
	
	}

}
