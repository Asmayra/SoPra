

//In Bearbeitung bei Sebastian


package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.model.User;

public class ProfileScreen extends JPanel {
	private User userProfile;
	private BufferedImage prflPicture;
	private JPanel userOverview;
	private JLabel userPicture;
	private JPanel userData;
	private JPanel buttons;
	private JPanel userName;
	private JScrollPane userContentScroll;
	private JPanel userContent;
	private JButton message;
	private JButton ignore;
	private JButton media;
	private JButton follow;
	
	public ProfileScreen(User selectedUser) {
		userProfile = selectedUser;
		//task: try catch Block hinzufügen für IOException
		try {
			prflPicture = userProfile.getPicture();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));

		userOverview = new JPanel();
		add(userOverview, BorderLayout.NORTH);
		userOverview.setLayout(new BorderLayout(0, 0));
		
		ImageIcon pic =new ImageIcon(prflPicture);
		userPicture = new JLabel(pic);
		userPicture.setSize(100, 100);
		userOverview.add(userPicture, BorderLayout.WEST);

		userData = new JPanel();
		userOverview.add(userData, BorderLayout.CENTER);

		buttons = new JPanel();
		userOverview.add(buttons, BorderLayout.SOUTH);
		
		message = new JButton("Nachricht senden");
		media = new JButton("Musik-Sammlung");
		follow = new JButton("follow");
		ignore = new JButton("ignore");
		buttons.add(message);
		buttons.add(media);
		buttons.add(follow);
		buttons.add(ignore);
		

		userName = new JPanel();
		userOverview.add(userName, BorderLayout.NORTH);
		
		userContent = new JPanel();
		userContentScroll = new JScrollPane(userContent);
		userContentScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(userContentScroll, BorderLayout.SOUTH);
		
		

	}

}
