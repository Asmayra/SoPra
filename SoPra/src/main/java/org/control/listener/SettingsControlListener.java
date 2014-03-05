package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.control.DatabaseControl;
import org.control.LoginControl;
import org.control.PasswordControl;
import org.model.User;
import org.view.screens.Center.SettingsScreen;
import org.view.screens.Center.SettingsScreen.Attributfelder;

/**
 * 
 * @author Tim Lange
 *
 */
public class SettingsControlListener implements ActionListener{

	private SettingsScreen settingsScreen;
	private User benutzer;
	
	public SettingsControlListener(SettingsScreen setScreen) {
		this.settingsScreen = setScreen;
		this.benutzer = (User) DatabaseControl.getInstance().load(User.class, LoginControl.getInstance().getCurrentUser().getUsername());
	}
	public void actionPerformed(ActionEvent e) {
		this.saveDataToDatabase();
	}
	
	private void saveDataToDatabase(){
		
		Date date = new Date();
		
        try {
            SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
            date = sdfToDate.parse(this.settingsScreen.getFieldData(Attributfelder.GEBURTSDATUM));
        } catch (ParseException ex2) {
            ex2.printStackTrace();
            return;
        }
        
		this.benutzer.setFirstname(this.settingsScreen.getFieldData(Attributfelder.VORNAME));
		this.benutzer.setLastname(this.settingsScreen.getFieldData(Attributfelder.NACHNAME));
		this.benutzer.setEMail(this.settingsScreen.getFieldData(Attributfelder.EMAIL));
		this.benutzer.setDob(date);
		this.benutzer.setCity(this.settingsScreen.getFieldData(Attributfelder.STADT));
		this.benutzer.setCountry(this.settingsScreen.getFieldData(Attributfelder.LAND));
		
		if(this.settingsScreen.getFieldData(Attributfelder.ALTESPASSWORT).equals("") &&
				(this.settingsScreen.getFieldData(Attributfelder.NEUESPASSWORT).equals("")) &&
				(this.settingsScreen.getFieldData(Attributfelder.NEUESPASSWORTWIEDERHOLUNG).equals(""))){
		} else{
			if(this.checkPassword() == false){
				return;
			} else{
				this.benutzer.setPassword(PasswordControl.encodePassword(this.settingsScreen.getFieldData(Attributfelder.NEUESPASSWORT), this.benutzer.getSalt()));
			}
		}

		try {
			DatabaseControl.getInstance().save(this.benutzer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkPassword(){

		if(benutzer.getPassword().equals(PasswordControl.encodePassword(this.settingsScreen.getFieldData(Attributfelder.ALTESPASSWORT), benutzer.getSalt()))){
			if(this.settingsScreen.getFieldData(Attributfelder.NEUESPASSWORT).equals(this.settingsScreen.getFieldData(Attributfelder.NEUESPASSWORTWIEDERHOLUNG))){
				return true;
			} else{
				System.out.println("passwortwiederholung fehlerhaft");
				JOptionPane.showMessageDialog(null, "Passwortwiederholung fehlerhaft", "Fehler", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} else{
			System.out.println("altes passwort fehlerhaft");
			return false;
		}
		
	}

}
