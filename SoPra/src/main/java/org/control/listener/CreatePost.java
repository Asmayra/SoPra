package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Post;
import org.view.MainScreen;
import org.view.screens.Center.HomeScreen;

/**
 * Creates a Post with the give Text
 * @author Philipp
 *
 */
public class CreatePost implements ActionListener{
	JTextArea message;
	JFrame frame;
	/**
	 * 
	 * @param message The text to post
	 */
	public CreatePost(JTextArea message,JFrame frame){
		this.message = message;
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		Post pst = new Post();
		LoginControl.getInstance().getCurrentUser().addPosts(pst);
		pst.setMessage(message.getText());	
		DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser());
		frame.setVisible(false);
		frame.dispose();
		MainScreen.getInstance().updateCenter(new HomeScreen());
		
	}
	
}
