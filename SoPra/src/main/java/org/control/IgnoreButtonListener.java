package org.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.view.screens.Center.ProfileScreen;

public class IgnoreButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		if (((ProfileScreen) ((JButton) arg0.getSource()).getParent()).getIgnore()) {
			LoginControl.getInstance().getCurrentUser().unignore(((ProfileScreen) ((JButton) arg0.getSource()).getParent()).getUserProfile());
			((ProfileScreen) ((JButton) arg0.getSource()).getParent()).setIgnore(false);
			((JButton) arg0.getSource()).setText("ignore");
			DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		} else {
			LoginControl.getInstance().getCurrentUser().ignore(((ProfileScreen) ((JButton) arg0.getSource()).getParent()).getUserProfile());
			((ProfileScreen) ((JButton) arg0.getSource()).getParent()).setIgnore(true);
			((JButton) arg0.getSource()).setText("unignore");
			DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		}
		
	}

}
