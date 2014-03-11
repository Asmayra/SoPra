package org.view.screens.Center;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.model.Ticket;

/**
 * Anzeige der Tickets, die im Tickettab des Admins angezeigt werden
 * @author Tim
 *
 */
public class TicketScreenAdmin extends JPanel {
	/**
	 * Erzeugt den Ticketscreen 
	 * @param tickets Die anzuzeigenden Tickets
	 */
	public TicketScreenAdmin(LinkedList<Ticket> tickets){
		JPanel structure = new JPanel();
		structure.setLayout(new BoxLayout(structure,BoxLayout.Y_AXIS));
		
		JScrollPane scrollBar = new JScrollPane();
		scrollBar.setPreferredSize(new Dimension(430,530));
		scrollBar.setViewportView(structure);
		for(int i=0;i<tickets.size();i++){
			structure.add(tickets.get(i).create());
		}
		
		this.add(scrollBar);
		
	}

}