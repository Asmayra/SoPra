package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.MailboxControl;
import org.view.MessagePopUpScreen;

public class MailboxReplyMessageButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MessagePopUpScreen popup = MessagePopUpScreen.getInstance();
		popup.setRecipient(MailboxControl.getInstance().getCurMessage().getSender().getUsername());
		//automatisch empfänger eintragen

	}

}
