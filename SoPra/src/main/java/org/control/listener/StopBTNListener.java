package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * Listener, um das aktuelle Leid zu beenden
 * @author Philipp, Ioann
 *
 */
public class StopBTNListener implements ActionListener {

	private BasicPlayer player; 
	
	public StopBTNListener(BasicPlayer player){
		this.player = player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			player.stop();
		} catch (BasicPlayerException e1) {
			e1.printStackTrace();
		}
		
	}
}
