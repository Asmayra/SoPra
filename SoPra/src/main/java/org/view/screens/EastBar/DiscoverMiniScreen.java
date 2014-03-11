package org.view.screens.EastBar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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
	
	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		miniScreen = null;
	}
	
	private DiscoverMiniScreen() {
		this.setLayout(new GridBagLayout());
		
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
		GridBagConstraints c = new GridBagConstraints();
		this.removeAll();
		if(this.discoverOnePan != null){
			c.gridy=0;
			this.add(this.discoverOnePan,c);
		}
		if(this.discoverTwoPan != null){
			c.gridy=1;
			this.add(this.discoverTwoPan,c);
		}
		if(this.discoverThreePan != null){
			c.gridy=2;
			this.add(this.discoverThreePan,c);
		}
		
		this.validate();
		this.repaint();
	}
}
