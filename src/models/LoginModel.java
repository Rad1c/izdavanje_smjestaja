package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import database.DBConnection;
import user.User;

public class LoginModel {
	User user = null;
	Preferences pref = Preferences.userNodeForPackage(this.getClass());

	public String[] getSavedUsernamPassword() {
		String[] usernamePassword = { "", "" };
		String usr, pw;

		usr = pref.get("Email", "");
		if (usr != null)
			usernamePassword[0] = usr;
		pw = pref.get("Password", "");
		if (pw != null)
			usernamePassword[1] = pw;

		return usernamePassword;
	}

	public void saveEmailPassword(String email, String password) {
		pref = Preferences.userNodeForPackage(this.getClass());

		pref.put("Email", email);
		pref.put("Password", password);
	}

	public boolean chechLoginData(String username, String password) {
		boolean login = false;
		Connection db = DBConnection.getConnection();
		if(!isValid(username)) {
			JOptionPane.showMessageDialog(null, "Please enter a valid username", "Error!", JOptionPane.OK_OPTION);
			return false;
		}
		try {
			CallableStatement procedureStatement = db.prepareCall("{ call pisg4.spLogin(?,?) }");
			procedureStatement.setString(1, username);
			procedureStatement.setString(2, password);
			ResultSet rez = procedureStatement.executeQuery();

			if (rez.next()) {
				this.user = new User((String) rez.getObject(1), (Integer) rez.getObject(2), (Integer) rez.getObject(3),
						(String) rez.getObject(4), (Integer) rez.getObject(5), (Integer) rez.getObject(6),
						(String) rez.getObject(7), (String) rez.getObject(8), (Boolean) rez.getObject(9),
						(String) rez.getObject(10));
				login = true;
			}
		} catch (SQLException e) {
			System.out.println("(class: LoginModel) " + e.toString());
		}
		return login;
	}

	public static boolean isValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public User getUser() {
		return user;
	}
}
