package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.control.DatabaseControl;
import org.model.User;
import org.view.screens.Center.AdminLabelScreen;

/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class AdminLabelScreenAddNewArtistButtonListener implements
		ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		User artistUser = (User) DatabaseControl.getInstance().load(User.class, AdminLabelScreen.getInstance().getNewArtistTF());
		
		if (AdminLabelScreen.getInstance().getSelectedRow() < 0){
			return;
		}
		
		if (artistUser ==null){
			JOptionPane.showMessageDialog(null, "User existiert nicht!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (!artistUser.getRights().equals("Artist") ){
			JOptionPane.showMessageDialog(null, "User ist kein Künstler!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (artistUser.getLabel() != null){
			JOptionPane.showMessageDialog(null, "Der User gehört schon zu einem Label!", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		User mUser = (User) DatabaseControl.getInstance().load(User.class,AdminLabelScreen.getInstance().getLabelManager());
		if (mUser == null || mUser.getLabel() == null){
			JOptionPane.showMessageDialog(null, "Oops es ist was schief gegangen. Bitte kontaktieren Sie einen Admin", "Fehler", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		
		artistUser.setLabel(mUser.getLabel());
		DatabaseControl.getInstance().update(artistUser);
		mUser.getLabel().addArtist(artistUser);
		DatabaseControl.getInstance().update(mUser.getLabel());
		
	}

}
