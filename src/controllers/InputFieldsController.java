package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import helpers.EnableDisableComponents;
import models.AppModel;
import models.ColumnModel;
import models.RowModel;
import views.AppView;
import views.InputFieldsView;
import views.input.FieldView;

public class InputFieldsController implements ActionListener {
	AppModel appModel;
	AppView appView;
	InputFieldsView inputFieldsView;
	JPanel pnlInputFields;

	public InputFieldsController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		int selRow = appModel.getGeneralTableModel().getCurrentSelectedRow();
		inputFieldsView = new InputFieldsView(appView.getPnlInputFields(), appModel.getGeneralTableModel().getRow(selRow));
		this.pnlInputFields = inputFieldsView.getPnlInputFields();
		inputFieldsView.getInputHeaderView().setActionListeners(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("edit")) {
			this.inputFieldsView.getInputHeaderView().getBtnCancel().setEnabled(true);
			this.inputFieldsView.getInputHeaderView().getBtnAccept().setEnabled(true);
			EnableDisableComponents.setPanelEnabled(inputFieldsView.getPnlFields(), true);
		}
		if (e.getActionCommand().equals("cancel")) {
			this.pnlInputFields.removeAll();
			this.pnlInputFields.repaint();
		}
		if (e.getActionCommand().equals("accept")) {
			boolean valuesCorrect = true;
			
			for (FieldView fw : inputFieldsView.getFields()) {
				if(!fw.checkInputFields(appModel.getGeneralTableModel().getColumnDataLength()))
					valuesCorrect = false;
			}
			
			if(valuesCorrect) {
				System.out.println("Data correct");
				RowModel row = inputFieldsView.getRow();
				RowModel rowUpdate = null;
	
				System.out.println(rowUpdate.toString());
				System.out.println(row.toString());
				/*	ArrayList<FieldView> fields = inputFieldsView.getFields();
				ArrayList<ColumnModel> columnsUpdate = new ArrayList<ColumnModel>();
				
				for(int i = 0; i < row.getColumns().size(); i++) {
					for(int j =0; j < fields.size(); j++) {
						if(row.get)
					}
				}*/
			}else {
				JOptionPane.showMessageDialog(null, "Incorrect data!", "Error", JOptionPane.OK_OPTION);
					
			}
			
			System.out.println(valuesCorrect);
		}

	}
	
	
}
