package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.control.DatabaseControl;
import org.model.Ticket;
import org.model.User;

public class AdminHomeScreen extends JPanel {
	public AdminHomeScreen(){
		JPanel structure = new JPanel();
		structure.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setPreferredSize(new Dimension(430,530));
		scrollBar.setViewportView(structure);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//**************************************************************
		//Get ALL THE TICKETS
		List<?> tickets;
		tickets=  DatabaseControl.getInstance().getTableContent("Ticket");
		//Print out ALL THE TICKETS
		Iterator<?> it = tickets.iterator();
		while(it.hasNext()){
			c.gridy++;
			structure.add(((Ticket)it.next()).create(),c);
		}
		this.add(scrollBar);
		
	}

}
