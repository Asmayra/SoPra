package org.control;

public class PasswordControl {
	
	
	public static int encodePassword(String pass, String salt)
	{
		String password = pass + salt;
		
		return password.hashCode();
	}
	
	public static boolean comparePasswords(int pass1, int pass2)
	{
		return pass1 == pass2;
	}

}
