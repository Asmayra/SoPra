package org.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class RegScreen extends JFrame{

	private JPanel contentPane;
	private JTextField username, mail, firstname, lastname, dob, city, country;
	private JPasswordField password, passwordConfirm;
	private JButton confirm, cancel;
	
	private final int MIN_WIDTH = 300;
	private final int MIN_HEIGHT = 200;
	
	
	
	public RegScreen() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initGui();
	}
	
	private void initGUI(){
		this.setMaximumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		this.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		
		
	}
	
	private JComponent initButtons(){
		
	}
	
	private JComponent initEingabe(){
		JPanel eingabePanel = new JPanel();
		JLabel nameLabel = new JLabel("Name: ");
		
	}
	
	
	

}
