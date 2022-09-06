package main;

import views.LoginView;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("OptionPane.yesButtonText", "Yes");
			UIManager.put("OptionPane.noButtonText", "No");
			UIManager.put("OptionPane.cancelButtonText", "Cancel");

		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		new LoginView();
	}
}
