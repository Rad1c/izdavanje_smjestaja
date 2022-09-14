
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import models.AppModel;

public class StatusBarView {

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDateTime now = LocalDateTime.now();
	private Font fntLabel = new Font("Courier", Font.BOLD, 11);
	private JPanel pnlStatusBar;
	public JLabel lblState=new JLabel(), lblCurrentTable, lblCurrentRow, lblDateTime;
	private AppModel appModel;

	public StatusBarView(AppModel applicationModel) {
		this.appModel=applicationModel;
		pnlStatusBar = new JPanel();
		pnlStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pnlStatusBar.setBackground(Color.decode("#f0f0f0"));
		pnlStatusBar.setPreferredSize(new Dimension(1000, 30));
		pnlStatusBar.setLayout(new BoxLayout(pnlStatusBar, BoxLayout.X_AXIS));

		lblState.setText("State: "+ appModel.getApplicationState().toString());
		lblCurrentTable = new JLabel("Currently selected table: 'none'");
		lblCurrentRow = new JLabel("Currently selected row: 00/nn");
		lblDateTime = new JLabel("Date: " + dtf.format(now));

		lblState.setFont(fntLabel);
		lblCurrentTable.setFont(fntLabel);
		lblCurrentRow.setFont(fntLabel);
		lblDateTime.setFont(fntLabel);

		pnlStatusBar.add(Box.createRigidArea(new Dimension(180, 0)));
		pnlStatusBar.add(lblDateTime);
		pnlStatusBar.add(Box.createRigidArea(new Dimension(100, 0)));
		pnlStatusBar.add(lblState);
		pnlStatusBar.add(Box.createRigidArea(new Dimension(100, 0)));
		pnlStatusBar.add(lblCurrentTable);
		pnlStatusBar.add(Box.createRigidArea(new Dimension(100, 0)));
		pnlStatusBar.add(lblCurrentRow);

	}
	
	public void updateSelectedRow(int selectedRow, int numberOfRows)
	{
		this.lblCurrentRow.setText("Currently selected row:"+selectedRow+"/"+numberOfRows);
	}
	
	public void updateTableName(String newTableName)
	{
		this.lblCurrentTable.setText("Currently selected table: "+newTableName);
	}
	
	public void updateState(String state)
	{
		this.lblState.setText("State: "+state);
	}

	public void setStatusBar(JFrame frApp) {
		frApp.add(pnlStatusBar, BorderLayout.SOUTH);
	}

}
