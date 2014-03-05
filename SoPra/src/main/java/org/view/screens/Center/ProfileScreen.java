
//In Bearbeitung bei Sebastian

package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import org.control.IgnoreButtonListener;
import org.control.LoginControl;
import org.control.listener.FollowButtonListener;
import org.model.User;
import org.view.ProfilePostsPanel;
import org.view.ProfileSongsPanel;

public class ProfileScreen extends JPanel {
	private User userProfile;
	private Image prflPicture;
	private boolean ignoreProfile;
	private boolean followProfile;
	private JPanel userOverview;
	private JLabel userPictureL;
	private JPanel userData;
	private JPanel buttons;
	private JScrollPane userContentScrollPlaylists;
	private JScrollPane userContentScrollAlben;
	private JScrollPane userContentScrollPosts;
	private JScrollPane userContentScrollSongs;
	private JTabbedPane userContent;
	private JPanel playlists;
	private JPanel alben;
	private JPanel posts;
	private JPanel songs;
	private JButton message;
	private JButton ignore;
	private JButton follow;
	private JLabel lblName;
	private JLabel lblLastName;
	private JLabel lblCity;
	private JLabel lblCountry;
	private JLabel lblAge;
	private JLabel lblUserName;

	public ProfileScreen(User selectedUser) {
		userProfile = selectedUser;
		// task: try catch Block hinzufügen für IOException
		try {
			prflPicture = userProfile.getPicture();
			prflPicture = prflPicture.getScaledInstance(150, -1, BufferedImage.SCALE_DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		lblUserName = new JLabel(userProfile.getUsername());
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 20));
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

		userOverview.add(userData, BorderLayout.CENTER);

		buttons = new JPanel();
		userOverview.add(buttons, BorderLayout.SOUTH);

		message = new JButton("Nachricht senden");
		buttons.add(message);
		if (userProfile != LoginControl.getInstance().getCurrentUser()){
			if (followProfile){
				follow = new JButton("unfollow");
			} else {
				follow = new JButton("follow");
			}
			follow.addActionListener(new FollowButtonListener());
			if (ignoreProfile){
				ignore = new JButton("unignore");
			} else {
				ignore = new JButton("ignore");
			}
			ignore.addActionListener(new IgnoreButtonListener());
			buttons.add(follow);
			buttons.add(ignore);
		}
		
		


		userContent = new JTabbedPane();
		playlists = new JPanel();
		alben = new JPanel();
		posts = new ProfilePostsPanel(userProfile);
		songs = new ProfileSongsPanel(userProfile);
		
		userContentScrollPlaylists = new JScrollPane(playlists);
		userContentScrollAlben = new JScrollPane(alben);
		userContentScrollPosts = new JScrollPane(posts);
		userContentScrollSongs = new JScrollPane(songs);
		userContentScrollPlaylists.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		userContentScrollAlben.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		userContentScrollPosts.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		userContentScrollSongs.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		userContent.addTab("Playlists", userContentScrollPlaylists);
		userContent.addTab("Posts", userContentScrollPosts);
		if (LoginControl.getInstance().getCurrentUser().getRights()=="Artist"||LoginControl.getInstance().getCurrentUser().getRights()=="LabelManager"){
			userContent.addTab("Songs", userContentScrollSongs);
			userContent.addTab("Alben", userContentScrollAlben);
		}
		add(userContent, BorderLayout.CENTER);

	}
	
	public boolean getIgnore(){
		return ignoreProfile;
	}
	
	public boolean getFollow(){
		return followProfile;
	}

	public void setFollow(boolean b) {
		followProfile = b;
		
	}
	
	public void setIgnore(boolean b){
		ignoreProfile = b;
	}

	public User getUserProfile() {
		return userProfile;
	}



}
