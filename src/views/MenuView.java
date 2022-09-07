package views;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controllers.MenuController;

public class MenuView extends JMenuBar {
	private static final long serialVersionUID = 1L;

	JMenuBar mnubAppMenu;
	public JFrame frame;
	private JMenu mnuFile, mnuEdit, mnuHelp;
	private JMenuItem mniSwitchXML, mniExit, mniFirst, mniNext, mniPrevious, mniLast, mniNew, mniEdit, mniDelete,
			mniAccpet, mniCancel, mniReport, mniAbout;

	public MenuView(JFrame frame) {

		this.frame = frame;

		mnubAppMenu = new JMenuBar();
		mnuFile = new JMenu("File");
		mnuEdit = new JMenu("Edit");
		mnuHelp = new JMenu("Help");

		mniSwitchXML = new JMenuItem("Switch XML");
		mniExit = new JMenuItem("Exit");
		mniFirst = new JMenuItem("First");
		mniNext = new JMenuItem("Next");
		mniPrevious = new JMenuItem("Previous");
		mniLast = new JMenuItem("Last");
		mniNew = new JMenuItem("New");
		mniEdit = new JMenuItem("Edit");
		mniDelete = new JMenuItem("Delete");
		mniAccpet = new JMenuItem("Accept");
		mniCancel = new JMenuItem("Cancel");
		mniReport = new JMenuItem("Report");
		mniAbout = new JMenuItem("About");

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
		mnuEdit.add(mniAccpet);
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
		mniAccpet.setActionCommand("accept");
		mniCancel.setActionCommand("cancel");
		mniReport.setActionCommand("report");
		mniAbout.setActionCommand("about");
	}

	public void setActionListeners(MenuController menuController) {
		mniSwitchXML.addActionListener(menuController);
		mniExit.addActionListener(menuController);
		mniFirst.addActionListener(menuController);
		mniNext.addActionListener(menuController);
		mniPrevious.addActionListener(menuController);
		mniLast.addActionListener(menuController);
		mniNew.addActionListener(menuController);
		mniEdit.addActionListener(menuController);
		mniDelete.addActionListener(menuController);
		mniAccpet.addActionListener(menuController);
		mniCancel.addActionListener(menuController);
		mniReport.addActionListener(menuController);
		mniAbout.addActionListener(menuController);
	}

}
