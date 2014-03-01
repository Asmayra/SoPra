package org.view.screens.EastBar;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Tim Lange
 *
 */
public class DiscoverElement extends JPanel {
	
	private JLabel label = new JLabel();
	private JLabel pictureLabel;
	
	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
		this.updateComponents();
	}

	public JLabel getPicture() {
		return pictureLabel;
	}

	public void setPicture(JLabel pictureLabel) {
		this.pictureLabel = pictureLabel;
		this.updateComponents();
	}

	private void updateComponents(){
		this.removeAll();
		this.add(label);
		this.add(pictureLabel);
		this.validate();
		this.repaint();
	}
	
	public DiscoverElement(String text, JLabel pictureLabel) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.label.setText(text);
		this.pictureLabel = pictureLabel;
		this.add(this.label);
		this.add(this.pictureLabel);
	}
}
