package org.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * Erzeugt das Loginfenster
 * @author Michael Pfennings, Mattias Schoenke
 *
 */

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	
	private JTextField loginText;
	private JPasswordField passwordText;
	
	private final int MIN_WIDTH = 300;
	private final int MIN_HEIGHT = 200;
	
	
	public LoginScreen(){
		//this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initGui();
	}
	
	/**
	 * Initialisiert das Gui
	 */
	private void initGui()
	{
		this.setMaximumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(initEingabe(), BorderLayout.CENTER);
		
		contentPane.add(initButtons(), BorderLayout.SOUTH);
		
		this.add(contentPane);
	}
	
	/**
	 * Erzeugt das "Eingabe"-Panel mit der Eingabemaske
	 * @return
	 */
	private JComponent initEingabe()
	{
		JPanel eingabe = new JPanel();
		eingabe.setLayout(new BorderLayout());
		
		JPanel textFields = new JPanel();
		GridLayout textFieldsLayout = new GridLayout(0,1);
		
		textFields.setLayout(textFieldsLayout);
		
		loginText = new JTextField();
		
		passwordText = new JPasswordField();
		
		textFields.add(loginText);
		textFields.add(passwordText);

		eingabe.add(textFields, BorderLayout.CENTER);
		
		JPanel labels = new JPanel();
		GridLayout labelsLayout = new GridLayout(0,1);
		labels.setLayout(labelsLayout);

		JLabel loginLabel = new JLabel("Nutzer:");
		
		JLabel passwordLabel = new JLabel("Passwort:");

		labels.add(loginLabel);
		labels.add(passwordLabel);
		
		eingabe.add(labels, BorderLayout.WEST);

		return eingabe;
		
	}
	/**
	 * Erzeugt das "buttons"-Panel und platziert die Buttons
	 * @return das "buttons"-Panel
	 */
	private JComponent initButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		
		JButton loginButton = new JButton("Login");
		JButton registerButton = new JButton("Registrieren");
		
		buttons.add(loginButton);
		buttons.add(registerButton);
		
		
		return buttons;
	}
}
