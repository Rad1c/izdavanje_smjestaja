package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.GeneralTableModel;
import models.RowModel;
import views.input.DialogBoxVIew;

public class DialogBoxController implements ActionListener, ListSelectionListener {
	DialogBoxVIew dialogBox;
	GeneralTableModel tableModel = null;
	int selectedRow = -99;
	RowModel row = null;
	String tableName;

	public DialogBoxController(String tableName, JFrame frame) {
		this.tableName = tableName;
		dialogBox = new DialogBoxVIew(tableName, frame);
		dialogBox.getDialog().setModal(true);
		tableModel = new GeneralTableModel(tableName);
		dialogBox.setTable(tableModel);
		dialogBox.getGeneralTableView().addListener(this);
		dialogBox.addBtnActionListeners(this);
		dialogBox.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ok")) {
			if (selectedRow > 0) {
				row = tableModel.getRow(selectedRow);
				dialogBox.close();
				tableModel = null;
			}
		}
		if (e.getActionCommand().equals("cancel")) {
			dialogBox.close();
			tableModel = null;
			selectedRow = -99;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel model = dialogBox.getGeneralTableView().getModel();

		if (e.getValueIsAdjusting())
			return;

		if (!model.isSelectionEmpty()) {
			selectedRow = model.getMinSelectionIndex() + 1;
		}
	}

	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public RowModel getRow() {
		return row;
	}

	public String getTableName() {
		return tableName;
	}
}
