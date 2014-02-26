package org.control;

import org.view.MainScreen;
/**
 * MainControll Class
 * @author Philipp
 *
 */
public class Main {
	public static void main(String[] args){
			Main main = new Main();
			main.setUp();
	}
	/**
	 * Initializes the Mainscreen with a default Layout
	 */
	public void setUp(){
		MainScreen mainScreen = new MainScreen();
		mainScreen.setUpUserLayout();
		
	}

}
