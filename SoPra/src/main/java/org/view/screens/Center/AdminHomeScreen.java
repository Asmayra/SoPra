package org.view.screens.Center;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.control.DatabaseControl;
import org.model.Ticket;
import org.model.User;

public class AdminHomeScreen extends JPanel {
	public AdminHomeScreen(){
		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setPreferredSize(new Dimension(430,500));
		scrollBar.setViewportView(this);
		//**************************************************************
		Ticket t = new Ticket();
		User u = new User();
		u.setUsername("q");
		t.setCategory("Viagra onine kaufen");
		t.setRequester(u);
		t.setShortInfo("blablablablablabalbalbalbal");	
	
		this.add(t.create());
	}

}
