package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controllers.LoginController;
import models.LoginModel;
import views.LoginView;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		new LoginController(new LoginModel(), new LoginView());
	}
}
