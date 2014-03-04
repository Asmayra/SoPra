package org.control;

import java.io.IOException;
import org.model.Post;
import org.model.User;
import org.view.LoginScreen;
/**
 * MainControll Class
 * @author Philipp
 *
 */
public class Main {
	public static void main(String[] args){
			LoginScreen ls = LoginScreen.getInstance();
			ls.setVisible(true);
			
			//disover control initialize
			//DiscoverControl discContr = new DiscoverControl();
			
			//****************************
				//TestUser
				//Passwort: passwort
				User testUser = (User) DatabaseController.getInstance().load(User.class,"MaxMuster");
				Post pst1 = new Post();
				pst1.setMessage("Ich bin jetzt auch bei soPra");
				Post pst2 = new Post();
				pst2.setMessage("Hier kann man nix machen");
				testUser.addPosts(pst1);
				testUser.addPosts(pst2);
				
				try {
					DatabaseController.getInstance().save(pst1);
					DatabaseController.getInstance().save(pst2);
					DatabaseController.getInstance().save(testUser);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			//****************************		
	}
}
