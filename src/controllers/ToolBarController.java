package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.AppModel;
import models.ToolBarModel;
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
		this.toolBarModel = new ToolBarModel();
		this.appModel.setToolBarModel(this.toolBarModel);
		this.toolbarView = new ToolBarView(appView.getPnlHeader());
		this.toolbarView.addActionListeners(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("(class: ToolBarController) pritisnuto " + e.getActionCommand());

	}
}
