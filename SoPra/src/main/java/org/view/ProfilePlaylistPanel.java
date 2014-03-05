package org.view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.model.Playlist;
import org.model.Song;
import org.model.User;

public class ProfilePlaylistPanel extends JPanel {

	private String[] columns = new String[] { "Name", "Ersteller", "#Lieder", "Länge", "Favor" };
	private Object[][] context;
	private DefaultTableModel model;
	private JTable table;

	public ProfilePlaylistPanel(User u) {

		model = new DefaultTableModel(context, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 4;
			}
		};
		table.setFillsViewportHeight(true);
		table = new JTable();
		table.setModel(model);
		List<Playlist> songs = u.getPlaylists();
		for (int i = 0; i < songs.size(); i++) {
			String Name = songs;
			String title = songs.get(i).getTitle();
			boolean favored;
			if (u.getFavorites().contains(songs.get(i))) {
				favored = true;
			} else {
				favored = false;
			}
			String album;
			if (songs.get(i).getAlbum() == null) {
				album = " - ";
			} else {
				album = songs.get(i).getAlbum().getName();
			}
			String playtime = ((Integer) songs.get(i).getPlaytime()).toString();
			String comRating = ((Integer) songs.get(i).getVrgRating()).toString();
			Object[] entry = { interpret, title, album, playtime, comRating, favored };
			model.addRow(entry);
		}

	}
}
