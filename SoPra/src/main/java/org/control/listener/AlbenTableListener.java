package org.control.listener;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Album;
import org.model.Playlist;
import org.model.Song;
import org.model.User;
import org.view.screens.WestBar.PlaylistMiniScreen;

public class AlbenTableListener implements TableModelListener {

	@Override
	public void tableChanged(TableModelEvent e) {

		int row = e.getFirstRow();
		TableModel model = (TableModel) e.getSource();
		boolean favor = (boolean) model.getValueAt(row, 3);
		int id = (int) model.getValueAt(row, 4);
		Album selected = (Album) DatabaseControl.getInstance().load(Album.class, id);
		String owner = selected.getOwner().getUsername();
		User current = LoginControl.getInstance().getCurrentUser();
		if ((boolean) favor) {
				current.addAlben(selected);
				PlaylistMiniScreen.getInstance().updateMiniScreen();
				//DatabaseControl.getInstance().update(current);
		} else {
			if (owner.equals(current.getUsername())) {
				model.setValueAt(true, row, 3);
				JOptionPane.showMessageDialog(null, "Sie können ihre eignen Alben nicht entfernen.\n Sie haben aber die Möglichkeit ihr Album komplett zu löschen.");
			} else {
				current.removeAlbum(selected);
				PlaylistMiniScreen.getInstance().updateMiniScreen();
				//DatabaseControl.getInstance().update(current);
			}
		}
	}
}
