package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.view.MainScreen;

public class HomeScreen extends JPanel{
	public HomeScreen(){
		this.setLayout(new BorderLayout());
		JLabel title = new JLabel("HOME");
		title.setPreferredSize(new Dimension(400,20));
		this.add(title,BorderLayout.NORTH);
		
	}

}
