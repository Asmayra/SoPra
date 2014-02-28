package org.view.screens.EastBar;

import javax.swing.JPanel;

/**
 * 
 * @author Tim
 *
 */
public class DiscoverMiniScreen extends JPanel {
	
	private static DiscoverMiniScreen miniScreen;
	
	public static DiscoverMiniScreen getInstance(){
		if(miniScreen == null){
			miniScreen = new DiscoverMiniScreen();
		}
		return miniScreen;
	}
	private DiscoverMiniScreen() {

	}

	public void setDiscoverOne(JPanel discOne){
		this.add(discOne);
	}
}
