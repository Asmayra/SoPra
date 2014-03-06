package org.model;

import java.awt.FlowLayout;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.control.LoginControl;
import org.control.listener.DeleteTicket;
import org.control.listener.EditTicket;

@Entity
@Table(name="TICKET_TABLE")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int ticketId;		//Needs to be saved in tickedpool
	@ManyToOne
	private User inWorkBy;	
	private String name;
	@ManyToOne
	private User requester;
	private String shortInfo;
	private String category;
	
	public String getKategory() {
		return category;
	}
	public void setKategory(String kategory) {
		this.category = kategory;
	}
	public String getShortInfo() {
		return shortInfo;
	}
	public void setShortInfo(String shortInfo) {
		this.shortInfo = shortInfo;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public User getInWorkBy() {
		return inWorkBy;
	}
	public void setInWorkBy(User inWorkBy) {
		this.inWorkBy = inWorkBy;
	}
	public User getRequester() {
		return requester;
	}
	public void setRequester(User requester) {
		this.requester = requester;
	}
	
	/**
	 * Generates a Panel for tickets
	 * @return ticketPanel
	 */
	public JPanel create(){
		JPanel ticketPanel = new JPanel();
		ticketPanel.setLayout(new BoxLayout(ticketPanel,BoxLayout.Y_AXIS));
		JLabel reqName = new JLabel("Anfrage von "+this.requester.getUsername()+" : "+this.category);
		JLabel srtInfo = new JLabel("<html><body>"+this.shortInfo+"</body></html>");
		JButton editBTN= new JButton("Bearbeiten");
		JButton delBTN = new JButton("Löschen");
		editBTN.addActionListener(new EditTicket(this,LoginControl.getInstance().getCurrentUser()));
		delBTN.addActionListener(new DeleteTicket(this));
		JPanel structure = new JPanel();
		structure.setLayout(new FlowLayout());
		structure.add(delBTN);
		structure.add(editBTN);
		ticketPanel.add(reqName);
		ticketPanel.add(srtInfo);
		ticketPanel.add(structure);
		return ticketPanel;
	}

}
