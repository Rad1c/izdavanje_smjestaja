package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.AppModel;
import models.RowModel;
import views.AppView;
import views.GeneralTableView;
import views.InputFieldsView;

public class GeneralTableController implements ListSelectionListener, ActionListener {
	AppModel appModel;
	AppView appView;
	GeneralTableView generalTableView;

	public GeneralTableController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		String[][] data = appModel.getGeneralTableModel().getColumnsDataShow();
		String[] columnsNames = appModel.getGeneralTableModel().getColumnsNames();
		this.generalTableView = new GeneralTableView(appView.getPnlTable(), data, columnsNames);
		this.generalTableView.addListener(this);
		appView.setGeneralTableView(this.generalTableView);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel model = generalTableView.getModel();
		if (e.getValueIsAdjusting())
	        return;

		if (!model.isSelectionEmpty()) {
			int rowNo = model.getMinSelectionIndex() + 1;
			appModel.getGeneralTableModel().setCurrentSelectedRow(rowNo);
			appView.getPnlInputFields().removeAll();
			appView.getPnlInputFields().repaint();
			appView.getStatusBarView().updateSelectedRow(rowNo, generalTableView.getTable().getRowCount());
			new InputFieldsController(appModel, appView);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "first row")
			System.out.println("general table controller first");
		
	}

}
