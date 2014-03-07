package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.MailboxControl;

public class MailboxDeleteMessageButtonListener implements ActionListener {

	public MailboxDeleteMessageButtonListener() {
		// TODO Auto-generated constructor stub
	}

	public void actionPerformed(ActionEvent arg0) {
		MailboxControl.getInstance().deleteMarked();

	}

}
