package org.control.listener;

import java.io.File;
import java.util.Map;

import org.control.PlaylistControl;
import org.view.screens.Southbar.MusicPlayer;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
/**
 * Listener that gives info over MusicPlayer State
 * @author unim95, Sebastian, Philipp.
 *
 */
public class PlayerListener implements BasicPlayerListener {
	
	private MusicPlayer musicplayer;
	private BasicPlayer player;
	long currentTime = 0;
	long maxTime = 0;
	
	public PlayerListener(MusicPlayer musicplayer,BasicPlayer player){
		this.musicplayer=musicplayer;
		this.player = player;
	}
	

	@Override
	public void opened(Object stream, Map properties) {
		for( Object o : properties.keySet() )
			System.out.println(o.toString());
		maxTime = (Long)properties.get("duration") / 1000000;
	}

	@Override
	public void progress(int bytesread, long elapsed, byte[] pcm, Map properties) {
		currentTime += elapsed;
		currentTime /= 1000000;
		musicplayer.updateBar(currentTime , maxTime);
	}

	@Override
	public void setController(BasicController arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateUpdated(BasicPlayerEvent e) {
		currentTime = e.getPosition();
		currentTime /= 1000000;
		// Notification of BasicPlayer states (opened, playing, end of media, ...)
		if(e.getCode() == BasicPlayerEvent.EOM){
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
		//System.out.println(player)
	}
	
}
