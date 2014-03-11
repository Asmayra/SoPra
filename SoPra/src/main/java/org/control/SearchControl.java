package org.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.model.Label;
import org.model.Song;
import org.model.User;
import org.view.screens.EastBar.EastBar;
import org.view.screens.EastBar.SearchMask;

public class SearchControl {

	
	/**
	 * generate the results to be shown
	 * @param searchKeyword String to search for
	 * @return List with results
	 */
	public void generateResults(String searchKeyword){	
		List<?> resultList = databaseQueries(searchKeyword);
				
	}
	
	/**
	 * generate the results to be shown
	 * @param searchKeyword String to search for
	 * @return List with results
	 */
	private List<?> databaseQueries(String searchKeyword){
		
		
		//User usr = (User)DatabaseController.getInstance().load(User.class, searchKeyword);
		ArrayList<ArrayList<String>> resultList = new ArrayList<ArrayList<String>>();
		
		resultList.add((ArrayList<String>) DatabaseControl.getInstance().queryForKeyword(User.class, "username", searchKeyword));
		resultList.add((ArrayList<String>) DatabaseControl.getInstance().queryForKeyword(Song.class, "title", searchKeyword));
		resultList.add((ArrayList<String>) DatabaseControl.getInstance().queryForKeyword(Label.class, "name", searchKeyword));
				
		
		return resultList;
	}
}
