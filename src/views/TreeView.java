package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import controllers.GeneralTableController;
import models.AppModel;
import models.GeneralTableModel;

public class TreeView {

	private DefaultMutableTreeNode izdavanjeSmjestaja;
	ArrayList<String> tables;
	JPanel pnlTree;
	AppModel appModel;
	AppView appView;

	public TreeView(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		this.pnlTree = appView.getPnlTree();
		this.tables = appModel.getTablesForTableBrowser();
		izdavanjeSmjestaja = new DefaultMutableTreeNode("Izdavanje smjestaja");
		
		for (String tableName : tables) {
			DefaultMutableTreeNode tbl = new DefaultMutableTreeNode(tableName);
			izdavanjeSmjestaja.add(tbl);
		}

		JTree jt = new JTree(izdavanjeSmjestaja);
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jt.getCellRenderer();
		Icon leafIcon = new ImageIcon("rezervacije.png");
		renderer.setLeafIcon(leafIcon);
		this.pnlTree = this.appView.getPnlTree();
		this.pnlTree.setLayout(new BorderLayout());
		this.pnlTree.add(jt, BorderLayout.PAGE_START);

		jt.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		jt.addTreeSelectionListener(new SelectionListener());

	}

	class SelectionListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			String selectedNodeName = selectedNode.toString();

			if (selectedNode.isLeaf()) {
				System.out.println(selectedNodeName);
				appModel.setGeneralTableModel(new GeneralTableModel(selectedNodeName));
				new GeneralTableController(appModel, appView);
				appView.getStatusBarView().updateTableName(selectedNodeName);
				appView.getStatusBarView().updateSelectedRow(0, 0);
			}
		}

	}
}