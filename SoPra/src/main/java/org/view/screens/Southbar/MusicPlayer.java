package org.view.screens.Southbar;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.*;

import javax.swing.event.*;
import javazoom.jlgui.basicplayer.*;

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
	private JButton btnSelectFile = new JButton("Select file");
	private BasicPlayer player = new BasicPlayer();
	
	private File selected_file=null;
	
	private double Music_gain = 0.5;
	public MusicPlayer(){
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
		add(Label,gbc);
		
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
				// TODO Auto-generated method stub
				Music_gain = volume_slider.getValue();
				Volume_Label.setText(String.valueOf(Music_gain).substring(0, String.valueOf(Music_gain).length()-2) +"%");
				Music_gain=Music_gain/100;
				try {
					player.setGain(Music_gain);
				} catch (BasicPlayerException e) {
					// TODO Auto-generated catch block
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
				if(player.getStatus()== -1){
					try {
						player.open(selected_file);
					} catch (BasicPlayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(player.getStatus());
					try {
						player.play();
					} catch (BasicPlayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					switch(player.getStatus()){
						case 0:
							try {
								player.pause();
							} catch (BasicPlayerException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							break;
						case 1:
							try {
								player.resume();
							} catch (BasicPlayerException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;
						case 2:
							try {
								player.play();
							} catch (BasicPlayerException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						case 3:
							try {
								player.play();
							} catch (BasicPlayerException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
					}
				}
			}
		});/*Btn_Play*/
		
		
		Btn_Stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println(player.getStatus());
				// TODO Auto-generated method stub
				try {
					player.stop();
				} catch (BasicPlayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(player.getStatus());
				
			}
		});/*Btn_Stop*/
		
		Btn_Forward.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});/*Btn_Forward*/
		Btn_Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});/*Btn_Back*/
	}
/*=============================================================================*/
	/*
		//
	}*/
}
