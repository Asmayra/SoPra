package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MainScreen;
import org.view.screens.Center.SettingsScreen;


/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class SettingsButtonListener implements ActionListener{


	public void actionPerformed(ActionEvent arg0) {
		MainScreen.getInstance().showSettingsScreen(SettingsScreen.getInstance());
	}

}
