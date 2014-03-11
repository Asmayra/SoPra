package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.LoginControl;
import org.control.SingletonControl;
import org.view.LoginScreen;
import org.view.MainScreen;


/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class LogOutButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		LoginScreen login = LoginScreen.getInstance();
		login.setVisible(true);
		MainScreen.getInstance().dispose();
		SingletonControl.destroyAll();
		LoginControl.getInstance().resetCurrentUser();
	}

}
