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
import org.control.listener.LabelScreenApplyButtonListener;
import org.control.listener.LabelScreenArtistDeleteButtonListener;
import org.control.listener.LabelScreenManagerDeleteButtonListener;
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
		if (LoginControl.getInstance().getCurrentUser().getRights().equals("Manager")){
			this.add(initManagerButtons(), BorderLayout.SOUTH);
			updateManagerTable();
		}
	}
	
	
	private JComponent initHeader(){
		JPanel headerPanel = new JPanel(new BorderLayout());
		JLabel labelLabel = new JLabel(currentLabel.getName());
		headerPanel.add(labelLabel,  BorderLayout.NORTH);
		
		if (LoginControl.getInstance().getCurrentUser().getRights().equals("Artist")){
			applyButton = new JButton("Bewerben");
			applyButton.addActionListener(new LabelScreenApplyButtonListener());
			headerPanel.add(applyButton, BorderLayout.EAST);
		}
			
		return headerPanel;
	}
	
	
	private JComponent initTable(){
		JPanel tablePanel = new JPanel(new BorderLayout());
		JScrollPane artistScrollPane = new JScrollPane();
		artistTable = new JTable(artistModel);
		artistModel.addColumn("Name");
		artistScrollPane.add(artistTable);
		tablePanel.add(artistScrollPane, BorderLayout.NORTH);
		
		if (LoginControl.getInstance().getCurrentUser().getRights().equals("Manager")){
			JScrollPane managerScrollPane = new JScrollPane();
			managerTable = new JTable(managerModel);
			managerModel.addColumn("Name");
			managerScrollPane.add(managerTable);
			tablePanel.add(managerScrollPane, BorderLayout.CENTER);
		}
		
		return tablePanel;
	}
	
	private JComponent initManagerButtons(){
		JPanel buttonPanel = new JPanel(new FlowLayout());
		deleteArtistButton = new JButton("Artist Löschen");
		deleteArtistButton.addActionListener(new LabelScreenArtistDeleteButtonListener(this));
		buttonPanel.add(deleteArtistButton);
		
		deleteManagerButton = new JButton("Artist löschen");
		deleteManagerButton.addActionListener(new LabelScreenManagerDeleteButtonListener());
		buttonPanel.add(deleteManagerButton);
		return buttonPanel;
	}
	
	
	private void updateArtistTable(){
		String[] temp = new String[1];
		ArrayList<User> artistList = (ArrayList<User>) currentLabel.getArtists();
		for (User a : artistList){
			temp[1]= a.getUsername();
			artistModel.addRow(temp);
		}
	}
	
	private void updateManagerTable(){
		String[] temp = new String[1];
		ArrayList<User> artistList = (ArrayList<User>) currentLabel.getManagers();
		for (User a : artistList){
			temp[1]= a.getUsername();
			managerModel.addRow(temp);
		}
	}
	
	public int getSelectedRowArtistTable(){
		return artistTable.getSelectedRow();
	}
	
	public int getSelectedRowManagerTable(){
		return managerTable.getSelectedRow();
	}
	
	public void deleteRowArtistTable(int row){
		artistModel.removeRow(row);
	}
	
	public void deleteRowManagerTable(int row){
		managerModel.removeRow(row);
	}

	public Label getCurrentLabel() {
		return currentLabel;
	}
	
	public String getRowUserNameArtist(int row){
		return artistModel.getValueAt(row, 0).toString();
	}
	
	public String getRowUserNameManager(int row){
		return managerModel.getValueAt(row, 0).toString();
	}
	
	
}
