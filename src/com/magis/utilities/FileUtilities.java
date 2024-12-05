package com.magis.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.codec.binary.Base64;


public class FileUtilities {

	public static void deleteFile(String fileName) throws IOException{
		try {
			Path path = Paths.get(fileName);
			String result ="";
			Files.deleteIfExists(path);
		} catch (Exception e) {
		}
	}
	
	public static String readFile(String fileName) throws IOException{
		Path path = Paths.get(fileName);
		String result ="";
		List<String> contents = Files.readAllLines(path);
		for(String content:contents)
			result = result +content;
		return result;
	}
	
	public static byte[] readFileAsBinary(String fileName) throws IOException{
		Path path = Paths.get(fileName);
		String result ="";
		byte[] contents = Files.readAllBytes(path);
		//for(String content:contents)
		//	result = result +content;
		return contents;
	}

	public static void writeFileAsBinary(String fileName,String content) throws IOException, IllegalBlockSizeException {
		Path path = Paths.get(fileName);
		if (!Files.exists(path)) Files.createDirectories(path.getParent());
		Files.write(path, Base64.decodeBase64(content));
	}

	
	public static void writeFile(String fileName,String content) throws IOException {
		Path path = Paths.get(fileName);
		Files.write(path, content.getBytes());
		/*
		BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
		writer.write(content);*/

	}

	public static boolean isFileExists(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.exists(path);
	}

	public static void delete(String fileName) throws IOException {
		try {
			Path path = Paths.get(fileName);
			Files.deleteIfExists(path);			
		} catch (Exception e) {}
	}
	public static boolean copyFile(String source, String dest){
		boolean result = false;
		try {
//		    CopyOption[] options = new CopyOption[] { StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES };
		    
			Path pathSrc = Paths.get(source);
			Path pathDest = Paths.get(dest);
			Files.copy(pathSrc, pathDest);			
			
			return true;
		} catch (Exception e) {}
		return result;
	}
	
	public static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
	public static List<File> listFilesOldestFirst(final String directoryPath) throws IOException {
	    try (final Stream<Path> fileStream = Files.list(Paths.get(directoryPath))) {
	        return fileStream
	            .map(Path::toFile)
	            .sorted(Comparator.comparing(File::lastModified))
//	            .map(File::toPath)  // remove this line if you would rather work with a List<File> instead of List<Path>
	            .collect(Collectors.toList());
	    }
	}
	
	public static void writeNewFile(String fileName,String content) throws IOException {
		Path path = Paths.get(fileName);
		Files.write(path, content.getBytes(),StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
		/*
		BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
		writer.write(content);*/
	}

	public static void appendFile(String fileName,String content) throws IOException {
		Path path = Paths.get(fileName);
		Files.write(path, content.getBytes(),StandardOpenOption.APPEND);
		/*
		BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
		writer.write(content);*/
	}
	
	public static void main(String[] args) {
		String src = "d:/upload/deneme.jpg";
		String dest = "d:/upload/out/deneme.jpg";
		System.out.println("Result:" + FileUtilities.copyFile(src, dest));
		
	}
}
