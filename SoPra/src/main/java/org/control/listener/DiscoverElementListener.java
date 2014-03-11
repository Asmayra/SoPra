package org.control.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.control.DatabaseControl;
import org.model.Label;
import org.model.User;
import org.view.DiscoverElement;
import org.view.MainScreen;
import org.view.screens.Center.LabelScreen;
import org.view.screens.Center.ProfileScreen;

/**
 * 
 * @author beide Tims
 *
 */
public class DiscoverElementListener implements MouseListener{

	private static DiscoverElementListener instance;
	
	public static DiscoverElementListener getInstance()
	{
		if(instance == null){
			instance = new DiscoverElementListener();
		}
		return instance;
	}

	public void mouseClicked(MouseEvent e) {
		DiscoverElement current = (DiscoverElement) e.getSource();
		String URL = current.getUrl();
		if(current.getObjectType().equals("Label")){
			int LabelID = Integer.parseInt(current.getUrl());
			Label currentLabel = (Label) DatabaseControl.getInstance().load(Label.class, LabelID);
			MainScreen.getInstance().updateCenter(new LabelScreen(currentLabel));
		}
		else{
			MainScreen.getInstance().updateCenter(new ProfileScreen((User)DatabaseControl.getInstance().load(User.class, URL)));
		}
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
