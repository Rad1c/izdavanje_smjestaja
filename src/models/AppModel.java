package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DBConnection;
import enums.RowCRUD;
import state.ApplicationState;
import state.IdleState;
import user.User;

public class AppModel {
	User user;
	ToolBarModel toolBarModel;
	GeneralTableModel generalTableModel;
	public ApplicationState applicationState;
	boolean tableOpened = false;
	RowCRUD rowState = RowCRUD.READ;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AppModel(User user) {
		this.user = user;
		setApplicationState(new IdleState());
	}

	public ArrayList<String> getTablesForTableBrowser() {
		Connection db = DBConnection.getConnection();
		ArrayList<String> tables = new ArrayList<String>();
		try {
			CallableStatement procedureStatement = db.prepareCall("{ call pisg4.spTabela_get_all(?,?,?)}");
			procedureStatement.setString(1, this.user.getPosOznaka());
			procedureStatement.setInt(2, this.user.getPosIdentifikator());
			procedureStatement.setInt(3, this.user.getUserId());

			ResultSet rez = (ResultSet) procedureStatement.executeQuery();

			while (rez.next()) {
				tables.add(rez.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return tables;
	}

	public void exitFromApp() {
		System.exit(0);
	}

	public ToolBarModel getToolBarModel() {
		return toolBarModel;
	}

	public void setToolBarModel(ToolBarModel toolBarModel) {
		this.toolBarModel = toolBarModel;
	}

	public GeneralTableModel getGeneralTableModel() {
		return generalTableModel;
	}

	public void setGeneralTableModel(GeneralTableModel generalTableModel) {
		this.generalTableModel = generalTableModel;
	}

	public boolean isTableOpened() {
		return tableOpened;
	}

	public void setTableOpened(boolean tableOpened) {
		this.tableOpened = tableOpened;
	}

	public ApplicationState getApplicationState() {
		return applicationState;
	}

	public void setApplicationState(ApplicationState applicationState) {
		this.applicationState = applicationState;
	}

	public RowCRUD getRowState() {
		return rowState;
	}

	public void setRowState(RowCRUD rowState) {
		this.rowState = rowState;
	}

}
