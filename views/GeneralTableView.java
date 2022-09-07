package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import controllers.GeneralTableController;
import models.GeneralTableModel;
import renderers.HeaderCellRenderer;
import renderers.TableCellRenderer;

public class GeneralTableView {

	JTable table = new JTable();
	GeneralTableModel tableModel;
	ListSelectionModel model;

	public GeneralTableView(JPanel pnlTable, String[][] data, String[] columns) {

		pnlTable.removeAll();
		pnlTable.setLayout(new BorderLayout());

		table = new JTable(data, columns) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
			
			public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
		};

		table.setPreferredScrollableViewportSize(new Dimension(200, 250));
		table.setFillsViewportHeight(true);
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		table.getTableHeader().setDefaultRenderer(new HeaderCellRenderer());
		this.model = table.getSelectionModel();
		
		JScrollPane scrlTable = new JScrollPane(table);
		pnlTable.add(scrlTable, BorderLayout.SOUTH);

	}

	public void addListener(GeneralTableController tableController) {
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(tableController);
	}

	public ListSelectionModel getModel() {
		return model;
	}

	public JTable getTable() {
		return table;
	}

}
