package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import org.control.DatabaseControl;
import org.control.SearchControl;
import org.model.User;
import org.view.MainScreen;
import org.view.screens.Center.ProfileScreen;
import org.view.screens.EastBar.EastBar;
import org.view.screens.EastBar.SearchMask;


public class SearchMaskListener implements KeyListener, ActionListener, MouseListener{

	public void keyPressed(KeyEvent e) {
		
	}

	/**
	 * update event when char entered in search mask
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			this.setResults();
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * set the results to the result pan and show it
	 */
	private void setResults(){
		new SearchControl().generateResults(SearchMask.getInstance().getSearchInput());
		EastBar.getInstance().showResultsPan();
	}
	

	/**
	 * gets called when the delete button next to the search mask is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		SearchMask.getInstance().resetSearchInput();
		EastBar.getInstance().hideResultsPan();
		
	}

	/**
	 * called when an item in the resultslist is pressed
	 */
	public void mouseClicked(MouseEvent e) {
		MainScreen.getInstance().updateCenter(new ProfileScreen((User)DatabaseControl.getInstance().load(User.class, ((JLabel)e.getSource()).getClientProperty("id").toString())));
		
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
