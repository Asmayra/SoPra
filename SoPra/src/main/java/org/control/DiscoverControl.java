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
   
	    DiscoverMiniScreen miniScreen = DiscoverMiniScreen.getInstance();
	    DiscoverElement discover1 = new DiscoverElement("text1", LoadImageControl.loadImageIcon("placeholder.jpg"), "url1");
	    DiscoverElement discover2 = new DiscoverElement("text2", LoadImageControl.loadImageIcon("placeholder.jpg"), "url2");
	    DiscoverElement discover3 = new DiscoverElement("text3", LoadImageControl.loadImageIcon("placeholder.jpg"), "url3");
	    
	    miniScreen.setDiscoverOne(discover1);
	    miniScreen.setDiscoverTwo(discover2);
	    miniScreen.setDiscoverThree(discover3);
	}



}
