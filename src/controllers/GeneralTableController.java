package controllers;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import models.AppModel;
import views.AppView;
import views.GeneralTableView;

public class GeneralTableController implements ListSelectionListener {
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
		System.out.println("selektovanoooo ide gasss");

		ListSelectionModel model = generalTableView.getModel();
		if (!model.isSelectionEmpty()) {
			int rowNo = model.getMinSelectionIndex() + 1;
			appView.getStatusBarView().updateSelectedRow(rowNo, generalTableView.getTable().getRowCount());
		}
	}

}
