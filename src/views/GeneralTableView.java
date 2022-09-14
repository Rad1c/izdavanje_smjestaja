package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import controllers.DialogBoxController;
import controllers.GeneralTableController;
import models.GeneralTableModel;
import models.Observer;
import renderers.HeaderCellRenderer;
import renderers.TableCellRenderer;

public class GeneralTableView implements Observer {
	static final long serialVersionUID = 1L;
	JTable table = new JTable();
	GeneralTableModel tableModel;
	ListSelectionModel model;
	JPanel pnlTable;
	JScrollPane scrollPane;
	String[][] data;
	String[] columns;
	JPanel pnlTableContainer;
	GeneralTableController generalTableController = null;

	public GeneralTableView(JPanel pnlTable, GeneralTableModel tableModel, GeneralTableController generalTableController) {
		this.pnlTable = pnlTable;
		this.tableModel = tableModel;
		this.generalTableController = generalTableController;
		pnlTableContainer = new JPanel(new BorderLayout());
		pnlTable.add(pnlTableContainer, BorderLayout.PAGE_START);
		int width = pnlTable.getWidth();
		pnlTableContainer.setPreferredSize(new Dimension(width, 270));
		init();
	}


	public void init() {
		this.pnlTableContainer.removeAll();
		this.pnlTableContainer.repaint();
		data = tableModel.getColumnsDataShow();
		columns = tableModel.getColumnsNames();

		table = new JTable(data, columns) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};

			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setPreferredScrollableViewportSize(new Dimension(200, 250));
		table.setFillsViewportHeight(false);
		table.setDefaultRenderer(Object.class, new TableCellRenderer());
		table.getTableHeader().setDefaultRenderer(new HeaderCellRenderer());
		this.model = table.getSelectionModel();
		scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		pnlTable.add(scrollPane, BorderLayout.PAGE_START);
		pnlTableContainer.add(scrollPane, BorderLayout.PAGE_START);

	}

	public void setPositionCenter() {
		pnlTable.removeAll();
		pnlTable.revalidate();
		pnlTable.repaint();

		pnlTable.add(scrollPane, BorderLayout.PAGE_START);
	}

	public void addListener(DialogBoxController dialogBoxController) {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(dialogBoxController);
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

	@Override
	public void update() {
		init();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = table.getSelectionModel();
		model.addListSelectionListener(generalTableController);
	}

	public void setModel(ListSelectionModel model) {
		this.model = model;
	}

}
