package models;

import java.util.ArrayList;

public class RowModel {
	static int counter = 0;
	int rowId = 0;
	ArrayList<ColumnModel> columns = new ArrayList<ColumnModel>();

	public RowModel(ArrayList<ColumnModel> columns) {
		super();
		this.columns = columns;
		rowId = ++counter;
	}

	public ArrayList<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<ColumnModel> columns) {
		this.columns = columns;
	}

	public void setColumnsData(ArrayList<Object> o) {
		if (o.size() != columns.size())
			return;

		for (int i = 0; i < o.size(); i++) {
			columns.get(i).setColumnData(o.get(i));
		}
	}

	public ArrayList<String> getColumnsData() {
		ArrayList<String> columnsData = new ArrayList<String>();
		columnsData.add(String.valueOf(rowId));

		for (ColumnModel columnModel : columns) {
			columnsData.add(columnModel.getColumnData());
		}

		return columnsData;
	}

	public ArrayList<String> getColumnsDataShow() {
		ArrayList<String> columnsData = new ArrayList<String>();
		columnsData.add(String.valueOf(rowId));

		for (ColumnModel columnModel : columns) {
			if (columnModel.show) {
				columnsData.add(columnModel.getColumnData());
			}
		}

		return columnsData;
	}

	public ArrayList<ColumnModel> getColumnsShow() {
		ArrayList<ColumnModel> returnColumns = new ArrayList<ColumnModel>();
		for (ColumnModel c : columns) {
			if (c.isShow())
				returnColumns.add(c);
		}
		return returnColumns;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		RowModel.counter = counter;
	}

	public void cleanRowData() {
		for (ColumnModel column : columns) {
			column.setColumnDataDirect("");
		}
	}

	@Override
	public String toString() {
		String out = "" + this.rowId + " ";

		for (int i = 0; i < columns.size(); i++) {
			out += columns.get(i).dbColumnName + ": " + columns.get(i).getColumnData() + " ";
		}
		return out;
	}
}
