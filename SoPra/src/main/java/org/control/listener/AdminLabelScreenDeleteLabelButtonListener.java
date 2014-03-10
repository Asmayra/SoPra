package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AdminLabelScreenDeleteLabelButtonListener implements
		ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		User manager = (User) DatabaseControl.getInstance().load(User.class, AdminLabelScreen.getInstance().getLabelManager());
		
		if( manager == null || manager.getLabel() == null )
		{
			JOptionPane.showMessageDialog(null, "Oops es ist was schief gegangen. Bitte kontaktieren Sie einen Admin", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		Label label = manager.getLabel();
		
		manager.setLabel(null);
		DatabaseControl.getInstance().update(manager);
		DatabaseControl.getInstance().delete(label);
		AdminLabelScreen.getInstance().updateTable();
	}

}
