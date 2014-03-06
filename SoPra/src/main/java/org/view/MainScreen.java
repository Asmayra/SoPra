package org.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.view.screens.Center.*;
import org.view.screens.EastBar.*;
import org.view.screens.Southbar.*;
import org.view.screens.WestBar.*;

/**
 * MainScreen Class to order screens in 4 Areas
 * 
 * @author Philipp
 * 
 */
public class MainScreen extends JFrame {
	private JPanel center, south, west;
	private JLayeredPane east;
	private static MainScreen instance;
	
	private final int MIN_WIDTH = 1024;
	private final int MIN_HEIGHT = 768;

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

	public JLayeredPane getEast() {
		return east;
	}

	public void setEast(JLayeredPane east) {
		this.east = east;
	}

	public static MainScreen getInstance(){
		if(instance == null){
			instance = new MainScreen();
		}
		return instance;
	}
	
	private MainScreen() {
		this.setLayout(new BorderLayout(5, 0));
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		//this.setExtendedState(MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Set's up the Mainscreen with a BorderLayout for the defaultUser
	 */
	public void setUpUserLayout() {
		// Add the MusicPlayer at the button
		this.south = new MusicPlayer();
		// Add a MiniDiscover bar in the right
		this.east = EastBar.getInstance();
		// Add the Westbar(BoxLayout) with a Menu, a Playlist MiniScreen and a
		// AcoverArt with a SongTicker
		this.west = new WestBar();
		// Add the HomeScreen to the Center
		this.center = HomeScreen.getInstance();
		this.updateAll();
	}
	/**
	 * Set's up the Mainscreen with a BorderLayout for the Admin
	 */
	public void setUpAdminLayout() {
		// Add the MusicPlayer at the button
		this.south = new MusicPlayer();
		// Add a MiniDiscover bar in the right
		this.east = EastBar.getInstance();
		// Add the Westbar(BoxLayout) with a Menu, a Playlist MiniScreen and a
		// AcoverArt with a SongTicker
		this.west = new WestBar();
		// Add the HomeScreen to the Center
		this.center = new AdminHomeScreen();
		this.updateAll();
	}
	

	/**
	 * Updates the 4 Areas of the MainScreen
	 */
	public void updateAll() {
		this.add(center, BorderLayout.CENTER);
		this.add(east, BorderLayout.EAST);
		this.add(south, BorderLayout.SOUTH);
		this.add(west, BorderLayout.WEST);
//		this.pack();
		this.setVisible(true);
	}
	
	public void updateCenter(JPanel screen)
	{
		this.remove(center);
//		this.pack();
		this.center = screen;
		this.add(center, BorderLayout.CENTER);
//		this.pack();
		this.repaint();
		this.setVisible(true);
	}
	
	public void updateWest(JPanel screen)
	{
		this.remove(west);
		this.pack();
		this.west = screen;
		this.add(west, BorderLayout.WEST);
		this.pack();
		this.setVisible(true);
	}
	
	public void updateEast(JLayeredPane screen)
	{
		this.remove(east);
		this.pack();
		this.east = screen;
		this.add(east, BorderLayout.EAST);
		this.pack();
		this.setVisible(true);
	}
	
	public void updateSouth(JPanel screen)
	{
		this.remove(south);
		this.pack();
		this.south = screen;
		this.add(south, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}

	public void showAdminGenreScreen(AdminGenreScreen screen) {
		this.updateCenter(screen);
	}
	
	public void showSettingsScreen(SettingsScreen screen) {
		this.updateCenter(screen);
	}

	public void showAdminHomeScreen(AdminHomeScreen screen) {
		this.updateCenter(screen);
	}

	public void showDiscoverExtendedScreen(DiscoverExtendedScreen screen) {
		this.updateCenter(screen);
	}

	public void showHomeScreen(HomeScreen screen) {
		updateCenter(screen);
	}

	public void showMailbox(Mailbox screen) {
		updateCenter(screen);
	}

	public void showPlaylistExtendedScreen(PlaylistExtendedScreen screen) {
		this.updateCenter(screen);
	}

	public void showPfofileScreen(ProfileScreen screen) {
		this.updateCenter(screen);
	}
	
	public void showSubscriptionScreen(SubscriptionScreen screen){
		this.updateCenter(screen);
	}


}
