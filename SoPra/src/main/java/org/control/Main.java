package org.control;

import java.io.IOException;
import java.util.Date;

import org.model.Post;
import org.model.User;
import org.view.LoginScreen;
import org.view.MainScreen;
/**
 * MainControll Class
 * @author Philipp
 *
 */
public class Main {
	public static void main(String[] args){
			LoginScreen ls = new LoginScreen();
			ls.setVisible(true);
			
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
	/**
	 * Initializes the Mainscreen with a default Layout
	 */
	public void setUp(){	
		MainScreen.getInstance().setUpUserLayout();
		
	}
	
	//jframe test
	public static String getHelloWorld() {
		 
		return "Hello World";
 
	}
 
	public static String getHelloWorld2() {
 
		return "Hello World 2";
 
	}

}
