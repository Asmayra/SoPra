package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import org.control.LoginControl;
import org.control.PlaylistControl;
import org.model.Playlist;
import org.view.MainScreen;
import org.view.screens.Center.AdminGenreScreen;
/**
 * Regelt das öffnen der Favoriten
 * @author Tim,Max
 *
 */
public class FavoritButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		PlaylistControl.getInstance().setCurrentPlaylist(LoginControl.getInstance().getCurrentUser().getFavorites());
		PlaylistControl.getInstance().showFavorites();
	}

}
