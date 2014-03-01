package org.view.screens.EastBar;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Tim Lange
 *
 */
public class SearchMask extends JPanel {
	
	private static SearchMask mask;
	private JTextField input = new JTextField(10);;

	public static SearchMask getInstance(){
		if(mask == null){
			mask = new SearchMask();
		}
		return mask;
	}
	private SearchMask(){
		this.add(input);
	}
	
	public void showResults() {

	}

}
