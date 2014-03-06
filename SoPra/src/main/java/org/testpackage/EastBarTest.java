package org.testpackage;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.control.LoadImageControl;
import org.view.DiscoverElement;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.EastBar.EastBar;

public class EastBarTest {

    private void display() {
    	JFrame f = new JFrame("EastBar");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EastBar eb = EastBar.getInstance();
    
        
        DiscoverMiniScreen miniScreen = DiscoverMiniScreen.getInstance();
        DiscoverElement discover1 = new DiscoverElement("text1", LoadImageControl.loadImageIcon("placeholder.jpg"), "url1");
	    DiscoverElement discover2 = new DiscoverElement("text2", LoadImageControl.loadImageIcon("placeholder.jpg"), "url2");
	    DiscoverElement discover3 = new DiscoverElement("text3", LoadImageControl.loadImageIcon("placeholder.jpg"), "url3");
        
        miniScreen.setDiscoverOne(discover1);
        miniScreen.setDiscoverTwo(discover2);
        miniScreen.setDiscoverThree(discover3);
        
        f.add(eb);
        f.setPreferredSize(new Dimension(200,500));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        DiscoverElement discover4 = new DiscoverElement("text4", LoadImageControl.loadImageIcon("placeholder.jpg"), "url4");
        miniScreen.setDiscoverTwo(discover4);
        
    	
    }
    
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            //@Override
            public void run() {
                new EastBarTest().display();
            }
        });

	}

}
