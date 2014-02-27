package org.control;

import java.util.Random;

public class PasswordControl {
	
	
	public static String encodePassword(String pass, String salt)
	{
		String password = pass + salt;
		
		return "" + password.hashCode();
	}
	
	public static boolean comparePasswords(int pass1, int pass2)
	{
		return pass1 == pass2;
	}
	
	public static String generateSalt()
	{
		String chars = "abcdefghijklmnopqrstuvwxyz1234567890!§$%&/()=?";
		
		String salt = "";
		
		Random rand = new Random();
		
		for(int i = 0; i < 10; i++)
		{
			salt = salt + chars.toCharArray()[rand.nextInt(chars.length())];
		}
		
		return salt;
	}

}
