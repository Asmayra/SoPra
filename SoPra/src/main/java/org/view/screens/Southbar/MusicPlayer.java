package org.view.screens.Southbar;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * A Structure Class for the MusicPlayer
 * @author Ioann
 *
 */
public class MusicPlayer extends JPanel {
	private JButton Btn_Stop = new JButton("Stop ");
	private JButton Btn_Play= new JButton("Play/Pause ");
	private JButton Btn_Forward= new JButton("Fwd ");
	private JButton Btn_Back= new JButton("Bck ");
	private JSlider volume_slider = new JSlider();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel Volume_Label =new JLabel("50%");
	private JLabel Label =new JLabel("Placeholder 4 seek bar");
	
	private double Music_gain = 0.5;
	public MusicPlayer(){
		
		this.setLayout(new GridBagLayout());
		gbc.gridx=0;
		gbc.gridy=0;
		add(Btn_Stop,gbc);
		
		gbc.gridx=1;
		gbc.gridy=0;
		add(Btn_Back,gbc);
		
		gbc.gridx=2;
		gbc.gridy=0;
		add(Btn_Play,gbc);
		
		gbc.gridx=3;
		gbc.gridy=0;
		add(Btn_Forward,gbc);
		
		gbc.gridx=4;
		gbc.gridy=0;
		add(Label,gbc);
		
		volume_slider.setPaintLabels(true);
		volume_slider.setPaintTicks(true);
		volume_slider.setMajorTickSpacing(20);
		volume_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				Music_gain = volume_slider.getValue();
				Volume_Label.setText(String.valueOf(Music_gain).substring(0, String.valueOf(Music_gain).length()-2) +"%");
				Music_gain=Music_gain/100;
				/*try {
					player.setGain(Music_gain);
				} catch (BasicPlayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
			
		});
		gbc.gridx=5;
		gbc.gridy=0;
		add(volume_slider,gbc);
		
		gbc.gridx=6;
		gbc.gridy=0;
		add(Volume_Label,gbc);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setMinimumSize(new Dimension(860,600));
	}
/*=============================================================================*/
	/*
		//
	}*/
}
