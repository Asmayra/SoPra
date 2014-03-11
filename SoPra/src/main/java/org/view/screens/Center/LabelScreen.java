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


/**
 * Zeigt eine Übersicht eines Labels an. Der LabelManager erhält zusätzliche Funktionen.
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class LabelScreen extends JPanel {

	
	
	private JButton applyButton, deleteArtistButton;
	private Label currentLabel;
	private DefaultTableModel artistModel, managerModel;
	private JTable artistTable, managerTable;
	
	
	/**
	 * Konstruktor des LabelScreens
	 * @param currentLabel das Label das angezeigt werden soll
	 */
	public LabelScreen(Label currentLabel){
		this.currentLabel = currentLabel;
		initGui();
	}
	
	
	
	/**
	 * Initialisiert das Gui. Ist der User ein LabelManager kriegt er zusätzliche Optionen angezeigt.
	 * 
	 */
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initHeader(),BorderLayout.NORTH);
		this.add(initTable(),BorderLayout.CENTER);
		updateArtistTable();
		if (LoginControl.getInstance().getCurrentUser().getRights().equals("LabelManager")){
			this.add(initManagerButtons(), BorderLayout.SOUTH);
			
		}
	}
	
	/**
	 * Erzeugt den Header, indem der Labelname und der Name des LabelManager anzeigt wird.
	 * @return das headerPanel
	 */
	private JComponent initHeader(){
		JPanel headerPanel = new JPanel(new BorderLayout());
		JLabel labelLabel = new JLabel(currentLabel.getName() + "                  Manager: " + currentLabel.getManager().getUsername());
		headerPanel.add(labelLabel,  BorderLayout.NORTH);
		
		
		return headerPanel;
	}
	
	
	/**
	 * Erzeugt die Tabelle in der alle Künstler, die in dem aktuellen Label sind, angezeigt werden.
	 * @return
	 */
	private JComponent initTable(){
		
		artistModel = new DefaultTableModel();
		
		artistTable = new JTable(artistModel);
		
		
		artistModel.addColumn("Name");
		
		
		return new JScrollPane(artistTable);
	}
	
	/**
	 * Erzeugt das ButtonPanel mit dem Button Artist löschen
	 * @return das buttonPanel
	 */
	private JComponent initManagerButtons(){
		deleteArtistButton = new JButton("Artist Löschen");
		deleteArtistButton.addActionListener(new LabelScreenArtistDeleteButtonListener(this));
		return deleteArtistButton;
		
	}
	
	/**
	 * Updatet die Tabelleneinträge
	 */
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
