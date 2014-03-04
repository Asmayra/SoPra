package org.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.model.Message;
import org.model.User;
import org.view.screens.Center.Mailbox;

public class MailboxControl {

	
	private static MailboxControl instance = null;
	private ArrayList<Integer> markedRows;
	
 	
	private MailboxControl() {
		markedRows = new ArrayList<Integer>();
	}
	
	public static MailboxControl getInstance()
	{
		if(instance == null)
			instance = new MailboxControl();
			
		return instance;
	}
	
	private Collection<Message> messages = new LinkedList<Message>();
	
	public void updateTable(){
		Mailbox.getInstance().clearTable();
		User curUser = LoginControl.getInstance().getCurrentUser();
		
		messages = curUser.getMessages();
		Message tempMessage = new Message();
		java.util.Iterator<Message> it = messages.iterator();
		
			while (it.hasNext() ){
				tempMessage = it.next();
				Mailbox.getInstance().addRow(tempMessage.getSender().getUsername(),tempMessage.getSubject(),tempMessage.getDate());
			}
	}
	
	
	public boolean markRow(int row)
	{
		if(markedRows.contains(new Integer(row)))
		{
			markedRows.remove(new Integer(row));
			System.out.println("Removed: " + row);
			return false;
		}
		else
		{
			markedRows.add(row);
			System.out.println("Added: " + row);
			return true;
		}
	}
	
	public void deleteMarked()
	{
		
	}
}
