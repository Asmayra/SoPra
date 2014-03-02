package org.view.screens.EastBar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.control.listener.SearchMaskListener;

/**
 * 
 * @author Tim Lange
 *
 */
public class SearchMask extends JPanel {
	
	private static SearchMask mask;
	private JTextField input = new JTextField(10);
	private JButton cancelButton;

	public static SearchMask getInstance(){
		if(mask == null){
			mask = new SearchMask();
		}
		return mask;
	}
	private SearchMask(){
		SearchMaskListener listen = new SearchMaskListener();
		
		//this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.input.setFocusable(true);
		this.input.requestFocusInWindow();
		this.input.addKeyListener(listen);
		this.add(input, BorderLayout.NORTH);
		
		Icon icon = UIManager.getIcon("OptionPane.errorIcon");
		this.cancelButton = new JButton(icon);
		this.cancelButton.setPreferredSize(new Dimension(25, 20));
		this.add(this.cancelButton, BorderLayout.NORTH);
		this.cancelButton.addActionListener(listen);
		
	}
	
	/**
	 * get the text currently stored in the search text field
	 * @return String with Searh input
	 */
	public String getSearchInput(){
		return this.input.getText();
	}
	
	public void resetSearchInput(){
		this.input.setText("");
	}

}
