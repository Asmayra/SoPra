package org.control.listener;


import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.PlaylistControl;
import org.model.Genre;
import org.model.Playlist;
import org.model.Song;
import org.view.ContextMenu;
import org.view.screens.Center.AdminGenreScreen;
import org.view.screens.Center.PlaylistExtendedScreen;
import org.view.screens.Center.PlaylistSingleScreen;
import org.view.screens.Southbar.MusicPlayer;

/**
 * Verwaltet die Genreübersicht
 * @author Max, Tim M.
 *
 */
public class PlaylistSingleScreenListener  extends MouseAdapter{
	
	private PlaylistExtendedScreen extScreen = PlaylistExtendedScreen.getInstance();
	private PlaylistControl control = PlaylistControl.getInstance();
	private PlaylistSingleScreen screen;
	JTable currentTable;
	int row=0;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			PlaylistControl.setCurrentPlaylist(((PlaylistSingleScreen)e.getComponent().getParent().getParent().getParent()).getPlaylist());
			currentTable = (JTable) e.getSource();
			row = currentTable.rowAtPoint(new Point(e.getX(),e.getY()));
			int songId=(int)currentTable.getModel().getValueAt(row,5);
			PlaylistControl.searchForSong(songId);
		}
		else{
			currentTable = (JTable) e.getSource();
			row = currentTable.rowAtPoint(new Point(e.getX(),e.getY()));
			currentTable.setRowSelectionInterval(row, row);
			maybeShowPopup(e);
		}
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    /**
     * Anzeige des Kontextmenus
     * @param e
     */
    private void maybeShowPopup(MouseEvent e) {
    	screen = (PlaylistSingleScreen) currentTable.getParent().getParent().getParent();
    	//DefaultMutableTreeNode selected = (DefaultMutableTreeNode) genrescreen.getGenretree().getComponentAt(e.getXOnScreen(), e.getYOnScreen());
    	String[] content = {"Löschen"};
    	ContextMenuListener delete = new ContextMenuListener();
    	ContextMenuListener[] contentlistener = {delete};
    	String submenuname = "Hinzufügen zu..";
    	ArrayList<Playlist> playlists = (ArrayList<Playlist>) LoginControl.getInstance().getCurrentUser().getPlaylists();
    	String[] playlistnames = new String[playlists.size()];
    	SubContextMenuListener[] sublistener = new SubContextMenuListener[playlistnames.length];
    	for (int i = 0; i < sublistener.length; i++) {
    		Playlist curList = playlists.get(i);
    		playlistnames[i]=curList.getName();
			sublistener[i]=new SubContextMenuListener(playlists.get(i).getPlaylistId());
		}
    	
    	ContextMenu context = new ContextMenu(content, contentlistener, submenuname, playlistnames, sublistener);
    	
        if (e.isPopupTrigger()) {
            context.show(e.getComponent(),e.getX(), e.getY());
        }
        
    }
    
    
    /**
     * Actionlistener für das Kontextmenü der Genreübersicht
     * @author Max,Tim
     *
     */
    private class ContextMenuListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("Löschen")){
				
				Playlist pL = screen.getPlaylist();
				int SongID = screen.getSongIDfromRow(row);
				DatabaseControl dbc = DatabaseControl.getInstance();
				Song chosen = (Song) dbc.load(Song.class,SongID);
				pL.deleteSong(chosen);
				dbc.update(pL);
				
				screen.removeSong(row);
				currentTable.updateUI();
			}
			else if(arg0.getActionCommand().equals("Hinzufügen zu..")){
				
			}
		}
    	
    }
    
    /**
     * Actionlistener für das Kontextmenü der Genreübersicht
     * @author Max,Tim
     *
     */
    private class SubContextMenuListener implements ActionListener{

    	private int playlistID;
    	
    	public SubContextMenuListener(int playlistID) {
			this.playlistID=playlistID;
		}
    	
    	
		public void actionPerformed(ActionEvent arg0) {
			Playlist destination = (Playlist) DatabaseControl.getInstance().load(Playlist.class, playlistID);
			Song clicked = screen.getPlaylist().getSongs().get(row);
			destination.addSong(clicked);
			DatabaseControl.getInstance().update(destination);
			if(destination.equals("Favorites")){
				currentTable.getModel().setValueAt(true, row, 5);
			}
			
			int destTabID = extScreen.getIndexOfTab(destination.getPlaylistId());
			if(destTabID!=-1){
				extScreen.setTabScreen(destTabID, new PlaylistSingleScreen(destination));
			}
			
			currentTable.updateUI();
		}
    	
    }
    
}

