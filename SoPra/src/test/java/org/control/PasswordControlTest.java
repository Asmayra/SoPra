package org.control;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordControlTest {

	@Test
	public void SaltGenerateTest() {
		PasswordControl pwControl = new PasswordControl();
		assertFalse((pwControl.generateSalt()).equals(pwControl.generateSalt()));
		
	}
	

}
