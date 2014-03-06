package org.control;

import java.io.IOException;
import java.util.Date;
import javax.swing.JLabel;
import org.model.User;


/**
 * Steuert die Registration eines neuen Nutzers mit dem System. Ist ein Singleton
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
	
	
	
	private static RegControl instance = null;

	
	private RegControl() {
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
	 * zerstört den SingleTon
	 */
	public void destroy()
	{
		RegControl.instance = null;
	}
	
	/**
	 * Schliesst die Registration ab
	 * @param username Name des neuen Nutzers
	 * @param firstname Vorname des neuen Nutzers
	 * @param lastname Nachname des neuen Nutzers
	 * @param city Stadt des neuen Nutzers
	 * @param country Land des neuen Nutzers
	 * @param dob Geburtsdatum des neuen Nutzers
	 * @param password Unverschlüsseltes Passwort des neuen Nutzers
	 * @pre Passwort und Pflichtfelder überprüft
	 * @post Neuer Nutzer wurde zum System hinzugefügt
	 */
	public void completeRegistration(	String username,
										String firstname,
										String lastname,
										String city,
										String country,
										Date dob,
										String password		)
	{
		User newUser = new User();
		boolean accurate = true;
		
		newUser.setUsername(username);
		newUser.setFirstname(firstname);
		newUser.setLastname(lastname);
		newUser.setCity(city);
		newUser.setDob(dob);
		newUser.setCountry(country);
		newUser.createFavorites();
		
		newUser.setSalt(PasswordControl.generateSalt());
		newUser.setPassword(PasswordControl.encodePassword(password, newUser.getSalt()));
		try{
			DatabaseControl.getInstance().save(newUser);
		}catch(IOException e){
			System.out.println("Username already taken!");
			accurate = false;
		}
		
		if (accurate) this.destroy();	
		
	}
	
	
	/**
	 * Zeigt den Error auf der GUI an
	 * @param errorLabel Label auf dem der Error angezeigt wird
	 */
	public void displayError(JLabel errorLabel)
	{
		errorLabel.setText(error);
		errorLabel.setVisible(true);
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
	 * Leert den error speicher und versteckt das Label
	 * @param errorLabel  Label auf dem der Error angezeigt wurde
	 */
	public void clearError(JLabel errorLabel)
	{
		error = "";
		errorLabel.setText("");
		errorLabel.setVisible(false);
	}
	

	/**
	 * Überprüft ob die Passwörter die bei Registration übergeben werden gleich sind oder leer
	 * @param pass1 Passwort
	 * @param pass2 Passwort Bestätigung
	 * @return true wenn gleich und nicht leer, false sonst
	 */
	public boolean checkPasswords(	String pass1,
									String pass2	)
	{
		return pass1 != null && pass2 != null && !pass1.equals("") && pass1.equals(pass2);
	}
	
	@SuppressWarnings("unused")
	/**
	 * Überprüft ob alle Pflichtfelder eingegeben wurden
	 * @param username Name des neuen Nutzers
	 * @param firstname Vorname des neuen Nutzers
	 * @param lastname Nachname des neuen Nutzers
	 * @param city Stadt des neuen Nutzers
	 * @param country Land des neuen Nutzers
	 * @param dob Geburtsdatum des neuen Nutzers
	 * @param mail Mailadresse des neuen Nutzers
	 * @return true wenn alle Pflicheintrage vorhanden, false sonst
	 */
	public boolean checkRegistration(	String username,
										String firstname,
										String lastname,
										String city,
										String country,
										Date dob,
										String mail		)
	{		
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
	 * @param username Name des neuen Nutzers
	 * @return true falls schon vorhanden, false sonst
	 * @pre username nicht null
	 * @post true
	 */
	public boolean userExists(	String username	)
	{
		return DatabaseControl.getInstance().load(User.class, username) != null;
	}

}
