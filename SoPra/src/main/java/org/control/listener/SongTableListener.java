package org.control.listener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Song;
import org.view.screens.WestBar.PlaylistMiniScreen;

/**
 * table listener which removes or adds a song to the own song collection of a
 * user
 * 
 * @author Sebastian Roth
 * 
 */
public class SongTableListener implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
		TableModel model = (TableModel) e.getSource();
		boolean favor = (boolean) model.getValueAt(row, 5);
		int id = (int) model.getValueAt(row, 6);
		if ((boolean) favor) {
			LoginControl.getInstance().getCurrentUser()
					.addFavorite((Song) DatabaseControl.getInstance().load(Song.class, id));
			DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
			PlaylistMiniScreen.getInstance().updateMiniScreen();
		} else {
			LoginControl.getInstance().getCurrentUser()
					.removeFavorite((Song) DatabaseControl.getInstance().load(Song.class, id));
			DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
			PlaylistMiniScreen.getInstance().updateMiniScreen();
		}
	}

}
