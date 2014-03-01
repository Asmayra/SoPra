package org.view.screens.EastBar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import org.control.listener.MoreDiscoverButtonListener;

/**
 * 
 * @author Tim Lange
 *
 */
public class EastBar extends JLayeredPane{

	private JButton moreButton = new JButton("mehr");
	private JPanel resultsPan = new JPanel();
	
	public EastBar() {
		//this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.setBorder(BorderFactory.createLineBorder(Color.red));
		
		//this.setPreferredSize(new Dimension(300, 310));
		
		SearchMask.getInstance().setBounds(40, 0, 100, 25);
		this.add(SearchMask.getInstance());

		DiscoverMiniScreen.getInstance().setBounds(20, 20, (int) DiscoverMiniScreen.getInstance().getPreferredSize().getWidth(), (int) DiscoverMiniScreen.getInstance().getPreferredSize().getHeight());
		this.add(DiscoverMiniScreen.getInstance());
		
		moreButton.setLocation(0, 400);
		this.add(moreButton);
		moreButton.addActionListener(new MoreDiscoverButtonListener());
		
		this.layTest();
	}
	
	private void layTest(){
		resultsPan.setLayout(new BoxLayout(resultsPan, BoxLayout.PAGE_AXIS));
		resultsPan.add(new JLabel("text1"));
		resultsPan.add(new JLabel("text2"));
		resultsPan.add(new JLabel("text3"));
		resultsPan.add(new JLabel("text4"));
		resultsPan.add(new JLabel("text5"));
		resultsPan.add(new JLabel("text6"));
		resultsPan.add(new JLabel("text7"));
		resultsPan.add(new JLabel("text8"));
		resultsPan.add(new JLabel("text9"));
		//this.add(resultsPan, new Integer(100));
		resultsPan.setBackground(Color.gray);
		resultsPan.setBounds( 40, 25,  100, 200 );
	}
}
