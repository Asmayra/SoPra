package org.control.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.view.screens.Center.ProfileScreen;

public class FavorSelectionListener implements ListSelectionListener {
	private ProfileScreen profile;
	
	public FavorSelectionListener(ProfileScreen p){
		profile = p;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		System.out.println("Test1");
		profile.resetSongPanel();
		
		
		
	}

}
