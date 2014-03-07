package org.view.screens.Southbar;

import java.io.File;
import java.util.Map;

import javax.swing.JPanel;

import org.control.PlaylistControl;
import javazoom.jlgui.basicplayer.*;

public class MusicPlayerControl extends JPanel{
	
	public void stop(BasicPlayer player){
		try {
			player.stop();
		} catch (BasicPlayerException e1) {
			e1.printStackTrace();
		}
	}
	
	public void playPause(BasicPlayer player, File selected_file){
		if(player.getStatus()== -1){
			try {
				player.open(selected_file);
			} catch (BasicPlayerException e) {
				e.printStackTrace();
			}
			System.out.println(player.getStatus());
			try {
				player.play();
			} catch (BasicPlayerException e) {
				e.printStackTrace();
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
					} catch (BasicPlayerException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						player.play();
					} catch (BasicPlayerException e) {
						e.printStackTrace();
					}
					break;
			}
		}
	}

	public void Forward(BasicPlayer player){
		
	}
	public void Back(BasicPlayer player){
		
	}
}
