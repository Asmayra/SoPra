package org.control.listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
				if(current.isLeaf()){//Prüfung, ob Lieder mit diesem Genre existieren, muss erfolgen!
					Genre currentGenre = (Genre) current.getUserObject();
					Genre parent = (Genre) ((DefaultMutableTreeNode)current.getParent()).getUserObject();
					parent.getSubGenres().remove(currentGenre);
					defMod.removeNodeFromParent(current);
					DatabaseControl.getInstance().update(parent);
					DatabaseControl.getInstance().delete(currentGenre);
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
				
				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newGenre);
				current.add(newNode);
				genreTree.expandPath(new TreePath(current.getPath()));
				genreTree.updateUI();
				try {
					DatabaseControl.getInstance().save(newGenre);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DatabaseControl.getInstance().update(currentGenre);
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
			Genre curGen = (Genre) current.getUserObject();
			current.setUserObject(curGen);
			DatabaseControl.getInstance().update(curGen);
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
