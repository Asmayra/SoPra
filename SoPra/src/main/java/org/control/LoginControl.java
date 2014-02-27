package org.control;
/**
 * Steuert den Login eines Nutzers
 * @author Michael Pfennings, Mattias Schoenke
 *
 */

public class LoginControl {
	
	
	
	/**
	 * Überprüft ob das Passwort zum mit dem des angegebenen Benutzers übereinstimmt
	 * @param nutzer Username der beim einloggen angegeben wurde
	 * @param passwort Passwort das beim einloggen angegeben wurde
	 * @return
	 */
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
