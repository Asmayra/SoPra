package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.PlaylistControl;
import org.model.Album;
import org.model.Playlist;
import org.model.User;
import org.view.ContextMenu;
import org.view.screens.Center.PlaylistExtendedScreen;

public class PlaylistTreeListener extends MouseAdapter{

	private Playlist playlistEdit;
	PlaylistControl control = PlaylistControl.getInstance();
	DatabaseControl databaseControl = DatabaseControl.getInstance();
	JTree playlisttree;
	int currentRow;
	int parentRow;
	public PlaylistTreeListener(){
	}
	
	public void mouseClicked(MouseEvent e) {
		try{
			playlistEdit = (Playlist)((DefaultMutableTreeNode)playlisttree.getLastSelectedPathComponent()).getUserObject();
		}catch(NullPointerException exc){}
		catch(ClassCastException exc){}
		playlisttree=(JTree) e.getComponent();
		
		if(e.getButton()==MouseEvent.BUTTON1){
			try{
			parentRow = playlisttree.getRowForPath(playlisttree.getSelectionPath().getParentPath());
			if(parentRow!=-1){
				if(parentRow==0){//Playlist ausgewählt
					Playlist currentPlaylist = (Playlist) ((DefaultMutableTreeNode)playlisttree.getLastSelectedPathComponent()).getUserObject();
					control.showPlaylist(currentPlaylist.getPlaylistId());//TODO +1 da sonst die erste Playlist die favoritenliste sein muss
				}
				else{//Album
					Album currentAlbum = (Album) ((DefaultMutableTreeNode)playlisttree.getLastSelectedPathComponent()).getUserObject();
					control.showAlbum(currentAlbum.getPlaylistId());//TODO s.o.
				}
			}}catch(NullPointerException npe){}
		}
		else{
			maybeShowPopup(e);
		}
        
    }
	
	
	
	private void maybeShowPopup(MouseEvent e) {
    	JTree currentTree = (JTree) e.getSource();
    	String[] content = {"Löschen","Bearbeiten","Neue Playlist","Anhören"};
    	ContextMenuListener delete = new ContextMenuListener();
    	ContextMenuListener edit = new ContextMenuListener();
    	ContextMenuListener add = new ContextMenuListener();
    	ContextMenuListener listen = new ContextMenuListener();
    	ContextMenuListener[] contentlistener = {delete,edit,add,listen};
    	ContextMenu context = new ContextMenu(content, contentlistener);
    	
    	TreeListener listener = new TreeListener();
    	currentTree.getModel().addTreeModelListener(listener);
    	
        context.show(e.getComponent(),e.getX(), e.getY());
        currentTree.setSelectionPath(currentTree.getPathForLocation(e.getX(), e.getY()));
    }
	
	 /**
     * Actionlistener für das Kontextmenü der Playlistübersicht
     * @author Max,Tim
     *
     */
    private class ContextMenuListener implements ActionListener{

		
		
		public void actionPerformed(ActionEvent arg0) {
			
			
			User currentUser = LoginControl.getInstance().getCurrentUser();
			if(arg0.getActionCommand().equals("Löschen")){
				try{
					DefaultMutableTreeNode current = (DefaultMutableTreeNode) playlisttree.getLastSelectedPathComponent();
					if(current.isLeaf()){
						//überprüfen pb Playlist oder Album gelöschte werden soll				
						if(parentRow==0){
							Playlist pL = (Playlist)current.getUserObject();
							//soll die Playliste vom User löschen
							currentUser.getPlaylists().remove(pL);
							databaseControl.update(currentUser);
							databaseControl.delete(pL);
						}
						else{
							Album alb = (Album)current.getUserObject();
							//soll die Playliste vom User löschen
							currentUser.getAlben().remove(alb);
							databaseControl.update(currentUser);
						}
	
						//soll die Playliste aus dem Baum löschen
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) current.getParent();
						parent.remove(parent.getIndex(current));
						playlisttree.updateUI();		
						
					}
					playlisttree.updateUI();
				}catch(NullPointerException npe){}
			}
			else if(arg0.getActionCommand().equals("Bearbeiten")){
				playlistEdit = (Playlist)((DefaultMutableTreeNode)playlisttree.getLastSelectedPathComponent()).getUserObject();
				playlisttree.startEditingAtPath(playlisttree.getSelectionPath());
			}
			else if(arg0.getActionCommand().equals("Anhören")){
				Playlist active = (Playlist)((DefaultMutableTreeNode)playlisttree.getLastSelectedPathComponent()).getUserObject();
				control.setCurrentPlaylist(active);
			}
			else if(arg0.getActionCommand().equals("Neue Playlist")){
				//erstellen der Playlist
				Playlist newPlaylist = new Playlist(currentUser);
				newPlaylist.setName("Neue Playlist");
				DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(newPlaylist);
				((DefaultMutableTreeNode)((DefaultMutableTreeNode)playlisttree.getModel().getRoot()).getFirstChild()).add(treeNode);
				playlisttree.updateUI();
				//fügt die Playlist dem Nutzer hinzu
				currentUser.addPlaylist(newPlaylist);
				try {
					databaseControl.save(newPlaylist);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				databaseControl.update(currentUser);
				
			}
		}
    	
    }
    
    /**
     * Listener für die (Namens-)Änderungen in der Playlistübersicht 
     * @author Max, Tim
     *
     */
    private class TreeListener implements TreeModelListener{

		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode current = (DefaultMutableTreeNode) playlisttree.getLastSelectedPathComponent();
			playlistEdit = (Playlist) current.getUserObject();
			//playlistEdit.setName(current.getUserObject().toString());
			//current.setUserObject(playlistEdit);
			databaseControl.update(playlistEdit);
			PlaylistExtendedScreen pes = PlaylistExtendedScreen.getInstance();
			int tabID = pes.getIndexOfTab(playlistEdit.getPlaylistId());
			pes.setTabName(tabID, playlistEdit.getName());
			
		}

		public void treeNodesInserted(TreeModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void treeNodesRemoved(TreeModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void treeStructureChanged(TreeModelEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
