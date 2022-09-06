package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.AppModel;
import models.ToolBarModel;
import views.ToolBarView;

public class ToolBarController implements ActionListener {
	AppModel appModel;
	ToolBarView toolbar;
	public ToolBarController(AppModel appModel, ToolBarView toolbar) {
		this.appModel = appModel;
		this.toolbar = toolbar;
		this.toolbar.addActionListeners(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("pritisnuto " + e.getActionCommand());
		
	}
}
