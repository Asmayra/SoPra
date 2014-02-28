package org.testpackage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.control.DatabaseController;
import org.model.User;
import org.view.screens.Center.ProfileScreen;

public class TestProfileScreen {

	public static void main(String[] args) {
		User tester = (User) DatabaseController.getInstance().load(User.class, "MaxMuster");
		JFrame test = new JFrame();
		JPanel screen = new ProfileScreen(tester);
		test.setVisible(true);
		test.setSize(800, 400);
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setContentPane(screen);

	}

}
