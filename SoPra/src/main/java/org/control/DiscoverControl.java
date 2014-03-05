package org.control;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.view.screens.Center.DiscoverExtendedScreen;
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
   
		LinkedList<DiscoverElement> artists= new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> albums= new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> songs= new LinkedList<DiscoverElement>();
		
	    DiscoverMiniScreen miniScreen = DiscoverMiniScreen.getInstance();
	    DiscoverExtendedScreen extScreen = DiscoverExtendedScreen.getInstance();
	    DiscoverElement discover1 = new DiscoverElement("text1", LoadImageController.loadImageIcon("placeholder.jpg"), "url1");
	    DiscoverElement discover2 = new DiscoverElement("text2", LoadImageController.loadImageIcon("placeholder.jpg"), "url2");
	    DiscoverElement discover3 = new DiscoverElement("text3", LoadImageController.loadImageIcon("placeholder.jpg"), "url3");
	    
	    miniScreen.setDiscoverOne(discover1);
	    miniScreen.setDiscoverTwo(discover2);
	    miniScreen.setDiscoverThree(discover3);
	    
	    artists.add(discover1);
	    albums.add(discover2);
	    songs.add(discover3);
	    
	    extScreen.setDiscoverArtists(artists);
	    extScreen.setDiscoverAlbums(albums);
	    extScreen.setDiscoverSongs(songs);
	    
	    
	}



}
