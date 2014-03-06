package org.control;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnitTest der PasswordControl Klasse
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class PasswordControlTest {
	
	/**
	 * Testet ob 2 generierte Salts nicht gleich sind.
	 */
	@Test
	public void SaltGenerateTest() {
		PasswordControl pwControl = new PasswordControl();
		assertFalse((pwControl.generateSalt()).equals(pwControl.generateSalt()));
		
	}
	

}
