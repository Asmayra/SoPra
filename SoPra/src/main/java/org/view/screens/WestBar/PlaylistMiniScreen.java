package org.view.screens.WestBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.listener.CreateLabelButtonListener;
import org.control.listener.FavoritButtonListener;
import org.control.listener.GenresButtonListener;
import org.control.listener.LabelButtonListener;
import org.control.listener.PlaylistTreeListener;
import org.control.listener.SubscriptionButtonListener;
import org.control.listener.TicketTreeListener;
import org.control.listener.UploadPageButtonListener;
import org.model.Album;
import org.model.Playlist;
import org.model.Ticket;
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
	private JButton createLabel;
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

	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		instance = null;
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
		java.util.Iterator<Playlist> it = userPlaylists.iterator();
		while (it.hasNext()) {
			Playlist next = it.next();
			if (!next.getName().equals("Favorites")) {
				dmtn = new DefaultMutableTreeNode(next);
				playlist.add(dmtn);
			}
		}
		for(int i =0;i< userAlbums.size();i++){//über Liste/Array/Vector von gefolgten Alben, die man vom Nutzer bekommt 
			dmtn = new DefaultMutableTreeNode(userAlbums.get(i)); // Name des Albums 
			album.add(dmtn);
		}
	}
	
	
	public void createAdminPanel(){
		admin.setLayout(new GridBagLayout());	
		createAdminTree();
		
		DefaultTreeModel dtm = new DefaultTreeModel(rootAdmin){
			@Override
			public void valueForPathChanged(TreePath path, Object newValue){
				Object obj = ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject();
				((Playlist)obj).setName(newValue.toString());
				super.valueForPathChanged(path, obj);
			}
		};
		
		tickets = new JTree(dtm){
		};
		tickets.addMouseListener(new TicketTreeListener());
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
		DefaultMutableTreeNode complaint =new DefaultMutableTreeNode("Beschwerde");
		DefaultMutableTreeNode genre =new DefaultMutableTreeNode("Genreanfrage");
		DefaultMutableTreeNode label =new DefaultMutableTreeNode("Label erstellen");
		DefaultMutableTreeNode artist =new DefaultMutableTreeNode("Künstler hinzufügen");
		DefaultMutableTreeNode other =new DefaultMutableTreeNode("Sonstiges");
		rootAdmin.add(account);
		rootAdmin.add(complaint);
		rootAdmin.add(genre);
		rootAdmin.add(label);
		rootAdmin.add(artist);
		rootAdmin.add(other);
		DefaultMutableTreeNode dmtn = null;
		List<?> tickets;
		tickets=  DatabaseControl.getInstance().getTableContent("Ticket");
		//Print out ALL THE TICKETS
		Iterator<?> it = tickets.iterator();
		while(it.hasNext()){
			Ticket curTicket = (Ticket) it.next();
			dmtn = new DefaultMutableTreeNode(curTicket);
			if(curTicket.getCategory().equals("Account erweitern")){
				account.add(dmtn);
			}
			else if(curTicket.getCategory().equals("Beschwerde")){
				complaint.add(dmtn);
			}
			else if(curTicket.getCategory().equals("Genreanfrage")){
				genre.add(dmtn);
			}
			else if(curTicket.getCategory().equals("Label erstellen")){
				label.add(dmtn);
			}
			else if(curTicket.getCategory().equals("Künstler hinzufügen")){
				artist.add(dmtn);
			}
			else if(curTicket.getCategory().equals("Sonstiges")){
				other.add(dmtn);
			}
			
		}
	}
	/**
	 * erstellt das Panel in dem die Playlists des Nutzers angezeigt wird
	 */
	public void createUserPanel(){
		user.setLayout(new GridBagLayout());
		
		
		DefaultTreeModel dtm = new DefaultTreeModel(rootUser){
			@Override
			public void valueForPathChanged(TreePath path, Object newValue){
				Object obj = ((DefaultMutableTreeNode)path.getLastPathComponent()).getUserObject();
				((Playlist)obj).setName(newValue.toString());
				super.valueForPathChanged(path, obj);
			}
		};
		
		playlists = new JTree(dtm){
			@Override
			public boolean isPathEditable(TreePath path) {
				if(this.getRowForPath(path.getParentPath())==0){return true;}
				return false;
			}
		};
		
		//Buttons
		subscriptions = new JButton("Subscriptions");
		favorits = new JButton("Favoriten");
		genres = new JButton("Genres");
		uploads = new JButton("Uploads");
		label = new JButton("Label");
		createLabel = new JButton("Label erstellen");
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
		createLabel.setOpaque(false);
		createLabel.setContentAreaFilled(false);
		createLabel.setBorderPainted(false);
		createLabel.setBackground(Color.WHITE);
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
			c.gridy++;
			createLabel.addActionListener(new CreateLabelButtonListener());
			user.add(createLabel,c);
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

	public JTree getTicketTree(){
		return tickets;
	}
}
