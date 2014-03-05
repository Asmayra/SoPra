package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MessagePopUpScreen;

public class MailboxNewMessageButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MessagePopUpScreen.getInstance().setVisible(true);
	}

}
