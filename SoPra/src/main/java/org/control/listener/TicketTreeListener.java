package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.PlaylistControl;
import org.control.TicketControl;
import org.model.Album;
import org.model.Playlist;
import org.model.Ticket;
import org.model.User;
import org.view.ContextMenu;
import org.view.screens.Center.PlaylistExtendedScreen;

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
			String thema = current.getUserObject().toString();
			TicketControl control = TicketControl.getInstance();
			if(thema.equals("Account erweitern")||thema.equals("Beschwerden")||thema.equals("Genreanfrage")||thema.equals("Sonstiges")){
				control.showTickets(thema);
			}
			else{
				control.showSingleTicket((Ticket)current.getUserObject());
			}
			
		}
    }
}
