package org.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.listener.FavorSelectionListener;
import org.model.Playlist;
import org.model.Song;
import org.model.User;
import org.view.screens.Center.ProfileScreen;

public class ProfileSongsPanel extends JScrollPane {

	private static String[] columns = new String[] { "Interpret", "Title", "Album", "Länge", "Community Rating",
			"Favor" };
	private static Object[][] context = new Object[][] {};
	private static DefaultTableModel model;
	private static JTable table;
	private static ListSelectionModel favor;

	public ProfileSongsPanel(ProfileScreen p) {
		super(create(p));
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private static JTable create(ProfileScreen p) {
		model = new DefaultTableModel(context, columns) {
			@Override
			public Class getColumnClass(int col) {
				if (col == 5)
					return Boolean.class;
				else
					return String.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 5;
			}
		};
		// this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		table = new JTable();
		table.setModel(model);
		favor = table.getSelectionModel();
	    favor.addListSelectionListener(new FavorSelectionListener(p));
	    table.setSelectionModel(favor);
		List<Song> songs = p.getUserProfile().getOwnSongs();
		Playlist favorites = LoginControl.getInstance().getCurrentUser().getFavorites();
		for (int i = 0; i < songs.size(); i++) {
			Song curSong= songs.get(i);
			String interpret="Kein Interpret";
			String title="Kein Titel";
			String album="Kein Album";
			int playtime=0;
			double rating=0.0;
			
			try{
				interpret = curSong.getInterpret();
			}catch(NullPointerException exc){}
			try{
				title = curSong.getTitle();
			}catch(NullPointerException exc){}
			try{
				album = curSong.getAlbum().getName();
			}catch(NullPointerException exc){}
			try{
				playtime = curSong.getPlaytime();
			}catch(NullPointerException exc){}
			try{
				rating = curSong.getVrgRating();
			}catch(NullPointerException exc){}
			
			boolean favored;
			if (favorites.contains(songs.get(i))) {
				favored = true;
			} else {
				favored = false;
			}
			Object[] entry = { interpret, title, album, playtime, rating, favored };
			model.addRow(entry);
		}
		table.setFillsViewportHeight(true);
		return table;

	}

}
