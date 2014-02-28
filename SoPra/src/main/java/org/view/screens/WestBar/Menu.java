package org.view.screens.WestBar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import org.control.SoPra;

public class Menu extends JPanel{
	
	private JLabel userName;
	private JToolBar toolbar;
	private Icon dummyIcon;
	private int BUTTONSIZE = 20;
	private JButton home = new JButton(dummyIcon);
	private JButton mail = new JButton(dummyIcon);
	private JButton logOut = new JButton(dummyIcon);
	private JButton setting = new JButton(dummyIcon);
	private JButton avatar = new JButton(new ImageIcon("C:/Users/Tym/git/SoPraMusic/SoPra/Avatar.jpg"));//benutzerbild x

	public Menu() {
		
		home.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		mail.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		logOut.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		setting.setPreferredSize(new Dimension(BUTTONSIZE,BUTTONSIZE));
		
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
		userName = new JLabel("  UserName  ");//"  "+ SoPra.getCurrentUser().getUsername()+ "  ");
		add(userName,c);
		
		c.gridx =5;
		c.gridwidth = 1;
		add(home,c);
		
		c.gridx =6;
		add(mail,c);
		c.gridx =7;
		add(logOut,c);
		c.gridx = 8;
		add(setting,c);		

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
	
	public void addSettingButtonListener(ButtonActionListener listener){
		if(setting.getAncestorListeners().length == 0){
			setting.addActionListener(listener);
		}
	}
	public void addHomeButtonListener(ButtonActionListener listener){
		if(home.getAncestorListeners().length == 0){
			home.addActionListener(listener);
		}
	}
	public void addLogOutButtonListener(ButtonActionListener listener){
		if(logOut.getAncestorListeners().length == 0){
			logOut.addActionListener(listener);
		}
	}
	public void addMailButtonListener(ButtonActionListener listener){
		if(mail.getAncestorListeners().length == 0){
			mail.addActionListener(listener);
		}
	}
	public void addMyProfileButtonListener(ButtonActionListener listener){
		if(avatar.getAncestorListeners().length == 0){
			avatar.addActionListener(listener);
		}
	}
}