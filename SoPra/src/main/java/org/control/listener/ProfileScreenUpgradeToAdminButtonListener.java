package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;

public class ProfileScreenUpgradeToAdminButtonListener implements
		ActionListener {
	
	private User selectedUser;

	public ProfileScreenUpgradeToAdminButtonListener(User selectedUser){
		this.selectedUser = selectedUser;
	}

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		selectedUser.setRights("Admin");
		
		DatabaseControl.getInstance().update(selectedUser);

	}

}
