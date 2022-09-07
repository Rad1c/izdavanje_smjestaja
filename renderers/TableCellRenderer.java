package renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRenderer extends DefaultTableCellRenderer{

	private static final long serialVersionUID = 1L;
	
	public TableCellRenderer() {
		setHorizontalAlignment(JLabel.CENTER);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub

		Component c = super.getTableCellRendererComponent(table,  value, isSelected, hasFocus, row, column);
		
		
		
		
		for(int i = 0; i< table.getModel().getRowCount();i++)
			table.setRowHeight(i,25);
		
		c.setFont(new Font("Times New Roman", Font.ITALIC, 14));
        if(row % 2 == 0)
		{
			c.setBackground(new Color(151, 246, 141));
			c.setForeground(Color.black);
		}
		else
		{
			c.setBackground(Color.white);
			c.setForeground(Color.black);
		}
        
        if (isSelected)
        {
        	c.setBackground(new Color(51, 153, 255));
        	c.setForeground(Color.white);
        	table.scrollRectToVisible(table.getCellRect(row,0, false));
        }
        return c;
        
		
	
        
	}
}