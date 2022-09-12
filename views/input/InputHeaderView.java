package views.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import controllers.InputFieldsController;


public class InputHeaderView {
	JPanel pnlHeader;
	JButton btnEdit, btnAccept,btnCancel;

	public InputHeaderView(JPanel pnl, boolean edit) {
		this.pnlHeader = pnl;
		pnlHeader.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnAccept = new JButton("Accept");
		btnAccept.setEnabled(false);
		btnCancel = new JButton("Cancel");
		btnCancel.setEnabled(false);
		btnEdit = new JButton("Edit");
		setButtons();
		
		pnlHeader.add(btnAccept);
		pnlHeader.add(btnCancel);
		if(edit)
			pnlHeader.add(btnEdit);
		
	}
	
	public void setButtons() {
		btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEdit.setFocusPainted(false);
		
		btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAccept.setFocusPainted(false);
		
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancel.setFocusPainted(false);
	}
	
	public void setActionListeners(InputFieldsController inputFieldsController) {
		this.btnAccept.addActionListener(inputFieldsController);
		this.btnCancel.addActionListener(inputFieldsController);
		this.btnEdit.addActionListener(inputFieldsController);
		
		this.btnAccept.setActionCommand("accept");
		this.btnCancel.setActionCommand("cancel");
		this.btnEdit.setActionCommand("edit");
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnAccept() {
		return btnAccept;
	}

	public void setBtnAccept(JButton btnAccept) {
		this.btnAccept = btnAccept;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public void setBtnCancel(JButton btnCancel) {
		this.btnCancel = btnCancel;
	}
}
