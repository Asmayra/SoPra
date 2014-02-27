package org.testpackage;

import javax.swing.JFrame;

import org.model.User;
import org.view.screens.Center.ProfileScreen;

public class TestProfileScreen {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User tester = new User();
		JFrame test = new JFrame();
		test.add(new ProfileScreen(tester));
		test.setVisible(true);
		test.setSize(800, 400);

	}

}
