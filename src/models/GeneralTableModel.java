package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import database.ConnectionInfo;
import database.DBConnection;
import parsers.XMLParser;

public class GeneralTableModel {
	String tableName;
	ArrayList<RowModel> rows;
	ArrayList<ColumnModel> columns;
	ArrayList<String> columnsNames;
	String[][] columnsData;
	int currentSelectedRow = -99;
	HashMap<String, Integer> columnDataLength = new HashMap<String, Integer>();

	public GeneralTableModel(String tableName) {
		
		RowModel.setCounter(0);
		rows = new ArrayList<RowModel>();
		columns = XMLParser.getColumnsFromTableName(tableName);
		Connection db = DBConnection.getConnection();
		ConnectionInfo connInfo = XMLParser.getConnectionString();
		String procedureName = connInfo.getSchema() + ".sp" + tableName.substring(0, 1).toUpperCase()
				+ tableName.substring(1);
		this.tableName = tableName;

		try {
			CallableStatement procedureStatement = db.prepareCall("{ call " + procedureName + "_get_all(?,?) }");
			procedureStatement.setString(1, "BIH");
			procedureStatement.setInt(2, 1);
			ResultSet rez = procedureStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = rez.getMetaData();
			
			for(int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
				if(columns.get(i).getColumnName().equals(resultSetMetaData.getColumnLabel(i + 1))) {
					columnDataLength.put(columns.get(i).getColumnName(), resultSetMetaData.getColumnDisplaySize(i + 1));
				}
			}
			
			while (rez.next()) {
				RowModel row = new RowModel(XMLParser.getColumnsFromTableName(tableName));
				ArrayList<Object> objectData = new ArrayList<Object>();

				for (int i = 0; i < columns.size(); i++) {
					if (columns.get(i).getDataType().equals("date"))
						objectData.add(rez.getDate(i + 1));
					else if (columns.get(i).getDataType().equals("number"))
						objectData.add(rez.getInt(i + 1));
					else if (columns.get(i).getDataType().equals("boolean"))
						objectData.add(rez.getBoolean(i + 1));
					else
						objectData.add(rez.getObject(i + 1));
				}
				row.setColumnsData(objectData);
				
				this.rows.add(row);
			}
		} catch (SQLException e) {
			System.out.println("(class: GeneralTableModel) " + e.toString());
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
			if (columnModel.show)
				namesOfColumns.add(columnModel.getColumnName());
		}

		return namesOfColumns.toArray(new String[0]);
	}

	public ArrayList<ColumnModel> getPrimaryKey() {
		ArrayList<ColumnModel> primaryKey = new ArrayList<ColumnModel>();

		for (ColumnModel columnModel : columns) {
			if (columnModel.isPk()) {
				primaryKey.add(columnModel);
			}
		}

		return primaryKey;
	}

	public ArrayList<ColumnModel> getForgeinKeyForTargetTable(String table) {
		ArrayList<ColumnModel> forgeinKey = new ArrayList<ColumnModel>();

		for (ColumnModel columnModel : columns) {
			if (columnModel.isFk() && columnModel.getTarget().equals(table)) {
				forgeinKey.add(columnModel);
			}
		}

		return forgeinKey;
	}
	
	public boolean update() {
		boolean success = false;
		
		Connection db = DBConnection.getConnection();
		ConnectionInfo connInfo = XMLParser.getConnectionString();
		String procedureName = connInfo.getSchema() + "sp" + tableName.substring(0, 1).toUpperCase()
				+ tableName.substring(1) + "_update(";
		for(int i = 0; i < columns.size() - 1; i++)
			procedureName += "?,";
		procedureName += "?)";
		try {
			CallableStatement procedureStatement = db.prepareCall("{ call " + procedureName + " }");
			for (ColumnModel c : columns) {
				if(c.getDataType().equals("string"))
					;
					//procedureStatement.setSting
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
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

	public String[][] getColumnsDataShow() {
		ArrayList<ArrayList<String>> mainList = new ArrayList<ArrayList<String>>();
		for (RowModel row : rows) {
			ArrayList<String> rowData = row.getColumnsDataShow();
			mainList.add(rowData);
		}

		this.columnsData = mainList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		return columnsData;
	}
	
	public RowModel getRow(int i) {
		RowModel row = null;
		
		for (RowModel r : rows) {
			if(r.rowId == i)
				row = r;
		}
		
		return row;
	}

	public int getCurrentSelectedRow() {
		return currentSelectedRow;
	}

	public void setCurrentSelectedRow(int currentSelectedRow) {
		this.currentSelectedRow = currentSelectedRow;
	}

	public HashMap<String, Integer> getColumnDataLength() {
		return columnDataLength;
	}

}
