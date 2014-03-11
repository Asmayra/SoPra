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
	
	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		instance = null;
	}
	
	public SubscriptionControl(){
		//aktuelle Subscriptions
		currentSubscriptions = (ArrayList<User>) LoginControl.getInstance().getCurrentUser().getFollowing();
		subscriptionElements = new LinkedList<DiscoverElement>();
		subScreen = SubscriptionScreen.getInstance();
		

		
		updateSubscriptions();
	}
	/**
	 * aktualisiert die Subscriptionseite 
	 */
	public void updateSubscriptions(){
		currentSubscriptions = (ArrayList<User>) ((User) DatabaseControl.getInstance().load(User.class, LoginControl.getInstance().getCurrentUser().getUsername())).getFollowing();
		
		//Anlegen der GUI-Elemente mittels der User-Liste
		for(int i=0;i<currentSubscriptions.size();i++){
			User followed = currentSubscriptions.get(i);
			try {
				DiscoverElement newsub = new DiscoverElement(followed.getUsername(),new ImageIcon(followed.getPicture()),followed.getUsername(),"");
				subscriptionElements.add(newsub);
			} catch (NullPointerException e) {System.err.println(e.getLocalizedMessage());}
		}
		subScreen.setSubscriptions(subscriptionElements);
		subScreen.showSubscriptions();
	}




}

