package com.filerReadWrite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReadWriteRunner {
	public static void main(String[] args) throws IOException {
		String currentDirectory = System.getProperty("user.dir");
		Path path = Paths.get(currentDirectory+"\\resources\\sample.txt");
		String fileContent = Files.readString(path);
		System.out.println(fileContent);
		
		String newFileContent = fileContent.replace("Line", "Linesk");
		Path newFilePath = Paths.get(currentDirectory+"\\resources\\sample-newk.txt");
		Files.writeString(newFilePath, newFileContent);
		System.out.println(newFileContent);	
		
	}
}
