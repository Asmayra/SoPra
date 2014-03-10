package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;

public class ProfileScreenUpgradeToAdminButtonListener implements
		ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		User currentUser = LoginControl.getInstance().getCurrentUser();
		currentUser.setRights("Admin");
		
		DatabaseControl.getInstance().update(currentUser);

	}

}
