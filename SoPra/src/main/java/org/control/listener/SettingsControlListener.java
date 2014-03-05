package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.view.screens.Center.SettingsScreen;
import org.view.screens.Center.SettingsScreen.Attributfelder;

public class SettingsControlListener implements ActionListener{

	private SettingsScreen settingsScreen;
	
	public SettingsControlListener(SettingsScreen setScreen) {
		this.settingsScreen = setScreen;
	}
	public void actionPerformed(ActionEvent e) {
		this.settingsScreen.saveDataToDatabase();
	}

}
