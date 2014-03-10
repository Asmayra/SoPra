package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;

public class ProfileScreenBannButtonListener implements ActionListener {
	
	private User selectedUser;

	public ProfileScreenBannButtonListener(User selectedUser){
		this.selectedUser = selectedUser;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (selectedUser.getBanned() == true) 
			selectedUser.setBanned(false);
		else selectedUser.setBanned(true);
		
		DatabaseControl.getInstance().update(selectedUser);
	}

}
