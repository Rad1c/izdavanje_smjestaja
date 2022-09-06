package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GeneralTableView {

	 JTable table = new JTable();
	
	public GeneralTableView(JPanel pnlTable, String[] columns, String[][] rows)
	{
		pnlTable.setLayout(new BorderLayout());
		System.out.println("usaooo");
		
		for (String strings : columns) {
			System.out.println(strings);
		}
		table = new JTable(rows,columns) {
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table.setPreferredScrollableViewportSize(new Dimension(200,250));
		table.setFillsViewportHeight(true);
	
		
		JScrollPane scrlTable = new JScrollPane(table);
		pnlTable.add(scrlTable, BorderLayout.SOUTH);
	}
	
	 
	
}
