package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MainScreen;
import org.view.screens.Center.AdminLabelScreen;

public class CreateLabelButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MainScreen.getInstance().updateCenter(AdminLabelScreen.getInstance());
		
	}

}
