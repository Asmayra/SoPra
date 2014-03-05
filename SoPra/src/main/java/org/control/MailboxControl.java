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
	private ArrayList<Message> messages;
	
 	
	private MailboxControl() {
		markedRows = new ArrayList<Integer>();
		messages = new ArrayList<Message>();
	}
	
	public static MailboxControl getInstance()
	{
		if(instance == null)
			instance = new MailboxControl();
			
		return instance;
	}
	
	
	
	public void updateTable(){
		
		messages.clear();
		
		Mailbox.getInstance().clearTable();
		User curUser = LoginControl.getInstance().getCurrentUser();
		
		messages.addAll(curUser.getMessages());
		for(Message m : messages)
				Mailbox.getInstance().addRow(m.getSender().getUsername(),m.getSubject(),m.getDate());
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
		User curUser = LoginControl.getInstance().getCurrentUser();
		int i = 0;
		for(Message m : messages)
		{
			if(markedRows.contains(new Integer(i)))
			{
				curUser.removeMessage(m);
			}
			i ++;
		}
		
		DatabaseController.getInstance().update(curUser);
		updateTable();
	}
}
