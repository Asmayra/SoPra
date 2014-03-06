package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.MailboxControl;
import org.model.User;
import org.view.MessagePopUpScreen;

public class ProfileMessageButtonListener implements ActionListener {
	
	private User receiver;
	
	public ProfileMessageButtonListener(User u){
		receiver = u;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MailboxControl.getInstance().resetGui();
		MessagePopUpScreen.getInstance().setVisible(true);
		MessagePopUpScreen.getInstance().setRecipient(receiver.getUsername());
	}

}
