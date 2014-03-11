package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Album;
import org.model.Genre;
import org.model.Song;
import org.model.User;
import org.view.screens.Center.OwnSongsScreen;


/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class OwnSongsScreenSaveButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		OwnSongsScreen oss = OwnSongsScreen.getInstance();
		int row = oss.getSelectedRow();
		
		User currentUser = LoginControl.getInstance().getCurrentUser();
		List<Song> songList = currentUser.getOwnSongs();
		
		songList.get(row).setTitle(oss.getTitelTF());
		
		List<Genre> currentGenre = (List<Genre>) DatabaseControl.getInstance().getTableContent("Genre");
		 for (Genre g: currentGenre){
			 if (g.getName().equals(oss.getGenreBox())){
				 songList.get(row).setGenre(g);
			 }
		 }
		
		DatabaseControl.getInstance().update(songList.get(row));
		try {
			DatabaseControl.getInstance().save(songList.get(row));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if (songList.get(row).getAlbum() != null){
			songList.get(row).getAlbum().setName(oss.getAlbumTF());
			DatabaseControl.getInstance().update(songList.get(row).getAlbum());
		}
		else {
			Album newAlbum = new Album(LoginControl.getInstance().getCurrentUser());
			newAlbum.setName(oss.getAlbumTF());
			try {
				DatabaseControl.getInstance().save(newAlbum);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			songList.get(row).setAlbum(newAlbum);
		}
		
		OwnSongsScreen.getInstance().updateTable();
	}

}
