package org.view.screens.EastBar;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DiscoverElement extends JPanel {

	public DiscoverElement() {
		JLabel label = new JLabel();
		label.setText("beispieltext");
		this.add(label);
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		try { 
			 image = ImageIO.read( new URL("/src/main/placeholder.jpg") );
			} catch (IOException e) { 
			  e.printStackTrace(); 
			}
		ImageIcon imageIcon = new ImageIcon(image);
	}
}
