package org.control;

import org.model.User;
import org.view.MainScreen;
/**
 * MainControll Class
 * @author Philipp
 *
 */
public class Main {
	public static void main(String[] args){
			Main main = new Main();
			RegControl.getInstance().showRegistration();
			main.setUp();
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
