package controllers;

import models.AppModel;
import models.StatusBarModel;
import views.AppView;
import views.MenuView;
import views.ToolBarView;
import views.TreeView;

public class AppController{
	AppModel appModel;
	AppView appView;
	
	public AppController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		appView.setUsername(appModel.getUser().getUsername());
		new MenuController(appModel, new MenuView(appView.getFrApp()));
		new ToolBarController(appModel, new ToolBarView(appView.getPnlHeader()));
		new ToolBarController(appModel, new ToolBarView(appView.getPnlHeader()));
		new TreeController(appView,appModel,new TreeView(appView.getPnlTree(), appModel.getTablesForTableBrowser(), appView));
		new StatusBarController(new StatusBarModel(),appView.getFrApp());
		/*GeneralTableModel tableModel = new GeneralTableModel("rezervacije");
		String[][] data = tableModel.getColumnsDataShow();
		String[] columns = tableModel.getColumnsNames();
		new GeneralTableView(appView.getPnlTable(),columns,data);*/
	}


	
	
	
}
