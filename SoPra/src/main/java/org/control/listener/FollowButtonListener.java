package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.view.screens.Center.ProfileScreen;
/**
 * Folgen-Button-Funktionalität wird bereitgestellt. Je nach dem ob dem User des
 * Profils auf dem sich der Button befindet schon vom aktuellen Benutzer
 * gefolgt wird ändert sich die Button-Funktionalität dementsprechend.
 * 
 * @author Sebastian Roth
 * 
 */
public class FollowButtonListener implements ActionListener {
	
	private ProfileScreen profile;
	
	public FollowButtonListener(ProfileScreen p){
		profile = p;
	}

	public void actionPerformed(ActionEvent arg0) {

		if (profile.getFollow()) {
			LoginControl.getInstance().getCurrentUser().unfollow(profile.getUserProfile());
			profile.setFollow(false);
			((JButton) arg0.getSource()).setText("follow");
			
		} else {
			LoginControl.getInstance().getCurrentUser().follow(profile.getUserProfile());
			profile.setFollow(true);
			((JButton) arg0.getSource()).setText("unfollow");
		}

	}

}
