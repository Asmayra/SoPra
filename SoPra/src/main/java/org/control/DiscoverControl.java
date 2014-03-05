package org.control;

import java.util.LinkedList;

import org.view.screens.Center.DiscoverExtendedScreen;
import org.view.screens.EastBar.DiscoverElement;
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
	    DiscoverElement discover1 = new DiscoverElement("text1", LoadImageControl.loadImageIcon("placeholder.jpg"), "url1");
	    DiscoverElement discover2 = new DiscoverElement("text2", LoadImageControl.loadImageIcon("placeholder.jpg"), "url2");
	    DiscoverElement discover3 = new DiscoverElement("text3", LoadImageControl.loadImageIcon("placeholder.jpg"), "url3");
	    DiscoverElement discover4 = new DiscoverElement("text4", LoadImageControl.loadImageIcon("placeholder.jpg"), "url4");
	    DiscoverElement discover5 = new DiscoverElement("text5", LoadImageControl.loadImageIcon("placeholder.jpg"), "url5");
	    DiscoverElement discover6 = new DiscoverElement("text6", LoadImageControl.loadImageIcon("placeholder.jpg"), "url6");
	    
	    miniScreen.setDiscoverOne(discover1);
	    miniScreen.setDiscoverTwo(discover2);
	    miniScreen.setDiscoverThree(discover3);
	    
	    artists.add(discover1);
	    artists.add(discover4);
	    albums.add(discover2);
	    albums.add(discover5);
	    songs.add(discover3);
	    songs.add(discover6);
	    
	    extScreen.setDiscoverArtists(artists);
	    extScreen.setDiscoverAlbums(albums);
	    extScreen.setDiscoverSongs(songs);
	    
	    
	}



}
