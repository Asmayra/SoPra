package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.view.screens.EastBar.DiscoverElement;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.EastBar.EastBar;

public class MoreDiscoverButtonListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("mehr Empfehlungen");
		
		BufferedImage image = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
		URL url = this.getClass().getResource("placeholder.jpg");
		try {
			image = ImageIO.read(new File(System.getProperty("user.dir")+"\\src\\main\\java\\placeholder.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(image);
		JLabel pictureLabel = new JLabel(imageIcon);
		DiscoverMiniScreen.getInstance().setDiscoverOne(new DiscoverElement(UUID.randomUUID().toString(), pictureLabel, UUID.randomUUID().toString()));

	}

}
