package org.view.screens.WestBar;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Zeigt zugehöriges Coverart zum aktuell abgespielten Song an
 * @author Max Küper, Tim Michels
 *
 */
public class CoverArt extends JPanel{
	
	private ImageIcon albumcover; 
	private JLabel albumcoverlabel;
	
	public CoverArt() {
		albumcoverlabel = new JLabel(albumcover);
		add(albumcoverlabel);
		this.setPreferredSize(new Dimension(250,180));
	}
	
	public void setCoverArt(Image image){
		albumcover = new ImageIcon(image);
		albumcoverlabel = new JLabel(albumcover);
	}
	
}
