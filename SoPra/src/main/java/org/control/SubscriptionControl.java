package org.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import org.model.User;
import org.view.DiscoverElement;
import org.view.screens.Center.SubscriptionScreen;

/**
 * 
 * @author Max Küper, Tim Michels
 *
 */
public class SubscriptionControl {
	SubscriptionScreen subScreen;
	ArrayList<User> currentSubscriptions;
	LinkedList<DiscoverElement> subscriptionElements;
	private static SubscriptionControl instance = null;
	
	public static SubscriptionControl getInstance() {
		if(instance == null){
			instance = new SubscriptionControl();
		}
		return instance;
	}
	
	public SubscriptionControl(){
		//aktuelle Subscriptions
		currentSubscriptions = (ArrayList<User>) LoginControl.getInstance().getCurrentUser().getFollowing();
		subscriptionElements = new LinkedList<DiscoverElement>();
		subScreen = SubscriptionScreen.getInstance();
		updateSubscriptions();
	}
	/**
	 * updates the Subscriptionsite
	 */
	public void updateSubscriptions(){
		subscriptionElements = new LinkedList<DiscoverElement>();
		System.out.println("load Subscriptions...");
		currentSubscriptions = (ArrayList<User>) ((User) DatabaseControl.getInstance().load(User.class, LoginControl.getInstance().getCurrentUser().getUsername())).getFollowing();
		//Setzen der Subscriptionliste
		for(int i=0;i<currentSubscriptions.size();i++){
			User followed = currentSubscriptions.get(i);
			try {
				DiscoverElement newsub = new DiscoverElement(followed.getUsername(),new ImageIcon(followed.getPicture()),followed.getUsername(),"");
				subscriptionElements.add(newsub);
			} catch (NullPointerException e) {System.err.println(e.getLocalizedMessage());}
		}
		//anzeigen der GUI
		subScreen.setSubscriptions(subscriptionElements);
		subScreen.showSubscriptions();
	}




}

