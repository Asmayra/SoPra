package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		Date dob;
		try {
			dob = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse(regScreen.getDob());
		} catch (ParseException e) {
			dob = null;
		}
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
		
		if(control.userExists( username ))
		{
			control.addUserExistsError();
			success = false;
		}
		
		if(success)
		{
			control.completeRegistration(username, firstname, lastname, city, country, dob, password);
			regScreen.dispose();
		}
		else
			control.displayError(regScreen.getErrorLabel());
	} 
}
