package views;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import controllers.AppController;
import database.DBConnection;
import models.AppModel;
import models.ColumnModel;
import models.GeneralTableModel;
import models.RowModel;
import parsers.XMLParser;
import user.User;

public class LoginView {
	JTextField txtUsername;
	JPasswordField passwordField;

	Preferences pref;
	
	public LoginView()
	{
		pref = Preferences.userNodeForPackage(this.getClass());
		
		JFrame frLogin = new JFrame();
		JPanel panel = new JPanel();
		
		String usr = "";
		String pw = "";
		
		frLogin.setSize(350, 230);
		frLogin.setTitle("Login");
		frLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frLogin.setLocationRelativeTo(null);
		frLogin.getContentPane().add(panel);
		frLogin.setResizable(false);
		
		panel.setLayout(null);
		
		ImageIcon icon = new ImageIcon("images/icon.png");
		frLogin.setIconImage(icon.getImage());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(25,0,300,98);
		
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10,20,100,25);
		panel1.add(lblUsername);
		
		this.txtUsername = new JTextField(20);
		txtUsername.setBounds(100,20,165,25);
		panel1.add(txtUsername);
		
		JLabel lblpassword = new JLabel("Password:");
		lblpassword.setBounds(10,60,100,25);
		panel1.add(lblpassword);
		
		this.passwordField = new JPasswordField();
		passwordField.setBounds(100,60,165,25);
		panel1.add(passwordField);
		
		JCheckBox chkRememberMe = new JCheckBox("Remember me");
		chkRememberMe.setBounds(88,105,150,25);
		chkRememberMe.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(chkRememberMe);
		
		panel.add(panel1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(80, 137, 80, 25);
		panel.add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(170, 137, 80, 25);
		panel.add(btnReset);
		frLogin.setVisible(true);
		
		usr = pref.get("Email","");
		if(usr!=null)
			txtUsername.setText(usr);
		pw = pref.get("Password", "");
		if(pw!=null)
			passwordField.setText(pw);
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frLogin.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				Connection db = DBConnection.getConnection();
				String username = txtUsername.getText();
				String password = new String(passwordField.getPassword());

				if(username.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please enter a valid username", "Error!",JOptionPane.OK_OPTION);
					return;
				}
				
				if(password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "The Password field is required", "Error!",JOptionPane.OK_OPTION);
					return;
				}
				
				try {
					CallableStatement procedureStatement = db.prepareCall("{ call pisg4.spLogin(?,?) }");
					procedureStatement.setString(1, username);
					procedureStatement.setString(2, password);
					ResultSet rez = procedureStatement.executeQuery();
					
					if(rez.next()) {
						User user = new User((String)rez.getObject(1), (Integer)rez.getObject(2), (Integer)rez.getObject(3), (String)rez.getObject(4),(Integer) rez.getObject(5),
								(Integer)rez.getObject(6), (String)rez.getObject(7), (String)rez.getObject(8), (Boolean)rez.getObject(9), (String)rez.getObject(10));
						AppModel appModel = new AppModel(user);
						AppView appView = new AppView();
						AppController appController = new AppController(appModel, appView);
						frLogin.dispose();
						
						if(chkRememberMe.isSelected())
							saveEmailPassword(username.toString(),password.toString());
						else
							saveEmailPassword("","");
					}
					else {
						JOptionPane.showMessageDialog(null, "Neispravno korisnicko ime ili lozinka!", "Greska!",JOptionPane.OK_OPTION);
					}
				} catch (SQLException e2) {
					System.out.println(e2.toString());
				}
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

	public void saveEmailPassword(String email, String password)
	{
		pref = Preferences.userNodeForPackage(this.getClass());
		
		pref.put("Email",email);
		pref.put("Password",password);
	}
	
}
 