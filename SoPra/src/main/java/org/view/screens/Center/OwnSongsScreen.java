package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.control.listener.OwnSongsScreenDeleteButtonListener;
import org.control.listener.OwnSongsScreenNewButtonListener;
import org.control.listener.OwnSongsScreenSaveButtonListener;
import org.control.listener.OwnSongsScreenTableMouseListener;
import org.model.Song;
import org.model.User;
import org.view.TicketScreen;

/**
 * 
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class OwnSongsScreen extends JPanel {

	private static OwnSongsScreen instance = null;
	
	private JTable songTable;
	private JButton newButton, deleteButton, saveButton;
	private DefaultTableModel model;
	private JTextField titelTF, albumTF, genreTF;
	
	
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
	
	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		instance = null;
	}
	
	
	private void initGui(){
		this.setLayout(new BorderLayout());
		this.add(initTable(),BorderLayout.NORTH);
		this.add(initButtons(),BorderLayout.CENTER);
		this.add(initEdit(),BorderLayout.SOUTH);
		updateTable();
	}
	
	
	private JComponent initTable(){
		songTable = new JTable();
		model = new DefaultTableModel();
		songTable.setModel(model);
		model.addColumn("Lied");
		model.addColumn("Länge");
		model.addColumn("Genre");
		songTable.addMouseListener(new OwnSongsScreenTableMouseListener());
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
	
	private JComponent initEdit(){
		JPanel editPanel = new JPanel(new BorderLayout());
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(3,1));
		JLabel songTitelLabel = new JLabel("Titel:  ");
		labelPanel.add(songTitelLabel);
		
		JLabel albumLabel = new JLabel("Album:  ");
		labelPanel.add(albumLabel);
		
		JLabel GenreLabel = new JLabel("Genre:  ");
		editPanel.add(labelPanel, BorderLayout.WEST);
		
		JPanel textFieldPanel = new JPanel(new GridLayout(3,1));
		titelTF = new JTextField();
		textFieldPanel.add(titelTF);
		
		albumTF = new JTextField();
		textFieldPanel.add(albumTF);
		
		genreTF = new JTextField();
		textFieldPanel.add(genreTF);
		editPanel.add(textFieldPanel,BorderLayout.CENTER);
		
		saveButton = new JButton("Speichern");
		saveButton.addActionListener(new OwnSongsScreenSaveButtonListener());
		editPanel.add(saveButton, BorderLayout.SOUTH);
		
		return editPanel;
		
		
	}
	
	public void updateTable(){
		User currentUser = LoginControl.getInstance().getCurrentUser();
		
		model.setRowCount(0);
		
		
		for(Song s :currentUser.getOwnSongs()){
			String[] newRow = new String[3];
			newRow[0] = s.getTitle();
			newRow[1] = (s.getPlaytime() / 60) + ":" + (s.getPlaytime() % 60) ;
			if( s.getAlbum()  !=  null )
				newRow[2] = s.getAlbum().toString();
			else
				newRow[2] = "";
			
			model.addRow(newRow);
		}
		
		
	}
	
	public int getSelectedRow(){
		return songTable.getSelectedRow();
	}
	
	public void removeRow(int Row){
		model.removeRow(Row);
	}
	
	public void setTitleTF(String title){
		titelTF.setText(title);
	}
	
	public void setAlbumTF(String album){
		albumTF.setText(album);
	}
	
	public String getTitelTF(){
		return titelTF.getText();
	}
	
	public String getAlbumTF(){
		return albumTF.getText();
				
	}


	public String getGenreTF() {
		return genreTF.getText();
	}


	public void setGenreTF(String genreTF) {
		this.genreTF.setText(genreTF);
	}
	
	
	
}
