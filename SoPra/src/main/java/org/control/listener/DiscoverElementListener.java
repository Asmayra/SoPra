package org.control.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import org.control.DatabaseControl;
import org.model.User;
import org.view.DiscoverElement;
import org.view.MainScreen;
import org.view.screens.Center.ProfileScreen;
import org.view.screens.Center.SubscriptionScreen;

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
		System.out.println("ToDo: DiscoverElementListener!"+ ((DiscoverElement) e.getSource()).getUrl());
		DiscoverElement current = (DiscoverElement) e.getSource();
		String URL = current.getUrl();
		String currentType = current.getObjectType();
		MainScreen main = MainScreen.getInstance();
		if(currentType.equals("User")){
			//main.updateCenter(new ProfileScreen((User)DatabaseControl.getInstance().load(User.class, URL)));
		}
		else if(currentType.equals("Album")){
			//main.updateCenter(new PlaylistScreen((Album)DatabaseControl.getInstance().load(Album.class, URL)));
		}
		else if(currentType.equals("Song")){
			//main.updateCenter(new PlaylistScreen((Song)DatabaseControl.getInstance().load(Song.class, URL)));
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
