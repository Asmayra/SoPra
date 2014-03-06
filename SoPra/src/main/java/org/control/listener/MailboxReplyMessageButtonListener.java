package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.MailboxControl;
import org.model.Message;
import org.model.User;
import org.view.MessagePopUpScreen;

public class MailboxReplyMessageButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MailboxControl.getInstance().resetGui();
		MessagePopUpScreen popup = MessagePopUpScreen.getInstance();
		
		Message toReply = MailboxControl.getInstance().getCurMessage();
		
		if(toReply != null)
		{
			User recip = toReply.getSender();
			if(recip != null)
			{
				popup.setRecipient(recip.getUsername());
				popup.setSubject("RE: " + toReply.getSubject());
			}
		}
		
		popup.setVisible(true);

	}

}
