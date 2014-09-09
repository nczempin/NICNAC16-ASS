package de.czempin.nicnac16.assembler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;


public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("test1.asm");
		Charset charset = Charset.defaultCharset();
			ImmutableList<String> lines = Files.asCharSource(file, charset).readLines();
			for (String line : lines) {
				System.out.println(line);
			}

	}

}
