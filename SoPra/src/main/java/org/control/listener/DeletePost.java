package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Post;
import org.model.User;
import org.view.MainScreen;
import org.view.screens.Center.HomeScreen;
/**
 * Klasse um Posts zu löschen
 * @author Philipp
 *
 */
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
				DatabaseControl.getInstance().update(usr);
				MainScreen.getInstance().updateCenter(new HomeScreen());
			}
		}
		}else{
			System.out.println("denied!");
		}
	
	}
	


}
