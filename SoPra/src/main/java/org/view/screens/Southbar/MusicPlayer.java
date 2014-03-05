package org.view.screens.Southbar;

import javax.swing.*;

public class MusicPlayer extends JPanel {
	JButton Btn_Stop = new JButton();
	JButton Btn_Start= new JButton();
	JButton Btn_Forward= new JButton();
	JButton Btn_Back= new JButton();
	JProgressBar ProgressBar = new JProgressBar();
/*=============================================================================*/
	public void main(){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initialize();
			}
		});
	}
	public void initialize(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame Player_Frame = new JFrame("Media Player frame");
		JPanel Player_Panel = new JPanel();
		//Panel.isVisible();
		Player_Panel.setLayout(null);
		//
		JLabel Lable = new JLabel("TestLabel");
		Player_Panel.add(Lable);
		
		
		Player_Frame.getContentPane().add(Player_Panel);
	}
}
