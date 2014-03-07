package org.control.listener;


import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.control.PlaylistControl;
import org.model.Genre;
import org.view.ContextMenu;
import org.view.screens.Center.AdminGenreScreen;
import org.view.screens.Center.PlaylistSingleScreen;

/**
 * Verwaltet die Genreübersicht
 * @author Max, Tim M.
 *
 */
public class PlaylistSingleScreenListener  extends MouseAdapter{
	
	private PlaylistControl control = PlaylistControl.getInstance();
	private PlaylistSingleScreen screen;
	JTable currentTable;
	int row=0;
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1){
			
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
    	String[] content = {"Löschen","Hinzufügen zu.."};
    	ContextMenuListener delete = new ContextMenuListener();
    	ContextMenuListener addTo = new ContextMenuListener();
    	ContextMenuListener[] contentlistener = {delete,addTo};
    	ContextMenu context = new ContextMenu(content, contentlistener);
    	
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
						
				screen.removeSong(row);
				control.removeSong(screen.getPlaylist(), row);
				currentTable.updateUI();
			}
			else if(arg0.getActionCommand().equals("Hinzufügen zu..")){
				
			}
		}
    	
    }
    
}

