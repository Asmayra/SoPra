package org.view.screens.WestBar;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.control.SoPra;

public class CoverArt extends JPanel{
	
	private ImageIcon albumcover; 
	private JLabel albumcoverlabel;
	
	public CoverArt() {
		albumcoverlabel = new JLabel(albumcover);
		add(albumcoverlabel);
	}
	
	public void setCoverArt(Image image){
		albumcover = new ImageIcon(image);
		albumcoverlabel = new JLabel(albumcover);
	}
	
}
