package org.testpackage;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.view.screens.EastBar.DiscoverElement;
import org.view.screens.EastBar.DiscoverMiniScreen;
import org.view.screens.EastBar.EastBar;

public class EastBarTest {

    private void display() {
        
    	JFrame f = new JFrame("EastBar");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EastBar eb = new EastBar();
        
        DiscoverMiniScreen miniScreen = DiscoverMiniScreen.getInstance();
        DiscoverElement discover1 = new DiscoverElement();
        DiscoverElement discover2 = new DiscoverElement();
        DiscoverElement discover3 = new DiscoverElement();
        
        miniScreen.setDiscoverOne(discover1);
        miniScreen.setDiscoverTwo(discover2);
        miniScreen.setDiscoverThree(discover3);
        
        f.add(eb);
        f.setPreferredSize(new Dimension(400,600));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
    	
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
