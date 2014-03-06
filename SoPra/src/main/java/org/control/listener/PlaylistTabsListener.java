package org.control.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTabbedPane;

import org.control.PlaylistControl;
import org.view.screens.Center.PlaylistExtendedScreen;

public class PlaylistTabsListener extends MouseAdapter{

	PlaylistExtendedScreen extScreen = PlaylistExtendedScreen.getInstance();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){
			JTabbedPane tabPane = (JTabbedPane) e.getComponent();
			int tabNumber = tabPane.indexAtLocation(e.getX(), e.getY());
			extScreen.removePlaylistTab(tabNumber);
		}
	}
}
