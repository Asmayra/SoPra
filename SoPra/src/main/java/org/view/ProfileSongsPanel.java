package org.view;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.control.listener.SongTableListener;
import org.model.Playlist;
import org.model.Song;
import org.view.screens.Center.ProfileScreen;

/**
 * JScrollPane which shows the songs of a user on his profile screen
 * 
 * @author Sebastian Roth
 * 
 */
public class ProfileSongsPanel extends JScrollPane {

	private static String[] columns = new String[] { "Interpret", "Title", "Album", "Länge","", "Favor", "ID" };
	private static Object[][] context = new Object[][] {};
	private static DefaultTableModel model;
	private static JTable table;

	/**
	 * constructor provides the songs panel on the profile screen
	 * 
	 * @param u
	 */
	public ProfileSongsPanel(ProfileScreen p) {
		super(create(p));
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	/**
	 * create method creates the needed jtable
	 * 
	 * @param u
	 *            user whose songs get shown
	 * @return a jtable with all songs of the user u
	 */
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
		table = new JTable();
		table.setModel(model);
		List<Song> songs = p.getUserProfile().getOwnSongs();
		Playlist favorites = LoginControl.getInstance().getCurrentUser().getFavorites();
		for (int i = 0; i < songs.size(); i++) {
			Song curSong = songs.get(i);
			String interpret = "Kein Interpret";
			String title = "Kein Titel";
			String album = "Kein Album";
			double playtime = 0;

			try {
				interpret = curSong.getInterpret();
			} catch (NullPointerException exc) {
			}
			try {
				title = curSong.getTitle();
			} catch (NullPointerException exc) {
			}
			try {
				album = curSong.getAlbum().getName();
			} catch (NullPointerException exc) {
			}
			try {
				playtime = curSong.getPlaytime();
			} catch (NullPointerException exc) {
			}
			boolean favored;
			if (favorites.contains(songs.get(i))) {
				favored = true;
			} else {
				favored = false;
			}
			int id = curSong.getSongId();
			Object[] entry = { interpret, title, album, playtime,"", favored, id };
			model.addRow(entry);
		}
		table.setFillsViewportHeight(true);
		model.addTableModelListener(new SongTableListener());
		return table;

	}

}
