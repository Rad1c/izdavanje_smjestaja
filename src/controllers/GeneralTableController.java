package controllers;

import models.GeneralTableModel;
import views.AppView;
import views.GeneralTableView;

public class GeneralTableController {

	public GeneralTableController(String tableName, AppView appView)
	{
		GeneralTableModel tableModel = new GeneralTableModel(tableName);
		String[][] data = tableModel.getColumnsDataShow();
		String[] columns = tableModel.getColumnsNames();
		new GeneralTableView(appView.getPnlTable(),columns,data);	
		
		System.out.println("table control");
	}
	
}
