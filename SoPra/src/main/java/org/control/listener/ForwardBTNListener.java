package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.view.screens.Southbar.MusicPlayer;

import javazoom.jlgui.basicplayer.BasicPlayer;

public class ForwardBTNListener implements ActionListener {

	private BasicPlayer player;
	
	
	public ForwardBTNListener(BasicPlayer player){
		this.player =player;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File selected_file = MusicPlayer.getCurrentSong();
		// TODO Auto-generated method stub
		
	}

}
