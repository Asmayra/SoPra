package org.control.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.view.screens.EastBar.DiscoverElement;

public class DiscoverElementListener implements MouseListener{



	public void mouseClicked(MouseEvent e) {
		System.out.println(((DiscoverElement) e.getSource()).getUrl());
		
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
