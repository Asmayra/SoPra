package org.view.screens.Southbar;

import javax.swing.*;
import java.awt.*;

import javax.swing.event.*;

/**
 * A Structure Class for the MusicPlayer
 * @author Ioann
 *
 */
public class MusicPlayer extends JPanel {
	private JButton Btn_Stop = new JButton("Stop");
	private JButton Btn_Play= new JButton("Play/Pause");
	private JButton Btn_Forward= new JButton("Fwd");
	private JButton Btn_Back= new JButton("Bck");
	private JSlider volume_slider = new JSlider();
	private GridBagConstraints gbc = new GridBagConstraints();
	private JLabel Volume_Label =new JLabel("Volume %");
	private JLabel Label =new JLabel("Placeholder 4 seek bar");
	
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
		gbc.gridx=5;
		gbc.gridy=0;
		add(Btn_Forward,gbc);
		
		gbc.gridx=6;
		gbc.gridy=0;
		add(Volume_Label,gbc);
		
	}
/*=============================================================================*/
	/*
		//
	}*/
}
