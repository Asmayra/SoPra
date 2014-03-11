package org.control.listener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Song;
import org.view.screens.WestBar.PlaylistMiniScreen;

public class PlaylistSingleScreenSongTableListner implements TableModelListener{

	@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
		TableModel model = (TableModel) e.getSource();
		boolean favor = (boolean) model.getValueAt(row, 4);
		int id = (int) model.getValueAt(row, 5);
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
