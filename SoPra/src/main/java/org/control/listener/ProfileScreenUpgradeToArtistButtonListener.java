package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;

public class ProfileScreenUpgradeToArtistButtonListener implements ActionListener {
	
	private User selectedUser;

	public ProfileScreenUpgradeToArtistButtonListener(User selectedUser){
		this.selectedUser = selectedUser;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		selectedUser.setRights("Artist");
		
		DatabaseControl.getInstance().update(selectedUser);

	}

}
