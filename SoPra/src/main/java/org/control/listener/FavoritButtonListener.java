package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.PlaylistControl;
import org.view.MainScreen;
import org.view.screens.Center.AdminGenreScreen;

public class FavoritButtonListener implements ActionListener {

	PlaylistControl control = PlaylistControl.getInstance();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		control.showFavorites();
		

	}

}
