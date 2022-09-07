package controllers;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import models.AppModel;
import models.GeneralTableModel;
import views.AppView;
import views.TreeView;

public class TreeController implements TreeSelectionListener{
	AppModel appModel;
	AppView appView;
	TreeView treeView;
	
	
	public TreeController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		this.treeView = new TreeView(appModel, appView);
	}
	

	@Override
		public void valueChanged(TreeSelectionEvent e) {
			JTree tree = (JTree) e.getSource();
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree
			        .getLastSelectedPathComponent();
			String selectedNodeName = selectedNode.toString();
			
			System.out.println("xx");
			if(selectedNode.isLeaf()) {
				appModel.setGeneralTableModel(new GeneralTableModel(selectedNodeName));
				new GeneralTableController(appModel, appView);
			}
		}

}
