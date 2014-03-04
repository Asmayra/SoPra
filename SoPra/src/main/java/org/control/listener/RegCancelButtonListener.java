package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.RegControl;
import org.view.RegScreen;

public class RegCancelButtonListener implements ActionListener {
	
	private RegScreen regScreen;

	public RegCancelButtonListener(RegScreen regScreen) {
		this.regScreen = regScreen;
	
	}

	public void actionPerformed(ActionEvent arg0) {
		RegControl.getInstance().destroy();
		regScreen.dispose();

	}


}
