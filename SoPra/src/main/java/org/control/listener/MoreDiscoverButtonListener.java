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

import org.control.LoadImageControl;
import org.view.screens.EastBar.DiscoverElement;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.EastBar.EastBar;

public class MoreDiscoverButtonListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("mehr Empfehlungen");
		
		JLabel pictureLabel = new JLabel(LoadImageControl.loadImageIcon("Placeholder.jpg"));
		//DiscoverMiniScreen.getInstance().setDiscoverOne(new DiscoverElement(UUID.randomUUID().toString(), pictureLabel, UUID.randomUUID().toString()));

	}

}
