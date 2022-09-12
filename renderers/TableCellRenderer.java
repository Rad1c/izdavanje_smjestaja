package renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public TableCellRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		table.getTableHeader().setPreferredSize(new Dimension(20, 30));

		for (int i = 0; i < table.getModel().getRowCount(); i++)
			table.setRowHeight(i, 30);

		c.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		//c.setForeground(Color.decode("#6b6a6e"));

		if (row % 2 == 0) {
			c.setBackground(Color.decode("#fffefe"));
		} else {
			c.setBackground(Color.decode("#f4f7fe"));
		}

		if (isSelected) {
			c.setBackground(Color.decode("#d6e5fe"));
			table.scrollRectToVisible(table.getCellRect(row, 0, false));
		}

		return c;
	}
}