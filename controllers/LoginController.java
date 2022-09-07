package controllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import models.*;
import views.AppView;
import views.LoginView;

public class LoginController implements ActionListener {
	LoginModel loginModel;
	LoginView loginView;
	JPasswordField passwordField;
	JTextField txtUsername;
	
	public LoginController(LoginModel loginModel, LoginView loginView) {
		this.loginModel = loginModel;
		this.loginView = loginView;
		loginView.setActionListener(this);
		this.passwordField = this.loginView.getPasswordField();
		this.txtUsername = this.loginView.getTxtUsername();
		String[] usernamePassword = loginModel.getSavedUsernamPassword();
		loginView.getTxtUsername().setText(usernamePassword[0]);
		loginView.getPasswordField().setText(usernamePassword[1]);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String password = new String(passwordField.getPassword());
		String username = txtUsername.getText();
		if(e.getActionCommand().equals("reset")) {
			this.txtUsername.setText("");
			this.passwordField.setText("");
		}
		
		if(e.getActionCommand().equals("login")) {
			loginView.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			if(password.isEmpty() || username.isEmpty()) {
				loginView.emptyUserPasswShowMsg();
				return;
			}
			
			if(this.loginModel.chechLoginData(username, password)) {
				loginView.getFrLogin().dispose();
				new AppController(new AppModel(loginModel.getUser()), new AppView());
				
				if(loginView.getChkRememberMe().isSelected())
					loginModel.saveEmailPassword(username, password);
				else
					loginModel.saveEmailPassword("", "");
			}
			else {
				JOptionPane.showMessageDialog(null, "The username or password is incorrct!", "Error!",
						JOptionPane.OK_OPTION);
			}
		}
		loginView.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	
}
