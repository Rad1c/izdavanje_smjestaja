package views;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import controllers.LoginController;
import net.miginfocom.swing.MigLayout;

public class LoginView {
	JTextField txtUsername;
	JPasswordField passwordField;
	JButton btnReset;
	JButton btnLogin;
	JFrame frLogin;
	Preferences pref;
	JCheckBox chkRememberMe;

	public LoginView() {
		pref = Preferences.userNodeForPackage(this.getClass());

		Font font1 = new Font("Tahoma", Font.BOLD, 12);
		Font font2 = new Font("Tahoma", Font.PLAIN, 13);
		this.frLogin = new JFrame();
		JPanel pnlContent = new JPanel(new MigLayout());

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

		this.chkRememberMe = new JCheckBox("Remember me");
		chkRememberMe.setSelected(true);
		chkRememberMe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chkRememberMe.setFont(font2);
		pnlContent.add(chkRememberMe, "skip, wrap, gaptop 5");

		this.btnLogin = new JButton("Login");
		btnLogin.setFocusable(false);
		btnLogin.setFont(font1);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlContent.add(btnLogin, "skip, split2, grow, gaptop 5");

		this.btnReset = new JButton("Reset");
		btnReset.setFont(font1);
		pnlContent.add(btnReset, "grow, gaptop 5");
		btnReset.setFocusable(false);
		btnReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReset.setPreferredSize(new Dimension(30, 27));
		frLogin.add(pnlContent);
		frLogin.setVisible(true);
	}

	public void emptyUserPasswShowMsg() {
		if (txtUsername.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please enter a valid username", "Error!", JOptionPane.OK_OPTION);
			return;
		}

		if (passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "The Password field is required", "Error!", JOptionPane.OK_OPTION);
			return;
		}
	}

	public void setCursor(Cursor cursor) {
		frLogin.setCursor(cursor);
	}

	public void setActionListener(LoginController loginController) {
		btnReset.setActionCommand("reset");
		btnLogin.setActionCommand("login");
		btnReset.addActionListener(loginController);
		btnLogin.addActionListener(loginController);

	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setTxtUsername(JTextField txtUsername) {
		this.txtUsername = txtUsername;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JCheckBox getChkRememberMe() {
		return chkRememberMe;
	}

	public void setChkRememberMe(JCheckBox chkRememberMe) {
		this.chkRememberMe = chkRememberMe;
	}

	public JFrame getFrLogin() {
		return frLogin;
	}

}
