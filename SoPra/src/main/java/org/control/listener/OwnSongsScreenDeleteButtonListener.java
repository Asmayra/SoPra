package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.UploadControl;
import org.hibernate.Session;
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
		DatabaseControl.getInstance().update(songList.get(delRow));
		DatabaseControl.getInstance().delete(songList.get(delRow));
		
		try {
			Song toDel = songList.get(delRow);
			UploadControl.deleteFile( toDel.getPath() );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
