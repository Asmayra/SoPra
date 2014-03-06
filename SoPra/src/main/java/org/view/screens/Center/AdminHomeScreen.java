package org.view.screens.Center;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		System.out.println("Admin eingeloggt!");
		//**************************************************************
		//Get ALL THE TICKETS
		List<?> tickets;
		tickets=  DatabaseControl.getInstance().getTableContent("Ticket");
		//Print out ALL THE TICKETS
		Iterator<?> it = tickets.iterator();
		while(it.hasNext()){
			this.add(((Ticket)it.next()).create());
		}
		
	}

}
