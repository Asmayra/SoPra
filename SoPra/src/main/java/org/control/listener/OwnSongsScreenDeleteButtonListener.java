package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Song;
import org.model.User;
import org.view.screens.Center.OwnSongsScreen;

public class OwnSongsScreenDeleteButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		User currentUser = LoginControl.getInstance().getCurrentUser();
		List<Song> songList = currentUser.getOwnSongs();
		int delRow = OwnSongsScreen.getInstance().getSelectedRow();
		
		currentUser.removeOwnSong(songList.get(delRow));
		
		
		OwnSongsScreen.getInstance().removeRow(delRow);
		DatabaseControl.getInstance().update(currentUser);
		DatabaseControl.getInstance().delete(songList.get(delRow));
		
	}

}
