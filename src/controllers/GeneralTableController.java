package controllers;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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

		this.generalTableView = new GeneralTableView(appView.getPnlTable(), appModel.getGeneralTableModel(), this);
		this.generalTableView.addListener(this);
		
		appView.setGeneralTableView(this.generalTableView);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		model = generalTableView.getModel();
		if (e.getValueIsAdjusting())
			return;
		
		if (!model.isSelectionEmpty()) {
			int rowNo = model.getMinSelectionIndex() + 1;
			appModel.getGeneralTableModel().setCurrentSelectedRow(rowNo);
			appView.getPnlInputFields().removeAll();
			appView.getPnlInputFields().repaint();
			appView.getToolbarView().disableEnableRowButtons(true);
			appView.getToolbarView().editRow.setEnabled(true);
			appView.getToolbarView().deleteRow.setEnabled(true);
			appView.getToolbarView().deleteRow.addActionListener(this);
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
		if (e.getActionCommand().equals("delete")) {
			System.out.println("delte usao");
			int dlgRes = JOptionPane.showConfirmDialog(appView.getFrApp(), "Are you sure?", "Delete row", JOptionPane.YES_NO_CANCEL_OPTION);
			
			if (dlgRes == JOptionPane.YES_OPTION) {
				dlgRes = -999;
				if (appModel.getGeneralTableModel() == null)
					return;

				try {
					ArrayList<ColumnModel> columnsPk = new ArrayList<ColumnModel>(appModel.getGeneralTableModel()
							.getPrimaryKey(appModel.getGeneralTableModel().getCurrentSelectedRow()));
					boolean success = appModel.getGeneralTableModel().delete(columnsPk, appModel.getUser());

					if (success) {
						inputFieldsController.inputFieldsView.close();
						JOptionPane.showMessageDialog(appView.getFrApp(), "Row was deleted.", "Message", JOptionPane.INFORMATION_MESSAGE);
						return;
						
					} else {
						JOptionPane.showMessageDialog(appView.getFrApp(), "Row was not deleted.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (HeadlessException e1) {
					System.out.println("delete failed");
					JOptionPane.showMessageDialog(appView.getFrApp(), "Row was not deleted.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			return;
		}
		
	}
}
