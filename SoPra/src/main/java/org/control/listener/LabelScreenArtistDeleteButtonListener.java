package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.control.DatabaseControl;
import org.model.Label;
import org.model.User;
import org.view.screens.Center.LabelScreen;

public class LabelScreenArtistDeleteButtonListener implements ActionListener {

	private LabelScreen currentLabelScreen;
	
	public LabelScreenArtistDeleteButtonListener(LabelScreen currentLabelScreen){
		this.currentLabelScreen = currentLabelScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = currentLabelScreen.getSelectedRowArtistTable();
		
		
		Label currentLabel = currentLabelScreen.getCurrentLabel();
		ArrayList<User> artistList = (ArrayList<User>) currentLabel.getArtists();
		for (User a : artistList){
			if (a.getUsername().equals(currentLabelScreen.getRowUserNameArtist(row))){
				currentLabel.removeArtist(a);
				DatabaseControl.getInstance().update(currentLabel);
				a.setLabel(null);
				DatabaseControl.getInstance().update(a);
			}
		}
		
		
		currentLabelScreen.deleteRowArtistTable(row);
	}

}
