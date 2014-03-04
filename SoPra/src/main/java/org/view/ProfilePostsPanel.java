package org.view;

import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.model.Post;
import org.model.User;



public class ProfilePostsPanel extends JPanel {
	
	
	public ProfilePostsPanel(User selectedUser){
		this.removeAll();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Iterator<Post> p = selectedUser.getPosts().iterator();
		while (p.hasNext()){
			this.add(p.next().create());
			
		}
	}
	

}
