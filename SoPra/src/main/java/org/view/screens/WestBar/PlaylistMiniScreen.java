package org.view.screens.WestBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

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
		createUserPanel();
		tabbedPane.addTab("Playlists", scroll);
		tabbedPane.setPreferredSize(new Dimension(450,350));
		add(tabbedPane);
		
	}
	
	public void createTree(){
		
		DefaultMutableTreeNode playlist = new DefaultMutableTreeNode("Playlisten");
		DefaultMutableTreeNode album =new DefaultMutableTreeNode("Alben");
		root.add(playlist);
		root.add(album);
		DefaultMutableTreeNode dmtn = null;
		for (int i = 0; i < 4; i++) {//über Liste/Array/Vector von Playlists die man vom User bekommt
			dmtn = new DefaultMutableTreeNode("super Playlist"); // Name der Playlist 
			playlist.add(dmtn);
		}
		for(int i =0;i< 3;i++){//über Liste/Array/Vector von gefolgten Alben, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode("awesome Album"); // Name des Albums 
			album.add(dmtn);
		}
	}
	public void createAdminPanel(){
		admin.setLayout(new GridBagLayout());
user.setLayout(new GridBagLayout());
		
		playlists = new JTree(root);//getcurrentUser.getuser.getPlaylists();
		
		//Buttons
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
		
		//Layoutmanager + Anordnung
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=0;
		c.gridy = 0;
		admin.add(playlists,c);
		

		createAdminTree();
		playlists.expandPath(new TreePath(root.getPath()));
		playlists.setRootVisible(false);
		
		scroll = new JScrollPane(admin);	

	}
	private void createAdminTree() {
		// TODO Auto-generated method stub
		
	}

	public void createUserPanel(){
		user.setLayout(new GridBagLayout());
		
		playlists = new JTree(root);//getcurrentUser.getuser.getPlaylists();
		
		//Buttons
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
		
		//Layoutmanager + Anordnung
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
		

		createTree();
		playlists.expandPath(new TreePath(root.getPath()));
		playlists.setRootVisible(false);
		
		
		
		scroll = new JScrollPane(user);	
	}
}
