package org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	

}
