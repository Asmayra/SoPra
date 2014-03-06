package org.control;

import java.util.LinkedList;

import org.view.DiscoverElement;
import org.view.screens.Center.DiscoverExtendedScreen;
import org.view.screens.EastBar.DiscoverMiniScreen;

/**
 * 
 * @author Tim Lange, Tim Michels
 *
 */
public class DiscoverControl {
	
	public DiscoverControl(){
   
		LinkedList<DiscoverElement> artists= new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> albums= new LinkedList<DiscoverElement>();
		LinkedList<DiscoverElement> songs= new LinkedList<DiscoverElement>();
		
	    DiscoverMiniScreen miniScreen = DiscoverMiniScreen.getInstance();
	    DiscoverExtendedScreen extScreen = DiscoverExtendedScreen.getInstance();
	    
	    //Erzeugen von Dummydaten
	    DiscoverElement discover1mini = new DiscoverElement("text1", LoadImageControl.loadImageIcon(""), "url1", "Album");
	    DiscoverElement discover2mini = new DiscoverElement("text2", LoadImageControl.loadImageIcon(""), "url2","User");
	    DiscoverElement discover3mini = new DiscoverElement("text3", LoadImageControl.loadImageIcon(""), "url3","Song");
	    
	    miniScreen.setDiscoverOne(discover1mini);
	    miniScreen.setDiscoverTwo(discover2mini);
	    miniScreen.setDiscoverThree(discover3mini);
	    
	    for (int i = 0; i < 12; i++) {
	    	 DiscoverElement discover = new DiscoverElement("Künstler "+(i+1), LoadImageControl.loadImageIcon(""), "url "+i,"User");
	    	 artists.add(discover);
		}
	    for (int i = 0; i < 126 ;i++) {
	    	 DiscoverElement discover = new DiscoverElement("Lied "+(i+1), LoadImageControl.loadImageIcon(""), "url "+i,"Song");
	    	 songs.add(discover);
		}
	    for (int i = 0; i < 15; i++) {
	    	 DiscoverElement discover = new DiscoverElement("Album "+(i+1), LoadImageControl.loadImageIcon(""), "url "+i,"Album");
	    	 albums.add(discover);
		}
	    
	    
	    extScreen.setDiscoverArtists(artists);
	    extScreen.setDiscoverAlbums(albums);
	    extScreen.setDiscoverSongs(songs);
	    extScreen.showRecommendations();
	    
	    
	}



}
