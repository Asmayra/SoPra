package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.SubscriptionControl;
import org.view.MainScreen;
import org.view.screens.Center.SubscriptionScreen;

/**
 * 
 * @author Max, Tim
 *
 */
public class SubscriptionButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		SubscriptionControl.getInstance().updateSubscriptions();
		MainScreen.getInstance().showSubscriptionScreen(SubscriptionScreen.getInstance());
		
	}

}
