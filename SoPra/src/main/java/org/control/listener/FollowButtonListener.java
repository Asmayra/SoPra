package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.control.DatabaseController;
import org.control.LoginControl;
import org.view.screens.Center.ProfileScreen;

public class FollowButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {

		if (((ProfileScreen) ((JButton) arg0.getSource()).getParent()).getFollow()) {
			LoginControl.getInstance().getCurrentUser().unfollow(((ProfileScreen) ((JButton) arg0.getSource()).getParent()).getUserProfile());
			((ProfileScreen) ((JButton) arg0.getSource()).getParent()).setFollow(false);
			((JButton) arg0.getSource()).setText("follow");
			DatabaseController.getInstance().update(LoginControl.getInstance().getCurrentUser());
		} else {
			LoginControl.getInstance().getCurrentUser().follow(((ProfileScreen) ((JButton) arg0.getSource()).getParent()).getUserProfile());
			((ProfileScreen) ((JButton) arg0.getSource()).getParent()).setFollow(true);
			((JButton) arg0.getSource()).setText("unfollow");
			DatabaseController.getInstance().update(LoginControl.getInstance().getCurrentUser());
		}

	}

}
