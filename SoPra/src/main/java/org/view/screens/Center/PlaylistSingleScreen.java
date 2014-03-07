package org.view.screens.Center;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.control.LoginControl;
import org.control.listener.PlaylistSingleScreenListener;
import org.model.Playlist;
import org.model.Song;


public class PlaylistSingleScreen extends JPanel{
	private Playlist playlist;
	private String[] columns = new String[] { "Interpret", "Titel", "Album", "Länge", "Community Rating", "Favor" };
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	
	
	public PlaylistSingleScreen(Playlist playlist){
		this.playlist=playlist;
		model = new DefaultTableModel(columns,0){
			@Override
			public Class getColumnClass(int col) {
				if (col == 5)
					return Boolean.class;
				else
					return String.class;
			}
			@Override
			public boolean isCellEditable(int row, int column){
				return column==5;
			}
		};	
		addSongsToTable();
		table= new JTable(model);
		table.setShowGrid(false);
		table.addMouseListener(new PlaylistSingleScreenListener());
		scroll = new JScrollPane(table);
		this.add(scroll);
		this.setPreferredSize(new Dimension(500,500));
	}
	
	private void addSongsToTable(){
		LinkedList<Song> songs = (LinkedList<Song>) playlist.getSongs();
		//model.
		Playlist favorites = LoginControl.getInstance().getCurrentUser().getFavorites();
		for(int i=0;i<songs.size();i++){
			Song curSong= songs.get(i);
			String interpret="Kein Interpret";
			String title="Kein Titel";
			String album="Kein Album";
			int playtime=0;
			double rating=0.0;
			
			try{
				interpret = curSong.getInterpret();
			}catch(NullPointerException exc){}
			try{
				title = curSong.getTitle();
			}catch(NullPointerException exc){}
			try{
				album = curSong.getAlbum().getName();
			}catch(NullPointerException exc){}
			try{
				playtime = curSong.getPlaytime();
			}catch(NullPointerException exc){}
			try{
				rating = curSong.getVrgRating();
			}catch(NullPointerException exc){}
			
			boolean favored;
			if (favorites.contains(songs.get(i))) {
				favored = true;
			} else {
				favored = false;
			}
			
			
			Object[] songData = new Object[]{interpret,title,album,playtime,rating,favored};
			model.addRow(songData);
		}
	}

	public int getPlaylistID(){
		return playlist.getPlaylistId();
	}
	
	public void removeSong(int row){
		model.removeRow(row);
	}
	
	public Playlist getPlaylist(){
		return playlist;
	}
	
}
