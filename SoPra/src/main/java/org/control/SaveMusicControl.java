package org.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 * @author Tim Lange
 *
 */
public class SaveMusicControl {
	
	/**
	 * !!!!!!!!!!!!!WORK IN PROGRESS!!!!!!!!!!!!!!!!!!!!!!
	 * 
	 * save music with specific filename(with extension)
	 * @param song File
	 * @param username for which user the image is to be saved
	 * @param filename specific filename(with extension eg. "filename.jpg") 
	 */
	public static void saveSong(File song, String username, String filename){
		
		String extension, filenameWithoutExtension, filepath;
		filenameWithoutExtension = filename.substring(0, filename.indexOf("."));
		extension = filename.substring(filename.indexOf(".")+1, filename.length());
		
		filepath = System.getProperty("user.dir")+"\\SoPraMediaDatabase\\"+username+"\\music\\";

		//create folder if needed
		new File(filepath+filename).mkdirs();

		File file = new File(filepath + filenameWithoutExtension + "."+extension);

		try (FileOutputStream fop = new FileOutputStream(file)) {
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			fop.flush();
			fop.close();
 
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
