package org.control;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.model.Post;
import org.model.User;
import org.view.LoginScreen;
/**
 * MainControll Class
 * @author Philipp
 *
 */
public class Main {
	public static boolean testDB = false;
	
	public static void main(String[] args){
			LoginScreen ls = LoginScreen.getInstance();
			ls.setVisible(true);
			DiscoverControl discControl = new DiscoverControl();
			SubscriptionControl subControl = new SubscriptionControl();
			//disover control initialize
			//DiscoverControl discContr = new DiscoverControl();
			
			//****************************
		
			//****************************		
//			PathControl.getInstance().makeDir("Bilder");
//			BufferedImage img = LoadImageControl.loadBufferedImage("avatar.jpg");
//			SaveImageControl.saveBufferedImage(img, "a");
	}
}
