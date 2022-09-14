package controllers;

import models.AppModel;
import views.AppView;
import views.StatusBarView;

public class StatusBarController {
	AppModel appModel;
	AppView appView;
	StatusBarView statusBarView;

	public StatusBarController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		this.statusBarView = new StatusBarView(appModel);
		this.statusBarView.setStatusBar(this.appView.getFrApp());
		this.appView.setStatusBarView(this.statusBarView);
	}

}
