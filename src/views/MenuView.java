package views;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controllers.GeneralTableController;
import controllers.InputFieldsController;
import controllers.MenuController;
import controllers.ToolBarController;

public class MenuView extends JMenuBar {
	private static final long serialVersionUID = 1L;

	JMenuBar mnubAppMenu;
	public JFrame frame;
	private JMenu mnuFile, mnuEdit, mnuHelp;
	private JMenuItem mniSwitchXML, mniExit, mniFirst, mniNext, mniPrevious, mniLast, mniNew, mniEdit, mniDelete,
			mniAccept, mniCancel, mniReport, mniAbout;

	public MenuView(JFrame frame) {

		this.frame = frame;

		mnubAppMenu = new JMenuBar();
		mnuFile = new JMenu("File");
		mnuEdit = new JMenu("Edit");
		mnuHelp = new JMenu("Help");
		
		mnuFile.setMnemonic(KeyEvent.VK_F);
		mnuEdit.setMnemonic(KeyEvent.VK_E);
		mnuHelp.setMnemonic(KeyEvent.VK_H);

		mniSwitchXML = new JMenuItem("Switch XML");
		mniExit = new JMenuItem("Exit");
		mniFirst = new JMenuItem("first row");
		mniNext = new JMenuItem("next");
		mniPrevious = new JMenuItem("Previous");
		mniLast = new JMenuItem("Last");
		mniNew = new JMenuItem("new");
		mniEdit = new JMenuItem("edit");
		mniDelete = new JMenuItem("delete");
		mniAccept = new JMenuItem("accept");
		mniCancel = new JMenuItem("cancel");
		mniReport = new JMenuItem("report");
		mniAbout = new JMenuItem("About");
		
		mniSwitchXML.setAccelerator(KeyStroke.getKeyStroke('G', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		mniNew.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		mniEdit.setAccelerator(KeyStroke.getKeyStroke('E', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		mniDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0));
		mniAccept.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		mniCancel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
		mniReport.setAccelerator(KeyStroke.getKeyStroke('R', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));

		mnuFile.add(mniSwitchXML);
		mnuFile.addSeparator();
		mnuFile.add(mniExit);

		mnuEdit.add(mniFirst);
		mnuEdit.add(mniNext);
		mnuEdit.add(mniPrevious);
		mnuEdit.add(mniLast);
		mnuEdit.addSeparator();
		mnuEdit.add(mniNew);
		mnuEdit.add(mniEdit);
		mnuEdit.add(mniDelete);
		mnuEdit.addSeparator();
		mnuEdit.add(mniAccept);
		mnuEdit.add(mniCancel);
		mnuEdit.add(mniReport);

		mnuHelp.add(mniAbout);

		mniSwitchXML.setPreferredSize(new Dimension(150, 25));
		mniReport.setPreferredSize(new Dimension(150, 25));
		mniAbout.setPreferredSize(new Dimension(150, 25));

		mnubAppMenu.add(mnuFile);
		mnubAppMenu.add(mnuEdit);
		mnubAppMenu.add(mnuHelp);

		setActionCommands();
		frame.setJMenuBar(mnubAppMenu);
	}

	private void setActionCommands() {
		mniSwitchXML.setActionCommand("switch XML");
		mniExit.setActionCommand("exit");
		mniFirst.setActionCommand("first");
		mniNext.setActionCommand("next");
		mniPrevious.setActionCommand("previous");
		mniLast.setActionCommand("last");
		mniNew.setActionCommand("new");
		mniEdit.setActionCommand("edit");
		mniDelete.setActionCommand("delete");
		mniAccept.setActionCommand("accept");
		mniCancel.setActionCommand("cancel");
		mniReport.setActionCommand("report");
		mniAbout.setActionCommand("about");
	}

	public void setActionListeners(MenuController menuController) {
		mniSwitchXML.addActionListener(menuController);
		mniExit.addActionListener(menuController);
		mniAbout.addActionListener(menuController);
	}
	
	public void setActionListeners(ToolBarController toolBarController) {
		mniFirst.addActionListener(toolBarController);
		mniPrevious.addActionListener(toolBarController);
		mniNext.addActionListener(toolBarController);
		mniLast.addActionListener(toolBarController);
		mniReport.addActionListener(toolBarController);
	}
	
	public void setActionListeners(InputFieldsController inputFieldsController) {
		mniEdit.addActionListener(inputFieldsController);
		mniAccept.addActionListener(inputFieldsController);
		mniCancel.addActionListener(inputFieldsController);
	}
	
	public void setActionListener(GeneralTableController generalTableController) {
		mniNew.addActionListener(generalTableController);
		mniDelete.addActionListener(generalTableController);
	}
	

}
