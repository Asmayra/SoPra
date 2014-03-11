package org.control.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.control.DatabaseControl;
import org.control.PlaylistControl;
import org.control.TicketControl;
import org.model.Playlist;
import org.model.Ticket;
import org.view.screens.WestBar.PlaylistMiniScreen;

public class TicketTreeListener extends MouseAdapter{

	private Playlist playlistEdit=null;
	PlaylistControl control = PlaylistControl.getInstance();
	DatabaseControl databaseControl = DatabaseControl.getInstance();
	JTree playlisttree;
	int currentRow;
	int parentRow;
	public TicketTreeListener(){
	}
	
	public void mouseClicked(MouseEvent e) {

		playlisttree=(JTree) e.getComponent();
		
		if(e.getButton()==MouseEvent.BUTTON1){
			DefaultMutableTreeNode current = (DefaultMutableTreeNode)playlisttree.getLastSelectedPathComponent();
			String thema = "";
			try{thema = current.getUserObject().toString();
				TicketControl control = TicketControl.getInstance();
				if(thema.equals("Account erweitern")||thema.equals("Beschwerde")||thema.equals("Genreanfrage")||thema.equals("Sonstiges")){
					int childCount = current.getChildCount();
					LinkedList<Ticket> ticketsToShow = new LinkedList<Ticket>();
					for(int i=0;i<childCount;i++){
						ticketsToShow.add((Ticket)((DefaultMutableTreeNode)current.getChildAt(i)).getUserObject());
					}
					control.showTicketList(ticketsToShow);
					//control.showTickets(thema);
				}
				else{
					control.showSingleTicket((Ticket)current.getUserObject());
				}
			}catch(NullPointerException npe){}
		}
    }
}
