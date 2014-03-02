package org.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.model.User;
import org.view.screens.EastBar.SearchMask;

public class SearchController {

	
	/**
	 * generate the results to be shown
	 * @param searchKeyword String to search for
	 * @return List with results
	 */
	public List<?> generateResults(String searchKeyword){	
		return databaseQueries(searchKeyword);
	}
	
	/**
	 * generate the results to be shown
	 * @param searchKeyword String to search for
	 * @return List with results
	 */
	private List<?> databaseQueries(String searchKeyword){
		
		
		//User usr = (User)DatabaseController.getInstance().load(User.class, searchKeyword);
		List<?> resultList = new ArrayList<String>();
		
		resultList = DatabaseController.getInstance().queryForKeyword(User.class, "username", searchKeyword);
		
		
		return resultList;
	}
}
