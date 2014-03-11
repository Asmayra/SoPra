package org.control.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

import org.control.PlaylistControl;
import org.view.screens.Center.PlaylistExtendedScreen;
/**
 * Schlatet die tabs weiter
 * @author Tim, Max
 *
 */
public class PlaylistTabsListener extends MouseAdapter{

	PlaylistExtendedScreen extScreen;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			extScreen = PlaylistExtendedScreen.getInstance();
			JTabbedPane tabPane = (JTabbedPane) e.getComponent();
			int tabNumber = tabPane.indexAtLocation(e.getX(), e.getY());
			extScreen.removePlaylistTab(tabNumber);
		}
	}
}
