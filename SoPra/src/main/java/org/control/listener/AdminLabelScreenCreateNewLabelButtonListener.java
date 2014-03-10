package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.control.DatabaseControl;
import org.model.Label;
import org.model.User;
import org.view.screens.Center.AdminLabelScreen;
/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class AdminLabelScreenCreateNewLabelButtonListener implements
		ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Label newLabel = new Label();
		AdminLabelScreen aScreen = AdminLabelScreen.getInstance();
		
		User lManager = (User) DatabaseControl.getInstance().load(User.class, aScreen.getManagerTF());
		if (lManager == null || !lManager.getRights().equals("LabelManager")){
			JOptionPane.showMessageDialog(null, "User existiert nicht oder ist nicht LabelManager", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (lManager.getLabel() != null)
		{
			JOptionPane.showMessageDialog(null, "Manager hat bereits ein Label", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		newLabel.setName(aScreen.getNameTF());
		newLabel.setManager(lManager);
		lManager.setLabel(newLabel);
		try {
			DatabaseControl.getInstance().save(newLabel);;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		DatabaseControl.getInstance().update(lManager);
		
		AdminLabelScreen.getInstance().updateTable();
	}

}
