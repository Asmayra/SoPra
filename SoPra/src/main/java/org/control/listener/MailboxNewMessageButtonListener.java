package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MailboxNewMessageButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MessagePopupScreen.getInstance().setVisible(true);
	}

}
