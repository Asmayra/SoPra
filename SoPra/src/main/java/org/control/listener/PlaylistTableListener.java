package org.control.listener;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Playlist;
import org.model.User;
import org.view.screens.WestBar.PlaylistMiniScreen;

/**
 * table listener which removes or adds a playlist to the own playist collection
 * of a user
 * 
 * @author Sebastian Roth
 * 
 */
public class PlaylistTableListener implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
		TableModel model = (TableModel) e.getSource();
		boolean favor = (boolean) model.getValueAt(row, 3);
		int id = (int) model.getValueAt(row, 4);
		String name = (String) model.getValueAt(row, 0);
		User current = LoginControl.getInstance().getCurrentUser();
		Playlist selected = (Playlist) DatabaseControl.getInstance().load(Playlist.class, id);
		if ((boolean) favor) {
			if (name.equals("Favorites")) {
				if (!(current.getFavorites().getPlaylistId() == id)) {
					current.addPlaylist(Playlist.copyFriendFavorites(current, selected));
					DatabaseControl.getInstance().update(current);
					PlaylistMiniScreen.getInstance().updateMiniScreen();
				}

			} else {
				Playlist copy = new Playlist(current);
				copy.setSongs(selected.getSongs());
				copy.setName(selected.getName());
				current.addPlaylist(copy);
				DatabaseControl.getInstance().update(current);
				PlaylistMiniScreen.getInstance().updateMiniScreen();
			}
		} else {
			if (current.getFavorites().getPlaylistId() == id) {
				model.setValueAt(true, row, 3);
				JOptionPane.showMessageDialog(null, "Sie können ihre eigene Favoriten-Playlist nicht entfernen.");
			} else {
				current.removePlaylist(selected);
				DatabaseControl.getInstance().update(current);
				PlaylistMiniScreen.getInstance().updateMiniScreen();
			}
		}
	}
}
