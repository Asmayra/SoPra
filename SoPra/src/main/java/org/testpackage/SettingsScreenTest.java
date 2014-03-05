package org.testpackage;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.view.screens.Center.SettingsScreen;

public class SettingsScreenTest {

	public static void main(String[] args) {
		JFrame f = new JFrame("Settings");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(500,400));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        f.add(SettingsScreen.getInstance());
        f.pack();

	}

}
