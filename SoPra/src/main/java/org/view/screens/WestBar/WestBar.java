package org.view.screens.WestBar;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
/**
 * A Structure Class for the WestBar
 * @author Philipp
 *
 */
public class WestBar extends JPanel {
	public WestBar(){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	}
	public void add(JPanel panel){
		this.add(panel);
	}

}
