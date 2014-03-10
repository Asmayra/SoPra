package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;

public class ProfileScreenBannButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		User currentUser = LoginControl.getInstance().getCurrentUser();
		if (currentUser.getBanned() == true) 
			currentUser.setBanned(false);
		else currentUser.setBanned(true);
		
		DatabaseControl.getInstance().update(currentUser);
	}

}
