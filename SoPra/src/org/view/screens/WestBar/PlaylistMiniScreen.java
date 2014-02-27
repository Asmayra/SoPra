package org.view.screens.WestBar;

import javax.swing.*;

public class PlaylistMiniScreen extends JPanel{
	
	private JTabbedPane tabbedPane  = new JTabbedPane();
	private JPanel admin = new JPanel();
	private JPanel user = new JPanel();
	private JScrollPane scroll;
	private JLabel subscriptions; 
	
	
	private JTree playlists = new JTree( );
	
	
	public PlaylistMiniScreen() {
		user.add(playlists);
		
		
		
		
		scroll = new JScrollPane(user);
		
		
	}
}
