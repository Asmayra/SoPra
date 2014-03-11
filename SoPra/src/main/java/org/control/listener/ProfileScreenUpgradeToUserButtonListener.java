package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.User;


/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class ProfileScreenUpgradeToUserButtonListener implements ActionListener {
	
	
	private User selectedUser;

	public ProfileScreenUpgradeToUserButtonListener(User selectedUser){
		this.selectedUser = selectedUser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		selectedUser.setRights("StandardUser");
		
		DatabaseControl.getInstance().update(selectedUser);

	}

}
