package org.control;

import org.model.User;

public class SoPra {

	private static User currentUser;
	
	
	public static User getCurrentUser()
	{
		return currentUser;
	}
	
	
	public static void setCurrentUser(User user)
	{
		currentUser = user;
	}
}
