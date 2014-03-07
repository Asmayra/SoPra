package org.view.screens.Southbar;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.util.Map;
import java.awt.event.*;

import javax.swing.event.*;

import org.control.PlaylistControl;

import javazoom.jlgui.basicplayer.*;

/**
 * A Structure Class for the MusicPlayer
 * @author Ioann
 *
 */
public class MusicPlayer extends JPanel {
	private JButton Btn_Stop = new JButton("■");
	private JButton Btn_Play= new JButton("► ||");
	private JButton Btn_Forward= new JButton(">");
	private JButton Btn_Back= new JButton("<");
	private JSlider volume_slider = new JSlider();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel Volume_Label =new JLabel("50%");
	private JLabel Label =new JLabel("Placeholder 4 seek bar");
	private JButton btnSelectFile = new JButton("Select file");
	private BasicPlayer player = new BasicPlayer();
	private JProgressBar progressBar = new JProgressBar();
	
	
	private static File selected_file=null;
	
	private double Music_gain = 0.5;
	public MusicPlayer(){
		player.addBasicPlayerListener(new TestListener(this));
		
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
		// TODO Delete this button
		gbc.gridx=1;
		gbc.gridy=1;
		add(btnSelectFile, gbc);
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
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				selected_file = openFile.getSelectedFile();
			}
		});
		Btn_Play.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				System.out.println(player.getStatus());
				playPause();
			}
		});/*Btn_Play*/
		
		
		Btn_Stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});/*Btn_Stop*/
		
		Btn_Forward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});/*Btn_Forward*/
		Btn_Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});/*Btn_Back*/
	}
/*=============================================================================*/
	/*
		//
	}*/
	
	class TestListener implements BasicPlayerListener
	{
		private MusicPlayer musicplayer;
		
		public TestListener(MusicPlayer musicplayer){
			this.musicplayer=musicplayer;
		}
		
		long currentTime = 0;
		long maxTime = 0;

		@Override
		public void opened(Object stream, Map properties) {
			maxTime = (Long)properties.get("duration") / 1000000;
			
		}

		@Override
		public void progress(int bytesread, long elapsed, byte[] pcm, Map properties) {
			currentTime += elapsed;
			currentTime /= 1000000;
			updateBar(currentTime , maxTime);
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
			    System.out.println("Song zuende!");
			    musicplayer.setCurrentSong(PlaylistControl.nextSong());			    
			}	
		}
		
	}
	public void updateBar(long actual_time, long max_time){
		long temp;
		temp=(actual_time *100)/max_time;
		progressBar.setValue(new Long(temp).intValue());
	}
	/**
	 * Sets the song which is played,when the playbutton is pressed
	 * @param song
	 */
	public void playPause(){
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
	
	public void stop(){
		System.out.println("Vor try/catch");
		try {
			System.out.println("In try/catch vor player.stop");
			player.stop();
			System.out.println("In try/catch nach player.stop");
		} catch (BasicPlayerException e1) {
			e1.printStackTrace();
		}
	}

	public void setCurrentSong(File song){
		stop();
		selected_file = song;
	}
}
