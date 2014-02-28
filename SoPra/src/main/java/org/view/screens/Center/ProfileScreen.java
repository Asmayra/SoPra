

//In Bearbeitung bei Sebastian


package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
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
	private Image prflPicture;
	private JPanel userOverview;
	private JLabel userPictureL;
	private JPanel userData;
	private JPanel buttons;
	private JPanel userName;
	private JScrollPane userContentScroll;
	private JPanel userContent;
	private JButton message;
	private JButton ignore;
	private JButton media;
	private JButton follow;
	private JLabel lblVornameame;
	private JLabel lblNachname;
	private JLabel lblStadt;
	private JLabel lblLand;
	private JLabel lblAlter;
	
	
	public ProfileScreen(User selectedUser) {
		userProfile = selectedUser;
		//task: try catch Block hinzufügen für IOException
		try {
			prflPicture = userProfile.getPicture();
			prflPicture= prflPicture.getScaledInstance(150, -1, BufferedImage.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new BorderLayout(0, 0));

		userOverview = new JPanel();
		add(userOverview, BorderLayout.NORTH);
		userOverview.setLayout(new BorderLayout(0, 0));
		
		ImageIcon pic =new ImageIcon(prflPicture);
		userPictureL = new JLabel(pic);
		userOverview.add(userPictureL, BorderLayout.WEST);

		userData = new JPanel();
		userData.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblVornameame = new JLabel("Vorname:");
		GridBagConstraints gbc_lblVornameame = new GridBagConstraints();
		gbc_lblVornameame.fill = GridBagConstraints.BOTH;
		gbc_lblVornameame.insets = new Insets(0, 0, 0, 5);
		gbc_lblVornameame.gridx = 0;
		gbc_lblVornameame.gridy = 0;
		userData.add(lblVornameame, gbc_lblVornameame);
		
		lblNachname = new JLabel("Nachname:");
		GridBagConstraints gbc_lblNachname = new GridBagConstraints();
		gbc_lblNachname.fill = GridBagConstraints.BOTH;
		gbc_lblNachname.insets = new Insets(0, 0, 0, 5);
		gbc_lblNachname.gridx = 1;
		gbc_lblNachname.gridy = 1;
		userData.add(lblNachname, gbc_lblNachname);
		
		lblStadt = new JLabel("Stadt:");
		GridBagConstraints gbc_lblStadt = new GridBagConstraints();
		gbc_lblStadt.fill = GridBagConstraints.BOTH;
		gbc_lblStadt.insets = new Insets(0, 0, 0, 5);
		gbc_lblStadt.gridx = 2;
		gbc_lblStadt.gridy = 2;
		userData.add(lblStadt, gbc_lblStadt);
		
		lblLand = new JLabel("Land:");
		GridBagConstraints gbc_lblLand = new GridBagConstraints();
		gbc_lblLand.fill = GridBagConstraints.BOTH;
		gbc_lblLand.insets = new Insets(0, 0, 0, 5);
		gbc_lblLand.gridx = 3;
		gbc_lblLand.gridy = 3;
		userData.add(lblLand, gbc_lblLand);
		
		lblAlter = new JLabel("Alter: ");
		GridBagConstraints gbc_lblAlter = new GridBagConstraints();
		gbc_lblAlter.fill = GridBagConstraints.BOTH;
		gbc_lblAlter.gridx = 4;
		gbc_lblAlter.gridy = 4;
		userData.add(lblAlter, gbc_lblAlter);
		
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
		add(userContentScroll, BorderLayout.CENTER);
		
		

	}

}
