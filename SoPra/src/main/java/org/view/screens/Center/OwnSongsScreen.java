package org.view.screens.Center;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.control.listener.OwnSongsDeleteButtonListener;
import org.control.listener.OwnSongsSaveButtonListener;
import org.view.TicketScreen;

public class OwnSongsScreen extends JPanel {

	private static OwnSongsScreen instance = null;
	
	private JTable songTable;
	private JButton saveButton, deleteButton;
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
		
	}
	
	
	private JComponent initTable(){
		songTable = new JTable();
		model = new DefaultTableModel();
		songTable.setModel(model);
		model.addColumn("Lied");
		model.addColumn("Länge");
		model.addColumn("Label");
		
		JScrollPane jsp = new JScrollPane(songTable);
		return jsp;
		
	}
	
	private JComponent initButtons(){
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		saveButton = new JButton("Speichern");
		saveButton.addActionListener(new OwnSongsSaveButtonListener());
		buttonPanel.add(saveButton);
		
		deleteButton = new JButton("Löschen");
		deleteButton.addActionListener(new OwnSongsDeleteButtonListener());
		buttonPanel.add(deleteButton);
		
		
		return buttonPanel;
		
	}
	
	
}
