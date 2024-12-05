package com.magis.utilities;

import java.util.UUID;

import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class Images {
//    private static ApplicationParameters parameters;
    protected static final Log logger = LogFactory.getLog("utilities.Images");


    public static String getImageType( byte [] data){
//        filetype    magic number(hex)
//        jpg         FF D8 FF
//        gif         47 49 46 38
//        png         89 50 4E 47 0D 0A 1A 0A
//        bmp         42 4D
//        tiff(LE)    49 49 2A 00
//        tiff(BE)    4D 4D 00 2A

        final byte[] pngPattern = new byte[] { (byte)0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
        final byte[] jpgPattern = new byte[] { (byte)0xFF, (byte)0xD8, (byte)0xFF};
        final byte[] gifPattern = new byte[] { 0x47, 0x49, 0x46, 0x38};
        final byte[] bmpPattern = new byte[] { 0x42, 0x4D };
        final byte[] tiffLEPattern = new byte[] { 0x49, 0x49, 0x2A, 0x00};
        final byte[] tiffBEPattern = new byte[] { 0x4D, 0x4D, 0x00, 0x2A};
        if (isMatch(pngPattern, data))
            return "png";

        if (isMatch(jpgPattern, data))
            return "jpg";

        if (isMatch(gifPattern, data))
            return "gif";

        if (isMatch(bmpPattern, data))
            return "bmp";

        if (isMatch(tiffLEPattern, data))
            return "tif";

        if (isMatch(tiffBEPattern, data))
            return "tif";

        return null;
    }

    private static boolean isMatch(byte[] pattern, byte[] data) {
        if (pattern.length <= data.length) {
            for (int idx = 0; idx < pattern.length; ++idx) {
                if (pattern[idx] != data[idx])
                    return false;
            }
            return true;
        }

        return false;
    }

    public static String saveImage(String base64, String folder,String prefix) {
        String fileName = UUID.randomUUID().toString();        
    	if (prefix.trim().length()>0) fileName = prefix + "_" + fileName;
        try {
        	if (!folder.endsWith("/")) folder = folder + "/";
        	String imageType = getImageType(Base64.decode(base64));
            if (imageType != null) {
                fileName += "." +imageType;
                FileUtilities.writeFileAsBinary(folder + fileName, base64);
                return fileName;
            } else return null;
		} catch (Exception e) {
			logger.error("Error:",e);
			return null;
		}
    }

    public static boolean isFileExists(String base64, String folder,String fileName) {
    	try {
        	if (!folder.endsWith("/")) folder = folder + "/";
        	String imageType = getImageType(Base64.decode(base64));
        	return FileUtilities.isFileExists(folder + fileName + "." + imageType);
    	} catch (Exception e) {
			logger.error("Error:",e);
		}
    	return false;
    }
    
    public static String saveImageWithFileName(String base64, String folder,String fileName) {
    	try {
        	if (!folder.endsWith("/")) folder = folder + "/";
        	String imageType = getImageType(Base64.decode(base64));
            if (imageType != null) {
                fileName += "." +imageType;
                FileUtilities.writeFileAsBinary(folder + fileName, base64);
                return fileName;
            } else return null;
		} catch (Exception e) {
			logger.error("Error:",e);
			return null;
		}
    }

    public static void main(String[] args) throws IllegalBlockSizeException, Exception {
//    	String fileName= saveImage("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", 
 //   			"d:/mehmet/user/");	 
    	//FileUtilities.deleteFile("D:/conf/servvis/binaries/user_images/39de35c8-2894-4bf0-a335-7c442aa7191f/1f71beff-31c9-40dc-9965-6ddd3af49116.jpg");
    	System.out.println(FileUtilities.isFileExists("D:/conf/servvis/binaries/public/announcements/1.png"));
    }	

}
