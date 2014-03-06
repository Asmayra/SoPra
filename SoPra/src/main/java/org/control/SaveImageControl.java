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
		
		String extension;
//		filenameWithoutExtension = filename.substring(0, filename.lastIndexOf("."));
		extension = filename.substring(filename.lastIndexOf(".")+1, filename.length());
		
		String path = PathControl.getInstance().getRoot() + File.separator
				 + "Bilder" + File.separator + username + File.separator + filename;
		
		File toSave = new File(path);
		//create folder if needed
		toSave.mkdirs();

		try {
            ImageIO.write(image, extension, toSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
}
