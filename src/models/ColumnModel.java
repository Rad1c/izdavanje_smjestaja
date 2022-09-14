package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ColumnModel {
	String dbColumnName;
	String columnName;
	String dataType;
	boolean isFk;
	boolean isPk;	
	boolean isNullable;
	boolean isLenkedField;
	String target = "";
	boolean show = true;
	String columnData = "NULL";
	
	public ColumnModel() {
		
	}

	public ColumnModel(String dbColumnName, String dataType, boolean isFk, boolean isPk, boolean isNullable,
			String target, boolean show, String columnName, boolean isLenkedField) {
		super();
		this.dbColumnName = dbColumnName;
		this.dataType = dataType;
		this.isFk = isFk;
		this.isPk = isPk;
		this.isNullable = isNullable;
		this.target = target;
		this.show = show;
		this.columnName = columnName;
		this.isLenkedField = isLenkedField;
	}
	
	public String getDbColumnName() {
		return dbColumnName;
	}

	public void setDbColumnName(String dbColumnName) {
		this.dbColumnName = dbColumnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isFk() {
		return isFk;
	}

	public void setFk(boolean isFk) {
		this.isFk = isFk;
	}

	public boolean isPk() {
		return isPk;
	}

	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public String getColumnData() {
		return columnData;
	}

	public void setColumnData(Object o) {
		if(this.getDataType().equals("string")) {
			this.columnData = (String) o;
		}
		else if(this.getDataType().equals("boolean")) {
			this.columnData = (Boolean) o? "DA": "NE";
		}
		else if(this.getDataType().equals("number")) {
			try {
				this.columnData = String.valueOf((int) o);
			} catch (Exception e) {
				this.columnData = "";
			}
		}
		else if(this.getDataType().equals("date")) {
			try {
				Date date = (Date)o; 
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String strDate = dateFormat.format(date);  
				this.columnData = strDate;
			} catch (Exception e) {
				this.columnData = "NULL";
			}
		}
		else;
	}
	
	public void setColumnDataDirect(String data) {
		this.columnData = data;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public boolean isLenkedField() {
		return isLenkedField;
	}

	public void setLenkedField(boolean isLenkedField) {
		this.isLenkedField = isLenkedField;
	}

	
}
