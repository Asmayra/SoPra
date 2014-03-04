package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.view.screens.Center.ProfileScreen;

public class ProfileMediaListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		((ProfileScreen) ((JButton) e.getSource()).getParent()).showMedia();
		
	}

}
