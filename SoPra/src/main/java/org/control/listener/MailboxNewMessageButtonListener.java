package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.MailboxControl;
import org.view.MessagePopUpScreen;

public class MailboxNewMessageButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MailboxControl.getInstance().resetGui();
		MessagePopUpScreen.getInstance().setVisible(true);
	}

}
