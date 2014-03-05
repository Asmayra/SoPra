package org.view.screens.EastBar;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.control.listener.MoreDiscoverButtonListener;
import org.control.listener.SearchMaskListener;
import org.model.User;

/**
 * 
 * @author Tim Lange
 *
 */
public class EastBar extends JLayeredPane{

	private static EastBar bar;
	private JButton moreButton = new JButton("mehr");
	private JPanel resultsPan = new JPanel();
	
	public static EastBar getInstance(){
		if(bar == null){
			bar = new EastBar();
		}
		
		return bar;
	}
	
	private EastBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setPreferredSize(new Dimension(150, 310));
		
		//add SearchMask Component
		//SearchMask.getInstance().setBounds(0, 0, 180, 20);
		this.add(SearchMask.getInstance(), new Integer(0));
		
		//add DiscoverComponent
		//DiscoverMiniScreen.getInstance().setBounds(20, 20, 200, 500);
		this.add(DiscoverMiniScreen.getInstance(), new Integer(0));
		
		//add moreButton component
		//moreButton.setBounds(50, 420, 100, 25); 
		this.add(moreButton, new Integer(0));
		moreButton.addActionListener(new MoreDiscoverButtonListener());

		//setup resultspan for search results
		this.resultsPan.setLayout(new BoxLayout(resultsPan, BoxLayout.PAGE_AXIS));
		this.resultsPan.setBackground(Color.gray);
		this.resultsPanHeight(0);
		this.resultsPan.setVisible(true);
		this.add(this.resultsPan, new Integer(2));
	}
	
	/**
	 * set resultspanel height to fit it to the results length
	 * @param y pixels
	 */
	public void resultsPanHeight(int y){
		if(y<0){
			y=0;
		}
		this.resultsPan.setBounds(20, 22, 125, y);
	}
	
	/**
	 * hide the results Pan for search results
	 */
	public void hideResultsPan(){
		this.resultsPan.setVisible(false);
	}
	
	/**
	 * show the results Pan for search results
	 */
	public void showResultsPan(){
		this.resultsPan.setVisible(true);
	}
	
	/**
	 * set the results shown in the resultspan
	 */
	public void setResults(List<?> resultList) {
		resultsPan.removeAll();
		SearchMaskListener listen = new SearchMaskListener();
		
		Iterator<?> itr = resultList.iterator();
		JLabel element;
		
		while(itr.hasNext()){
			element = new JLabel(((User) itr.next()).getFirstname());
			element.addMouseListener(listen);
			resultsPan.add(element);
		}
		resultsPan.validate();
		resultsPan.repaint();
	}
}
