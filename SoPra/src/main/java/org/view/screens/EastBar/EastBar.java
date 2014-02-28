package org.view.screens.EastBar;

import javax.swing.JPanel;

/**
 * 
 * @author Tim
 *
 */
public class EastBar extends JPanel{

	private static DiscoverMiniScreen miniScreen;
	
	public EastBar() {
		miniScreen = DiscoverMiniScreen.getInstance();
		this.add(miniScreen);
	}
}
