package org.testpackage;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.view.screens.WestBar.SongTicker;

//Test fÃ¼r den Songticker
public class MarqueeTest {

    private void display() {
        JFrame f = new JFrame("MarqueeTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SongTicker mp = new SongTicker();
        mp.setTickertext("Artist - Album - Songname");
        f.add(mp);
        f.setPreferredSize(new Dimension(200,100));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        mp.start();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            //@Override
            public void run() {
                new MarqueeTest().display();
            }
        });
    }
}