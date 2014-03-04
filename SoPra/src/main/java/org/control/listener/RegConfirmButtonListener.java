package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.RegControl;
import org.view.RegScreen;

public class RegConfirmButtonListener implements ActionListener {
	
	private RegScreen regScreen;

	public RegConfirmButtonListener(RegScreen regScreen) {
		this.regScreen = regScreen;
	}

	public void actionPerformed(ActionEvent arg0) {
		RegControl control = RegControl.getInstance();
		boolean success = true;
		
		control.clearError(regScreen.getErrorLabel());
		
		String username = regScreen.getUsername();
		String firstname = regScreen.getFirstname();
		String lastname = regScreen.getLastname();
		String city = regScreen.getCity();
		String country = regScreen.getCountry();
		String dob = regScreen.getDob();
		String mail = regScreen.getMail();
		String password = regScreen.getPassword();
		String passwordConfirm = regScreen.getPasswordConfirm();
		
		if(!control.checkRegistration(username, firstname, lastname, city, country, dob, mail))
		{
			control.addEntryError();
			success = false;
		}
		
		if(!control.checkPasswords( password, passwordConfirm ))
		{
			control.addPasswordError();
			success = false;
		}
		
		if(!control.userExists( username ))
		{
			control.addUserExistsError();
			success = false;
		}
		
		if(success)
		{
			control.completeRegistration(username, firstname, lastname, city, country, null, password);
			regScreen.dispose();
		}
		else
			control.displayError(regScreen.getErrorLabel());
	} 
}
