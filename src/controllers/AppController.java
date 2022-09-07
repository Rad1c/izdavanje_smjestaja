package controllers;

import models.AppModel;
import views.AppView;

public class AppController {
	AppModel appModel;
	AppView appView;

	public AppController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;

		appView.setUsername(appModel.getUser().getUsername());
		new MenuController(this.appModel, this.appView);
		new ToolBarController(this.appModel, this.appView);
		new TreeController(appModel, appView);
		new StatusBarController(appModel, appView);

	}

}
