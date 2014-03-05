package org.view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.model.Album;
import org.model.Song;
import org.model.User;


public class ProfileSongsPanel extends JPanel {
	
	private String[] columns = new String[] {"Interpret","Title","Album","Länge","Community Rating","Favor"};
	private Object[][] songs;
	private DefaultTableModel model;
	private JTable table;
	
	public ProfileSongsPanel(User u){
		
		model = new DefaultTableModel(songs, columns){
			 @Override
			   public boolean isCellEditable(int row, int column) {
			       return column == 5;
			   }
		};
		table.setFillsViewportHeight(true);
		table = new JTable();
		table.setModel(model);
		List<Song> songs = u.getOwnSongs();
		for (int i = 0; i< u.getOwnSongs().size();i++){
			String interpret = u.getUsername();
			String title = songs.get(i).getTitle();
			boolean favored;
			if(u.getFavorites().contains(songs.get(i))){
				favored = true;
			} else {
				favored = false;
			}
			String album;
			if (songs.get(i).getAlbum()==null){
				album = " - ";
			} else {
				album = songs.get(i).getAlbum().getName();
			}
			String playtime = ((Integer) songs.get(i).getPlaytime()).toString();
			String comRating =((Integer) songs.get(i).getVrgRating()).toString();
			Object[] entry = {interpret,title,album,playtime,comRating,favored};
			model.addRow(entry);
		}
		
		
	}

}
