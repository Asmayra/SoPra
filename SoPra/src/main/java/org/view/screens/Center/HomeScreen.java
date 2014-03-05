package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.control.LoginControl;
import org.control.listener.CreatePostButtonListener;
import org.model.Post;
import org.model.User;
import org.view.MainScreen;

public class HomeScreen extends JPanel{
	
	private static HomeScreen instance = null;
	
	public static HomeScreen getInstance()
	{
		if(instance == null)
			instance = new HomeScreen();
		
		return instance;
	}
	
	
	private HomeScreen(){
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new BorderLayout());
		JLabel title = new JLabel("HOME");
		title.setPreferredSize(new Dimension(400,20));
		this.add(title,BorderLayout.NORTH);
		JButton createPostBtn = new JButton("Post erstellen");
		createPostBtn.addActionListener(new CreatePostButtonListener());
		this.add(createPostBtn,BorderLayout.SOUTH);
		//***********************************************
		
		JPanel currentPosts = new JPanel();
		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setPreferredSize(new Dimension(430,500));
		scrollBar.setViewportView(currentPosts);
		currentPosts.setLayout(new BoxLayout(currentPosts,BoxLayout.Y_AXIS));		
		LinkedList<Post> followedUsers = new LinkedList<Post>();//Liste für die Posts der gefolgten benutzer
		Iterator<User> userIt = LoginControl.getInstance().getCurrentUser().getFollowing().iterator(); 
		while(userIt.hasNext()){
			Iterator<Post> userPostIt = userIt.next().getPosts().iterator();
			while(userPostIt.hasNext()){
				followedUsers.add(userPostIt.next());
			}
		}
		
		Iterator<Post> pstIt = LoginControl.getInstance().getCurrentUser().getPosts().iterator();
		while(pstIt.hasNext()){
			followedUsers.add(pstIt.next());
		}
		
		//Nun enthält followedUsers alle Posts der user denen er folgt; diese könnten nun sorteirt werden...
		Iterator<Post> followPostsIt = followedUsers.iterator();
		while(followPostsIt.hasNext()){
			currentPosts.add(followPostsIt.next().create());
		}
		
		this.add(scrollBar,BorderLayout.CENTER);	
		//***********************************************
		
	}

}
