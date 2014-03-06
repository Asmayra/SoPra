package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.LoginControl;
import org.view.MainScreen;
import org.view.screens.Center.AdminHomeScreen;
import org.view.screens.Center.HomeScreen;

public class HomeScreenButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		if(LoginControl.getInstance().getCurrentUser().getRights().equals("Admin"))
			MainScreen.getInstance().updateCenter(new AdminHomeScreen());
		else
			MainScreen.getInstance().updateCenter(HomeScreen.getInstance());
		
	}

}
