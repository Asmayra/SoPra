package org.view;

import java.awt.BorderLayout;







import javax.swing.JFrame;
import javax.swing.JPanel;

import org.view.screens.Center.HomeScreen;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.Soutbar.MusicPlayer;
import org.view.screens.WestBar.CoverArt;
import org.view.screens.WestBar.Menu;
import org.view.screens.WestBar.PlaylistMiniScreen;
import org.view.screens.WestBar.SongTicker;
import org.view.screens.WestBar.WestBar;
/**
 * MainScreen Class to order screens in 4 Areas
 * @author Philipp
 *
 */
public class MainScreen extends JFrame {
	JPanel center,south,west,east;
	
	public JPanel getCenter() {
		return center;
	}
	public void setCenter(JPanel center) {
		this.center = center;
	}
	public JPanel getSouth() {
		return south;
	}
	public void setSouth(JPanel south) {
		this.south = south;
	}
	public JPanel getWest() {
		return west;
	}
	public void setWest(JPanel west) {
		this.west = west;
	}
	public JPanel getEast() {
		return east;
	}
	public void setEast(JPanel east) {
		this.east = east;
	}
	public MainScreen(){
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	/**
	 * Set's up the Mainscreen with a BorderLayout for the defaultUser
	 */
	public void setUpUserLayout(){
		//Add the MusicPlayer at the button
		this.south = new MusicPlayer();
		//Add a MiniDiscover bar in the right
		this.east = new DiscoverMiniScreen();
		//Add the Westbar(BoxLayout) with a Menu, a Playlist MiniScreen and a AcoverArt with a SongTicker		
		this.west = new WestBar();
		west.add(new Menu());
		west.add(new PlaylistMiniScreen());
		west.add(new CoverArt());
		west.add(new SongTicker());
		//Add the HomeScreen to the Center
		this.center = new HomeScreen();
		this.update();
	}
	
	/**
	 * Updates the 4 Areas of the MainScreen
	 */
	public void update(){
		this.add(center,BorderLayout.CENTER);
		this.add(east,BorderLayout.EAST);
		this.add(south,BorderLayout.SOUTH);
		this.add(west,BorderLayout.WEST);
		this.pack();this.setVisible(true);
	}
	
	

}
