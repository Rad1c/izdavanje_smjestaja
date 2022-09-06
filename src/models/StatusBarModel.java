package models;

import javax.swing.JFrame;

import views.StatusBarView;

public class StatusBarModel {

	public void createStatusBar(JFrame frame)
	{
		new StatusBarView(frame);
	}
	
}
