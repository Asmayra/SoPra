package org.control.listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.model.Genre;
import org.view.ContextMenu;
import org.view.screens.Center.AdminGenreScreen;

/**
 * Verwaltet die Genreübersicht
 * @author Max, Tim M.
 *
 */
public class AdminGenreListener  extends MouseAdapter{
	
	private AdminGenreScreen genrescreen;
	
	public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    /**
     * Anzeige des Kontextmenus
     * @param e
     */
    private void maybeShowPopup(MouseEvent e) {
    	JTree currentTree = (JTree) e.getSource();
    	genrescreen = (AdminGenreScreen) currentTree.getParent();
    	//DefaultMutableTreeNode selected = (DefaultMutableTreeNode) genrescreen.getGenretree().getComponentAt(e.getXOnScreen(), e.getYOnScreen());
    	String[] content = {"Löschen","Hinzufügen","Speichern"};
    	ContextMenuListener delete = new ContextMenuListener();
    	ContextMenuListener save = new ContextMenuListener();
    	ContextMenuListener addGen = new ContextMenuListener(); 
    	ContextMenuListener[] contentlistener = {delete,addGen,save};
    	ContextMenu context = new ContextMenu(content, contentlistener);
    	
    	TreeListener listener = new TreeListener();
    	currentTree.getModel().addTreeModelListener(listener);
    	
        if (e.isPopupTrigger()) {
            context.show(e.getComponent(),e.getX(), e.getY());
            currentTree.setSelectionPath(currentTree.getPathForLocation(e.getX(), e.getY()));
        }
        
    }
    
    private void saveToDatabase(){
    	
    }
    
    private void loadFromDatabase(){
    	
    }
    
    /**
     * Actionlistener für das Kontextmenü der Genreübersicht
     * @author Max,Tim
     *
     */
    private class ContextMenuListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("Löschen")){
				JTree genreTree = genrescreen.getGenretree();
				DefaultTreeModel defMod = (DefaultTreeModel) genreTree.getModel();
				DefaultMutableTreeNode current = (DefaultMutableTreeNode) genrescreen.getGenretree().getLastSelectedPathComponent();
				Genre currentGenre = (Genre) current.getUserObject();
				currentGenre.getParent().getSubGenres().remove(currentGenre);
				
				if(current.isLeaf()){//Prüfung, ob Lieder mit diesem Genre existieren, muss erfolgen!
					defMod.removeNodeFromParent(current);
				}
				
				genreTree.updateUI();
			}
			else if(arg0.getActionCommand().equals("Speichern")){
				Genre testGenre = genrescreen.getRoot();
				saveToDatabase();		
			}
			else if(arg0.getActionCommand().equals("Hinzufügen")){
				JTree genreTree = genrescreen.getGenretree();
				DefaultMutableTreeNode current = (DefaultMutableTreeNode) genreTree.getLastSelectedPathComponent();
				Genre newGenre = new Genre();
				newGenre.setName("NEU");
				
				Genre currentGenre = (Genre) current.getUserObject();
				currentGenre.addSubGenre(newGenre);
				newGenre.setParent(currentGenre);
				
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newGenre);
				current.add(newNode);
				genreTree.expandPath(new TreePath(current.getPath()));
				//genreTree.setEditable(true);
				//genreTree.updateUI();
				//genreTree.startEditingAtPath(new TreePath(newNode.getPath()));
				genreTree.updateUI();
			}
		}
    	
    }
    
    /**
     * Listener für die (Namens-)Änderungen in der Genreübersicht 
     * @author Max, Tim
     *
     */
    private class TreeListener implements TreeModelListener{

		public void treeNodesChanged(TreeModelEvent e) {
			JTree genreTree = genrescreen.getGenretree();
			DefaultMutableTreeNode current = (DefaultMutableTreeNode) genreTree.getLastSelectedPathComponent();
			int parentRow = genreTree.getRowForPath(genreTree.getSelectionPath().getParentPath());
			genreTree.expandRow(parentRow);
			int currentRow = genreTree.getRowForPath(genreTree.getSelectionPath());
			Genre currentParentGenre = (Genre) ((DefaultMutableTreeNode) current.getParent()).getUserObject();
			Genre neuesGenre = currentParentGenre.getSubGenres().get(currentRow-parentRow-1);
			neuesGenre.setName(current.getUserObject().toString());
			current.setUserObject(neuesGenre);
			
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
