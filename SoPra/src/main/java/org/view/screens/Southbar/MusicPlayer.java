 package org.view.screens.Southbar;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.Map;
import java.awt.event.*;

import javax.swing.event.*;

import org.control.PlaylistControl;
import org.control.listener.BackwardBTNListener;
import org.control.listener.ForwardBTNListener;
import org.control.listener.PlayBTNListener;
import org.control.listener.PlayerListener;
import org.control.listener.StopBTNListener;
import org.omg.CORBA.Current;

import javazoom.jlgui.basicplayer.*;

/**
 * A Structure Class for the MusicPlayer
 * @author Ioann 
 *
 */
public class MusicPlayer extends JPanel{
	private JButton Btn_Stop = new JButton("■");
	private JButton Btn_Play= new JButton("► ||");
	private JButton Btn_Forward= new JButton(">");
	private JButton Btn_Back= new JButton("<");
	private JSlider volume_slider = new JSlider();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel Volume_Label =new JLabel("50%");
	private JLabel Label =new JLabel("Placeholder 4 seek bar");

	private static BasicPlayer player = new BasicPlayer();
	private JProgressBar progressBar = new JProgressBar();
	private static File selected_file;
	private double Music_gain = 0.5;
	
	
	public static BasicPlayer getPlayer(){
		return player;
	}
	
	
	/**
	 * Creates Interface for MusicPlayer in Southbar
	 */
	public MusicPlayer(){
		//Makes event listener for our MusicPlayer
		player.addBasicPlayerListener(new PlayerListener(this,player));
		//Initializes the Current Song as the first Song from favorites
		MusicPlayer.setCurrentSong(PlaylistControl.nextSong());
		
		this.setLayout(new GridBagLayout());
		gbc.insets=new Insets(2,2,2,2);
		gbc.gridx=0;
		gbc.gridy=0;
		add(Btn_Stop,gbc);
		
		gbc.gridx=1;
		add(Btn_Back,gbc);
		
		gbc.gridx=2;
		add(Btn_Play,gbc);
		
		gbc.gridx=3;
		add(Btn_Forward,gbc);
		
		gbc.gridx=4;
		add(progressBar,gbc);
		progressBar.setBounds(34, 377, 384, 14);
		
		gbc.gridx=5;
		add(volume_slider,gbc);
		
		gbc.gridx=6;
		add(Volume_Label,gbc);

		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//this.setMinimumSize(new Dimension(860,600));
/*=============================================================================================*/
/*Action Listeners*/
		volume_slider.setPaintLabels(true);
		volume_slider.setPaintTicks(true);
		volume_slider.setMajorTickSpacing(20);
		volume_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Music_gain = volume_slider.getValue();
				Volume_Label.setText(String.valueOf(Music_gain).substring(0, String.valueOf(Music_gain).length()-2) +"%");
				Music_gain=Music_gain/100;
				try {
					player.setGain(Music_gain);
				} catch (BasicPlayerException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		Btn_Play.addActionListener(new PlayBTNListener(player)); 
		
		Btn_Stop.addActionListener(new StopBTNListener(player));
		
		Btn_Forward.addActionListener(new ForwardBTNListener(player));
		
		Btn_Back.addActionListener(new BackwardBTNListener(player));
		
		
	}
	/**
	 * Method that updates ProgressBar of MusicPlayer
	 * @param actual_time
	 * @param max_time
	 */
	public void updateBar(long actual_time, long max_time){
		long temp;
		temp=(actual_time *100)/max_time;
		progressBar.setValue(new Long(temp).intValue());
	}
	
	/**
	 * Sets the song which is played,when the playbutton is pressed
	 * @param song
	 */
	public static void setCurrentSong(File song){
		selected_file = song;
	}
	/**
	 * Returns the selected File
	 * @return selected File
	 */
	public static File getCurrentSong(){
		return selected_file;
	}

}
