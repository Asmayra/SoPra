package org.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.model.Playlist;
import org.model.Song;
import org.model.User;

public class ProfilePlaylistPanel extends JScrollPane {

	private static String[] columns = new String[] { "Name", "Ersteller", "#Lieder", "Favor" };
	private static Object[][] context;
	private static DefaultTableModel model;
	private static JTable table;

	public ProfilePlaylistPanel(User u) {
		super(create(u));
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	private static JTable create(User u) {
		model = new DefaultTableModel(context, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3;
			}
		};
		table = new JTable();
		table.setModel(model);
		List<Playlist> playlists = u.getPlaylists();
		for (int i = 0; i < playlists.size(); i++) {
			String name = playlists.get(i).getName();
			String builder = playlists.get(i).getOwner().getUsername();
			String songCount = ((Integer) playlists.get(i).getSongs().size()).toString();
			boolean favor;
			if (LoginControl.getInstance().getCurrentUser().getPlaylists().contains(playlists.get(i))) {
				favor = true;
			} else {
				favor = false;
			}
			Object[] entry = { name, builder, songCount, favor };
			model.addRow(entry);
		}
		return table;
	}
}
