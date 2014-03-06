package org.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.control.listener.CloseFrameListener;
import org.control.listener.MessagePopUpScreenSendButtonListener;
import org.control.listener.TicketScreenSendButtonListener;


/**
 * PopUp zum versenden von Nachrichten. Ist ein Singleton.
 * @author Michael Pfennings, Mattias Schoenke
 *
 */

public class TicketScreen extends JFrame {
	
	
	private static TicketScreen instance = null;
	
	private JPanel contentPane;
	private JComboBox categoryBox; 
	private JTextArea messageArea;
	private JButton sendButton, cancelButton;
	private JLabel errorLabel;
		
	private final int TEXTAREA_WIDTH = 450;
	private final int TEXTAREA_HEIGHT = 450;


	
	/**
	 * Konstruktor von MessagePopUpScreen
	 */
	private TicketScreen(){
		super("Ticket erstellen");
		initGui();
		
	}
	
	
	
	public static TicketScreen getInstance(){
		if(instance == null)
		{
			instance = new TicketScreen();
		}
		
		return instance;
	}
	
	/**
	 * Initialisiert die Gui
	 */
	
	private void initGui(){
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(initHeaders(),BorderLayout.NORTH);
		contentPane.add(initTextArea(), BorderLayout.CENTER);
		contentPane.add(initButtons(), BorderLayout.SOUTH);
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
	}
	
	
	/**
	 * Erzeugt das headerPanel mit den JTextFields Empfänger und Betreff
	 * @return das headerPanel
	 */
	private JComponent initHeaders(){
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new FlowLayout());
		
		JLabel categoryLabel = new JLabel("Kategorie:  ");
		headerPanel.add(categoryLabel);
		
		String[] categories = {"", "Account", "Legal"};
		categoryBox = new JComboBox(categories);
		categoryBox.setSelectedIndex(0);
		headerPanel.add(categoryBox);
		return headerPanel;
		
	}
	
	/**
	 * Erzeugt die JTextArea.
	 * @return die MessageArea
	 */
	private JComponent initTextArea(){
		
		messageArea = new JTextArea();
		messageArea.setEditable(true);
		messageArea.setPreferredSize(new Dimension(TEXTAREA_WIDTH, TEXTAREA_HEIGHT));
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		
		return messageArea;
		
	}
	
	
	/**
	 * Erzeugt das ButtonPanel mit den Buttons Senden und Abbrechen.
	 * @return das ButtonPanel
	 */
	private JComponent initButtons(){
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,2));
		
		sendButton = new JButton("Senden");
		sendButton.addActionListener(new TicketScreenSendButtonListener());
		buttonPanel.add(sendButton);
		
		cancelButton = new JButton("Abbrechen");
		cancelButton.addActionListener(new CloseFrameListener(this));
		buttonPanel.add(cancelButton);
		
		errorLabel = new JLabel("Bitte Kategorie auswählen!");
		errorLabel.setVisible(false);
		buttonPanel.add(errorLabel);
		
		
		return buttonPanel;
		
	}
	
	public String getCategory(){
		return categoryBox.getSelectedItem().toString();
	}
	
	public String getTicketText(){
		return messageArea.getText();
	}
	

	public void setTicket(String message){
		messageArea.setText(message);
	}
	
	public void displayErrorLabel(){
		errorLabel.setVisible(true);
	}
	
	public void hideErrorLabel(){
		errorLabel.setVisible(false);
	}
	
	public void resetGui(){
		errorLabel.setVisible(false);
		categoryBox.setSelectedIndex(0);
		messageArea.setText("");
	}

}
