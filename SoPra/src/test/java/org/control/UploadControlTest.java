package org.control;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class UploadControlTest {

	/**
	 * Test für die UploadImage Funktion
	 */
	@Test
	public void uploadImageTest(){
		
		try {
			UploadControl.uploadImage(ClassLoader.getSystemClassLoader().getResource("placeholder.jpg").getPath(), PathControl.getInstance().getRoot(), "test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Upload fehlgeschlagen");
		}
		
		try {
			UploadControl.deleteFile(PathControl.getInstance().getRoot() + File.separator + "test.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Löschen fehlgeschlagen");
		}
		
		
	}

}
