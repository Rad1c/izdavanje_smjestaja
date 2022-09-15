package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import enums.RowCRUD;
import helpers.EnableDisableComponents;
import models.AppModel;
import models.ColumnModel;
import models.RowModel;
import state.EditingState;
import state.WorkingState;
import views.AppView;
import views.GeneralTableView;
import views.InputFieldsView;
import views.input.FieldView;

public class InputFieldsController implements ActionListener {
	AppModel appModel;
	AppView appView;
	InputFieldsView inputFieldsView;
	JPanel pnlInputFields;
	String tableName;
	ListSelectionModel model;
	GeneralTableView generalTableView;

	public InputFieldsController(AppModel appModel, AppView appView, GeneralTableView generalTableView) {
		this.appModel = appModel;
		this.generalTableView = generalTableView;
		tableName = appModel.getGeneralTableModel().getTableName();
		this.appView = appView;
		int selRow = appModel.getGeneralTableModel().getCurrentSelectedRow();
		inputFieldsView = new InputFieldsView(appView.getPnlInputFields(),
				appModel.getGeneralTableModel().getRow(selRow), true);
		this.pnlInputFields = inputFieldsView.getPnlInputFields();
		inputFieldsView.getInputHeaderView().setActionListeners(this);
		appView.getToolbarView().addActionListenersEdit(this);
		this.inputFieldsView.setBtnLinkedFieldsActList(this);
		appView.setInputFieldsView(inputFieldsView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("edit") && appModel.getRowState() == RowCRUD.READ) {
			appModel.setRowState(RowCRUD.UPDATE);
			this.inputFieldsView.getInputHeaderView().getBtnCancel().setEnabled(true);
			this.inputFieldsView.getInputHeaderView().getBtnAccept().setEnabled(true);
			EnableDisableComponents.setPanelEnabled(inputFieldsView.getPnlFields(), true);
			appModel.setApplicationState(new EditingState());
			appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
		}
		if (e.getActionCommand().equals("cancel") && appModel.getRowState() == RowCRUD.UPDATE) {
			appModel.setRowState(RowCRUD.READ);
			this.pnlInputFields.removeAll();
			this.pnlInputFields.repaint();
			appModel.setApplicationState(new WorkingState());
			appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
		}
		if (e.getActionCommand().equals("accept") && appModel.getRowState() == RowCRUD.UPDATE) {
			boolean valuesCorrect = true;
			appModel.setRowState(RowCRUD.READ);
			for (FieldView fw : inputFieldsView.getFields()) {
				if (!fw.checkInputFields(appModel.getGeneralTableModel().getColumnDataLength()))
					valuesCorrect = false;
			}

			if (valuesCorrect) {
				RowModel row = inputFieldsView.getRow();

				ArrayList<ColumnModel> columnsUpdate = new ArrayList<ColumnModel>(row.getColumns());

				for (int i = 0; i < columnsUpdate.size(); i++) {
					for (FieldView f : inputFieldsView.getFields()) {
						if (columnsUpdate.get(i).getColumnName() == f.getColumn().getColumnName()) {
							columnsUpdate.get(i).setColumnDataDirect(f.getData());
						}
					}
				}

				appModel.getGeneralTableModel().setTableName(tableName);
				boolean success = appModel.getGeneralTableModel().update(columnsUpdate, appModel.getUser());

				if (success) {
					inputFieldsView.close();
					appModel.setApplicationState(new WorkingState());
					appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
					appView.getGeneralTableView().getTable().setRowSelectionInterval(
							appModel.getGeneralTableModel().getCurrentSelectedRow() - 1,
							appModel.getGeneralTableModel().getCurrentSelectedRow() - 1);
					JOptionPane.showMessageDialog(appView.getFrApp(), "Row was updated.", "Update row",
							JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(appView.getFrApp(), "Row was not updated.", "Error",
							JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(appView.getFrApp(), "Incorrect data!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			return;
		}

		if (e.getActionCommand().equals("linked")) {
			Object source = e.getSource();
			ArrayList<FieldView> fields = appView.getInputFieldsView().getFields();
			source = (JButton) source;
			for (FieldView f : fields) {

				if (source.equals(f.getBtnLinkedField())) {
					DialogBoxController dialog = new DialogBoxController(f.getColumn().getTarget(), appView.getFrApp());

					RowModel row = inputFieldsView.getRow();
					if (dialog.selectedRow > 0 && dialog.getRow() != null) {
						RowModel selectedRow = dialog.getRow();
						for (ColumnModel c : row.getColumns()) {
							for (ColumnModel selColumn : selectedRow.getColumns()) {
								if (c.getDbColumnName().equals(selColumn.getDbColumnName()) && c.isFk()
										&& selColumn.isPk()
										|| (c.getDbColumnName().equals(selColumn.getDbColumnName())
												&& c.getTarget().equals(dialog.getTableName()))) {
									c.setColumnDataDirect(selColumn.getColumnData());
								}
							}
						}
					}
					inputFieldsView.setRow(row);
					System.out.println(row.toString());
					appModel.getGeneralTableModel().setTableName(tableName);

					return;
				}
			}
		}
	}
}
