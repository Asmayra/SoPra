package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.view.MainScreen;
import org.view.screens.Center.SubscriptionScreen;

/**
 * 
 * @author Max, Tim
 *
 */
public class SubscriptionButtonListener implements ActionListener  {

	
	public void actionPerformed(ActionEvent e) {
		MainScreen.getInstance().showSubscriptionScreen(SubscriptionScreen.getInstance());
		
	}

}
