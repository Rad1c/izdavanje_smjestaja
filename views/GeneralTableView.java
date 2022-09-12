package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

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
			
		/*	public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }*/
		};
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredScrollableViewportSize(new Dimension(200, 250));
		//table.setFillsViewportHeight(false);
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		table.getTableHeader().setDefaultRenderer(new HeaderCellRenderer());
		this.model = table.getSelectionModel();
		
		JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		pnlTable.add(scrollPane, BorderLayout.SOUTH);
		//pnlTable.add(table, BorderLayout.SOUTH);
		
		//JScrollPane scrlTable = new JScrollPane(table);
		//pnlTable.add(scrlTable, BorderLayout.CENTER);

	}

	public void addListener(GeneralTableController tableController) {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
