package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import database.ConnectionInfo;
import database.DBConnection;
import parsers.XMLParser;
import user.User;

public class GeneralTableModel implements Subject {
	static String tableName;
	ArrayList<RowModel> rows;
	ArrayList<ColumnModel> columns;
	ArrayList<String> columnsNames;
	String[][] columnsData;
	int currentSelectedRow = -99;
	HashMap<String, Integer> columnDataLength = new HashMap<String, Integer>();
	ArrayList<Observer> observers;

	public GeneralTableModel(String tableName) {
		observers = new ArrayList<Observer>();
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

			for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
				if (columns.get(i).getColumnName().equals(resultSetMetaData.getColumnLabel(i + 1))) {
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
		} finally {
			this.tableName = tableName;
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
		namesOfColumns.addAll(XMLParser.getColumnsNames(tableName));

		return namesOfColumns.toArray(new String[0]);
	}

	public ArrayList<ColumnModel> getPrimaryKey(int rowNo) {
		ArrayList<ColumnModel> primaryKey = new ArrayList<ColumnModel>();

		for (ColumnModel c : this.getRow(rowNo).getColumns()) {
			if (c.isPk)
				primaryKey.add(c);
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

	public boolean update(ArrayList<ColumnModel> columnsUpdate, User user) {
		boolean success = true;

		Connection db = DBConnection.getConnection();
		ConnectionInfo connInfo = XMLParser.getConnectionString();
		String procedureName = connInfo.getSchema() + ".sp" + tableName.substring(0, 1).toUpperCase()
				+ tableName.substring(1) + "_update(";
		for (int i = 0; i < columns.size() + 1; i++)
			procedureName += "?,";
		procedureName += "?)";

		try {
			CallableStatement procedureStatement = db.prepareCall("{ call " + procedureName + " }");
			procedureStatement.setString(1, user.getOznaka());
			procedureStatement.setInt(2, user.getIdentifikator());

			for (int i = 0; i < columnsUpdate.size(); i++) {
				if (columnsUpdate.get(i).isNullable() && (columnsUpdate.get(i).getColumnData().equals("")
						|| columnsUpdate.get(i).getColumnData().equals("NULL"))) {
					if (columnsUpdate.get(i).getDataType().equals("string"))
						procedureStatement.setNull(i + 3, Types.VARCHAR);
					if (columnsUpdate.get(i).getDataType().equals("boolean"))
						procedureStatement.setNull(i + 3, Types.BOOLEAN);
					if (columnsUpdate.get(i).getDataType().equals("date"))
						procedureStatement.setNull(i + 3, Types.DATE);
					if (columnsUpdate.get(i).getDataType().equals("number"))
						procedureStatement.setNull(i + 3, Types.INTEGER);
					continue;
				}

				if (columnsUpdate.get(i).getDataType().equals("string")) {
					procedureStatement.setString(i + 3, columnsUpdate.get(i).getColumnData());
				}
				if (columnsUpdate.get(i).getDataType().equals("boolean")) {
					System.out.println(columnsUpdate.get(i).getColumnData());
					boolean boolData = columnsUpdate.get(i).getColumnData() == "DA" ? true : false;
					procedureStatement.setBoolean(i + 3, boolData);
				}
				if (columnsUpdate.get(i).getDataType().equals("number")) {
					try {
						procedureStatement.setInt(i + 3, Integer.parseInt(columnsUpdate.get(i).getColumnData()));
					} catch (NumberFormatException e) {
						return false;
					}
				}
				if (columnsUpdate.get(i).getDataType().equals("date")) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date myDate = formatter.parse(columnsUpdate.get(i).getColumnData());
					java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
					procedureStatement.setDate(i + 3, sqlDate);
				}
			}

			procedureStatement.execute();
			notifyObserver();
			return true;
		} catch (SQLException | ParseException e) {
			success = false;
			System.out.println("(class: generalTableModel) " + e.toString());
			e.printStackTrace();
		}

		return success;
	}

	public boolean delete(ArrayList<ColumnModel> columnsPk, User user) {
		boolean success = true;

		Connection db = DBConnection.getConnection();
		ConnectionInfo connInfo = XMLParser.getConnectionString();
		String procedureName = connInfo.getSchema() + ".sp" + tableName.substring(0, 1).toUpperCase()
				+ tableName.substring(1) + "_delete(";
		for (int i = 0; i < columnsPk.size() + 1; i++)
			procedureName += "?,";
		procedureName += "?)";

		CallableStatement procedureStatement;
		try {
			procedureStatement = db.prepareCall("{ call " + procedureName + " }");
			procedureStatement.setString(1, user.getOznaka());
			procedureStatement.setInt(2, user.getIdentifikator());

			for (int i = 0; i < columnsPk.size(); i++) {
				if (columnsPk.get(i).getDataType().equals("string")) {
					procedureStatement.setString(i + 3, columnsPk.get(i).getColumnData());
				}
				if (columnsPk.get(i).getDataType().equals("boolean")) {
					boolean boolData = columnsPk.get(i).getColumnData() == "DA" ? true : false;
					procedureStatement.setBoolean(i + 3, boolData);
				}
				if (columnsPk.get(i).getDataType().equals("number")) {
					try {
						procedureStatement.setInt(i + 3, Integer.parseInt(columnsPk.get(i).getColumnData()));
					} catch (NumberFormatException e) {
						return false;
					}
				}
				if (columnsPk.get(i).getDataType().equals("date")) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date myDate = formatter.parse(columnsPk.get(i).getColumnData());
					java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
					procedureStatement.setDate(i + 3, sqlDate);
				}
			}
			procedureStatement.executeUpdate();
			this.rows.remove(currentSelectedRow - 1);
			notifyObserver();
			success = true;

		} catch (SQLException | ParseException e) {
			System.out.println("(class: GeneralTableModel) " + e.toString());
			success = false;
			e.printStackTrace();
		}

		notifyObserver();
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
		notifyObserver();
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
			if (r.rowId == i)
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

	@Override
	public void register(Observer newObserver) {
		if (!observers.contains(newObserver)) {
			observers.add(newObserver);
			System.out.println("observer registred");
		} else {
			System.out.println("Observer already exists");
		}
	}

	@Override
	public void unregister(Observer deleteObserver) {
		if (observers.contains(deleteObserver)) {
			int observerIndex = observers.indexOf(deleteObserver);
			observers.remove(observerIndex);
			System.out.println("observer unregistred");
		}

	}

	@Override
	public void notifyObserver() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

}
