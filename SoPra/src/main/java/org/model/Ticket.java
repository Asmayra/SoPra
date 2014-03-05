package org.model;

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
	private String kategory;
	
	public String getKategory() {
		return kategory;
	}
	public void setKategory(String kategory) {
		this.kategory = kategory;
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
	 * @return
	 */
	public JPanel create(){
		JPanel ticketPanel = new JPanel();
		ticketPanel.setLayout(new BoxLayout(ticketPanel,BoxLayout.Y_AXIS));
		JLabel reqName = new JLabel("Anfrage von "+this.requester.getUsername()+" : "+this.kategory);
		JLabel srtInfo = new JLabel(this.shortInfo);
		JButton editBTN= new JButton("Bearbeiten");
		ticketPanel.add(reqName);
		ticketPanel.add(srtInfo);
		ticketPanel.add(editBTN);
		return ticketPanel;
	}

}
