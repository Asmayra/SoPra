package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.control.listener.LabelScreenArtistDeleteButtonListener;
import org.model.Label;
import org.model.User;

public class LabelScreen extends JPanel {

	
	
	private JButton applyButton, deleteArtistButton, deleteManagerButton;
	private Label currentLabel;
	private DefaultTableModel artistModel, managerModel;
	private JTable artistTable, managerTable;
	
	public LabelScreen(Label currentLabel){
		this.currentLabel = currentLabel;
		initGui();
	}
	
	
	
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initHeader(),BorderLayout.NORTH);
		this.add(initTable(),BorderLayout.CENTER);
		updateArtistTable();
		if (LoginControl.getInstance().getCurrentUser().getRights().equals("LabelManager")){
			this.add(initManagerButtons(), BorderLayout.SOUTH);
			
		}
	}
	
	
	private JComponent initHeader(){
		JPanel headerPanel = new JPanel(new BorderLayout());
		JLabel labelLabel = new JLabel(currentLabel.getName() + "                  Manager: " + currentLabel.getManager().getUsername());
		headerPanel.add(labelLabel,  BorderLayout.NORTH);
		
		
		return headerPanel;
	}
	
	
	private JComponent initTable(){
		
		artistModel = new DefaultTableModel();
		
		artistTable = new JTable(artistModel);
		
		
		artistModel.addColumn("Name");
		
		
		return new JScrollPane(artistTable);
	}
	
	private JComponent initManagerButtons(){
		deleteArtistButton = new JButton("Artist Löschen");
		deleteArtistButton.addActionListener(new LabelScreenArtistDeleteButtonListener(this));
		return deleteArtistButton;
		
	}
	
	
	private void updateArtistTable(){
		String[] temp = new String[1];
		ArrayList<User> artistList = (ArrayList<User>) currentLabel.getArtists();
		for (User a : artistList){
			temp[0]= a.getUsername();
			artistModel.addRow(temp);
		}
	}
	
	
	public int getSelectedRowArtistTable(){
		return artistTable.getSelectedRow();
	}
	

	public void deleteRowArtistTable(int row){
		artistModel.removeRow(row);
	}
	

	public Label getCurrentLabel() {
		return currentLabel;
	}
	
	public String getRowUserNameArtist(int row){
		return artistModel.getValueAt(row, 0).toString();
	}
	
	
}
