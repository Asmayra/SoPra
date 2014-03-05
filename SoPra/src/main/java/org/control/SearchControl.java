package org.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		EastBar.getInstance().resultsPanHeight(resultList.size()*14);
		EastBar.getInstance().setResults(resultList);
	}
	
	/**
	 * generate the results to be shown
	 * @param searchKeyword String to search for
	 * @return List with results
	 */
	private List<?> databaseQueries(String searchKeyword){
		
		
		//User usr = (User)DatabaseController.getInstance().load(User.class, searchKeyword);
		List<?> resultList = new ArrayList<String>();
		
		resultList = DatabaseControl.getInstance().queryForKeyword(User.class, "username", searchKeyword);
		
		
		return resultList;
	}
}
