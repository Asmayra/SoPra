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

import org.control.listener.DiscoverElementListener;

/**
 * 
 * @author Tim Lange
 *
 */
public class DiscoverElement extends JPanel {
	
	private JLabel label = new JLabel();
	private JLabel pictureLabel;
	private String url;
	
	public String getUrl(){
		return this.url;
	}
	/**
	 * creates an Discover Element
	 * @param text String to be shown as description
	 * @param pictureLabel JLabel with picture to be shown
	 * @param url to get to this discover
	 */
	public DiscoverElement(String text, JLabel pictureLabel, String url) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.label.setText(text);
		this.pictureLabel = pictureLabel;
		this.add(this.label);
		this.add(this.pictureLabel);
		this.url = url;
		this.addMouseListener(new DiscoverElementListener());
	}
}
