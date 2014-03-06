package org.control.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;

import org.control.PlaylistControl;

public class PlaylistTreeListener extends MouseAdapter{

	PlaylistControl control = PlaylistControl.getInstance();
	JTree playlisttree;
	
	public PlaylistTreeListener(){
	}
	
	public void mouseClicked(MouseEvent e) {
        playlisttree=(JTree) e.getComponent();
		int parentRow = playlisttree.getRowForPath(playlisttree.getSelectionPath().getParentPath());
		playlisttree.expandRow(parentRow);
		int currentRow = playlisttree.getRowForPath(playlisttree.getSelectionPath());
		if(parentRow!=-1){
			if(parentRow==0){//Playlist ausgewählt
				control.showPlaylist(currentRow);
			}
			else{//Album
				control.showAlbum(currentRow-parentRow);
			}
		}
        
    }
}
