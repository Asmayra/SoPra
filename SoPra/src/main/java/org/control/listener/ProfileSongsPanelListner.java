package org.control.listener;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.model.Playlist;
import org.model.Song;
import org.view.ContextMenu;
import org.view.screens.Center.PlaylistExtendedScreen;
import org.view.screens.Center.PlaylistSingleScreen;
/**
 * Listener für alle Aktionen mit den Profilplaylisten
 * @author Tim, Max
 *
 */
public class ProfileSongsPanelListner extends MouseAdapter {
	private PlaylistExtendedScreen extScreen = PlaylistExtendedScreen.getInstance();
//	private PlaylistControl control = PlaylistControl.getInstance();
	private PlaylistSingleScreen screen;
	JTable currentTable;
	int row=0;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
//			PlaylistControl.setCurrentPlaylist(((PlaylistSingleScreen)e.getComponent().getParent().getParent().getParent()).getPlaylist());
//			currentTable = (JTable) e.getSource();
//			row = currentTable.rowAtPoint(new Point(e.getX(),e.getY()));
//			int songId=(int)currentTable.getModel().getValueAt(row,5);
//			PlaylistControl.searchForSong(songId);
		}
		else{
			currentTable = (JTable) e.getSource();
			row = currentTable.rowAtPoint(new Point(e.getX(),e.getY()));
			if(row != -1){
				System.out.println(row);
				currentTable.setRowSelectionInterval(row, row);
				maybeShowPopup(e);
			}
		}
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    /**
     * Anzeige des Kontextmenus
     * @param e mouseEvent
     */
    private void maybeShowPopup(MouseEvent e) {
//    	screen = (...) currentTable.getParent().getParent().getParent();
    	String[] content = {"leererPlatz"};
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
     * Actionlistener für das Kontextmenü des SongPanels
     * @author Max,Tim
     *
     */
    private class ContextMenuListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(false){//arg0.getActionCommand().equals("Löschen")){
				
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
     * Actionlistener für das Kontextmenü die einzelnen Playlisten
     * @author Max,Tim
     *
     */
    private class SubContextMenuListener implements ActionListener{

    	private int playlistID;
    	
    	public SubContextMenuListener(int playlistID) {
			this.playlistID=playlistID;
		}
    	
    	

		public void actionPerformed(ActionEvent arg0) {
			DatabaseControl dbc = DatabaseControl.getInstance();
			int songID = (int) currentTable.getModel().getValueAt(row, 6);
			Playlist destination = (Playlist) dbc.load(Playlist.class, playlistID);
			Song clicked = (Song) dbc.load(Song.class, songID);
			destination.addSong(clicked);
			dbc.update(destination);
			if(destination.equals("Favorites")){
				LoginControl.getInstance().getCurrentUser().getFavorites().addSong(clicked);
				DatabaseControl.getInstance().update(LoginControl.getInstance().getCurrentUser().getFavorites());
				System.out.println("Das Lied wurde den Favoriten hinzugefügt!");
			}
			int destTabID = extScreen.getIndexOfTab(destination.getPlaylistId());
			if(destTabID!=-1){
				extScreen.setTabScreen(destTabID, new PlaylistSingleScreen(destination));
			}
			currentTable.updateUI();
		}
    	
    }

}
