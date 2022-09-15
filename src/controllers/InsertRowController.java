package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import enums.RowCRUD;
import helpers.EnableDisableComponents;
import models.AppModel;
import models.ColumnModel;
import models.RowModel;
import parsers.XMLParser;
import state.WorkingState;
import views.AppView;
import views.InputFieldsView;
import views.input.FieldView;

public class InsertRowController implements ActionListener {
	AppModel appModel;
	AppView appView;
	RowModel newRow;
	InputFieldsView inputFieldsNewRow;
	String tableName;
	JPanel pnlInputFields;

	public InsertRowController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		pnlInputFields = appView.getPnlInputFields();
		tableName = appModel.getGeneralTableModel().getTableName();
		newRow = new RowModel(XMLParser.getColumnsFromTableName(tableName));
		newRow.cleanRowData();
		appView.getPnlInputFields().removeAll();
		appView.getPnlInputFields().revalidate();
		appView.getPnlInputFields().repaint();
		inputFieldsNewRow = new InputFieldsView(appView.getPnlInputFields(), newRow, false);
		System.out.println("new row");
		EnableDisableComponents.setPanelEnabled(inputFieldsNewRow.getPnlFields(), true);
		EnableDisableComponents.setPanelEnabled(inputFieldsNewRow.getPnlHeader(), true);
		inputFieldsNewRow.getInputHeaderView().setActionListeners(this);
		inputFieldsNewRow.setBtnLinkedFieldsActList(this);
		appView.setInputFieldsView(inputFieldsNewRow);
		appModel.setRowState(RowCRUD.CREATE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("accept") && appModel.getRowState() == RowCRUD.CREATE) {
			boolean valuesCorrect = true;

			appModel.setRowState(RowCRUD.READ);
			for (FieldView fw : inputFieldsNewRow.getFields()) {
				if (!fw.checkInputFields(appModel.getGeneralTableModel().getColumnDataLength()))
					valuesCorrect = false;
			}

			if (valuesCorrect) {
				RowModel row = inputFieldsNewRow.getRow();

				ArrayList<ColumnModel> columnsUpdate = new ArrayList<ColumnModel>(row.getColumns());

				for (int i = 0; i < columnsUpdate.size(); i++) {
					for (FieldView f : inputFieldsNewRow.getFields()) {
						if (columnsUpdate.get(i).getColumnName() == f.getColumn().getColumnName()) {
							columnsUpdate.get(i).setColumnDataDirect(f.getData());
						}
					}
				}

				appModel.getGeneralTableModel().setTableName(tableName);
				boolean success = appModel.getGeneralTableModel().insert(columnsUpdate, appModel.getUser());

				if (success) {
					inputFieldsNewRow.close();
					appModel.setApplicationState(new WorkingState());
					appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
					appView.getGeneralTableView().getTable().setRowSelectionInterval(
							appModel.getGeneralTableModel().getCurrentSelectedRow() - 1,
							appModel.getGeneralTableModel().getCurrentSelectedRow() - 1);
					JOptionPane.showMessageDialog(appView.getFrApp(), "Row was inserted.", "Insert row",
							JOptionPane.INFORMATION_MESSAGE);
					appModel.getGeneralTableModel().setTableName(tableName);
					appModel.setRowState(RowCRUD.READ);
					this.pnlInputFields.removeAll();
					this.pnlInputFields.repaint();
					appModel.setApplicationState(new WorkingState());
					appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
				} else {
					JOptionPane.showMessageDialog(appView.getFrApp(), "Row was not inserted.", "Error",
							JOptionPane.ERROR_MESSAGE);
					appModel.setRowState(RowCRUD.CREATE);
				}
			} else {
				JOptionPane.showMessageDialog(appView.getFrApp(), "Incorrect data!", "Error",
						JOptionPane.ERROR_MESSAGE);
				appModel.setRowState(RowCRUD.CREATE);
			}
			return;

		}
		if (e.getActionCommand().equals("cancel") && appModel.getRowState() == RowCRUD.CREATE) {
			appModel.setRowState(RowCRUD.READ);
			this.pnlInputFields.removeAll();
			this.pnlInputFields.repaint();
			appModel.setApplicationState(new WorkingState());
			appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
		}
		if (e.getActionCommand().equals("linked")) {
			Object source = e.getSource();
			ArrayList<FieldView> fields = appView.getInputFieldsView().getFields();
			source = (JButton) source;
			for (FieldView f : fields) {

				if (source.equals(f.getBtnLinkedField())) {
					DialogBoxController dialog = new DialogBoxController(f.getColumn().getTarget(), appView.getFrApp());

					RowModel row = inputFieldsNewRow.getRow();
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
					inputFieldsNewRow.setRow(row);
					appModel.getGeneralTableModel().setTableName(tableName);

					return;
				}
			}
		}
	}

}
