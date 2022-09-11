package views;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import helpers.EnableDisableComponents;
import models.RowModel;
import net.miginfocom.swing.MigLayout;
import views.input.InputHeaderView;
import views.input.FieldView;

public class InputFieldsView {
	JPanel pnlInputFields;
	InputHeaderView inputHeaderView;
	JPanel pnlHeader;
	JPanel pnlFields;
	JScrollPane scrlFields;
	ArrayList<FieldView> fields = new ArrayList<FieldView>();
	RowModel row;

	public InputFieldsView(JPanel pnlInputFields, RowModel row) {
		this.row = row;
		this.pnlInputFields = pnlInputFields;
		this.pnlInputFields.setLayout(new MigLayout());
		this.pnlInputFields.setBorder(BorderFactory.createLineBorder(Color.decode("#828790")));
		this.pnlInputFields.setBackground(Color.decode("#ece8e7"));
		int widith = pnlInputFields.getWidth();
		this.pnlHeader = new JPanel();
		this.pnlFields = new JPanel(new MigLayout());

		pnlHeader.setBackground(Color.decode("#ece8e7"));

		inputHeaderView = new InputHeaderView(pnlHeader);
		this.pnlInputFields.add(pnlHeader,
				"wrap, width " + (int) (widith * 0.5) + ":" + (int) (widith * 95) + ":" + widith);

		int n = row.getColumnsShow().size();
		for (int i = 0; i < n / 2; i++) {
			FieldView f = new FieldView(row.getColumnsShow().get(i));
			fields.add(f);
			pnlFields.add(f, "gapright 20");
			if (i + n / 2 < n) {
				f = new FieldView(row.getColumnsShow().get(i + n / 2));
				fields.add(f);
				pnlFields.add(f, "wrap");
			}
		}
		EnableDisableComponents.setPanelEnabled(pnlFields, false);
		this.scrlFields = new JScrollPane(pnlFields);
		this.pnlInputFields.add(scrlFields, "width " + (int) (widith * 0.5) + ":" + (int) (widith * 95) + ":" + widith);

	}

	public JPanel getPnlInputFields() {
		return pnlInputFields;
	}

	public void setPnlInputFields(JPanel pnlInputFields) {
		this.pnlInputFields = pnlInputFields;
	}

	public JPanel getPnlFields() {
		return pnlFields;
	}

	public void setPnlFields(JPanel pnlFields) {
		this.pnlFields = pnlFields;
	}

	public JPanel getPnlHeader() {
		return pnlHeader;
	}

	public void setPnlHeader(JPanel pnlHeader) {
		this.pnlHeader = pnlHeader;
	}

	public InputHeaderView getInputHeaderView() {
		return inputHeaderView;
	}

	public void setInputHeaderView(InputHeaderView inputHeaderView) {
		this.inputHeaderView = inputHeaderView;
	}

	public ArrayList<FieldView> getFields() {
		return fields;
	}

	public RowModel getRow() {
		return row;
	}

}
