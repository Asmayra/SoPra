package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.LoginControl;
import org.control.MailboxControl;
import org.view.MessagePopUpScreen;

public class MessagePopUpScreenSendButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		String[] recipent = MessagePopUpScreen.getInstance().getRecipients();
		String subject = MessagePopUpScreen.getInstance().getSubject();
		String content = MessagePopUpScreen.getInstance().getMessageText();
		
		if( MailboxControl.getInstance().sendMessage(LoginControl.getInstance().getCurrentUser(), recipent, subject, content) )
		{
			MessagePopUpScreen.getInstance().dispose();
			MailboxControl.getInstance().updateTable();
		}
		else
			MessagePopUpScreen.getInstance().displayError();
	}

}
