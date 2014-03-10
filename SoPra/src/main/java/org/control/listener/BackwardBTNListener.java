package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.control.PlaylistControl;
import org.view.screens.Southbar.MusicPlayer;

import javazoom.jlgui.basicplayer.BasicPlayer;

public class BackwardBTNListener implements ActionListener {

	
	private BasicPlayer player;
	
	
	public BackwardBTNListener(BasicPlayer player){
		this.player =player;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File selected_file = PlaylistControl.getInstance().prevSong();
		// TODO Auto-generated method stub
		
	}
}
