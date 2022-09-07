package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.AppController;

public class AppView {
	Font font1 = new Font("Tahoma", Font.BOLD, 13);
	Font font2 = new Font("Tahoma", Font.BOLD, 12);
	JLabel logout;
	JFrame frApp;
	JLabel username;
	JPanel pnlHeader;
	JPanel pnlTree;
	JPanel pnlTable;
	JPanel pnlInputFields;
	JPanel pnlUserLogout;

	MenuView menuView;
	ToolBarView toolbarView;
	StatusBarView statusBarView;
	TreeView treeView;
	GeneralTableView generalTableView;

	public AppView() {
		this.frApp = new JFrame();
		username = new JLabel();
		logout = new JLabel("  Logout   ");
		logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ImageIcon icon = new ImageIcon("images/icon.png");
		frApp.setTitle("Izdavanje smjestaja");
		frApp.setIconImage(icon.getImage());
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int ScrHeight = toolkit.getScreenSize().height;
		int ScrWidth = toolkit.getScreenSize().width;
		frApp.setLayout(new BorderLayout());
		frApp.getContentPane().setBackground(Color.WHITE);

		pnlHeader = new JPanel();
		pnlHeader.setBackground(Color.decode("#FDFBFB"));
		pnlHeader.setLayout(new BorderLayout());

		pnlUserLogout = new JPanel();

		pnlUserLogout.setLayout(new BorderLayout());
		JPanel pnlUser = new JPanel();

		pnlUser.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		JLabel userImg = new JLabel("");
		userImg.setIcon(new ImageIcon("images/user_small.png"));
		pnlUser.add(userImg);

		this.username.setFont(font2);
		this.logout.setFont(font1);

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
		pnlTree.setBackground(Color.decode("#fffefe"));
		pnlInputFields.setPreferredSize(new Dimension(640, (int) (ScrHeight * 0.35)));
		pnlTree.setPreferredSize(new Dimension(250, 200));
		frApp.add(panel);

		pnlHeader.add(pnlUserLogout, BorderLayout.PAGE_START);
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

	public void setActionListener(AppController appController) {
		logout.addMouseListener(appController);
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

	public MenuView getMenuView() {
		return menuView;
	}

	public void setMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public ToolBarView getToolbarView() {
		return toolbarView;
	}

	public void setToolbarView(ToolBarView toolbarView) {
		this.toolbarView = toolbarView;
	}

	public StatusBarView getStatusBarView() {
		return statusBarView;
	}

	public void setStatusBarView(StatusBarView statusBarView) {
		this.statusBarView = statusBarView;
	}

	public GeneralTableView getGeneralTableView() {
		return generalTableView;
	}

	public void setGeneralTableView(GeneralTableView generalTableView) {
		this.generalTableView = generalTableView;
	}

	public JPanel getPnlUserLogout() {
		return pnlUserLogout;
	}

}
