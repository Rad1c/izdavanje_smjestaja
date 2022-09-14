package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import models.AppModel;
import models.ToolBarModel;
import state.EditingState;
import state.WorkingState;
import views.AppView;
import views.ToolBarView;

public class ToolBarController implements ActionListener {
	AppModel appModel;
	AppView appView;
	ToolBarView toolbarView;
	ToolBarModel toolBarModel;

	public ToolBarController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		this.toolBarModel = new ToolBarModel(appModel.isTableOpened());
		this.appModel.setToolBarModel(this.toolBarModel);
		this.toolbarView = new ToolBarView(appView.getPnlUserLogout());
		appView.setToolbarView(this.toolbarView);
		this.toolbarView.addActionListeners(this);
		toolbarView.newRow.addActionListener(this);
	}

	@SuppressWarnings("null")
	@Override
	public void actionPerformed(ActionEvent e) {
		JTable table = appView.getGeneralTableView().getTable();
		if (table != null || table.getRowCount() != 0 || appModel.getGeneralTableModel().getCurrentSelectedRow() > 0) {

			if (e.getActionCommand().equals("first row")) {
				table.setRowSelectionInterval(0, 0);
				appModel.getGeneralTableModel().setCurrentSelectedRow(0);
			}

			if (e.getActionCommand().equals("last")) {
				table.setRowSelectionInterval(table.getRowCount() - 1, table.getRowCount() - 1);
				appModel.getGeneralTableModel().setCurrentSelectedRow(table.getRowCount() - 1);
			}

			if (e.getActionCommand().equals("previous")) {
				if (table.getSelectedRow() > 0) {
					table.setRowSelectionInterval(table.getSelectedRow() - 1, table.getSelectedRow() - 1);
					appModel.getGeneralTableModel().setCurrentSelectedRow(table.getSelectedRow() - 1);
				}
			}

			if (e.getActionCommand().equals("next")) {
				if (table.getSelectedRow() < table.getRowCount()) {
					table.setRowSelectionInterval(table.getSelectedRow() + 1, table.getSelectedRow() + 1);
					appModel.getGeneralTableModel().setCurrentSelectedRow(table.getSelectedRow() + 1);
				}
			}
		} else
			appView.getToolbarView().disableEnableRowButtons(false);

		if (e.getActionCommand().equals("new")) {
			appView.getPnlInputFields().removeAll();
			appView.getPnlInputFields().revalidate();
			appView.getPnlInputFields().repaint();
		}

		if (e.getActionCommand().equals("edit")) {
			if (table.getSelectedRow() < table.getRowCount()) {
				appModel.setApplicationState(new EditingState());
				appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
			}
		}
		if (e.getActionCommand().equals("accept")) {
			if (table.getSelectedRow() < table.getRowCount()) {
				appModel.setApplicationState(new WorkingState());
				appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
			}
		}
		if (e.getActionCommand().equals("cancel")) {
			if (table.getSelectedRow() < table.getRowCount()) {
				appModel.setApplicationState(new WorkingState());
				appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
			}
		}
	}
}
