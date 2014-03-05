package org.control.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.control.LoginControl;
import org.view.LoginScreen;
import org.view.MainScreen;

public class LoginScreenLoginButtonListener implements ActionListener {

	private LoginScreen login;
	
	public LoginScreenLoginButtonListener(LoginScreen login) {
		this.login = login;
	}

	public void actionPerformed(ActionEvent e) {
		if (LoginControl.getInstance().checkLogin(login.getLoginText(), login.getPassword())){
			if(LoginControl.getInstance().getCurrentUser().getRights()=="Admin"){
				MainScreen.getInstance().setUpAdminLayout();
			}else{
				System.out.println("UserLayout aufgesetzt");
				MainScreen.getInstance().setUpUserLayout();
				
			}
			login.dispose();
		}
		
		else {
			login.displayErrorLabel();
		}
	}
}
