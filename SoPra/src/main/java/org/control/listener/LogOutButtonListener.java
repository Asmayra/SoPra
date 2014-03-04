package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.LoginScreen;
import org.view.MainScreen;

public class LogOutButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		LoginScreen login = LoginScreen.getInstance();
		login.setVisible(true);
		MainScreen.getInstance().dispose();
		
	}

}
