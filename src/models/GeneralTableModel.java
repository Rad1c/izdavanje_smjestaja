package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectionInfo;
import database.DBConnection;
import parsers.XMLParser;

public class GeneralTableModel {
	String tableName;
	ArrayList<RowModel> rows = new ArrayList<RowModel>();
	ArrayList<ColumnModel> columns;
	ArrayList<String> columnsNames;
	String[][] columnsData;
	
	public GeneralTableModel(String tableName) {
		columns = XMLParser.getColumnsFromTableName(tableName);
		Connection db = DBConnection.getConnection();
		ConnectionInfo connInfo = XMLParser.getConnectionString();
		String procedureName = connInfo.getSchema() + ".sp" + tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
		this.tableName = tableName;
		
		try {
			CallableStatement procedureStatement = db.prepareCall("{ call " + procedureName + "_get_all(?,?) }");
			procedureStatement.setString(1, "BIH");
			procedureStatement.setInt(2, 1);
			ResultSet rez = procedureStatement.executeQuery();
			
			while(rez.next()) {
				RowModel row = new RowModel(columns);
				ArrayList<Object> objectData = new ArrayList<Object>();
				
				for(int i = 0; i < columns.size(); i++) {
					if(columns.get(i).getDataType().equals("date"))
						objectData.add(rez.getDate(i + 1));
					else
						objectData.add(rez.getObject(i + 1));
				}
				
				row.setColumnsData(objectData);
				rows.add(row);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getColumnsNamesAll() {
		ArrayList<String> namesOfColumns = new ArrayList<String>();
		namesOfColumns.add("Rb");
		
		for (ColumnModel columnModel : columns) {
			namesOfColumns.add(columnModel.getColumnName());
		}
		
		return namesOfColumns;
	}
	
	public String[] getColumnsNames() {
		ArrayList<String> namesOfColumns = new ArrayList<String>();
		namesOfColumns.add("rb");
		for (ColumnModel columnModel : columns) {
			if(columnModel.show)
				namesOfColumns.add(columnModel.getColumnName());
		}
		
		return namesOfColumns.toArray(new String[0]);
	}
	
	public ArrayList<ColumnModel> getPrimaryKey(){
		ArrayList<ColumnModel> primaryKey = new ArrayList<ColumnModel>();
		
		for (ColumnModel columnModel : columns) {
			if(columnModel.isPk()) {
				primaryKey.add(columnModel);
			}
		}
		
		return primaryKey;
	}
	
	public ArrayList<ColumnModel> getForgeinKeyForTargetTable(String table){
		ArrayList<ColumnModel> forgeinKey = new ArrayList<ColumnModel>();
		
		for (ColumnModel columnModel : columns) {
			if(columnModel.isFk() && columnModel.getTarget().equals(table)) {
				forgeinKey.add(columnModel);
			}
		}
		
		return forgeinKey;
	}
	
	public String getTableName() {
		return tableName;
	}


	public void setTableName(String tableName) {
		this.tableName = tableName;
	}


	public ArrayList<RowModel> getRows() {
		return rows;
	}


	public void setRows(ArrayList<RowModel> rows) {
		this.rows = rows;
	}

	public ArrayList<ColumnModel> getColumns() {
		return columns;
	}
	
	public String[][] getColumnsDataShow(){
		ArrayList<ArrayList<String>> mainList = new ArrayList<ArrayList<String>>();
		for (RowModel row : rows) {
			ArrayList<String> rowData = row.getColumnsDataShow();
			mainList.add(rowData);
		}
		
		this.columnsData = mainList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		return columnsData;
	}
}
