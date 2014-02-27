package org.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	 * Fügt den Actionlistener zum "Bestätigen"-Button hinzu, falls dieser noch keinen Actionlistener hat
	 * @param listener Actionlistener des "Bestätigen"-Buttons
	 * @return true falls der Actionlistener hinzugefügt wurde, sonst false
	 */
	
	public boolean addListenerToConfirmButton(ActionListener listener)
	{
		if(confirmButton.getActionListeners() != null && confirmButton.getActionListeners().length > 0)
			return false;
		else
		{
			confirmButton.addActionListener(listener);
			return true;
		}
	}
	
	/**
	 * Fügt den Actionlistener zum "Abbrechen"-Button hinzu, falls dieser noch keinen Actionlistener hat
	 * @param listener Actionlistener des "Abbrechen"-Buttons
	 * @return true falls der Actionlistener hinzugefügt wurde, sonst false
	 */
	public boolean addListenerToCancelButton(ActionListener listener)
	{
		if(cancelButton.getActionListeners() != null && cancelButton.getActionListeners().length > 0)
			return false;
		else
		{
			cancelButton.addActionListener(listener);
			return true;
		}
	}
	
	/**
	 * Erzeugt das Buttonpanel mit dem "Bestätigen"- und "Abbrechen"-Button
	 * @return das Buttonpanel
	 */
	
	private JComponent initButtons(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		confirmButton = new JButton("Absenden");
		cancelButton = new JButton("Abbrechen");
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
	
	/**
	 * Gibt Fehlertext aus, wenn ein Fehler aufgetreten ist
	 * @param error Fehlertext der ausgegeben wird
	 */
	
	public void displayError(String error){
		errorLabel.setText(error);
		errorLabel.setVisible(true);
		
	}
	
	/**
	 * Leert den Errorstring und versteckt das Errorlabel
	 */
	public void clearError()
	{
		errorLabel.setText("");
		errorLabel.setVisible(false);
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

}
