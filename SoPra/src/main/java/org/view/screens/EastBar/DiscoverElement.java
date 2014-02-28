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

public class DiscoverElement extends JPanel {

	public DiscoverElement() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel label = new JLabel();
		label.setText("beispieltext");
		this.add(label);
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		URL url = this.getClass().getResource("placeholder.jpg");
		//BufferedImage img = ImageIO.read(this.getClass().getResource("/res/testMap.png"));

		try {
			image = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\main\\java\\placeholder.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ImageIcon imageIcon = new ImageIcon(image);


		JLabel picture = new JLabel(imageIcon);
		this.add(picture);
	}
}
