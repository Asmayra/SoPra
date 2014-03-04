package org.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 
 * @author Tim Lange
 *
 */
public class LoadImageController {
	

	private LoadImageController(){
		
	}
	
	/**
	 * load ImageIcon from Filesystem
	 * @param imagePath String to the location of the file on the filesystem
	 * @return loaded ImageIcon
	 */
	public static ImageIcon loadImage(String imagePath){
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		File currDir = new File(".");
	    String path = currDir.getAbsolutePath();
	    path = path.substring(0, path.length()-1);
	    System.out.println(path);
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\"+imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(image);
		
		return imageIcon;
	}
	
	public static String CurrentDir(){
		String path=System.getProperty("java.class.path");
		String FileSeparator=(String)System.getProperty("file.separator");
		return path.substring(0, path.lastIndexOf(FileSeparator)+1);
	}
}