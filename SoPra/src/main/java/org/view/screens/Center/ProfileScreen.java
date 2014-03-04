

//In Bearbeitung bei Sebastian


package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import org.control.LoginControl;
import org.model.User;

public class ProfileScreen extends JPanel {
	private User userProfile;
	private Image prflPicture;
	private boolean ignoreProfile;
	private boolean followProfile;
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
	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblCity;
	private JLabel lblCountry;
	private JLabel lblAge;
	private JLabel lblUserName;
	
	
	
	public ProfileScreen(User selectedUser) {
		userProfile = selectedUser;
		//task: try catch Block hinzufügen für IOException
		try {
			prflPicture = userProfile.getPicture();
			prflPicture = prflPicture.getScaledInstance(150, -1, BufferedImage.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (LoginControl.getInstance().getCurrentUser().isFollowing(userProfile)){
			followProfile=true;
		}
		setLayout(new BorderLayout(0, 0));

		userOverview = new JPanel();
		add(userOverview, BorderLayout.NORTH);
		userOverview.setLayout(new BorderLayout(0, 0));
		
		ImageIcon pic =new ImageIcon(prflPicture);
		userPictureL = new JLabel(pic);
		userOverview.add(userPictureL, BorderLayout.WEST);

		userData = new JPanel();
		userData.setLayout(new BoxLayout(userData, BoxLayout.Y_AXIS));
		
		lblUserName = new JLabel(userProfile.getUsername());
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 20));
		userData.add(lblUserName);
		lblName = new JLabel("Vorname:"+userProfile.getFirstname());
		userData.add(lblName);
		lblLastName = new JLabel("Nachname:"+userProfile.getLastname());
		userData.add(lblLastName);
		lblCity = new JLabel("Stadt:"+userProfile.getCity());
		userData.add(lblCity);
		lblCountry = new JLabel("Land:"+userProfile.getCountry());
		userData.add(lblCountry);
		lblAge = new JLabel("Alter:"+userProfile.getAge());
		userData.add(lblAge);
		

		
		

		
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
