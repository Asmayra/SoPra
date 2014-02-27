package org.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private int ticketId;
	private Admin inWorkBy;
	private User requester;
	
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public Admin getInWorkBy() {
		return inWorkBy;
	}
	public void setInWorkBy(Admin inWorkBy) {
		this.inWorkBy = inWorkBy;
	}
	public User getRequester() {
		return requester;
	}
	public void setRequester(User requester) {
		this.requester = requester;
	}
	

}
