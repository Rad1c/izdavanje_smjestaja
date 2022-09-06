package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.AppModel;
import models.MenuModel;
import views.MenuView;

public class MenuController implements ActionListener {

	AppModel appModel;
	MenuView menuView;
	
	public MenuController(AppModel appModel, MenuView menuView) {
		this.appModel = appModel;
		this.menuView = menuView;
		this.menuView.setActionListeners(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("pritisnuto " + e.getActionCommand());
		if(e.getActionCommand() =="exit")
		{
			new MenuModel().Logout(menuView);;
		}
		
		if(e.getActionCommand().equals("switch XML"))
		{
			String filePath = "xml/xml.xml";
			File file = new File(filePath);
			JFrame frame;
			
			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(new JFileChooser());
			if(i == JFileChooser.APPROVE_OPTION){
				File f = fc.getSelectedFile();
				String filepath = f.getPath();
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
					String s1 = "", s2 = "";
					while((s1=bufferedReader.readLine())!=null){    
				        s2+=s1+"\n";    
				        }   
					@SuppressWarnings("unused")
					FileWriter writer = new FileWriter(file);
					writer.write(s2);
					writer.close();
					bufferedReader.close();
					frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Xml file successfully saved.");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
		}
		
		if(e.getActionCommand().equals("about"))
		{
			JFrame frame;
			frame = new JFrame();
			JOptionPane.showMessageDialog(frame, "Created by:\nAleksandar Radic\nAleksandar Skoco\nStefan Vujic\nSvjetlana Pejicic\nBoris Stanar\nJovan Djurdjic");
		}
			
	}

}
