package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.DBConnection;
import user.User;

public class AppModel {
	User user;
	ToolBarModel toolBarModel;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AppModel(User user) {
		this.user = user;
	}
	
	public String getDateTime() {
		LocalDateTime myDateObj = LocalDateTime.now();
	    System.out.println("Before formatting: " + myDateObj);
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	    String formattedDate = myDateObj.format(myFormatObj);
		
		return formattedDate;
	}
	
	public ArrayList<String> getTablesForTableBrowser() {
		Connection db = DBConnection.getConnection();
		ArrayList<String> tables = new ArrayList<String>();
		try {
			CallableStatement procedureStatement = db.prepareCall("{ call pisg4.spTabela_get_all(?,?,?)}");
			procedureStatement.setString(1, this.user.getPosOznaka());
			procedureStatement.setInt(2, this.user.getPosIdentifikator());
			procedureStatement.setInt(3, this.user.getUserId());
			
			ResultSet rez = (ResultSet)procedureStatement.executeQuery();

			while(rez.next()) {
				tables.add(rez.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return tables;
	}

	public ToolBarModel getToolBarModel() {
		return toolBarModel;
	}

	public void setToolBarModel(ToolBarModel toolBarModel) {
		this.toolBarModel = toolBarModel;
	}
}
