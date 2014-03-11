package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.MailboxControl;
import org.view.MessagePopUpScreen;


/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class MailboxNewMessageButtonListener implements ActionListener {

	
	public void actionPerformed(ActionEvent e) {
		MailboxControl.getInstance().resetGui();
		MessagePopUpScreen.getInstance().setVisible(true);
	}

}
