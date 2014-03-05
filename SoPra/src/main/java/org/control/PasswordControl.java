package org.control;

import java.util.Random;

/**
 * Stellt statische Methoden zur Verwaltung von Passwörtern bereit
 * @author Michael Pfennings, Mattias Schoenke
 *
 */

public class PasswordControl {
	
	/**
	 * Verschlüsselt das Passwort
	 * @param pass unverschlüsseltes Passwort
	 * @param salt Salt zur Verschlüsselung
	 * @return verschlüsseltes Passwort
	 * @pre pass und salt nicht null
	 * @post true
	 */
	public static String encodePassword(String pass, String salt)
	{
		String password = pass + salt;
		
		return "" + password.hashCode();
	}
	
	/**
	 * Vergleicht zwei Passwörter
	 * @param pass1 Passwort
	 * @param pass2 Passwort
	 * @return True wenn gleich und keines der beiden null, sonst false
	 */
	public static boolean comparePasswords(String pass1, String pass2)
	{
		return pass1 != null && pass2 != null && pass1.equals(pass2);
	}
	
	/**
	 * Generiert einen zufälligen Salt aus folgenden Zeichen: "abcdefghijklmnopqrstuvwxyz1234567890!§$%&/()=?"
	 * @return zufälligen Salt
	 */
	public static String generateSalt()
	{
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!§$%&/()=?";
		
		String salt = "";
		
		Random rand = new Random();
		
		for(int i = 0; i < 10; i++)
		{
			salt = salt + chars.toCharArray()[rand.nextInt(chars.length())];
		}
		
		return salt;
	}

}
