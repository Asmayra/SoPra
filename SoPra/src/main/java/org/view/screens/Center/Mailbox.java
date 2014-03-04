package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.view.LoginScreen;

public class Mailbox extends JPanel{
	
	
	private JTable msgTable;
	private DefaultTableModel msgTableModel;
	
	public static Mailbox instance = null;
	
	private Mailbox(){
	
	}
	
	public static Mailbox getInstance(){
		if(instance == null)
		{
			instance = new Mailbox();
		}
		
		return instance;
	}
	
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initTable(), BorderLayout.NORTH);
		this.add(initButtons(), BorderLayout.CENTER);
		this.setVisible(true);
		
		
	}
	
	
	private JComponent initTable(){
		msgTable = new JTable();
		msgTableModel = new DefaultTableModel(){
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return false;
			}
		};
		msgTable.setModel(msgTableModel);
		msgTableModel.addColumn("Absender");
		msgTableModel.addColumn("Betreff");
		msgTableModel.addColumn("Datum");
		msgTableModel.addColumn("");
		
		
		JScrollPane tableScrollPane = new JScrollPane(msgTable);
		return tableScrollPane;
		
		
	}
	
	private JComponent initButtons(){
		
		JPanel  buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton newButton = new JButton("Neu");
		
		JButton replyButton = new JButton("Antworten");
		
		JButton delButton = new JButton("löschen");
		
		
		buttonPanel.add(newButton);
		buttonPanel.add(replyButton);
		buttonPanel.add(delButton);
		return buttonPanel;
	}

}
