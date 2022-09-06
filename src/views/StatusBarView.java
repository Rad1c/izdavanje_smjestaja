
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
	private Font fntLabel = new Font("Courier",Font.BOLD,11);
	private JPanel pnlStatusBar = new JPanel();
	
	public StatusBarView(JFrame frame)
	{
		pnlStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		pnlStatusBar.setBackground(Color.decode("#f0f0f0"));
		pnlStatusBar.setPreferredSize(new Dimension(frame.getWidth(),30));
		pnlStatusBar.setLayout(new BoxLayout(pnlStatusBar, BoxLayout.X_AXIS));
		//pnlStatusBar.setLayout(new FlowLayout(FlowLayout.CENTER,150,5));
		
		JLabel lblState = new JLabel("State: 'state_name'");
		JLabel lblCurrentTable = new JLabel("Currently selected table: 'table_name'");
		JLabel lblCurrentRow = new JLabel("Currently selected row: 00/nn");
		JLabel lblDateTime = new JLabel("Date: "+dtf.format(now));
		
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
		
		frame.add(pnlStatusBar,BorderLayout.SOUTH);
	}
	
}
