package org.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.control.LoginControl;
import org.control.RegControl;


/**
 * Erzeugt das Loginfenster. Ist ein Singleton
 * @author Michael Pfennings, Mattias Schoenke
 *
 */

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	
	private JTextField loginText;
	private JPasswordField passwordText;
	private JLabel errorLabel;
	
	private final int MIN_WIDTH = 300;
	private final int MIN_HEIGHT = 150;

	
	
	public String getLoginText() {
		return loginText.getText();
	}

	public String getPassword() {
		return new String(passwordText.getPassword());
	}

	public LoginScreen(){
		super("Login");
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
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		this.setLocation( ( (int)width/2 - MIN_WIDTH/2) ,( (int) height/2 - MIN_HEIGHT/2 ) );
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(initEingabe(), BorderLayout.NORTH);
		
		contentPane.add(initButtons(), BorderLayout.CENTER);
		
		contentPane.add(initErrorLabel(), BorderLayout.SOUTH);
		
		this.add(contentPane);
	}
	
	/**
	 * Erzeugt das "Eingabe"-Panel mit der Eingabemaske
	 * @return eingabe Das "Eingabe"-Panel
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
		loginButton.addActionListener(new LoginScreenLoginButtonListener());
		JButton registerButton = new JButton("Registrieren");
		registerButton.addActionListener(new LoginScreenRegisterButtonListener());
		
		buttons.add(loginButton);
		buttons.add(registerButton);
		
		
		return buttons;
	}
	
	
	/**
	 * Erzeugt das "error"-Panel mit dem errorLabel
	 * @return das "error-"Panel
	 */
	
	private JComponent initErrorLabel(){
		JPanel error = new JPanel();
		errorLabel = new JLabel("Username oder Passwort falsch!");
		errorLabel.setVisible(false);
		error.add(errorLabel);
		
		return error;
	}
	
	/**
	 * Der Buttonlistener des Registierungsbuttons auf dem Loginscreen
	 *
	 */
	private class LoginScreenRegisterButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			RegScreen.getInstance().setVisible(true);
			RegScreen.getInstance().toFront();
			
		}
		
	}
	/**
	 * Der Buttonlistener des Loginbuttons auf dem Loginscreen
	 *
	 */
	private class LoginScreenLoginButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if (LoginControl.getInstance().checkLogin(getLoginText(), getPassword())){
				MainScreen.getInstance().setUpUserLayout();
				LoginScreen.this.dispose();
			}
			
			else {
				errorLabel.setVisible(true);
			}
		}
		
	}
}
