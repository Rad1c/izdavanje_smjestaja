package views;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controllers.AppController;
import database.DBConnection;
import models.AppModel;
import net.miginfocom.swing.MigLayout;
import user.User;

public class LoginView {
	JTextField txtUsername;
	JPasswordField passwordField;

	Preferences pref;

	public LoginView() {
		pref = Preferences.userNodeForPackage(this.getClass());

		Font font1 = new Font("Tahoma", Font.BOLD, 12);
		Font font2 = new Font("Tahoma", Font.PLAIN, 13);
		JFrame frLogin = new JFrame();
		JPanel pnlContent = new JPanel(new MigLayout());

		String usr = "";
		String pw = "";

		frLogin.setSize(430, 240);
		frLogin.setTitle("Login - Izdavanje smještaja");
		frLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frLogin.setLocationRelativeTo(null);
		frLogin.getContentPane().add(pnlContent);
		frLogin.setResizable(false);

		ImageIcon icon = new ImageIcon("images/icon.png");
		frLogin.setIconImage(icon.getImage());

		JLabel lblUsername = new JLabel("Username:  ");
		lblUsername.setFont(font2);
		pnlContent.add(lblUsername, "gapleft 30, gaptop 30");

		this.txtUsername = new JTextField(30);
		pnlContent.add(txtUsername, "wrap");

		JLabel lblpassword = new JLabel("Password:  ");
		lblpassword.setFont(font2);
		pnlContent.add(lblpassword, "gapleft 30, gaptop 10");

		this.passwordField = new JPasswordField(30);
		pnlContent.add(passwordField, "wrap, gaptop 10");

		JCheckBox chkRememberMe = new JCheckBox("Remember me");
		chkRememberMe.setFont(font2);
		pnlContent.add(chkRememberMe, "skip, wrap, gaptop 5");

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(font1);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlContent.add(btnLogin, "skip, split2, grow, gaptop 5");

		JButton btnReset = new JButton("Reset");
		btnReset.setFont(font1);
		pnlContent.add(btnReset, "grow, gaptop 5");
		btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReset.setPreferredSize(new Dimension(30, 27));
		frLogin.add(pnlContent);
		frLogin.setVisible(true);

		usr = pref.get("Email", "");
		if (usr != null)
			txtUsername.setText(usr);
		pw = pref.get("Password", "");
		if (pw != null)
			passwordField.setText(pw);

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frLogin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Connection db = DBConnection.getConnection();
				String username = txtUsername.getText();
				String password = new String(passwordField.getPassword());

				if (username.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid username", "Error!",
							JOptionPane.OK_OPTION);
					return;
				}

				if (password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "The Password field is required", "Error!",
							JOptionPane.OK_OPTION);
					return;
				}

				try {
					CallableStatement procedureStatement = db.prepareCall("{ call pisg4.spLogin(?,?) }");
					procedureStatement.setString(1, username);
					procedureStatement.setString(2, password);
					ResultSet rez = procedureStatement.executeQuery();

					if (rez.next()) {
						User user = new User((String) rez.getObject(1), (Integer) rez.getObject(2),
								(Integer) rez.getObject(3), (String) rez.getObject(4), (Integer) rez.getObject(5),
								(Integer) rez.getObject(6), (String) rez.getObject(7), (String) rez.getObject(8),
								(Boolean) rez.getObject(9), (String) rez.getObject(10));
						
						new AppController(new AppModel(user), new AppView());
						frLogin.dispose();

						if (chkRememberMe.isSelected())
							saveEmailPassword(username.toString(), password.toString());
						else
							saveEmailPassword("", "");
					} else {
						JOptionPane.showMessageDialog(null, "The username or password is incorrct!", "Error!",
								JOptionPane.OK_OPTION);
					}
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "The username or password is incorrct!", "Error!",
							JOptionPane.OK_OPTION);
				}
				frLogin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
				passwordField.setText("");
			}
		});
	}

	public void saveEmailPassword(String email, String password) {
		pref = Preferences.userNodeForPackage(this.getClass());

		pref.put("Email", email);
		pref.put("Password", password);
	}

}
