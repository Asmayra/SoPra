package org.control;

import java.io.IOException;
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
	
	public SubscriptionControl(){
		//aktuelle Subscriptions
//		LinkedList<User> currentSubscriptions = (LinkedList<User>) LoginControl.getInstance().getCurrentUser().getFollowing();
		LinkedList<DiscoverElement> subscriptionElements= new LinkedList<DiscoverElement>();
		SubscriptionScreen subScreen = SubscriptionScreen.getInstance();
		
		//Anlegen der GUI-Elemente mittels der User-Liste
//		for(int i=0;i<currentSubscriptions.size();i++){
//			User followed = currentSubscriptions.get(i);
//			try {
//				DiscoverElement newsub = new DiscoverElement(followed.getUsername(),new ImageIcon(followed.getPicture()),followed.getUsername());
//				subscriptionElements.add(newsub);
//			} catch (IOException e) {}
//		}
		

	    //Dummdaten erzeugen
	    for (int i = 0; i < 12; i++) {
	    	 DiscoverElement discover = new DiscoverElement("Followed "+(i+1), LoadImageControl.loadImageIcon("", LoginControl.getInstance().getCurrentUser()), "url "+i, "User");
	    	 subscriptionElements.add(discover);
	    }
	    
	    subScreen.setSubscriptions(subscriptionElements);
	    subScreen.showSubscriptions();
	    
	}



}

