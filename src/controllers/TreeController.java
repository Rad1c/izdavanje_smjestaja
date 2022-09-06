package controllers;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import models.AppModel;
import views.AppView;
import views.TreeView;

public class TreeController implements TreeSelectionListener{
	AppModel appModel;
	TreeView treeView;
	AppView appView;
	
	
	public TreeController(AppView appView,AppModel appModel, TreeView treeView) {
		this.appModel = appModel;
		this.treeView = treeView;
		this.appView = appView;
	}
	

	@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
			        .getLastSelectedPathComponent();
			String selectedNodeName = selectedNode.toString();
			
			System.out.println("xx");
			if(selectedNode.isLeaf()) {
				new GeneralTableController(selectedNodeName.toString(),this.appView);
			}
		}

}
