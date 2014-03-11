package org.control;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.model.Post;
import org.model.User;
import org.view.LoginScreen;
/**
 * MainControll Class
 * @author Philipp
 *
 */
public class Main {
	public static boolean localDB = false;
	public static boolean testDB = false;
	
	public static void main(String[] args){
	    try {
	    	//Design setzen
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    } 
	    catch (Exception e) {
	    	// handle exception
	    }
	    
	    //DB initialisieren
	    DatabaseControl.getInstance();
	    
		LoginScreen ls = LoginScreen.getInstance();
			ls.setVisible(true);
			//disover control initialize
			//DiscoverControl discContr = new DiscoverControl();
			
			//****************************
		
			//****************************		
//			PathControl.getInstance().makeDir("Bilder");
//			BufferedImage img = LoadImageControl.loadBufferedImage("avatar.jpg");
//			SaveImageControl.saveBufferedImage(img, "a");
	}
}
