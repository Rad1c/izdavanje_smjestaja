package controllers;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import models.AppModel;
import models.GeneralTableModel;
import state.WorkingState;
import views.AppView;
import views.TreeView;

public class TreeController implements TreeSelectionListener {
	AppModel appModel;
	AppView appView;
	TreeView treeView;

	public TreeController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		this.treeView = new TreeView(appModel, appView);
		treeView.setSelecetionListener(this);
	}	

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		JTree tree = (JTree) e.getSource();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		String selectedNodeName = selectedNode.toString();

		if (selectedNode.isLeaf()) {
			revalidateUI();
			appModel.setTableOpened(true);
			appModel.setGeneralTableModel(new GeneralTableModel(selectedNodeName));
			new GeneralTableController(appModel, appView);
			appView.getStatusBarView().updateTableName(selectedNodeName);
			appView.getStatusBarView().updateSelectedRow(0, 0);
			appModel.getGeneralTableModel().setCurrentSelectedRow(-99);
			appView.getToolbarView().disableEnableRowButtons(false);
			appView.setTableName(selectedNodeName);
			appModel.setApplicationState(new WorkingState());
			appView.getStatusBarView().updateState(appModel.getApplicationState().toString());
		}
	}
	
	public void revalidateUI() {
		appView.getPnlTable().removeAll();
		appView.getPnlTable().revalidate();
		appView.getPnlTable().repaint();
		appView.getPnlInputFields().removeAll();
		appView.getPnlInputFields().revalidate();
		appView.getPnlInputFields().repaint();
		appView.getToolbarView().editRow.setEnabled(false);
		appView.getToolbarView().deleteRow.setEnabled(false);
		appView.getToolbarView().newRow.setEnabled(true);
		appView.getToolbarView().report.setEnabled(true);
	}

}
