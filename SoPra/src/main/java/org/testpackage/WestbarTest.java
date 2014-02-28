package org.testpackage;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.view.screens.WestBar.CoverArt;
import org.view.screens.WestBar.SongTicker;
import org.view.screens.WestBar.WestBar;

//Test fuer die Westbar
public class WestbarTest {

	WestBar west = new WestBar();
	BufferedImage image = new BufferedImage(256,256, BufferedImage.TYPE_INT_RGB);
	
    private void display() {
        JFrame f = new JFrame("MarqueeTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(west);
        SongTicker mp = west.getSongticker();
        mp.setTickertext("Artist - Album - Songname");
        
        CoverArt cover = west.getCoverart();
        cover.setCoverArt(image);
        
        f.setPreferredSize(new Dimension(270,800));
        f.setMinimumSize(new Dimension(270,800));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        mp.start();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            //@Override
            public void run() {
                new WestbarTest().display();
            }
        });
    }
}
