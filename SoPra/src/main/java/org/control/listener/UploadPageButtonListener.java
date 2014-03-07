package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MainScreen;
import org.view.screens.Center.OwnSongsScreen;

/**
 * 
 * @author Max, Tim
 *
 */
public class UploadPageButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		MainScreen.getInstance().updateCenter(OwnSongsScreen.getInstance());
		OwnSongsScreen.getInstance().updateTable();
		
	}

}
