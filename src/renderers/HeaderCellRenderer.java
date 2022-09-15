package renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class HeaderCellRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public HeaderCellRenderer() {
		setFont(new Font("Consolas", Font.BOLD, 12));
		setOpaque(true);
		setBackground(Color.decode("#fffefe"));
		setForeground(Color.decode("#6b6a6e"));
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(BorderFactory.createEtchedBorder());
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		return this;
	}
}
