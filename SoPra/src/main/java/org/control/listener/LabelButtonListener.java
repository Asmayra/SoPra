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
		List<Label> labelList =  (List<Label>) DatabaseControl.getInstance().getTableContent("Label");
		User current = LoginControl.getInstance().getCurrentUser();
		for (Label l : labelList){
				if ( l.getManager().getUsername().equals(current.getUsername()) && current.getLabel() != null
						&& current.getLabel().getId() == l.getId() ){
					MainScreen.getInstance().updateCenter(new LabelScreen(l));
					return;
				}
			}
		}
		
		

}


