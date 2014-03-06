package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.view.screens.Center.ProfileScreen;

/**
 * Ignore-Button-Funktionalität wird bereitgestellt. Je nach dem ob dem User des
 * Profils auf dem sich der Button befindet schon vom aktuellen Benutzer
 * ignoriert wird ändert sich die Button-Funktionalität dementsprechend.
 * 
 * @author Sebastian Roth
 * 
 */
public class IgnoreButtonListener implements ActionListener {
	private ProfileScreen profile;

	public IgnoreButtonListener(ProfileScreen p) {
		profile = p;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (profile.getIgnore()) {
			LoginControl.getInstance().getCurrentUser().unignore(profile.getUserProfile());
			profile.setIgnore(false);
			((JButton) arg0.getSource()).setText("ignore");
			
		} else {
			LoginControl.getInstance().getCurrentUser().ignore(profile.getUserProfile());
			profile.setIgnore(true);
			((JButton) arg0.getSource()).setText("unignore");
			
		}
		DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());

	}

}
