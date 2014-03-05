package org.view.screens.Center;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.control.DatabaseController;
import org.control.LoginControl;
import org.control.listener.SettingsControlListener;
import org.model.User;

/**
 * 
 * @author Tim Lange
 *
 */
public class SettingsScreen extends JPanel{
	
	private JPanel profileDataPanel = new JPanel();
	private HashMap<String, JTextField> inputFieldMap = new HashMap<String,JTextField>();
	private User benutzer;
	
	private static SettingsScreen screen;
	
	public static SettingsScreen getInstance(){
		if(screen == null){
			screen = new SettingsScreen();
		}
		return screen;
	}
	private SettingsScreen(){
		this.setUpProfileDataFields();
		
		//this.benutzer = (User) DatabaseController.getInstance().load(User.class, LoginControl.getInstance().getCurrentUser().getUsername());
		this.benutzer = (User) DatabaseController.getInstance().load(User.class, "q");
		
		this.insertDataToFields();
	}
	
	private void insertDataToFields(){		
		((JTextField)this.inputFieldMap.get("Vorname: ")).setText(benutzer.getFirstname());
		((JTextField)this.inputFieldMap.get("Nachname: ")).setText(benutzer.getLastname());
		//((JTextField)this.inputFieldMap.get("Email: ")).setText(benutzer.getEmail());
		//((JTextField)this.inputFieldMap.get("Geburtsdatum: ")).setText(benutzer.getDob());
		((JTextField)this.inputFieldMap.get("Stadt: ")).setText(benutzer.getCity());
		((JTextField)this.inputFieldMap.get("Land: ")).setText(benutzer.getCountry());
		((JTextField)this.inputFieldMap.get("altes Passwort: ")).setText(benutzer.getPassword());
		//((JTextField)this.inputFieldMap.get("neues Passwort: ")).setText(benutzer.getFirstname());
		//((JTextField)this.inputFieldMap.get("neues Passwort wiederholen: ")).setText(benutzer.getFirstname());
	}
	
	public void saveDataToDatabase(){
		benutzer.setFirstname(((JTextField)this.inputFieldMap.get("Vorname: ")).getText());
		benutzer.setLastname(((JTextField)this.inputFieldMap.get("Nachname: ")).getText());
		//benutzer.setEmail((JTextField)this.inputFieldMap.get("Email: ")).getText());
		//benutzer.setDob(((JTextField)this.inputFieldMap.get("Geburtsdatum: ")).getText());
		benutzer.setCity(((JTextField)this.inputFieldMap.get("Stadt: ")).getText());
		benutzer.setCountry(((JTextField)this.inputFieldMap.get("Land: ")).getText());
		benutzer.setPassword(((JTextField)this.inputFieldMap.get("altes Passwort: ")).getText());
		//((JTextField)this.inputFieldMap.get("neues Passwort: ")).getText();
		//((JTextField)this.inputFieldMap.get("neues Passwort wiederholen: ")).getText();
		try {
			DatabaseController.getInstance().save(benutzer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setUpProfileDataFields(){
		this.profileDataPanel.setLayout(new BoxLayout(this.profileDataPanel, BoxLayout.PAGE_AXIS));
		
		final String[] labels = {"Vorname: ", "Nachname: ", "Email: ", "Geburtsdatum: ", "Stadt: ", "Land: ", "altes Passwort: ", "neues Passwort: ", "neues Passwort wiederholen: "};
	    int labelsLength = labels.length;
		for (int i=0; i < labelsLength; i++){
			JPanel dataRow = new JPanel();
			dataRow.setLayout(new FlowLayout(2));
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			//l.setSize(new Dimension(30, 10));
			dataRow.add(l);
	        JTextField textField = new JTextField(20);
	        this.inputFieldMap.put(labels[i], textField);
	        l.setLabelFor(textField);
	        dataRow.add(textField);
	        this.profileDataPanel.add(dataRow);
		}
		
		JPanel dataRow = new JPanel();
		dataRow.setLayout(new FlowLayout());
		
		JButton speichern = new JButton("Speichern");
		speichern.addActionListener(new SettingsControlListener(this));
		dataRow.add(speichern);
		 this.profileDataPanel.add(dataRow);
		 
		this.add(this.profileDataPanel, BorderLayout.NORTH);
	}
	
	public enum Attributfelder{
		VORNAME { public String toString() {return "Vorname";}},
		NACHNAME { public String toString() {return "Nachname";}},
		EMAIL { public String toString() {return "Email";}},
		GEBURTSDATUM { public String toString() {return "Geburtsdatum";}},
		STADT { public String toString() {return "Stadt";}},
		LAND { public String toString() {return "Land";}},
		ALTESPASSWORT { public String toString() {return "altes Passwort";}},
		NEUESPASSWORT { public String toString() {return "neues Passwort";}},
		NEUESPASSWORTWIEDERHOLUNG { public String toString() {return "neues Passwort wiederholen";}},
	}

	public String getFieldData(Attributfelder fieldname){
		return this.inputFieldMap.get(fieldname+": ").getText();
	}

}
