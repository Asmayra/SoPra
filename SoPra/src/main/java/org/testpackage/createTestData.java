package org.testpackage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.control.DatabaseControl;
import org.model.Song;
import org.model.User;

public class createTestData {

	public static void main(String[] args){
		createTestData test = new createTestData();
		//test.createUser();
		test.createSongs();
		System.out.println("done");
	}
	
	
	public void createSongs(){
		Song song = new Song("interpret", "title", "");
		
		try {
			DatabaseControl.getInstance().save(song);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void createUser(){
		User standard = new User();
		standard.setUsername("q");
		standard.setEMail("muster@gmail.com");
		standard.setCity("mustercity");
		standard.setCountry("mustercountry");
		Date date;
		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		standard.setDob(date);
		standard.setFirstname("Max");
		standard.setLastname("mustermann");
		standard.setImagePath("avatar.jpg");
		standard.setPassword("127648922");
		standard.setSalt("B150M)gWN!");
		standard.setRights("StandardUser");
		
		User admin = new User();
		admin.setUsername("a");
		admin.setEMail("muster@gmail.com");
		admin.setCity("mustercity");
		admin.setCountry("mustercountry");

		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		admin.setDob(date);
		admin.setFirstname("Max");
		admin.setLastname("mustermann");
		admin.setImagePath("avatar.jpg");
		admin.setPassword("127648922");
		admin.setSalt("B150M)gWN!");
		admin.setRights("Admin");
		
		User artist = new User();
		artist.setUsername("k");
		artist.setEMail("muster@gmail.com");
		artist.setCity("mustercity");
		artist.setCountry("mustercountry");

		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		artist.setDob(date);
		artist.setFirstname("Max");
		artist.setLastname("mustermann");
		artist.setImagePath("avatar.jpg");
		artist.setPassword("127648922");
		artist.setSalt("B150M)gWN!");
		artist.setRights("Artist");
		
		User labelmanager = new User();
		labelmanager.setUsername("l");
		labelmanager.setEMail("muster@gmail.com");
		labelmanager.setCity("mustercity");
		labelmanager.setCountry("mustercountry");

		try {
			date = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN).parse("01.01.2000");
		} catch (ParseException e) {
			date = null;
			e.printStackTrace();
		}
		labelmanager.setDob(date);
		labelmanager.setFirstname("Max");
		labelmanager.setLastname("mustermann");
		labelmanager.setImagePath("avatar.jpg");
		labelmanager.setPassword("127648922");
		labelmanager.setSalt("B150M)gWN!");
		labelmanager.setRights("LabelManager");
		
		
		try {
			DatabaseControl.getInstance().save(standard);
			DatabaseControl.getInstance().save(admin);
			DatabaseControl.getInstance().save(artist);
			DatabaseControl.getInstance().save(labelmanager);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
