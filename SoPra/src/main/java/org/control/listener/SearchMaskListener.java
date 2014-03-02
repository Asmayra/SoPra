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

import org.view.screens.EastBar.EastBar;
import org.view.screens.EastBar.SearchMask;


public class SearchMaskListener implements KeyListener, ActionListener, MouseListener{

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		this.setResults();
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * set the results to the result pan and show it
	 */
	private void setResults(){
		EastBar.getInstance().setResults(this.generateResults());
		EastBar.getInstance().showResultsPan();
	}
	
	/**
	 * generate the results to be show
	 * @return return List with results
	 */
	private List<String> generateResults(){
		List<String> resultList = new ArrayList<String>();
		for(int i=0; i<10; i++){
			resultList.add(SearchMask.getInstance().getSearchInput()+i);
		}
		
		return resultList;
	}

	public void actionPerformed(ActionEvent e) {
		SearchMask.getInstance().resetSearchInput();
		EastBar.getInstance().hideResultsPan();
		
	}

	public void mouseClicked(MouseEvent e) {
		System.out.println(((JLabel)e.getSource()).getText());
		
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
