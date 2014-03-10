package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;

public class ProfileScreenUpgradeToUserButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		User currentUser = LoginControl.getInstance().getCurrentUser();
		currentUser.setRights("StandardUser");
		
		DatabaseControl.getInstance().update(currentUser);

	}

}
