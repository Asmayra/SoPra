package org.control;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.view.screens.EastBar.DiscoverElement;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.EastBar.EastBar;

/**
 * 
 * @author Tim Lange
 *
 */
public class DiscoverControl {
	
	public DiscoverControl(){
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		URL url = this.getClass().getResource("placeholder.jpg");
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\placeholder.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(image);
		JLabel pictureLabel = new JLabel(imageIcon);
	    
	    DiscoverMiniScreen miniScreen = DiscoverMiniScreen.getInstance();
	    DiscoverElement discover1 = new DiscoverElement("text1", pictureLabel, "url1");
	    pictureLabel = new JLabel(imageIcon);
	    DiscoverElement discover2 = new DiscoverElement("text2", pictureLabel, "url2");
	    pictureLabel = new JLabel(imageIcon);
	    DiscoverElement discover3 = new DiscoverElement("text3", pictureLabel, "url3");
	    
	    miniScreen.setDiscoverOne(discover1);
	    miniScreen.setDiscoverTwo(discover2);
	    miniScreen.setDiscoverThree(discover3);
	}



}
