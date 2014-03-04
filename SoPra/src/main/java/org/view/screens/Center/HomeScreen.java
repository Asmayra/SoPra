package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		this.setLayout(new BorderLayout());
		JLabel title = new JLabel("HOME");
		title.setPreferredSize(new Dimension(400,20));
		this.add(title,BorderLayout.NORTH);
		JButton createPostBtn = new JButton("Post erstellen");
		createPostBtn.addActionListener(new CreatePostButtonListener());
		this.add(createPostBtn,BorderLayout.SOUTH);
		//***********************************************
			User tst = new User();
			Post pst = new Post();
			tst.setUsername("FirstTestUser");
			tst.setFirstname("Bob");
			tst.addPosts(pst);
			pst.setMessage("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, "
					+ "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, "
					+ "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. ");
			this.add(pst.create(),BorderLayout.CENTER);
					
		//***********************************************
		
	}

}
