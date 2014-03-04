package org.control.listener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CreatePostButtonListener implements ActionListener  {
	
	public void actionPerformed(ActionEvent arg0) {
		 JFrame createPostFrame = new JFrame();
		 //Create a new Textfield for the Post-Message
		 JTextArea messageTxt = new JTextArea("Post erstellen");
		 messageTxt.setPreferredSize(new Dimension(330,100));
		 messageTxt.setLineWrap(true);
		 messageTxt.setWrapStyleWord(true);
		 JButton abbort = new JButton("Abbrechen");
		 JButton post	= new JButton("Posten");
		 abbort.addActionListener(new CloseFrameListener(createPostFrame));		
		 post.addActionListener(new CreatePost(messageTxt.getText()));
		 createPostFrame.setLayout(new BorderLayout());
		 createPostFrame.add(messageTxt,BorderLayout.NORTH);
		 JPanel order = new JPanel();
		 order.setLayout(new FlowLayout());
		 order.add(post);
		 order.add(abbort);
		 order.setPreferredSize(new Dimension(330,50));
		 createPostFrame.add(order);
		 createPostFrame.pack();
		 createPostFrame.setVisible(true);
	}
	


}
