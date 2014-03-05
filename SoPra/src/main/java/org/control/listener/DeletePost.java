package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import org.control.DatabaseController;
import org.control.LoginControl;
import org.model.Post;
import org.model.User;

public class DeletePost implements ActionListener {
	//the selected Post
	private Post selection; 
	/**
	 * 
	 * @param selection Post to delete
	 */
	public DeletePost(Post selection){
		this.selection = selection;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Iterator<Post> it = selection.getAutor().getPosts().iterator();
		if (LoginControl.getInstance().getCurrentUser() == selection.getAutor()){
				while(it.hasNext()){
			Post temp = it.next();
			if(temp.equals(selection)){
				User usr = temp.getAutor();
				usr.getPosts().remove(selection);
				temp.setAutor(null);
				DatabaseController.getInstance().update(usr);
			}
		}
		}else{
			System.out.println("denied!");
		}
	
	}
	


}
