package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.LoginScreen;
import org.view.RegScreen;

/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class LoginScreenRegisterButtonListener implements ActionListener {
	
	

	public LoginScreenRegisterButtonListener() {
		
	}


	
	public void actionPerformed(ActionEvent arg0) {
		RegScreen.getInstance().setVisible(true);
		RegScreen.getInstance().toFront();
		
	}

}
