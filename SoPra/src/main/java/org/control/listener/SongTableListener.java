package org.control.listener;

import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Song;

public class SongTableListener implements TableModelListener {
	

	@Override
	public void tableChanged(TableModelEvent e) {
		
		int row = e.getFirstRow();
		TableModel model = (TableModel) e.getSource();
		boolean data = (boolean) model.getValueAt(row, 5);
		int id = (int) model.getValueAt(row, 6);
		if ((boolean) data) {
			System.out.println("add spice");
			LoginControl.getInstance().getCurrentUser().addFavorite((Song) DatabaseControl.getInstance().load(Song.class, id));
			//DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		} else {
			System.out.println("remove spice");
			LoginControl.getInstance().getCurrentUser().removeFavorite((Song) DatabaseControl.getInstance().load(Song.class, id));
			//DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		}
	}

}
