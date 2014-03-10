package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.view.screens.Southbar.MusicPlayer;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class PlayBTNListener implements ActionListener {

	
	private BasicPlayer player;
	
	
	
	public PlayBTNListener(BasicPlayer player){
		this.player = player;	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File selected_file = MusicPlayer.getCurrentSong();
		if(player.getStatus()== -1){
			try {
				player.open(selected_file);
			} catch (BasicPlayerException e1) {
				e1.printStackTrace();
			}
			System.out.println(player.getStatus());
			try {
				player.play();
			} catch (BasicPlayerException e1) {
				e1.printStackTrace();
			}
		}else{
			switch(player.getStatus()){
				case 0:
					try {
						player.pause();
					} catch (BasicPlayerException e2) {
						e2.printStackTrace();
					}
					break;
				case 1:
					try {
						player.resume();
					} catch (BasicPlayerException e1) {
						e1.printStackTrace();
					}
					break;
				case 2:
					try {
						player.play();
					} catch (BasicPlayerException e1) {
						e1.printStackTrace();
					}
					break;
				case 3:
					try {
						player.play();
					} catch (BasicPlayerException e1) {
						e1.printStackTrace();
					}
					break;
			}
		}
		
	}

}
