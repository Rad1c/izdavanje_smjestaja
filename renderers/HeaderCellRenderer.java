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
	        setFont(new Font("Consolas", Font.BOLD, 14));
	        setOpaque(true);
	        setForeground(Color.WHITE);
	        setBackground(new Color(80,220,45));
	        setHorizontalAlignment(JLabel.CENTER);
	        setBorder(BorderFactory.createEtchedBorder());
	    }
	     
	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	        setText(value.toString());
	        return this;
	    }
}
