package views;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import controllers.TreeController;
import models.AppModel;

public class TreeView {

	private DefaultMutableTreeNode izdavanjeSmjestaja;
	ArrayList<String> tables;
	JPanel pnlTree;
	AppModel appModel;
	AppView appView;
	JTree jt;

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

		this.jt = new JTree(izdavanjeSmjestaja);
		DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jt.getCellRenderer();
		Icon leafIcon = new ImageIcon("rezervacije.png");
		renderer.setLeafIcon(leafIcon);
		this.pnlTree = this.appView.getPnlTree();
		this.pnlTree.setLayout(new BorderLayout());
		this.pnlTree.add(jt, BorderLayout.PAGE_START);

		jt.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

	}

	public void setSelecetionListener(TreeController treeController) {
		this.jt.addTreeSelectionListener(treeController);
	}
}