package org.control;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.model.User;

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
	 * @param imagePath String to the location of the file on the filesystem, if emtpy placeholder is loaded
	 * @return loaded ImageIcon
	 * @pre Imagepath is valid
	 * @post Image is returned
	 */
	public static ImageIcon loadImageIcon(String imagePath, User user){
		ImageIcon imageIcon = new ImageIcon(loadBufferedImage(imagePath, user).getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		return imageIcon;
	}
	
	/**
	 * Lädt ein icon für das Menü auf der Westbar
	 * @param name Name des Icons das geladen werden soll
	 * @return ImageIcon für das Menü
	 * @pre name existiert
	 * @past true
	 */
	public static ImageIcon loadMenuIcon(String name, int size)
	{
		BufferedImage image = new BufferedImage(40, 40, BufferedImage.TYPE_INT_RGB);
		try {
				image = ImageIO.read(ClassLoader.getSystemClassLoader().getResource(name));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ImageIcon(image.getScaledInstance(size, size, Image.SCALE_SMOOTH));
	}
	
	/**
	 * load BufferedImage from Filesystem
	 * @param imagePath String to the location of the file on the filesystem, if emtpy placeholder is loaded
	 * @return loaded ImageIcon
	 * @pre Imagepath is valid
	 * @post Image is returned
	 */
	public static BufferedImage loadBufferedImage(String imagePath, User user){
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		try {
			if(imagePath == null || imagePath.equals("") || imagePath.equals("placeholder.jpg")){

				image = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("placeholder.jpg"));
			} else{
				String filepath = PathControl.getInstance().getRoot() + File.separator
						+ "Bilder" + File.separator + user.getUsername() + File.separator + imagePath;
				image = ImageIO.read(new File(filepath));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
}
