package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import org.control.LoginControl;
import org.control.listener.FollowButtonListener;
import org.control.listener.IgnoreButtonListener;
import org.control.listener.ProfileMessageButtonListener;
import org.control.listener.ProfileScreenBannButtonListener;
import org.control.listener.ProfileScreenInviteToLabelButtonListener;
import org.control.listener.ProfileScreenUpgradeToAdminButtonListener;
import org.control.listener.ProfileScreenUpgradeToArtistButtonListener;
import org.control.listener.ProfileScreenUpgradeToManagerButtonListener;
import org.control.listener.ProfileScreenUpgradeToUserButtonListener;
import org.model.User;
import org.view.ProfileAlbenPanel;
import org.view.ProfilePlaylistPanel;
import org.view.ProfilePostsPanel;
import org.view.ProfileSongsPanel;

/**
 * profil-screen to show a users data, his posts and his music collection with
 * functions for follow, ignore, send messages and ban provided by dynamic
 * buttons
 * 
 * @author Sebastian Roth, Michael Pfennings
 * 
 */
public class ProfileScreen extends JPanel {
	private User userProfile;
	private Image prflPicture;
	private boolean ignoreProfile;
	private boolean followProfile;
	private JPanel userOverview;
	private JLabel userPictureL;
	private JPanel userData;
	private JPanel buttons;
	private JScrollPane userContentScrollPosts;
	private JTabbedPane userContent;
	private JScrollPane playlists;
	private ProfileAlbenPanel alben;
	private JPanel posts;
	private JScrollPane songs;
	private JButton message;
	private JButton ignore;
	private JButton follow;
	private JButton bann, upgradeToArtist, upgradeToUser, upgradeToManager, upgradeToAdmin, inviteToLabel;
	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblCity;
	private JLabel lblCountry;
	private JLabel lblAge;
	private JLabel lblUserName;
	private JLabel lblBanned;
	private JLabel lblLabel;

	/**
	 * Konstruktor erstellt das Profil-Screen-Panel
	 * 
	 * @param selectedUser
	 *            User dessen Profil-Screen angezeigt wird
	 */
	public ProfileScreen(User selectedUser) {
		userProfile = selectedUser;
		// task: try catch Block hinzufügen für IOException
		prflPicture = userProfile.getPicture();
		prflPicture = prflPicture.getScaledInstance(150, 150, BufferedImage.SCALE_DEFAULT);
		if (LoginControl.getInstance().getCurrentUser().isFollowing(userProfile)) {
			followProfile = true;
		} else {
			followProfile = false;
		}
		if (LoginControl.getInstance().getCurrentUser().isIgnoring(userProfile)) {
			ignoreProfile = true;
		} else {
			ignoreProfile = false;
		}
		setLayout(new BorderLayout(0, 0));

		userOverview = new JPanel();
		add(userOverview, BorderLayout.NORTH);
		userOverview.setLayout(new BorderLayout(0, 0));

		ImageIcon pic = new ImageIcon(prflPicture);
		userPictureL = new JLabel(pic);
		userOverview.add(userPictureL, BorderLayout.WEST);

		userData = new JPanel();
		userData.setLayout(new BoxLayout(userData, BoxLayout.Y_AXIS));

		lblUserName = new JLabel(userProfile.getUsername() + "  " + "(" + userProfile.getRights() + ")");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUserName.setForeground(Color.pink);
		userData.add(lblUserName);
		lblName = new JLabel("Vorname:" + userProfile.getFirstname());
		userData.add(lblName);
		lblLastName = new JLabel("Nachname:" + userProfile.getLastname());
		userData.add(lblLastName);
		lblCity = new JLabel("Stadt:" + userProfile.getCity());
		userData.add(lblCity);
		lblCountry = new JLabel("Land:" + userProfile.getCountry());
		userData.add(lblCountry);
		lblAge = new JLabel("Alter:" + userProfile.getAge());
		userData.add(lblAge);
		
		if( (userProfile.getRights().equals("Artist")  ||  userProfile.getRights().equals("LabelManager")) &&
				userProfile.getLabel() != null )
		{
			lblLabel = new JLabel("Label: " + userProfile.getLabel().getName());
			userData.add(lblLabel);
		}

		if (userProfile.getBanned() == true) {
			lblBanned = new JLabel("Nutzer ist gebannt!");
			lblBanned.setForeground(Color.RED);
			userData.add(lblBanned);
		}

		userOverview.add(userData, BorderLayout.CENTER);

		buttons = new JPanel();
		userOverview.add(buttons, BorderLayout.SOUTH);

		if (!(userProfile.getUsername().equals(LoginControl.getInstance().getCurrentUser().getUsername()))) {
			message = new JButton("Nachricht senden");
			message.addActionListener(new ProfileMessageButtonListener(this.getUserProfile()));
			buttons.add(message);
			if (followProfile) {
				follow = new JButton("unfollow");
			} else {
				follow = new JButton("follow");
			}
			follow.addActionListener(new FollowButtonListener(this));
			if (ignoreProfile) {
				ignore = new JButton("unignore");
			} else {
				ignore = new JButton("ignore");
			}
			ignore.addActionListener(new IgnoreButtonListener(this));
			buttons.add(follow);
			buttons.add(ignore);
		}

		if (LoginControl.getInstance().getCurrentUser().getRights().equals("Admin")) {
			JPanel adminPanel = new JPanel();
			adminPanel.setLayout(new GridLayout(5, 1));
			bann = new JButton("Nutzer (ent-)bannen");
			bann.addActionListener(new ProfileScreenBannButtonListener(selectedUser));
			adminPanel.add(bann);

			upgradeToArtist = new JButton("Zu Artist befördern");
			upgradeToArtist.addActionListener(new ProfileScreenUpgradeToArtistButtonListener(selectedUser));
			adminPanel.add(upgradeToArtist);

			upgradeToUser = new JButton("Zu User befördern");
			upgradeToUser.addActionListener(new ProfileScreenUpgradeToUserButtonListener(selectedUser));
			adminPanel.add(upgradeToUser);

			upgradeToManager = new JButton("Zu Manager befördern");
			upgradeToManager.addActionListener(new ProfileScreenUpgradeToManagerButtonListener(selectedUser));
			adminPanel.add(upgradeToManager);

			upgradeToAdmin = new JButton("Zu Admin befördern");
			upgradeToAdmin.addActionListener(new ProfileScreenUpgradeToAdminButtonListener(selectedUser));
			adminPanel.add(upgradeToAdmin);

			userOverview.add(adminPanel, BorderLayout.EAST);

		}

		if (LoginControl.getInstance().getCurrentUser().getRights().equals("LabelManager")) {
			inviteToLabel = new JButton("Zu Label einladen");
			inviteToLabel.addActionListener(new ProfileScreenInviteToLabelButtonListener());
			userOverview.add(inviteToLabel, BorderLayout.EAST);
		}

		userContent = new JTabbedPane();
		playlists = new ProfilePlaylistPanel(userProfile);
		alben = new ProfileAlbenPanel(userProfile);
		posts = new ProfilePostsPanel(userProfile);
		songs = new ProfileSongsPanel(this);

		new JScrollPane(playlists);
		new JScrollPane(alben);
		userContentScrollPosts = new JScrollPane(posts);
		userContentScrollPosts.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		userContent.addTab("Playlists", playlists);
		userContent.addTab("Posts", userContentScrollPosts);
		if (userProfile.getRights().equals("Artist") || userProfile.getRights().equals("LabelManager")) {
			userContent.addTab("Songs", songs);
			userContent.addTab("Alben", alben);
		}
		add(userContent, BorderLayout.CENTER);

	}

	public boolean getIgnore() {
		return ignoreProfile;
	}

	public boolean getFollow() {
		return followProfile;
	}

	public void setFollow(boolean b) {
		followProfile = b;

	}

	public void setIgnore(boolean b) {
		ignoreProfile = b;
	}

	/**
	 * returns the user of the depending user profile
	 * 
	 * @return
	 */
	public User getUserProfile() {
		return userProfile;
	}

	public void resetPanels() {
		// toDo

	}

}
