package org.control;

import org.model.User;

/**
 * Steuert den Login eines Nutzers. Ist ein Singleton
 * @author Michael Pfennings, Mattias Schoenke
 *
 */

public class LoginControl {
	
	private static LoginControl instance = null;
	private User currentUser = null;
	private LoginControl(){
		
	}
	
	/**
	 * Gibt den aktuellen Nutzer zurück
	 * @return aktuell eingeloggter Nutzer
	 */
	public User getCurrentUser(){
		return currentUser;
	}
	

	/**
	 * Setzt den aktuellen Nutzer zurück, aka kein Nutzer eingeloggt
	 */
	public void resetCurrentUser(){
		this.currentUser = null;
	}
	
	/**
	 * Singleton
	 * @return Instanz des Singletons
	 */
	public static LoginControl getInstance(){
		if(instance == null)
		{
			instance = new LoginControl();
		}
		
		return instance;
	}
	
	/**
	 * Überprüft ob das Passwort zum mit dem des angegebenen Benutzers übereinstimmt
	 * @param nutzer Username der beim einloggen angegeben wurde
	 * @param passwort Passwort das beim einloggen angegeben wurde
	 * @return true wenn Login erfolgreich, false sonst
	 * @pre true
	 * @post Der Nutzer hat sich eingeloggt
	 */
	public boolean checkLogin(String username, String password)
	{
		
		if( ( username == null || username.equals("") ) || ( password == null || password.equals("") ) )
			return false;
		User loginUser = (User) DatabaseControl.getInstance().load(User.class, username);
		
		if (loginUser != null ){
			String salt = loginUser.getSalt();
		
			
			String pass = PasswordControl.encodePassword(password, salt);
			
			String userPass = loginUser.getPassword();
			currentUser = loginUser;
			return PasswordControl.comparePasswords(pass, userPass);	
				
			
		}
		else{
			return false;
		}
		
		
		
	}

}
