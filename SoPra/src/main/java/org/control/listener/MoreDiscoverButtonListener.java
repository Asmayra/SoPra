package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.view.MainScreen;
import org.view.screens.Center.DiscoverExtendedScreen;

<<<<<<< HEAD
=======
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.control.LoadImageControl;
import org.view.screens.EastBar.DiscoverElement;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.EastBar.EastBar;
>>>>>>> branch 'master' of https://github.com/Asmayra/SoPra.git

public class MoreDiscoverButtonListener implements ActionListener{

	public void actionPerformed(ActionEvent arg0) {
<<<<<<< HEAD
		MainScreen.getInstance().showDiscoverExtendedScreen(DiscoverExtendedScreen.getInstance());
=======
		System.out.println("mehr Empfehlungen");
		
		JLabel pictureLabel = new JLabel(LoadImageControl.loadImageIcon("Placeholder.jpg"));
		//DiscoverMiniScreen.getInstance().setDiscoverOne(new DiscoverElement(UUID.randomUUID().toString(), pictureLabel, UUID.randomUUID().toString()));

>>>>>>> branch 'master' of https://github.com/Asmayra/SoPra.git
	}

}
