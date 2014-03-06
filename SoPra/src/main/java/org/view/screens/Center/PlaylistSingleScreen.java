package org.view.screens.Center;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.model.Playlist;
import org.model.Song;


public class PlaylistSingleScreen extends JPanel{
	Playlist playlist;
	private String[] columns = new String[] { "Interpret", "Titel", "Album", "Länge", "Community Rating", "Favor" };
	private Object[][] context = new Object[][] {};
	private DefaultTableModel model;
	private JTable table;
	
	
	public PlaylistSingleScreen(Playlist playlist){
		this.playlist=playlist;
		model = new DefaultTableModel(columns,0){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};	
		addSongsToTable();
		table= new JTable(model);
		this.add(table);
		this.setPreferredSize(new Dimension(500,500));
	}
	
	private void addSongsToTable(){
		LinkedList<Song> songs = (LinkedList<Song>) playlist.getSongs();
		for(int i=0;i<songs.size();i++){
			Song curSong= songs.get(i);
			String[] songData = new String[]{curSong.getInterpret(),curSong.getTitle(),"Album","Playtime","Rating", "TODO!"};
			model.addRow(songData);
		}
	}

	
	
	
}
