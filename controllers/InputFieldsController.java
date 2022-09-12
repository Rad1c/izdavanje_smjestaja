package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import helpers.EnableDisableComponents;
import models.AppModel;
import models.ColumnModel;
import models.RowModel;
import views.AppView;
import views.InputFieldsView;
import views.input.FieldView;

public class InputFieldsController implements ActionListener {
	AppModel appModel;
	AppView appView;
	InputFieldsView inputFieldsView;
	JPanel pnlInputFields;

	public InputFieldsController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		int selRow = appModel.getGeneralTableModel().getCurrentSelectedRow();
		inputFieldsView = new InputFieldsView(appView.getPnlInputFields(),
				appModel.getGeneralTableModel().getRow(selRow));
		this.pnlInputFields = inputFieldsView.getPnlInputFields();
		inputFieldsView.getInputHeaderView().setActionListeners(this);
		appView.getToolbarView().addActionListenersEdit(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("edit")) {
			this.inputFieldsView.getInputHeaderView().getBtnCancel().setEnabled(true);
			this.inputFieldsView.getInputHeaderView().getBtnAccept().setEnabled(true);
			EnableDisableComponents.setPanelEnabled(inputFieldsView.getPnlFields(), true);
		}
		if (e.getActionCommand().equals("cancel")) {
			this.pnlInputFields.removeAll();
			this.pnlInputFields.repaint();
		}
		if (e.getActionCommand().equals("accept")) {
			boolean valuesCorrect = true;

			for (FieldView fw : inputFieldsView.getFields()) {
				if (!fw.checkInputFields(appModel.getGeneralTableModel().getColumnDataLength()))
					valuesCorrect = false;
			}

			if (valuesCorrect) {
				System.out.println("Data correct");
				RowModel row = inputFieldsView.getRow();
				ArrayList<ColumnModel> columnsUpdate = new ArrayList<ColumnModel>(row.getColumns());

				for (int i = 0; i < columnsUpdate.size(); i++) {
					for (FieldView f : inputFieldsView.getFields()) {
						if (columnsUpdate.get(i).getColumnName() == f.getColumn().getColumnName()) {
							columnsUpdate.get(i).setColumnDataDirect(f.getData());
						}
					}
				}

				boolean success = appModel.getGeneralTableModel().update(columnsUpdate, appModel.getUser());

				if (success)
					JOptionPane.showMessageDialog(null, "Row was updated.");
				else
					JOptionPane.showMessageDialog(null, "Row was not updated.", "Error", JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect data!", "Error", JOptionPane.OK_OPTION);

			}
		}

		if (e.getActionCommand().equals("delete")) {
			// da li zelite da obrisete odabrani red
			ArrayList<ColumnModel> columnsPk = new ArrayList<ColumnModel>(appModel.getGeneralTableModel()
					.getPrimaryKey(appModel.getGeneralTableModel().getCurrentSelectedRow()));
			boolean success = appModel.getGeneralTableModel().delete(columnsPk, appModel.getUser());

			if (success) {
				JOptionPane.showMessageDialog(null, "Row was deleted.");
			} else {
				JOptionPane.showMessageDialog(null, "Row was not deleted.", "Error", JOptionPane.OK_OPTION);
			}
		}
		
		

	}

}
