package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.control.PlaylistControl;
import org.view.screens.Southbar.MusicPlayer;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
/**
 * Listener um das nächste Lied einer Playlist abzuspielen
 * @author Philipp
 *
 */
public class ForwardBTNListener implements ActionListener {

	private BasicPlayer player;
	
	
	public ForwardBTNListener(BasicPlayer player){
		this.player =player;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File selected_file = PlaylistControl.getInstance().nextSong();
		try {
			player.open(selected_file);
			player.play();
		}catch (NullPointerException nullEXC){
			System.out.println("Playlist zuende");
			//Falls Endlosmusik: Liste von vorne laden und neu starten!
		} catch (BasicPlayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		
	}

}
