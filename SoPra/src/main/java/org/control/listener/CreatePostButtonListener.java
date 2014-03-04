package org.control.listener;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.control.LoginControl;


public class CreatePostButtonListener implements ActionListener  {

	public void actionPerformed(ActionEvent arg0) {
		 JFrame createPostFrame = new JFrame();
		 //Creates a new Frame at the current mousePosition
		 createPostFrame.setLocation(MouseInfo.getPointerInfo().getLocation());
		 //Create a new Textfield for the Post-Message
		 JTextField messageTxt = new JTextField();
		 messageTxt.setPreferredSize(new Dimension(100,320));
		 JButton abbort = new JButton("Abbrechen");
		 JButton post	= new JButton("Posten");
		 abbort.addActionListener(new CloseFrameListener(createPostFrame));
		 
	}

}
