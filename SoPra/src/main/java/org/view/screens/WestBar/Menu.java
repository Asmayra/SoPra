package org.view.screens.WestBar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import org.control.LoadImageControl;
import org.control.LoginControl;
import org.control.listener.HomeScreenButtonListener;
import org.control.listener.LogOutButtonListener;
import org.control.listener.MailButtonListener;
import org.control.listener.OpenProfile;
import org.control.listener.SettingsButtonListener;
/**
 * erstellt das Menu
 * @author Tim Michels, Max Küper
 */
public class Menu extends JPanel{
	
	private JLabel userName;
	private JToolBar toolbar;
	private int BUTTONSIZE = 20;
	private JButton home = new JButton(LoadImageControl.loadMenuIcon("Home.png", BUTTONSIZE));
	private JButton mail = new JButton(LoadImageControl.loadMenuIcon("Mail.png", BUTTONSIZE));
	private JButton logOut = new JButton(LoadImageControl.loadMenuIcon("Logout.png", BUTTONSIZE));
	private JButton setting = new JButton(LoadImageControl.loadMenuIcon("Settings.png", BUTTONSIZE));
	private JButton avatar = new JButton(LoadImageControl.loadImageIcon(LoginControl.getInstance().getCurrentUser().getImagePath(), LoginControl.getInstance().getCurrentUser()));//benutzerbild x
	/**
	 * erstllt ein JPanel mit Knöpfen und einer kleinen Version des Profilbilds
	 */
	public Menu() {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		home.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		home.addActionListener(new HomeScreenButtonListener());
		home.setToolTipText("Home");
		
		mail.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		mail.addActionListener(new MailButtonListener());
		mail.setToolTipText("Mail");
		
		logOut.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		logOut.addActionListener(new LogOutButtonListener());
		logOut.setToolTipText("Logout");
		
		setting.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		setting.addActionListener(new SettingsButtonListener());
		setting.setToolTipText("Settings");
		
		avatar.addMouseListener(new OpenProfile(LoginControl.getInstance().getCurrentUser()));
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		c.gridwidth =2;
		c.gridheight =2;
		avatar.setPreferredSize(new Dimension(50,50));
		add(avatar,c);
		c.gridheight =1;
		c.gridx = 2;
		c.gridwidth = 3;
		userName = new JLabel(" "+ LoginControl.getInstance().getCurrentUser().getUsername()+ "  ");
		add(userName,c);
		
		c.gridx =5;
		c.gridwidth = 1;
		add(home,c);
		
		c.gridx =6;
		add(mail,c);
		c.gridx =7;
		add(setting,c);
		c.gridx = 8;
		add(logOut,c);		

		this.setPreferredSize(new Dimension(250,80));
		
	}

	public JButton getSettingButton() {
		return setting;
	}
	public JButton getHomeButton() {
		return home;
	}
	public JButton getLogOutButton() {
		return logOut;
	}
	public JButton getMailButton() {
		return mail;
	}
	
}