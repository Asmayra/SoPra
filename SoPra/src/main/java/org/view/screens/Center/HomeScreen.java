package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.model.Post;
import org.model.User;
import org.view.MainScreen;

public class HomeScreen extends JPanel{
	public HomeScreen(){
		this.setLayout(new BorderLayout());
		JLabel title = new JLabel("HOME");
		title.setPreferredSize(new Dimension(400,20));
		this.add(title,BorderLayout.NORTH);
		//***********************************************
			User tst = new User();
			Post pst = new Post();
			
			Collection<Post> psts = new LinkedList<Post>();
			tst.addPosts(pst);
			tst.setUsername("FirstTestUser");
			
			this.add(pst.create(),BorderLayout.CENTER);
			
		
		
		
		
		//***********************************************
		
	}

}
