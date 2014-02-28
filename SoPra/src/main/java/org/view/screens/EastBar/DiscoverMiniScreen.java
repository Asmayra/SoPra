package org.view.screens.EastBar;

import javax.swing.BoxLayout;
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
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void setDiscoverOne(JPanel discOne){
		this.add(discOne);
	}
	
	public void setDiscoverTwo(JPanel discTwo){
		this.add(discTwo);
	}
	
	public void setDiscoverThree(JPanel discThree){
		this.add(discThree);
	}
}
