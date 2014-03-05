package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MainScreen;
import org.view.screens.Center.AdminGenreScreen;

/**
 * 
 * @author Max, Tim
 *
 */
public class GenresButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		MainScreen.getInstance().showAdminGenreScreen(AdminGenreScreen.getInstance());
		
	}

}

