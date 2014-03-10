package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import org.control.DatabaseControl;
import org.model.Label;
import org.model.User;
import org.view.screens.Center.LabelScreen;

public class LabelScreenManagerDeleteButtonListener implements ActionListener {
	
	private LabelScreen currentLabelScreen;
	public LabelScreenManagerDeleteButtonListener(LabelScreen currentLabelScreen){
		this.currentLabelScreen = currentLabelScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = currentLabelScreen.getSelectedRowManagerTable();
		
		
		Label currentLabel = currentLabelScreen.getCurrentLabel();
		ArrayList<User> managerList = (ArrayList<User>) currentLabel.getManagers();
		for (User a : managerList){
			if (a.getUsername().equals(currentLabelScreen.getRowUserNameManager(row))){
				currentLabel.removeManager(a);
				DatabaseControl.getInstance().update(currentLabel);
			}
		}
		
		currentLabelScreen.deleteRowManagerTable(row);
	}
}
