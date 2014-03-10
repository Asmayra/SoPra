package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class PlaylistTreeListener extends MouseAdapter{

	PlaylistControl control = PlaylistControl.getInstance();
	DatabaseControl databaseControl = DatabaseControl.getInstance();
	JTree playlisttree;
	int currentRow;
	int parentRow;
	public PlaylistTreeListener(){
	}
	
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton()==MouseEvent.BUTTON1){
			 playlisttree=(JTree) e.getComponent();
				parentRow = playlisttree.getRowForPath(playlisttree.getSelectionPath().getParentPath());
				playlisttree.expandRow(parentRow);
				currentRow = playlisttree.getRowForPath(playlisttree.getSelectionPath());
				if(parentRow!=-1){
					if(parentRow==0){//Playlist ausgewählt
						control.showPlaylist(currentRow);//TODO +1 da sonst die erste Playlist die favoritenliste sein muss
					}
					else{//Album
						control.showAlbum(currentRow-parentRow);//TODO s.o.
					}
				}
		}
		else if(e.getButton()==MouseEvent.BUTTON3){
			maybeShowPopup(e);
		}
        
    }
	private void maybeShowPopup(MouseEvent e) {
    	JTree currentTree = (JTree) e.getSource();
//    	playlistscreen = (AdminPlaylistScreen) currentTree.getParent();
    	//DefaultMutableTreeNode selected = (DefaultMutableTreeNode) playlistscreen.getPlaylisttree().getComponentAt(e.getXOnScreen(), e.getYOnScreen());
    	String[] content = {"Löschen","neue Playlist"};
    	ContextMenuListener delete = new ContextMenuListener();
    	ContextMenuListener add = new ContextMenuListener();
    	ContextMenuListener[] contentlistener = {delete,add};
    	ContextMenu context = new ContextMenu(content, contentlistener);
    	
    	TreeListener listener = new TreeListener();
    	currentTree.getModel().addTreeModelListener(listener);
    	
        if (e.isPopupTrigger()) {
            context.show(e.getComponent(),e.getX(), e.getY());
            currentTree.setSelectionPath(currentTree.getPathForLocation(e.getX(), e.getY()));
        }
        
    }
	
	 /**
     * Actionlistener für das Kontextmenü der Playlistübersicht
     * @author Max,Tim
     *
     */
    private class ContextMenuListener implements ActionListener{

		
		
		public void actionPerformed(ActionEvent arg0) {
			
			DefaultMutableTreeNode current = (DefaultMutableTreeNode) playlisttree.getLastSelectedPathComponent();
			User currentUser = LoginControl.getInstance().getCurrentUser();
			if(arg0.getActionCommand().equals("Löschen")){
				if(current.isLeaf()){
					//überprüfen pb Playlist oder Album gelöschte werden soll				
					if(parentRow==0){
						Playlist pL = (Playlist)current.getUserObject();
						//soll die Playliste vom User löschen
						control.deletePlaylist(pL.getPlaylistId());//TODO vllt +1

					}
					else{
						Album alb = (Album)current.getUserObject();
						//soll die Playliste vom User löschen
						control.deletePlaylist(alb.getPlaylistId());//TODO vllt +1
					}

					//soll die Playliste aus dem Baum löschen
					playlisttree.remove(current.getParent().getIndex(current));
					
					playlisttree.updateUI();
				}
				playlisttree.updateUI();
			}
			else if(arg0.getActionCommand().equals("neue Playlist")){
				//erstellen der Playlist
				Playlist newPlaylist = new Playlist();
				newPlaylist.setName("Neue Playlist");
				DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(newPlaylist);
				((DefaultMutableTreeNode)((DefaultMutableTreeNode)playlisttree.getModel().getRoot()).getFirstChild()).add(treeNode);
				playlisttree.updateUI();
				//fügt die Playlist dem Nutzer hinzu
				currentUser.getPlaylists().add(newPlaylist);
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
			int parentRow = playlisttree.getRowForPath(playlisttree.getSelectionPath().getParentPath());
			playlisttree.expandRow(parentRow);
			int currentRow = playlisttree.getRowForPath(playlisttree.getSelectionPath());
			Playlist currentParentPlaylist = (Playlist) ((DefaultMutableTreeNode) current.getParent()).getUserObject();
			Playlist neuePlaylist = new Playlist();
			neuePlaylist.setName(current.getUserObject().toString());
			current.setUserObject(neuePlaylist);
			
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
