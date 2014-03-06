package org.view.screens.Center;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.view.DiscoverElement;


public class SubscriptionScreen extends JPanel{
	
	private static SubscriptionScreen instance = null;
	private LinkedList<DiscoverElement> subscriptions;
	private JScrollPane subscripScrol;
	private JPanel subscripPan = new JPanel();

	/**
	 * @return Instanz des AdminGenreScreens
	 */
	public static SubscriptionScreen getInstance()
	{
		if(instance == null){
			instance = new SubscriptionScreen();
		}
		return instance;
	}
	
	public SubscriptionScreen(){
		this.setPreferredSize(new Dimension(500,500));
		subscripPan.setLayout(new GridBagLayout());
		subscripScrol = new JScrollPane(subscripPan);
		subscripScrol.setPreferredSize(new Dimension(490,490));
		this.add(subscripScrol);
	}
	
	public void showSubscriptions(){
		try{
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx=20;
		c.ipady=20;
		for(int i=0;i<subscriptions.size();i++){
			
			c.gridx=(i%3);
			c.gridy=(i/3);
			subscripPan.add(subscriptions.get(i),c);
		}
		
		}catch(NullPointerException exc){System.err.println("error");}
		this.updateUI();
	}
	
	public void setSubscriptions(LinkedList<DiscoverElement> list){
		this.subscriptions=list;
	}

}