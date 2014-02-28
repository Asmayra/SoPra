package org.view.screens.WestBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class PlaylistMiniScreen extends JPanel{
	
	private JTabbedPane tabbedPane  = new JTabbedPane();
	private JPanel admin = new JPanel();
	private JPanel user = new JPanel();
	private JButton subscriptions; 
	private JButton favorits;
	private JScrollPane scroll;
	private JLabel invisible;
	private JTree playlists;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
	
	
	public PlaylistMiniScreen() {
		
		playlists = new JTree(root);//getcurrentUser.getuser.getPlaylists();
		subscriptions = new JButton("Subscriptions");
		favorits = new JButton("Favoriten");
		subscriptions.setOpaque(false);
		subscriptions.setContentAreaFilled(false);
		subscriptions.setBorderPainted(false);
		subscriptions.setBackground(Color.WHITE);
		favorits.setOpaque(false);
		favorits.setContentAreaFilled(false);
		favorits.setBorderPainted(false);
		favorits.setBackground(Color.WHITE);
		user.setLayout(new GridBagLayout());
		GridBagConstraints c  = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0001;
		c.weighty = 0.0001;
		user.add(subscriptions,c);
		c.gridy = 1;
		user.add(favorits,c);
		c.gridx=0;
		c.gridy = 2;
		c.weightx = 0.1;
		c.weighty = 0.1;
		user.add(playlists,c);
		
	
		playlists.setRootVisible(false);
		
		
		
		scroll = new JScrollPane(user);
		tabbedPane.addTab("Playlists", scroll);
		//tabbedPane.setPreferredSize(new Dimension(450,350));
		add(tabbedPane);
		
	}
	
	public void createTree(){
		
		DefaultMutableTreeNode playlist = null;
		for (int i = 0; i < 6; i++) {//über List von Playlists die man vom User bekommt
			playlist = new DefaultMutableTreeNode("superPlaylist"); // Name der Playlist 
			root.add(playlist );
		}
	}
}
