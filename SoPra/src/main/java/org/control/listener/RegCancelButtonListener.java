package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.RegControl;

public class RegCancelButtonListener implements ActionListener {

	public RegCancelButtonListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		RegControl.getInstance().destroy();

	}

}
