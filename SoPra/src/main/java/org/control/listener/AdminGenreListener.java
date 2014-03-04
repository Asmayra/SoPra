package org.control.listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.view.ContextMenu;
import org.view.screens.Center.AdminGenreScreen;

public class AdminGenreListener  extends MouseAdapter{
	
	private AdminGenreScreen genrescreen;
	
	public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
    	JTree currentTree = (JTree) e.getSource();
    	genrescreen = (AdminGenreScreen) currentTree.getParent();
    	//DefaultMutableTreeNode selected = (DefaultMutableTreeNode) genrescreen.getGenretree().getComponentAt(e.getXOnScreen(), e.getYOnScreen());
    	String[] content = {"Löschen","Bearbeiten","Hinzufügen"};
    	ContextMenuListener delete = new ContextMenuListener();
    	ContextMenuListener edit = new ContextMenuListener();
    	ContextMenuListener addGen = new ContextMenuListener(); 
    	ContextMenuListener[] contentlistener = {delete,edit,addGen};
    	ContextMenu context = new ContextMenu(content, contentlistener);
        if (e.isPopupTrigger()) {
            context.show(e.getComponent(),e.getX(), e.getY());
            currentTree.setSelectionPath(currentTree.getPathForLocation(e.getX(), e.getY()));
        }
        
    }
    
    private void saveToDatabase(){
    	
    }
    
    private void loadFromDatabase(){
    	
    }
    
   
    
    private class ContextMenuListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("Löschen")){
				JTree genreTree = genrescreen.getGenretree();
				DefaultTreeModel defMod = (DefaultTreeModel) genreTree.getModel();
				DefaultMutableTreeNode current = (DefaultMutableTreeNode) genrescreen.getGenretree().getLastSelectedPathComponent();
				if(current.isLeaf()){
					defMod.removeNodeFromParent(current);
					saveToDatabase();
				}
			}
			else if(arg0.getActionCommand().equals("Bearbeiten")){
				JTree genreTree = genrescreen.getGenretree();
				DefaultTreeModel defMod = (DefaultTreeModel) genreTree.getModel();
				DefaultMutableTreeNode current = (DefaultMutableTreeNode) genrescreen.getGenretree().getLastSelectedPathComponent();
				
				
			}
			else if(arg0.getActionCommand().equals("Hinzufügen")){
				JTree genreTree = genrescreen.getGenretree();
				DefaultTreeModel defMod = (DefaultTreeModel) genreTree.getModel();
				DefaultMutableTreeNode current = (DefaultMutableTreeNode) genrescreen.getGenretree().getLastSelectedPathComponent();
				
				
			}
		}
    	
    }
	
}
