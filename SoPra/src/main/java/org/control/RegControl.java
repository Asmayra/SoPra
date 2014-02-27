package org.control;

import java.io.IOException;
import java.util.Date;

import org.control.listener.RegCancelButtonListener;
import org.control.listener.RegConfirmButtonListener;
import org.model.User;
import org.view.RegScreen;

/**
 * Steuert die Registration eines neuen Nutzers mit dem System
 * @author Michael Pfennings, Mattias Schoenke
 *
 */
public class RegControl {
	
	private final String PASSWORD_ERROR = "Passwörter stimmen nicht überein!";
	private final String ENTRY_ERROR = "Nicht alle Pflichfelder ausgefüllt!";
	private final String EXISTS_ERROR = "Nutzername existiert bereits!";
	
	//Angabe ob Pflichteintrag. true = pflicht
	private final boolean USERNAME = true;
	private final boolean FIRSTNAME = true;
	private final boolean LASTNAME = true;
	private final boolean CITY = false;
	private final boolean COUNTRY = false;
	private final boolean DOB= true;
	private final boolean EMAIL = true;
	
	private String error;
	
	private RegScreen screen;
	
	
	private static RegControl instance = null;

	
	private RegControl() {
		screen = new RegScreen();
		screen.addListenerToConfirmButton(new RegConfirmButtonListener());
		screen.addListenerToCancelButton(new RegCancelButtonListener());
		error = "";
	}
	
	/**
	 * Gibt die Instanz des Singletons zurück
	 * @return Instanz des SingleTons
	 */
	public static RegControl getInstance()
	{
		if(instance == null)
		{
			instance = new RegControl();
		}
		
		return instance;
	}
	
	/**
	 * Versteckt die GUI und zerstört den SingleTon
	 */
	public void destroy()
	{
		screen.setVisible(false);
		screen = null;
		RegControl.instance = null;
	}
	
	/**
	 * Schliesst die Registration ab
	 */
	public void completeRegistration()
	{
		User newUser = new User();
		boolean accurate = true;
		
		newUser.setUsername(screen.getUsername());
		newUser.setFirstname(screen.getFirstname());
		newUser.setLastname(screen.getLastname());
		newUser.setCity(screen.getCity());
		newUser.setCountry(screen.getCountry());
		
		newUser.setSalt(PasswordControl.generateSalt());
		newUser.setPassword(PasswordControl.encodePassword(screen.getPassword(), newUser.getSalt()));
		try{
			DatabaseController.getInstance().save(newUser);
		}catch(IOException e){
			System.out.println("USername already taken!");
			accurate = false;
		}
		
		if (accurate) this.destroy();	
		
	}
	
	
	/**
	 * zeigt die Registrations maske
	 */
	public void showRegistration()
	{
		screen.setVisible(true);
	}
	
	/**
	 * Zeigt den Error auf der GUI an
	 */
	public void displayError()
	{
		screen.displayError(error);
	}
	
	/**
	 * Fügt einen Error in den Passwörtern zum error speicher hinzu
	 */
	public void addPasswordError()
	{
		if(!error.equals(""))
			error = error + "\n" + PASSWORD_ERROR;
		else
			error = error + PASSWORD_ERROR;
	}
	
	/**
	 * Fügt einen Error in der Eingabe zum error speicher hinzu
	 */
	public void addEntryError()
	{
		if(!error.equals(""))
			error = error + "\n" + ENTRY_ERROR;
		else
			error = error + ENTRY_ERROR;
	}
	
	/**
	 * Fügt einen Error zum error speicher
	 */
	public void addUserExistsError()
	{
		if(!error.equals(""))
			error = error + "\n" + EXISTS_ERROR;
		else
			error = error + EXISTS_ERROR;
	}
	
	/**
	 * Leert den error speicher
	 */
	public void clearError()
	{
		error = "";
		screen.clearError();
	}
	

	/**
	 * Überprüft ob die Passwörter die bei Registration übergeben werden gleich sind oder leer
	 * @return true wenn gleich und nicht leer, false sonst
	 */
	public boolean checkPasswords()
	{
		String pass1 = screen.getPassword();
		String pass2 = screen.getPasswordConfirm();
		return pass1 != null && pass2 != null && !pass1.equals("") && pass1.equals(pass2);
	}
	
	@SuppressWarnings("unused")
	/**
	 * Überprüft ob alle Pflichtfelder eingegeben wurden
	 * @return true wenn alle Pflicheintrage vorhanden, false sonst
	 */
	public boolean checkRegistration()
	{
		String username = screen.getUsername();
		String firstname = screen.getFirstname();
		String lastname = screen.getLastname();
		String city = screen.getCity();
		String country = screen.getCountry();
		String dob = screen.getDob();
		String mail = screen.getMail();
		
		
		if(USERNAME && (username == null || username.equals("")))
			return false;
		
		if(FIRSTNAME && (firstname == null || firstname.equals("")))
			return false;
		
		if(LASTNAME && (lastname == null || lastname.equals("")))
			return false;
		
		if(CITY && (city == null || city.equals("")))
			return false;
		
		if(COUNTRY && (country == null || country.equals("")))
			return false;
		
		if(DOB && (dob == null || dob.equals("")))
			return false;
		
		if(EMAIL && (mail == null || mail.equals("")))
			return false;
		
		return true;
	}
	
	/**
	 * Überprüft ob ein Nutzer bereits existiert
	 * @return true falls schon vorhanden, false sonst
	 */
	public boolean userExists()
	{
		return DatabaseController.getInstance().load(User.class, screen.getUsername()) != null;
	}

}
