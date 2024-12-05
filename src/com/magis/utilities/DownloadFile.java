package com.magis.utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class DownloadFile {

	public static void main(String[] args) throws MalformedURLException, IOException {
		FileUtils.copyURLToFile(
				  new URL("https://servvis-mediacdn-bucket-s3.s3.eu-central-1.amazonaws.com/Media/Brand/00a20404-9f02-4c91-b15d-be32c282c219.jpg"), 
				  new File("e:\\dene.jpg"), 
				  30000, 
				  30000);
	}

}
