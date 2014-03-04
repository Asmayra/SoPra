package org.view.screens.WestBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.control.LoginControl;
import org.model.User;

public class PlaylistMiniScreen extends JPanel{
	
	private JTabbedPane tabbedPane  = new JTabbedPane();
	private JPanel admin = new JPanel();
	private JPanel user = new JPanel();
	private JButton subscriptions; 
	private JButton favorits;
	private JButton genres;
	private JScrollPane scrollUser;
	private JScrollPane scrollAdmin;
	private JLabel invisible;
	private JTree playlists;
	private JTree tickets;
	DefaultMutableTreeNode rootUser = new DefaultMutableTreeNode("root");
	DefaultMutableTreeNode rootAdmin = new DefaultMutableTreeNode("root");
	
	private User currentUser;
	
	
	public PlaylistMiniScreen() {
		LoginControl logcon = LoginControl.getInstance();
		currentUser = logcon.getCurrentUser();
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		createUserPanel();
		
		tabbedPane.addTab("Playlists", scrollUser);
		if(currentUser.getRights().equals("Admin")){
			createAdminPanel();
			tabbedPane.addTab("Tickets", scrollAdmin);
		}
		tabbedPane.setPreferredSize(new Dimension(250,240));
		add(tabbedPane);
		
		this.setPreferredSize(new Dimension(250,250));
		
	}
	
	public void createTree(){
		
		DefaultMutableTreeNode playlist = new DefaultMutableTreeNode("Playlisten");
		DefaultMutableTreeNode album =new DefaultMutableTreeNode("Alben");
		rootUser.add(playlist);
		rootUser.add(album);
		DefaultMutableTreeNode dmtn = null;
		for (int i = 0; i < 4; i++) {//über Liste/Array/Vector von Playlists die man vom User bekommt
			dmtn = new DefaultMutableTreeNode("super Playlist "+i); // Name der Playlist 
			playlist.add(dmtn);
		}
		for(int i =0;i< 3;i++){//über Liste/Array/Vector von gefolgten Alben, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode("awesome Album "+i); // Name des Albums 
			album.add(dmtn);
		}
	}
	public void createAdminPanel(){
		admin.setLayout(new GridBagLayout());	
		createAdminTree();
		tickets = new JTree(rootAdmin);//getcurrentUser.getuser.getPlaylists();
		//Layoutmanager + Anordnung
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy = 0;
		c.weightx = 10;
		c.weighty =10;
		admin.add(tickets,c);
		c.gridy = 11;
		c.weightx =0.1;
		c.weighty = 0.1;
		invisible = new JLabel();
		admin.add(invisible,c);

		
		tickets.expandPath(new TreePath(rootAdmin.getPath()));
		tickets.setRootVisible(false);
		
		scrollAdmin = new JScrollPane(admin);	

	}
	private void createAdminTree() {
		DefaultMutableTreeNode account = new DefaultMutableTreeNode("Account erweitern");
		DefaultMutableTreeNode complaint =new DefaultMutableTreeNode("Beschwerden");
		DefaultMutableTreeNode password =new DefaultMutableTreeNode("Passwort vergessen");
		DefaultMutableTreeNode genre =new DefaultMutableTreeNode("Genreanfrage");
		DefaultMutableTreeNode other =new DefaultMutableTreeNode("Sonstiges");
		rootAdmin.add(account);
		rootAdmin.add(complaint);
		rootAdmin.add(password);
		rootAdmin.add(genre);
		rootAdmin.add(other);
		DefaultMutableTreeNode dmtn = null;
		for (int i = 0; i < 2; i++) {//über Liste/Array/Vector von Tickets die man vom Nutzer bekommt
			dmtn = new DefaultMutableTreeNode("Ich will mehr _"+i); // Name des Tickets
			account.add(dmtn);
		}
		for(int i =0;i< 3;i++){//über Liste/Array/Vector von gefolgten Tickets, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode("du bist doof _"+i); // Name des Tickets 
			complaint.add(dmtn);
		}
		for(int i =0;i< 4;i++){//über Liste/Array/Vector von gefolgten Tickets, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode("HILFE!!! _"+i); // Name des Tickets 
			password.add(dmtn);
		}
		for(int i =0;i< 5;i++){//über Liste/Array/Vector von gefolgten Tickets, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode("Tolle neue Musik _"+i); // Name des Tickets 
			genre.add(dmtn);
		}
		for(int i =0;i< 7;i++){//über Liste/Array/Vector von gefolgten Tickets, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode("Ich weiß nicht weiter _"+i); // Name des Tickets 
			other.add(dmtn);
		}
	}
	public void createUserPanel(){
		user.setLayout(new GridBagLayout());
		
		playlists = new JTree(rootUser);//getcurrentUser.getuser.getPlaylists();
		
		//Buttons
		subscriptions = new JButton("Subscriptions");
		favorits = new JButton("Favoriten");
		genres = new JButton("Genres");
		subscriptions.setOpaque(false);
		subscriptions.setContentAreaFilled(false);
		subscriptions.setBorderPainted(false);
		subscriptions.setBackground(Color.WHITE);
		favorits.setOpaque(false);
		favorits.setContentAreaFilled(false);
		favorits.setBorderPainted(false);
		favorits.setBackground(Color.WHITE);
		genres.setOpaque(false);
		genres.setContentAreaFilled(false);
		genres.setBorderPainted(false);
		genres.setBackground(Color.WHITE);
		
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
		c.gridy++;
		user.add(favorits,c);
		
		if(currentUser.getRights().equals("Admin")){
			c.gridy++;
			user.add(genres,c);
		}
		c.gridx=0;
		c.gridy++;
		c.weightx = 0.1;
		c.weighty = 0.1;
		user.add(playlists,c);
		

		createTree();
		playlists.expandPath(new TreePath(rootUser.getPath()));
		playlists.setRootVisible(false);
		
		
		
		scrollUser = new JScrollPane(user);	
	}
}
