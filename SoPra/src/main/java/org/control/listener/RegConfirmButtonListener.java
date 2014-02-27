package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.RegControl;

public class RegConfirmButtonListener implements ActionListener {
	
	public RegConfirmButtonListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		RegControl control = RegControl.getInstance();
		boolean success = true;
		
		control.clearError();
		
		if(!control.checkRegistration())
		{
			control.addEntryError();
			success = false;
		}
		
		if(!control.checkPasswords())
		{
			control.addPasswordError();
			success = false;
		}
		
		//Warten bis Hibernate läuft
//		if(!control.userExists())
//		{
//			control.addUserExistsError();
//			success = false;
//		}
		
		if(success)
			control.completeRegistration();
		else
			control.displayError();
	}

}
