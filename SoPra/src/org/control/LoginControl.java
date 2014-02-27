package org.control;

public class LoginControl {
	
	public boolean checkLogin(String nutzer, String passwort)
	{
		//getUser(nutzer)
		
		String salt = "";
		
		//getSalt()
		
		int pass = PasswordControl.encodePassword(passwort, salt);
		
		int userPass = 0;
		
		return PasswordControl.comparePasswords(pass, userPass);		
	}

}
