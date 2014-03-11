package org.control.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.model.User;
import org.view.MainScreen;
import org.view.screens.Center.ProfileScreen;
/**
 * Wechselt den Hauptschirm zu dem,des angeklickten users
 * @author Tim,Max
 *
 */
public class OpenProfile implements MouseListener{
	User usr;
	
	
	public OpenProfile(User usr){
		this.usr =usr;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		MainScreen.getInstance().updateCenter(new ProfileScreen(usr));
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
