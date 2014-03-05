package org.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.model.Message;
import org.model.User;
import org.view.screens.Center.Mailbox;

/**
 * Stellt Methoden zur Verwaltung der Mailbox und erstellen neuer Nachrichten bereit
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class MailboxControl {

	
	private static MailboxControl instance = null;
	private ArrayList<Integer> markedRows;
	private ArrayList<Message> messages;
	
	private Message curMessage = null;
	
 	
	private MailboxControl() {
		markedRows = new ArrayList<Integer>();
		messages = new ArrayList<Message>();
	}
	
	/**
	 * Singleton
	 * @return Instanz des Singletons
	 */
	public static MailboxControl getInstance()
	{
		if(instance == null)
			instance = new MailboxControl();
			
		return instance;
	}
	
	/**
	 * Sendet eine neue Nachricht an die angegebenen Benutzer
	 * @param sender Sender der Nachricht
	 * @param recipients Empfänger der Nachricht
	 * @param subject Betreff der Nachricht
	 * @param content Inhalt der Nachricht
	 * @return true wenn Nachricht versendet wurde, false sonst
	 * @pre kein Parameter ist null
	 * @post eine neue Nachricht wurde erzeugt und den Empfängern zugestellt
	 */
	public boolean sendMessage(User sender, String[] recipients, String subject, String content)
	{
		User[] recv = new User[recipients.length];
		int i = 0;
		for(String s : recipients)
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
	
	
	/**
	 * Aktualisiert die Tabelle in der Mailbox mit neuen Daten
	 */
	public void updateTable(){
		
		messages.clear();
		
		Mailbox.getInstance().clearTable();
		User curUser = LoginControl.getInstance().getCurrentUser();
		
		messages.addAll(curUser.getMessages());
		for(Message m : messages)
				Mailbox.getInstance().addRow(m.getSender().getUsername(),m.getSubject(),m.getDate());
	}
	
	
	/**
	 * Makiert oder demakiert eine Zeile der Tabelle zum Löschen
	 * @param row Zeile die makiert wird
	 * @return true wenn zeile makiert wurde, false wenn demakiert wurde
	 */
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
	
	/**
	 * Löscht alle makierten Nachrichten
	 */
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
	
	public Message getCurMessage()
	{
		return curMessage;
	}
	
	public void setCurMessage(Message curMessage)
	{
		this.curMessage = curMessage;
	}
}
