package org.control;

import static org.junit.Assert.*;

import org.junit.Test;

public class PasswordControlTest {

	@Test
	public void SaltGenerateTest() {
		PasswordControl pwControl = new PasswordControl();
		assertTrue((pwControl.generateSalt()) != (pwControl.generateSalt()));
		
	}
	

}
