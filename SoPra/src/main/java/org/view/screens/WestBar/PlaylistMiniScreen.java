package org.view.screens.WestBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.control.LoginControl;
import org.control.listener.FavoritButtonListener;
import org.control.listener.GenresButtonListener;
import org.control.listener.LabelButtonListener;
import org.control.listener.PlaylistTreeListener;
import org.control.listener.SubscriptionButtonListener;
import org.control.listener.UploadButtonListener;
import org.control.listener.UploadPageButtonListener;
import org.model.Album;
import org.model.Playlist;
import org.model.User;
import org.view.screens.Center.PlaylistExtendedScreen;
/**
 * 
 * @author Tim Michels, Max Küper
 */
public class PlaylistMiniScreen extends JPanel{
	
	private JTabbedPane tabbedPane  = new JTabbedPane();
	private JPanel admin = new JPanel();
	private JPanel user = new JPanel();
	private JButton subscriptions; 
	private JButton favorits;
	private JButton genres;
	private JButton uploads;
	private JButton label;
	private JScrollPane scrollUser;
	private JScrollPane scrollAdmin;
	private JLabel invisible;
	private JTree playlists;
	private JTree tickets;
	DefaultMutableTreeNode rootUser = new DefaultMutableTreeNode("root");
	DefaultMutableTreeNode rootAdmin = new DefaultMutableTreeNode("root");
	private User currentUser;
	
	/**
	 * erstellt eine TabbedPane mit einer Playlistanzeige für den Benutzer und zusätzlich einer Ticketanzeige für den Admin(falls man als solcher angemeldet ist)
	 * 
	 */
	
	private static PlaylistMiniScreen instance;
	
	public static PlaylistMiniScreen getInstance()
	{
		if(instance == null)
			instance = new PlaylistMiniScreen();
		
		return instance;
	}

	
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
		currentUser=LoginControl.getInstance().getCurrentUser();
		ArrayList<Playlist> userPlaylists = (ArrayList<Playlist>) currentUser.getPlaylists();
		ArrayList<Album> userAlbums = (ArrayList<Album>) currentUser.getAlben();
		
		DefaultMutableTreeNode dmtn = null;
		for (int i = 0; i < userPlaylists.size(); i++) {//über Liste/Array/Vector von Playlists die man vom User bekommt
			dmtn = new DefaultMutableTreeNode(userPlaylists.get(i)); // Name der Playlist 
			playlist.add(dmtn);
		}
		for(int i =0;i< userAlbums.size();i++){//über Liste/Array/Vector von gefolgten Alben, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode(userAlbums.get(i)); // Name des Albums 
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
	/**
	 * erstellt das Panel in dem die Playlists des Nutzers angezeigt wird
	 */
	public void createUserPanel(){
		user.setLayout(new GridBagLayout());
		
		playlists = new JTree(rootUser){
			@Override
			public boolean isPathEditable(TreePath path) {
				if(this.getRowForPath(path.getParentPath())==0){return true;}
				return false;
			}
		};//getcurrentUser.getuser.getPlaylists();
		//Buttons
		subscriptions = new JButton("Subscriptions");
		favorits = new JButton("Favoriten");
		genres = new JButton("Genres");
		uploads = new JButton("Uploads");
		label = new JButton("Label");
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
		uploads.setOpaque(false);
		uploads.setContentAreaFilled(false);
		uploads.setBorderPainted(false);
		uploads.setBackground(Color.WHITE);
		label.setOpaque(false);
		label.setContentAreaFilled(false);
		label.setBorderPainted(false);
		label.setBackground(Color.WHITE);
		//Layoutmanager + Anordnung
		GridBagConstraints c  = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0001;
		c.weighty = 0.0001;
		subscriptions.addActionListener(new SubscriptionButtonListener());
		user.add(subscriptions,c);
		c.gridy++;
		favorits.addActionListener(new FavoritButtonListener());
		user.add(favorits,c);
		
		if(currentUser.getRights().equals("Admin")){
			c.gridy++;
			genres.addActionListener(new GenresButtonListener());
			user.add(genres,c);
		}	
		else if(currentUser.getRights().equals("Artist")){
			c.gridy++;
			uploads.addActionListener(new UploadPageButtonListener());
			user.add(uploads,c);
		}
		else if(currentUser.getRights().equals("LabelManager")){
			c.gridy++;
			label.addActionListener(new LabelButtonListener());
			user.add(label,c);
		}
		
		c.gridx=0;
		c.gridy++;
		c.weightx = 0.1;
		c.weighty = 0.1;
		user.add(playlists,c);
		

		createTree();
		playlists.expandPath(new TreePath(rootUser.getPath()));
		playlists.setRootVisible(false);
		playlists.addMouseListener(new PlaylistTreeListener());
		playlists.setEditable(true);
		
		scrollUser = new JScrollPane(user);	
	}
	
	public JTree getPlaylists(){
		return playlists;
	}
	
	public void updateMiniScreen(){
		playlists.removeAll();
		createTree();
		playlists.updateUI();
	}

}
