package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.control.DatabaseController;
import org.control.LoginControl;
import org.model.Post;

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
		DatabaseController.getInstance().update(LoginControl.getInstance().getCurrentUser());
		frame.setVisible(false);
		frame.dispose();
		
	}
	
}
