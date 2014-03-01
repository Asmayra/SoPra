package org.view.screens.EastBar;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Tim Lange
 *
 */
public class DiscoverMiniScreen extends JPanel {
	
	private static DiscoverMiniScreen miniScreen;
	private JPanel discoverOnePan, discoverTwoPan, discoverThreePan;
	
	public static DiscoverMiniScreen getInstance(){
		if(miniScreen == null){
			miniScreen = new DiscoverMiniScreen();
		}
		return miniScreen;
	}
	private DiscoverMiniScreen() {
		this.setPreferredSize(new Dimension(200, 500));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
	}

	public void setDiscoverOne(JPanel discOne){
		this.discoverOnePan = discOne;
		this.updateDiscover();
	}
	
	public void setDiscoverTwo(JPanel discTwo){
		this.discoverTwoPan = discTwo;
		this.updateDiscover();
	}
	
	public void setDiscoverThree(JPanel discThree){
		this.discoverThreePan = discThree;
		this.updateDiscover();
	}
	
	private void updateDiscover(){
		this.removeAll();
		if(this.discoverOnePan != null){
			this.add(this.discoverOnePan);
		}
		if(this.discoverTwoPan != null){
			this.add(this.discoverTwoPan);
		}
		if(this.discoverThreePan != null){
			this.add(this.discoverThreePan);
		}
		
		this.validate();
		this.repaint();
	}
}
