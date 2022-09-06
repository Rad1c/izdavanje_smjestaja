package models;

import views.ToolBarView;

public class ToolBarModel {

	public void disableAllButtons(ToolBarView toolbar)
	{
		toolbar.accept.setEnabled(false);
		toolbar.cancel.setEnabled(false);
		toolbar.deleteRow.setEnabled(false);
		toolbar.editRow.setEnabled(false);
		toolbar.firstRow.setEnabled(false);
		toolbar.lastRow.setEnabled(false);
		toolbar.newRow.setEnabled(false);
		toolbar.nextRow.setEnabled(false);
		toolbar.previousRow.setEnabled(false);
		toolbar.report.setEnabled(false);
	}
	
	public void enableAllButtons(ToolBarView toolbar)
	{
		toolbar.accept.setEnabled(true);
		toolbar.cancel.setEnabled(true);
		toolbar.deleteRow.setEnabled(true);
		toolbar.editRow.setEnabled(true);
		toolbar.firstRow.setEnabled(true);
		toolbar.lastRow.setEnabled(true);
		toolbar.newRow.setEnabled(true);
		toolbar.nextRow.setEnabled(true);
		toolbar.previousRow.setEnabled(true);
		toolbar.report.setEnabled(true);
	}
	
	
}
