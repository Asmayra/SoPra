package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.view.screens.Center.ProfileScreen;

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
			DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		} else {
			LoginControl.getInstance().getCurrentUser().follow(profile.getUserProfile());
			profile.setFollow(true);
			((JButton) arg0.getSource()).setText("unfollow");
			DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		}

	}

}
