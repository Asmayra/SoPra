package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import org.control.LoginControl;
import org.view.LoginScreen;
import org.view.MainScreen;

public class LoginScreenLoginButtonListener implements ActionListener, KeyListener{

	private LoginScreen login;
	
	public LoginScreenLoginButtonListener(LoginScreen login) {
		this.login = login;
	}

	public void actionPerformed(ActionEvent e) {
		this.login();
	}

	private void login(){
		if (LoginControl.getInstance().checkLogin(login.getLoginText(), login.getPassword())){
			if(LoginControl.getInstance().getCurrentUser().getRights().equals("Admin")){
				MainScreen.getInstance().setUpAdminLayout();
			}else{
				System.out.println("UserLayout aufgesetzt");
				MainScreen.getInstance().setUpUserLayout();
				login.resetPassword();
				
			}
			login.dispose();
		}
		
		else {
			login.displayErrorLabel();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			this.login();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
