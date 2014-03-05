package org.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
	
	
	public boolean sendMessage(User sender, String[] receips, String subject, String content)
	{
		User[] recv = new User[receips.length];
		int i = 0;
		for(String s : receips)
		{
			if( ( recv[i] = (User) DatabaseController.getInstance().load(User.class, s) ) == null ) 
				return false;
			i++;
		}
		
		Message newMsg = new Message();
		newMsg.setSender(sender);
		newMsg.setRecipient(recv);
		newMsg.setSubject(subject);
		newMsg.setContent(content);
		newMsg.setDate(new Date().toString());
		
		for(User u : recv)
		{
			u.addMessage(newMsg);
			DatabaseController.getInstance().update(u);
		}
		
		return true;
		
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
		boolean removed = false;
		for(Message m : messages)
		{
			if(markedRows.contains(new Integer(i)))
			{
				curUser.removeMessage(m);
				removed = true;
			}
			i ++;
		}
		
		if(removed)
		{
			DatabaseController.getInstance().update(curUser);
			updateTable();
		}
	}
}
