package views;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controllers.ToolBarController;

public class ToolBarView {
	
	public JButton firstRow = new JButton(new ImageIcon("images/first.png"));
	public JButton previousRow = new JButton(new ImageIcon("images/previous.png"));
	public JButton nextRow = new JButton(new ImageIcon("images/next.png"));
	public JButton lastRow = new JButton(new ImageIcon("images/last.png"));
	public JButton newRow = new JButton(new ImageIcon("images/add.png"));
	public JButton editRow = new JButton(new ImageIcon("images/edit.png"));
	public JButton deleteRow = new JButton(new ImageIcon("images/delete.png"));
	public JButton accept = new JButton(new ImageIcon("images/accept.png"));
	public JButton cancel = new JButton(new ImageIcon("images/cancel.png"));
	public JButton report = new JButton(new ImageIcon("images/report.png"));
	
	public ToolBarView(JPanel pnlToolBar) {
		JToolBar toolbar = new JToolBar();  
		toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.X_AXIS));
		toolbar.add(Box.createRigidArea(new Dimension(50, 20)));
        toolbar.setRollover(true);  
        setButtonsMarginsAndBorders();
        setctionComands();
        
        toolbar.add(firstRow);  
        firstRow.setToolTipText("First row");
        toolbar.add(Box.createRigidArea(new Dimension(5, 10)));
        toolbar.add(previousRow);  
        previousRow.setToolTipText("Previous row");
        toolbar.add(Box.createRigidArea(new Dimension(5, 10)));
        toolbar.add(nextRow);  
        nextRow.setToolTipText("Next row");
        toolbar.add(Box.createRigidArea(new Dimension(5, 10)));
        toolbar.add(lastRow); 
        lastRow.setToolTipText("Last row");
        toolbar.add(Box.createRigidArea(new Dimension(20, 10)));
        toolbar.add(newRow); 
        newRow.setToolTipText("New row");
        toolbar.add(Box.createRigidArea(new Dimension(5, 10)));
        toolbar.add(editRow);  
        editRow.setToolTipText("Edit row");
        toolbar.add(Box.createRigidArea(new Dimension(5, 10)));
        toolbar.add(deleteRow);
        deleteRow.setToolTipText("Delete row");
        toolbar.add(Box.createRigidArea(new Dimension(20, 10)));
        toolbar.add(accept); 
        accept.setToolTipText("Accept");
        toolbar.add(Box.createRigidArea(new Dimension(5, 10)));
        toolbar.add(cancel);
        cancel.setToolTipText("Cancel");
        toolbar.add(Box.createRigidArea(new Dimension(20, 10)));
        toolbar.add(report); 
        report.setToolTipText("Report");
        
        pnlToolBar.add(toolbar, BorderLayout.NORTH);
	}
	
	private void setButtonsMarginsAndBorders() {
		firstRow.setMargin(new Insets(0,0,0,0));
		previousRow.setMargin(new Insets(0,0,0,0));
		nextRow.setMargin(new Insets(0,0,0,0));
		lastRow.setMargin(new Insets(0,0,0,0));
		newRow.setMargin(new Insets(0,0,0,0));
		editRow.setMargin(new Insets(0,0,0,0));
		deleteRow.setMargin(new Insets(0,0,0,0));
		accept.setMargin(new Insets(0,0,0,0));
		cancel.setMargin(new Insets(0,0,0,0));
		report.setMargin(new Insets(0,0,0,0));
		
		firstRow.setFocusPainted(false);
		previousRow.setFocusPainted(false);
		nextRow.setFocusPainted(false);
		lastRow.setFocusPainted(false);
		newRow.setFocusPainted(false);
		editRow.setFocusPainted(false);
		deleteRow.setFocusPainted(false);
		accept.setFocusPainted(false);
		cancel.setFocusPainted(false);
		report.setFocusPainted(false);
		
		firstRow.setBorder(BorderFactory.createBevelBorder(0));
		previousRow.setBorder(BorderFactory.createBevelBorder(0));
		nextRow.setBorder(BorderFactory.createBevelBorder(0));
		lastRow.setBorder(BorderFactory.createBevelBorder(0));
		newRow.setBorder(BorderFactory.createBevelBorder(0));
		editRow.setBorder(BorderFactory.createBevelBorder(0));
		deleteRow.setBorder(BorderFactory.createBevelBorder(0));
		accept.setBorder(BorderFactory.createBevelBorder(0));
		cancel.setBorder(BorderFactory.createBevelBorder(0));
		report.setBorder(BorderFactory.createBevelBorder(0));
		
		firstRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		previousRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nextRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lastRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deleteRow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		accept.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		report.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	private void setctionComands() {
		firstRow.setActionCommand("first row");
		previousRow.setActionCommand("previous");
		nextRow.setActionCommand("next");
		newRow.setActionCommand("new");
		lastRow.setActionCommand("last");
		editRow.setActionCommand("edit");
		deleteRow.setActionCommand("delete");
		accept.setActionCommand("accept");
		cancel.setActionCommand("cancel");
		report.setActionCommand("report");
	}
	
	public void addActionListeners(ToolBarController toolBarController) {
		firstRow.addActionListener(toolBarController);
		previousRow.addActionListener(toolBarController);
		nextRow.addActionListener(toolBarController);
		lastRow.addActionListener(toolBarController);
		newRow.addActionListener(toolBarController);
		editRow.addActionListener(toolBarController);
		deleteRow.addActionListener(toolBarController);
		accept.addActionListener(toolBarController);
		cancel.addActionListener(toolBarController);
		report.addActionListener(toolBarController);
	}

}
