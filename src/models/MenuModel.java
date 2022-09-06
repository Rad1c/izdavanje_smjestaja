package models;

import views.LoginView;
import views.MenuView;

public class MenuModel{

	
	public void Logout(MenuView menuView)
	{
		menuView.frame.dispose();
		new LoginView();
	}

}
