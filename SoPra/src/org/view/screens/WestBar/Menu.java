package org.view.screens.WestBar;

import javax.swing.*;

public class Menu extends JPanel{
	private JLabel userName;
	private JToolBar toolbar;
	private JButton home;
	private JButton mail;
	private JButton logOut;
	private JButton settings;
	private Icon dummyIcon;
	//Admin
	//Image
	
	public Menu() {
		userName = new JLabel();//Kontrollklasse.getUser().getUsername)
		
		toolbar = new JToolBar();
		
		home = new JButton(dummyIcon);
		mail = new JButton(dummyIcon);
		logOut = new JButton(dummyIcon);
		settings = new JButton(dummyIcon);
		
		toolbar.add(home);
		toolbar.add(mail);
		toolbar.add(logOut);
		toolbar.add(settings);		
	}
}
