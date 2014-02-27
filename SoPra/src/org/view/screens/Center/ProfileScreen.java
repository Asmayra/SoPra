package org.view.screens.Center;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.model.User;

public class ProfileScreen extends JPanel {
	private User userProfile;

	public ProfileScreen(User selectedUser) {

		JPanel main = new JPanel();
		main.setLayout(new BorderLayout(0, 0));

		JPanel userOverview = new JPanel();
		main.add(userOverview, BorderLayout.NORTH);
		userOverview.setLayout(new BorderLayout(0, 0));

		JPanel userPicture = new JPanel();

		userOverview.add(userPicture, BorderLayout.WEST);

		JPanel userData = new JPanel();
		userOverview.add(userData, BorderLayout.CENTER);

		JPanel buttons = new JPanel();
		userOverview.add(buttons, BorderLayout.SOUTH);

		JPanel userName = new JPanel();
		userOverview.add(userName, BorderLayout.NORTH);

		JScrollPane userContentScroll = new JScrollPane();
		main.add(userContentScroll, BorderLayout.WEST);
		userContentScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel userContent = new JPanel();
		main.add(userContent, BorderLayout.SOUTH);
		userContentScroll = new JScrollPane(userContent);

	}

}
