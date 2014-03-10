package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;

public class ProfileScreenUpgradeToManagerButtonListener implements
		ActionListener {
	
	private User selectedUser;

	public ProfileScreenUpgradeToManagerButtonListener(User selectedUser){
		this.selectedUser = selectedUser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		selectedUser.setRights("LabelManager");
		
		DatabaseControl.getInstance().update(selectedUser);

	}

}
