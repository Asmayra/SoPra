package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.control.DatabaseControl;
import org.control.listener.AdminLabelScreenCreateNewLabelButtonListener;
import org.control.listener.AdminLabelScreenDeleteLabelButtonListener;
import org.model.Label;

/**
 * 
 * @author Michael Pfennings, Matthias Schoenke
 *
 */
public class AdminLabelScreen extends JPanel {
	
	private static AdminLabelScreen instance = null;
	
	private JButton createNewLabel, deleteLabel;
	private JTable labelTable;
	private DefaultTableModel model;
	private JTextField nameTF, managerTF;
	
	private AdminLabelScreen(){
		initGui();
	}
	
	public static AdminLabelScreen getInstance(){
		if (instance == null)
			instance = new AdminLabelScreen();
		return instance;
	}
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initNewLabel(),BorderLayout.NORTH);
		this.add(initTablePanel(),BorderLayout.CENTER);
		updateTable();
		
	}
	
	private JComponent initNewLabel(){
		JPanel headerPanel = new JPanel(new BorderLayout());
		
		JPanel centerHeader = new JPanel(new GridLayout(2,1));
		JLabel labelName = new JLabel("Labelname: ");
		centerHeader.add(labelName);
		JLabel labelManager = new JLabel("Labelmanager: ");
		centerHeader.add(labelManager);
		headerPanel.add(centerHeader, BorderLayout.WEST);
		
		JPanel westPanel = new JPanel(new GridLayout(2,1));
		nameTF = new JTextField();
		westPanel.add(nameTF);
		managerTF = new JTextField();
		westPanel.add(managerTF);
		headerPanel.add(westPanel, BorderLayout.CENTER);
		
		createNewLabel = new JButton("Neues Label anlegen");
		createNewLabel.addActionListener(new AdminLabelScreenCreateNewLabelButtonListener());
		headerPanel.add(createNewLabel, BorderLayout.SOUTH);
		
		return headerPanel;
	}
	
	private JComponent initTablePanel(){
		JPanel tablePanel = new JPanel(new BorderLayout());
		labelTable = new JTable();
		
		model = new DefaultTableModel();
		
		labelTable.setModel(model);
		model.addColumn("Labelname");
		model.addColumn("Labelmanager");
		tablePanel.add(labelTable,BorderLayout.CENTER);
		
		deleteLabel = new JButton("Löschen");
		deleteLabel.addActionListener(new AdminLabelScreenDeleteLabelButtonListener());
		tablePanel.add(deleteLabel,BorderLayout.SOUTH);
		
		return tablePanel;
	}
	
	public void updateTable(){
		List<Label> labelList = (List<Label>) DatabaseControl.getInstance().getTableContent("Label");
		String[] temp = new String[2];
		model.setRowCount(0);
		
		for (Label l: labelList){
			if( l.getManager() != null && l.getManager().getLabel() != null && l.getManager().getLabel().equals(l) )
			{
				temp[0] = l.getName();
				temp[1] = l.getManager().getUsername();
				model.addRow(temp);
			}
		}
	}
	
	
	
	
	public int getSelectedRow(){
		return labelTable.getSelectedRow();
	}
	
	public String getLabelManager()
	{
		return (String) labelTable.getValueAt(labelTable.getSelectedRow(), 1);
	}
	
	public void deleteRow(int row){
		model.removeRow(row);
	}

	public String getNameTF() {
		return nameTF.getText();
	}

	public void setNameTF(String nameTF) {
		this.nameTF.setText(nameTF);
	}

	public String getManagerTF() {
		return managerTF.getText();
	}

	public void setManagerTF(String managerTF) {
		this.managerTF.setText(managerTF);
	}
	
}
