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
	
	public static EastBar getInstance(){
		if(bar == null){
			bar = new EastBar();
		}
		
		return bar;
	}
	
	/**
	 * Zerstört den Singleton
	 */
	public static void destroy()
	{
		bar = null;
	}
	
	private EastBar() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setPreferredSize(new Dimension(250, 310));
		
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

	}
	
	
}
