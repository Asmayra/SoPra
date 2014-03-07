package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.control.listener.OwnSongsScreenDeleteButtonListener;
import org.control.listener.OwnSongsScreenNewButtonListener;
import org.model.Song;
import org.model.User;
import org.view.TicketScreen;

public class OwnSongsScreen extends JPanel {

	private static OwnSongsScreen instance = null;
	
	private JTable songTable;
	private JButton newButton, deleteButton;
	private DefaultTableModel model;
	
	
	private OwnSongsScreen()
	{
		initGui();
	}
	
	
	public static OwnSongsScreen getInstance(){
		if(instance == null)
		{
			instance = new OwnSongsScreen();
		}
		
		return instance;
	}
	
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initTable(),BorderLayout.NORTH);
		this.add(initButtons(),BorderLayout.CENTER);
		updateTable();
	}
	
	
	private JComponent initTable(){
		songTable = new JTable();
		model = new DefaultTableModel();
		songTable.setModel(model);
		model.addColumn("Lied");
		model.addColumn("Länge");
//		model.addColumn("Label");
		
		JScrollPane jsp = new JScrollPane(songTable);
		return jsp;
		
	}
	
	private JComponent initButtons(){
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		newButton = new JButton("Neu");
		newButton.addActionListener(new OwnSongsScreenNewButtonListener());
		buttonPanel.add(newButton);
		
		deleteButton = new JButton("Löschen");
		deleteButton.addActionListener(new OwnSongsScreenDeleteButtonListener());
		buttonPanel.add(deleteButton);
		
		
		return buttonPanel;
		
	}
	
	public void updateTable(){
		User currentUser = LoginControl.getInstance().getCurrentUser();
		
		model.setRowCount(0);
		
		
		for(Song s :currentUser.getOwnSongs()){
			String[] newRow = new String[2];
			newRow[0] = s.getTitle();
			newRow[1] = (s.getPlaytime() / 60) + ":" + (s.getPlaytime() % 60) ;
			
			model.addRow(newRow);
		}
		
		
	}
	
	public int getSelectedRow(){
		return songTable.getSelectedRow();
	}
	
	public void removeRow(int Row){
		model.removeRow(Row);
	}
	
	
}
