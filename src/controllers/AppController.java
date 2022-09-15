package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import database.DBConnection;
import models.AppModel;
import models.LoginModel;
import views.AppView;
import views.LoginView;

public class AppController implements MouseListener {
	AppModel appModel;
	AppView appView;

	public AppController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;

		appView.setUsername(appModel.getUser().getUsername());
		appView.setActionListener(this);
		new MenuController(this.appModel, this.appView);
		new TreeController(appModel, appView);
		new StatusBarController(appModel, appView);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		DBConnection.closeConnection();
		appView.getFrApp().dispose();
		new LoginController(new LoginModel(), new LoginView());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
