package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * Closes the given frame
 * @author Philipp
 *
 */
public class CloseFrameListener implements ActionListener{

	private JFrame frame;
	/**
	 * 
	 * @param frame the frame to close after a click
	 */
	public CloseFrameListener(JFrame frame){
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		this.frame.setVisible(false);
		this.frame.dispose();
		
	}

}
