package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppView {
	JFrame frApp;
	JLabel username;
	JLabel logout;
	Font font = new Font("Tahoma", Font.BOLD,12);
	JPanel pnlHeader;
	JPanel pnlTree;
	JPanel pnlTable;
	JPanel pnlInputFields;

	public AppView() {
		this.frApp = new JFrame();
		username = new JLabel();
		logout = new JLabel("Logout");

		ImageIcon icon = new ImageIcon("images/icon.png");
		frApp.setTitle("Izdavanje smjestaja");
		frApp.setIconImage(icon.getImage());
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int ScrHeight = toolkit.getScreenSize().height;
		int ScrWidth = toolkit.getScreenSize().width;
		frApp.setLayout(new BorderLayout());
		frApp.getContentPane().setBackground(Color.WHITE);
		
		pnlHeader=new JPanel();
        pnlHeader.setBackground(Color.decode("#FDFBFB"));  
        pnlHeader.setLayout(new BorderLayout());
        
        JPanel pnlUserLogout = new JPanel();
        
        pnlUserLogout.setLayout(new BorderLayout());
        JPanel pnlUser = new JPanel();
        pnlUserLogout.add(new JLabel("")); pnlUserLogout.add(new JLabel(""));
        pnlUser.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        JLabel userImg = new JLabel("");
        userImg.setIcon(new ImageIcon("images/user_small.png"));
        pnlUser.add(userImg);
        
        this.username.setFont(font);
        this.logout.setFont(font);
        
        logout.addMouseListener(new MouseListener()
        		{
        	public void mousePressed(MouseEvent e)
        	{
        		
        	}

			@Override
			public void mouseClicked(MouseEvent e) {
				
			frApp.dispose();
			new LoginView();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
        		});
        pnlUser.add(this.username);
        pnlUser.add(new JLabel("    "));
        pnlUser.add(logout);
        pnlUserLogout.add(pnlUser);
        pnlHeader.add(pnlUser);
        
        
        JPanel pnlContent = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        JPanel pnlContainer = new JPanel(new BorderLayout());
        this.pnlInputFields = new JPanel();
        pnlInputFields.setBackground(Color.decode("#a0d9af"));
        this.pnlTable = new JPanel();
        pnlTable.setBackground(Color.decode("#565782"));
        this.pnlTree = new JPanel();
        //pnlTree.setBackground(Color.decode("#d16041"));
        pnlInputFields.setPreferredSize(new Dimension(640, (int)(ScrHeight*0.35)));
        pnlTree.setPreferredSize(new Dimension(250, 200));
        frApp.add(panel);
		

        
        pnlHeader.add(pnlUserLogout, BorderLayout.PAGE_END);
        pnlContent.add(pnlTable, BorderLayout.CENTER);
        pnlContent.add(pnlInputFields, BorderLayout.PAGE_END);
        pnlContainer.add(pnlTree, BorderLayout.WEST);
        pnlContainer.add(pnlContent);
        frApp.add(pnlHeader, BorderLayout.NORTH);
        frApp.add(pnlContainer, BorderLayout.CENTER);
        
		frApp.setExtendedState(frApp.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frApp.setMinimumSize(new Dimension(ScrWidth, ScrHeight));
		frApp.setVisible(true);
		frApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setUsername(String username) {
		this.username.setText(username);
	}

	public JPanel getPnlTree() {
		return pnlTree;
	}

	public JPanel getPnlTable() {
		return pnlTable;
	}

	public JPanel getPnlInputFields() {
		return pnlInputFields;
	}
	
	public JPanel getPnlHeader() {
		return pnlHeader;
	}

	public JFrame getFrApp() {
		return frApp;
	}

	public void setFrApp(JFrame frApp) {
		this.frApp = frApp;
	}

}

