package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.view.MainScreen;
import org.view.screens.Center.DiscoverExtendedScreen;


public class MoreDiscoverButtonListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		MainScreen.getInstance().showDiscoverExtendedScreen(DiscoverExtendedScreen.getInstance());
	}

}
