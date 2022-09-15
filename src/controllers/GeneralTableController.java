package controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import enums.RowCRUD;
import models.AppModel;
import models.ColumnModel;
import views.AppView;
import views.GeneralTableView;

public class GeneralTableController implements ListSelectionListener, ActionListener {
	AppModel appModel;
	AppView appView;
	GeneralTableView generalTableView;
	InputFieldsController inputFieldsController = null;
	ListSelectionModel model;

	public GeneralTableController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		appModel.getGeneralTableModel();
		appView.getToolbarView().enableAllButtons();
		this.generalTableView = new GeneralTableView(appView.getPnlTable(), appModel.getGeneralTableModel(), this);
		this.generalTableView.addListener(this);
		appView.getToolbarView().setActionListener(this);
		appView.setGeneralTableView(this.generalTableView);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		model = generalTableView.getModel();
		if (e.getValueIsAdjusting())
			return;

		if (appModel.getRowState() != RowCRUD.READ) {
			int dlgRes = JOptionPane.showConfirmDialog(appView.getFrApp(), "Data you have entered may not be saved.",
					"Message", JOptionPane.YES_NO_CANCEL_OPTION);
			if (dlgRes != JOptionPane.YES_OPTION) {
				return;
			}
		}
		appModel.setRowState(RowCRUD.READ);

		if (!model.isSelectionEmpty()) {
			appView.getToolbarView().newRow.setEnabled(true);
			int rowNo = model.getMinSelectionIndex() + 1;
			appModel.getGeneralTableModel().setCurrentSelectedRow(rowNo);
			appView.getPnlInputFields().removeAll();
			appView.getPnlInputFields().repaint();
			appView.getToolbarView().enableAllButtons();
			appModel.getGeneralTableModel().register(generalTableView);
			appView.getStatusBarView().updateSelectedRow(rowNo, generalTableView.getTable().getRowCount());
			inputFieldsController = new InputFieldsController(appModel, appView, generalTableView);

		}
	}

	public InputFieldsController getInputFieldsController() {
		return inputFieldsController;
	}

	public void setInputFieldsController(InputFieldsController inputFieldsController) {
		this.inputFieldsController = inputFieldsController;
	}

	public ListSelectionModel getModel() {
		return model;
	}

	public void setModel(ListSelectionModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("delete") && appModel.getRowState() == RowCRUD.READ) {
			appModel.setRowState(RowCRUD.DELETE);
			int dlgRes = JOptionPane.showConfirmDialog(appView.getFrApp(), "Are you sure?", "Delete row",
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (dlgRes == JOptionPane.YES_OPTION) {
				if (appModel.getGeneralTableModel() == null)
					return;

				try {
					ArrayList<ColumnModel> columnsPk = new ArrayList<ColumnModel>(appModel.getGeneralTableModel()
							.getPrimaryKey(appModel.getGeneralTableModel().getCurrentSelectedRow()));
					boolean success = appModel.getGeneralTableModel().delete(columnsPk, appModel.getUser());

					if (success) {
						inputFieldsController.inputFieldsView.close();
						JTable table = appView.getGeneralTableView().getTable();
						table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
						appModel.getGeneralTableModel().setCurrentSelectedRow(table.getRowCount() - 1);
						JOptionPane.showMessageDialog(appView.getFrApp(), "Row was deleted.", "Message",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(appView.getFrApp(), "Row was not deleted.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					System.out.println("delete failed");
					JOptionPane.showMessageDialog(appView.getFrApp(), "Row was not deleted.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			appModel.setRowState(RowCRUD.READ);
		}

		if (e.getActionCommand().equals("new") && appModel.getRowState() == RowCRUD.READ) {
			if (inputFieldsController != null && appModel.getRowState() != RowCRUD.READ) {
				int dlgRes = JOptionPane.showConfirmDialog(appView.getFrApp(),
						"Data you have entered may not be saved.", "Message", JOptionPane.YES_NO_CANCEL_OPTION);
				if (dlgRes != JOptionPane.YES_OPTION) {
					return;
				} else {
					inputFieldsController.inputFieldsView.close();
					inputFieldsController = null;
				}
			}

			new InsertRowController(appModel, appView);
		}
	}
}
