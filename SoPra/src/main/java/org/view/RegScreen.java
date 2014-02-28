package org.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.control.RegControl;

/**
 * Erstellt das Registrierungsfensters
 * @author Michael Pfennings, Mattias Schoenke 
 *
 */

public class RegScreen extends JFrame{

	private JPanel contentPane;
	private JTextField usernameText, mailText, firstnameText, lastnameText, dobText, cityText, countryText;
	private JPasswordField passwordText, passwordConfirmText;
	private JButton confirmButton, cancelButton;
	private JLabel errorLabel;
	
	private final int MIN_WIDTH = 400;
	private final int MIN_HEIGHT = 300;
	
	
	/**
	 * Konstruktor zu RegScreen
	 */
	public RegScreen() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initGui();
	}
	
	
	/**
	 * Initialisiert die Gui
	 */
	private void initGui(){
		this.setMaximumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(initButtons(), BorderLayout.SOUTH);
		contentPane.add(initEingabe(), BorderLayout.CENTER);
		
		errorLabel = new JLabel("");
		errorLabel.setVisible(false);
		contentPane.add(errorLabel, BorderLayout.NORTH);
		
		this.add(contentPane);
		}
	
	
	/**
	 * Erzeugt das Buttonpanel mit dem "Bestätigen"- und "Abbrechen"-Button
	 * @return das Buttonpanel
	 */
	
	private JComponent initButtons(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		confirmButton = new JButton("Absenden");
		confirmButton.addActionListener(new RegConfirmButtonListener());
		
		cancelButton = new JButton("Abbrechen");
		cancelButton.addActionListener(new RegCancelButtonListener());
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		return buttonPanel;
		
		
	}
	/**
	 * Erzeugt das Eingabepanel mit der Eingabemaske
	 * @return das Eingabepanel
	 */
	
	private JComponent initEingabe(){
		JPanel eingabePanel = new JPanel();
		eingabePanel.setLayout(new GridLayout(0,2));
		JLabel usernameLabel = new JLabel("Username: ");
		JLabel firstnameLabel = new JLabel("Vorname: ");
		JLabel lastnameLabel = new JLabel("Nachname: ");
		JLabel passwordLabel = new JLabel("Passwort: ");
		JLabel passwordConfirmLabel = new JLabel("Password bestätigen: ");
		JLabel mailLabel = new JLabel("Mail: ");
		JLabel dobLabel = new JLabel("Geburtstag: ");
		JLabel cityLabel = new JLabel("Stadt: ");
		JLabel countryLabel = new JLabel("Land: ");
		
		usernameText = new JTextField();
		firstnameText = new JTextField();
		lastnameText = new JTextField();
		passwordText = new JPasswordField();
		passwordConfirmText = new JPasswordField();
		mailText = new JTextField();
		dobText = new JTextField();
		cityText = new JTextField();
		countryText = new JTextField();
		
		eingabePanel.add(usernameLabel);
		eingabePanel.add(usernameText);
		eingabePanel.add(firstnameLabel);
		eingabePanel.add(firstnameText);
		eingabePanel.add(lastnameLabel);
		eingabePanel.add(lastnameText);
		eingabePanel.add(cityLabel);
		eingabePanel.add(cityText);
		eingabePanel.add(countryLabel);
		eingabePanel.add(countryText);
		eingabePanel.add(dobLabel);
		eingabePanel.add(dobText);
		eingabePanel.add(mailLabel);
		eingabePanel.add(mailText);
		eingabePanel.add(passwordLabel);
		eingabePanel.add(passwordText);
		eingabePanel.add(passwordConfirmLabel);
		eingabePanel.add(passwordConfirmText);
		return eingabePanel;
		
		
		
	}

	
	// Getter um Textfeld auzulesen
	public String getUsername()
	{
		return usernameText.getText();
	}
	
	public String getFirstname()
	{
		return firstnameText.getText();
	}
	
	public String getLastname()
	{
		return lastnameText.getText();
	}
	
	public String getCountry()
	{
		return countryText.getText();
	}
	
	public String getCity()
	{
		return cityText.getText();
	}
	
	public String getDob()
	{
		return dobText.getText();
	}
	
	public String getMail()
	{
		return mailText.getText();
	}
	
	public String getPassword()
	{
		return new String(passwordText.getPassword());
	}
	
	public String getPasswordConfirm()
	{
		
		return new String(passwordConfirmText.getPassword());
	}

	
	private class RegConfirmButtonListener implements ActionListener {
		
		public RegConfirmButtonListener() {
			// TODO Auto-generated constructor stub
		}

		public void actionPerformed(ActionEvent arg0) {
			RegControl control = RegControl.getInstance();
			boolean success = true;
			
			control.clearError(errorLabel);
			
			String username = getUsername();
			String firstname = getFirstname();
			String lastname = getLastname();
			String city = getCity();
			String country = getCountry();
			String dob = getDob();
			String mail = getMail();
			String password = getPassword();
			String passwordConfirm = getPasswordConfirm();
			
			if(!control.checkRegistration(username, firstname, lastname, city, country, dob, mail))
			{
				control.addEntryError();
				success = false;
			}
			
			if(!control.checkPasswords( password, passwordConfirm ))
			{
				control.addPasswordError();
				success = false;
			}
			
			//Warten bis Hibernate läuft
//			if(!control.userExists( username ))
//			{
//				control.addUserExistsError();
//				success = false;
//			}
			
			if(success)
				control.completeRegistration(username, firstname, lastname, city, country, null, password);
			else
				control.displayError(errorLabel);
		} 
	}
	
	private class RegCancelButtonListener implements ActionListener {

		public RegCancelButtonListener() {
			// TODO Auto-generated constructor stub
		}

		public void actionPerformed(ActionEvent arg0) {
			RegControl.getInstance().destroy();

		}

	}
}
