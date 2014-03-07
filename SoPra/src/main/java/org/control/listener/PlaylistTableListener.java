package org.control.listener;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Playlist;
import org.model.Song;

public class PlaylistTableListener implements TableModelListener {
	

	@Override
	public void tableChanged(TableModelEvent e) {
		
		int row = e.getFirstRow();
		TableModel model = (TableModel) e.getSource();
		boolean data = (boolean) model.getValueAt(row, 3);
		int id = (int) model.getValueAt(row, 4);
		String name = (String) model.getValueAt(row, 0);
		if ((boolean) data) {
			if(name.equals("Favorites")){
				if (LoginControl.getInstance().getCurrentUser().getFavorites().getPlaylistId()==id){
					model.setValueAt(true, row, 3);
					JOptionPane.showMessageDialog(null, "Sie können ihre eigene Favoriten-Playlist nicht entfernen.");
				} else {
					Playlist friendFavorites;
					friendFavorites.copyFriendFavorites(DatabaseControl.getInstance().load(Playlist.class, id));
				}
			}
		} else {
			System.out.println("remove spice");
			//LoginControl.getInstance().getCurrentUser().removeFavorite((Song) DatabaseControl.getInstance().load(Song.class, id));
			//DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		}
	}

}
