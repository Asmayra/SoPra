package org.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.control.listener.CloseFrameListener;
import org.control.listener.MessagePopUpScreenSendButtonListener;

public class MessagePopUpScreen extends JFrame {
	
	
	private static MessagePopUpScreen instance = null;
	
	private JPanel contentPane;
	private JTextField recipientText, subjectText;
	private JTextArea messageArea;
	private JButton sendButton, cancelButton;
	
	private JLabel errorLabel;
	
	private final int TEXTAREA_WIDTH = 200;
	private final int TEXTAREA_HEIGHT = 200;


	private MessagePopUpScreen(){
		super("Nachricht versenden");
		initGui();
		
	}
	
	
	public static MessagePopUpScreen getInstance(){
		if(instance == null)
		{
			instance = new MessagePopUpScreen();
		}
		
		return instance;
	}
	
	
	
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
	
	
	private JComponent initHeaders(){
		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new GridLayout(2,2));
		
		JLabel recipentLabel = new JLabel("Empfänger: ");
		headerPanel.add(recipentLabel);
		
		recipientText = new JTextField();
		headerPanel.add(recipientText);
		
		JLabel subjectLabel = new JLabel("Betreff: ");
		headerPanel.add(subjectLabel);
		
		subjectText = new JTextField();
		headerPanel.add(subjectText);
		
		return headerPanel;
		
	}
	
	
	private JComponent initTextArea(){
		
		messageArea = new JTextArea();
		messageArea.setEditable(true);
		messageArea.setPreferredSize(new Dimension(TEXTAREA_WIDTH, TEXTAREA_HEIGHT));
		
		return messageArea;
		
	}
	
	private JComponent initButtons(){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		sendButton = new JButton("Senden");
		sendButton.addActionListener(new MessagePopUpScreenSendButtonListener());
		
		
		cancelButton = new JButton("Abbrechen");
		cancelButton.addActionListener(new CloseFrameListener(this));
		
		errorLabel = new JLabel("Nutzer existiert nicht!");
		errorLabel.setVisible(false);
		
		buttonPanel.add(errorLabel);
		buttonPanel.add(sendButton);
		buttonPanel.add(cancelButton);
		
		
		return buttonPanel;
		
	}
	
	public String getMessageText(){
		return messageArea.getText();
	}
	
	
	public String[] getRecipients(){
		return recipientText.getText().split(",");
	}
	
	public String getSubject(){
		return subjectText.getText();
	}
	
	public void displayError()
	{
		errorLabel.setVisible(true);
	}
	
	public void setRecipient(String recip)
	{
		recipientText.setText(recip);
	}
	

}
