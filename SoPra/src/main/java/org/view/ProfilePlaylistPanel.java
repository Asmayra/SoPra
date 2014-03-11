package org.view;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.control.listener.PlaylistTableListener;
import org.model.Playlist;
import org.model.User;

/**
 * JScrollPane which shows the playlists of a user on his profile screen
 * 
 * @author Sebastian Roth
 * 
 */
public class ProfilePlaylistPanel extends JScrollPane {

	private static String[] columns = new String[] { "Name", "Ersteller", "#Lieder", "Favor", "ID" };
	private static Object[][] context;
	private static DefaultTableModel model;
	private static JTable table;

	/**
	 * constructor provides the playlist panel on the profile screen
	 * 
	 * @param u
	 */
	public ProfilePlaylistPanel(User u) {
		super(create(u));
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}

	/**
	 * create method creates the needed jtable
	 * 
	 * @param u
	 *            user whose playlists get shown
	 * @return a jtable with all playlists of the user u
	 */
	private static JTable create(User u) {
		model = new DefaultTableModel(context, columns) {
			@Override
			public Class getColumnClass(int col) {
				if (col == 3)
					return Boolean.class;
				else
					return String.class;
			}

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
			int id = playlists.get(i).getPlaylistId();
			Object[] entry = { name, builder, songCount, favor, id };
			model.addRow(entry);
		}
		model.addTableModelListener(new PlaylistTableListener());
		table.removeColumn(table.getColumnModel().getColumn(4));
		return table;
	}
}
