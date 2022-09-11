package views.input;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import models.ColumnModel;

public class FieldView extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel lblContent;
	JTextField txtContent;
	JButton btnLinkedField;
	JComboBox<String> cmbContent;
	ColumnModel column;

	public FieldView(ColumnModel column) {
		this.txtContent = new JTextField(30);
		this.column = column;
		JLabel lblContent = new JLabel(column.getColumnName());
		Dimension dim = txtContent.getPreferredSize();

		JLabel lblRed = new JLabel();
		lblRed.setText(!column.isNullable() ? "*" : "  ");
		lblRed.setForeground(Color.red);
		lblRed.setPreferredSize(new Dimension(dim.width - lblContent.getPreferredSize().width, dim.height));

		setBackground(Color.decode("#ece8e7"));
		add(lblContent);
		add(lblRed);
		setBackground(Color.decode("#ece8e7"));

		if (column.getDataType() == "boolean") {
			this.cmbContent = new JComboBox<String>(new String[] { "DA", "NE" });
			cmbContent.setSelectedIndex(column.getColumnData() == "DA" ? 0 : 1);
			add(cmbContent);
		} else if (column.isLenkedField()) {
			this.txtContent = new JTextField(23);
			this.btnLinkedField = new JButton("...");
			btnLinkedField.setFocusable(false);
			txtContent.setText(column.getColumnData());
			add(txtContent);
			add(btnLinkedField);
		} else {
			txtContent.setText(column.getColumnData());
			add(txtContent);
		}
	}

	public boolean checkInputFields(HashMap<String, Integer> columnsDataLength) {
		boolean valueCorrect = false;
		txtContent.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
		if (column.isNullable()) {
			valueCorrect = true;
			return true;
		}

		if (column.getDataType().equals("string")) {
			if (txtContent.getText().length() < columnsDataLength.get(column.getColumnName())) {
				valueCorrect = true;
			} else {
				valueCorrect = false;
				txtContent.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
			if (!column.isNullable() && txtContent.getText().length() == 0) {
				valueCorrect = false;
				txtContent.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
		}

		if (column.getDataType().equals("date")) {
			try {
				Date d = parseDate(txtContent.getText(), "yyyy-MM-dd", false);

				valueCorrect = d != null ? true : false;
				if (!valueCorrect)
					txtContent.setBorder(BorderFactory.createLineBorder(Color.RED));
			} catch (Exception e) {
				txtContent.setBorder(BorderFactory.createLineBorder(Color.RED));
				valueCorrect = false;
			}
		}

		if (column.getDataType().equals("number")) {
			try {
				Long.valueOf(txtContent.getText());
				valueCorrect = true;
			} catch (NumberFormatException e) {
				valueCorrect = false;
				txtContent.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
		}

		return valueCorrect;
	}

	Date parseDate(String maybeDate, String format, boolean lenient) {
		Date date = null;

		String reFormat = Pattern.compile("d+|M+").matcher(Matcher.quoteReplacement(format)).replaceAll("\\\\d{1,2}");
		reFormat = Pattern.compile("y+").matcher(reFormat).replaceAll("\\\\d{4}");
		if (Pattern.compile(reFormat).matcher(maybeDate).matches()) {

			SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
			sdf.applyPattern(format);
			sdf.setLenient(lenient);
			try {
				date = sdf.parse(maybeDate);
			} catch (ParseException e) {
			}
		}
		return date;
	}

	public JButton getBtnLinkedField() {
		return btnLinkedField;
	}

	public void setBtnLinkedField(JButton btnLinkedField) {
		this.btnLinkedField = btnLinkedField;
	}

	public JTextField getTxtContent() {
		return txtContent;
	}

	public void setTxtContent(JTextField txtContent) {
		this.txtContent = txtContent;
	}

	public JComboBox<String> getCmbContent() {
		return cmbContent;
	}

	public void setCmbContent(JComboBox<String> cmbContent) {
		this.cmbContent = cmbContent;
	}

	public ColumnModel getColumn() {
		return column;
	}

	public void setColumn(ColumnModel column) {
		this.column = column;
	}
}
