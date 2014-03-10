package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Label;
import org.model.User;
import org.view.MainScreen;
import org.view.screens.Center.LabelScreen;
import org.view.screens.Center.OwnSongsScreen;

public class LabelButtonListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

		User current = LoginControl.getInstance().getCurrentUser();
		if ( current.getLabel() != null){
			MainScreen.getInstance().updateCenter(new LabelScreen(current.getLabel()));
			return;
		}
	}	
		

}


