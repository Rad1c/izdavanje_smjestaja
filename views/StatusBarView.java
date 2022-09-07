
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

public class StatusBarView {

	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private LocalDateTime now = LocalDateTime.now();
	private Font fntLabel = new Font("Courier", Font.BOLD, 11);
	private JPanel pnlStatusBar;
	public JLabel lblState, lblCurrentTable, lblCurrentRow, lblDateTime;

	public StatusBarView() {
		pnlStatusBar = new JPanel();
		pnlStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pnlStatusBar.setBackground(Color.decode("#f0f0f0"));
		pnlStatusBar.setPreferredSize(new Dimension(1000, 30));
		pnlStatusBar.setLayout(new BoxLayout(pnlStatusBar, BoxLayout.X_AXIS));

		lblState = new JLabel("State: 'state_name'");
		lblCurrentTable = new JLabel("Currently selected table: 'table_name'");
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

	public void setStatusBar(JFrame frApp) {
		frApp.add(pnlStatusBar, BorderLayout.SOUTH);
	}

}
