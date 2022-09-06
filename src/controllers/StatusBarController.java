package controllers;

import javax.swing.JFrame;

import models.StatusBarModel;

public class StatusBarController {

	public StatusBarController(StatusBarModel statusBar,JFrame frame)
	{
		statusBar.createStatusBar(frame);
	}
	
}
