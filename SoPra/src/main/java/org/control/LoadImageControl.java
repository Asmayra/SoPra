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
public class LoadImageControl {
	

	private LoadImageControl(){
		
	}
	
	/**
	 * load ImageIcon from Filesystem
	 * @param imagePath String to the location of the file on the filesystem
	 * @return loaded ImageIcon
	 */
	public static ImageIcon loadImageIcon(String imagePath){
		ImageIcon imageIcon = new ImageIcon(loadBufferedImage(imagePath));
		return imageIcon;
	}
	
	/**
	 * load BufferedImage from Filesystem
	 * @param imagePath String to the location of the file on the filesystem
	 * @return loaded ImageIcon
	 */
	public static BufferedImage loadBufferedImage(String imagePath){
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		try {
			if(imagePath == null || imagePath == ""){
				image = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("placeholder.jpg"));
			} else{
				String filepath = System.getProperty("user.dir")+"\\SoPraMediaDatabase\\"+LoginControl.getInstance().getCurrentUser().getUsername()+"\\";
				image = ImageIO.read(new File(filepath));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
