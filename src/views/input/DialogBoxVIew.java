package views.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controllers.DialogBoxController;
import models.GeneralTableModel;
import net.miginfocom.swing.MigLayout;
import views.GeneralTableView;

public class DialogBoxVIew {
	JDialog dialog;
	String tableName = "Dialog";
	JPanel mainPanel;
	GeneralTableView generalTableView;
	JButton btnOk;
	JButton btnCancel;
	Dimension dialogDim;
	JFrame frame;
	
	public DialogBoxVIew(String tableName, JFrame frame) {
		this.frame = frame;
		this.tableName = tableName;
		init();
	}

	@SuppressWarnings("removal")
	protected void init() {
		this.dialog = new JDialog(frame, tableName, false);
		this.dialog.getContentPane().add(createPane());
		this.dialog.pack();
		Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
		dialogDim = new Dimension((int) (scrnSize.getWidth() * 0.5), (int) (scrnSize.getHeight() * 0.52));
		this.dialog.setSize(dialogDim.width, dialogDim.height);
		this.dialog.setLocation(new Double((scrnSize.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(),
				new Double((scrnSize.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());
		this.dialog.setResizable(false);
	}

	protected Container createPane() {
		JPanel container = new JPanel(new MigLayout());
		mainPanel = new JPanel(new BorderLayout());

		mainPanel.setBackground(Color.decode("#efefef"));
		container.setBackground(Color.decode("#efefef"));
		container.add(mainPanel, "dock center");
		return container;

	}
	
	public void setTable(GeneralTableModel tableModel) {
		mainPanel.setPreferredSize(new Dimension(dialogDim.width, (int)(dialogDim.height*0.80)));
		generalTableView = new GeneralTableView(mainPanel, tableModel, null);
		generalTableView.setPositionCenter();
		addButtons();
	}
	
	protected void addButtons() {
		Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
		JPanel pnlButtons = new JPanel(new MigLayout());
		pnlButtons.setSize((int)dialogDim.width, (int)(dialogDim.height * 0.55));
		pnlButtons.setBackground(Color.decode("#efefef"));
		btnOk = new JButton("OK");
		btnOk.setFocusable(false);
		btnCancel = new JButton("Cancel");
		btnCancel.setFocusable(false);
		pnlButtons.add(btnOk, "gapleft 50");
		pnlButtons.add(btnCancel);
		btnOk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainPanel.add(pnlButtons, BorderLayout.PAGE_END);
	}
	
	public void addBtnActionListeners(DialogBoxController boxController) {
		btnOk.setActionCommand("ok");
		btnCancel.setActionCommand("cancel");
		btnOk.addActionListener(boxController);
		btnCancel.addActionListener(boxController);
	}

	public void show() {
		if (this.dialog == null) {
			init();
		}
		this.dialog.setVisible(true);
	}


	public void close() {
		this.dialog.removeAll();
		this.dialog.invalidate();
		this.dialog.dispose();
		this.dialog.setVisible(false);
	}

	public GeneralTableView getGeneralTableView() {
		return generalTableView;
	}

	public void setGeneralTableView(GeneralTableView generalTableView) {
		this.generalTableView = generalTableView;
	}

	public JDialog getDialog() {
		return dialog;
	}

}
