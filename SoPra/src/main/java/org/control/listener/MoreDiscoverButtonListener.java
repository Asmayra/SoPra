package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.view.MainScreen;
import org.view.screens.Center.DiscoverExtendedScreen;

/**
 * @author Tim Michels
 */
public class MoreDiscoverButtonListener implements ActionListener{

	/**
	 * Wenn der Button im DiscoverMiniScreen aktiviert wird, soll im Center der ExtendedScreen angezeigt werden
	 */
	public void actionPerformed(ActionEvent arg0) {
		MainScreen.getInstance().showDiscoverExtendedScreen(DiscoverExtendedScreen.getInstance());

	}

}
