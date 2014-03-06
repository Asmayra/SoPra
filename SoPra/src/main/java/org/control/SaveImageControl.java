package org.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Tim Lange
 *
 */
public class SaveImageControl {

	/**
	 * saves the image as cover for username	
	 * @param image Buffered image
	 * @param username for which user the cover is to be saved
	 */
	public static void saveBufferedImage(BufferedImage image, String username){
		saveBufferedImage(image, username, "avatar.jpg");
	}
	
	/**
	 * save image with specific filename(with extension)
	 * @param image Buffered image
	 * @param username for which user the image is to be saved
	 * @param filename specific filename(with extension eg. "filename.jpg") 
	 */
	public static void saveBufferedImage(BufferedImage image, String username, String filename){
		
		String extension, filenameWithoutExtension, filepath;
		filenameWithoutExtension = filename.substring(0, filename.indexOf("."));
		extension = filename.substring(filename.indexOf(".")+1, filename.length());
		
		filepath = System.getProperty("user.dir")+"\\SoPraMediaDatabase\\"+username+"\\";

		//create folder if needed
		new File(filepath+filename).mkdirs();

		try {
            ImageIO.write(image, extension, new File(filepath + filenameWithoutExtension + "."+extension));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
}
