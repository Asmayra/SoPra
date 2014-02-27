package org.control;

import org.junit.Test;
import org.junit.Assert;

/**
 * first simple Unit test.
 */
public class TestMain
{
	@Test
	public void testPrintHelloWorld() {
 
		Assert.assertEquals(Main.getHelloWorld(), "Hello World");
 
	}
}
