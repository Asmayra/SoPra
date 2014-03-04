package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MainScreen;
import org.view.screens.Center.HomeScreen;

public class HomeScreenButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		MainScreen.getInstance().showHomeScreen(HomeScreen.getInstance());
		
	}

}
