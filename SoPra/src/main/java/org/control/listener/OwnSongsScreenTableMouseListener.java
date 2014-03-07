package org.control.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import org.control.LoginControl;
import org.model.Song;
import org.model.User;
import org.view.screens.Center.OwnSongsScreen;

public class OwnSongsScreenTableMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		OwnSongsScreen oss = OwnSongsScreen.getInstance();
		int row = oss.getSelectedRow();
		
		User currentUser = LoginControl.getInstance().getCurrentUser();
		List<Song> songList = currentUser.getOwnSongs();
		
		oss.setTitleTF(songList.get(row).getTitle());
		if (songList.get(row).getAlbum() != null){
			oss.setAlbumTF(songList.get(row).getAlbum().getName());
		}
		else oss.setAlbumTF("");
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
