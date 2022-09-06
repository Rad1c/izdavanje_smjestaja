package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import controllers.GeneralTableController;

public class TreeView {

	private DefaultMutableTreeNode izdavanjeSmjestaja;
	ArrayList<String> tables;
	public AppView appView;

	public TreeView(JPanel pnlTree, ArrayList<String> tables, AppView appView) {
		this.tables = tables;
		this.appView = appView;
		izdavanjeSmjestaja = new DefaultMutableTreeNode("Izdavanje smjestaja");
		for (String tableName : tables) {
			DefaultMutableTreeNode tbl = new DefaultMutableTreeNode(tableName);
			izdavanjeSmjestaja.add(tbl);
		}
		

		JTree jt = new JTree(izdavanjeSmjestaja);
		pnlTree.setLayout(new BorderLayout());
		pnlTree.add(jt, BorderLayout.PAGE_START);

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
				new GeneralTableController(selectedNodeName.toString(), appView);
			}
		}

	}
}