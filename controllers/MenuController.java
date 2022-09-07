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
import views.AppView;
import views.MenuView;

public class MenuController implements ActionListener {

	AppModel appModel;
	MenuView menuView;
	AppView appView;

	public MenuController(AppModel appModel, AppView appView) {
		this.appModel = appModel;
		this.appView = appView;
		this.menuView = new MenuView(this.appView.getFrApp());
		appView.setMenuView(this.menuView);
		this.menuView.setActionListeners(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("exit")) {
			appModel.exitFromApp();
		}

		if (e.getActionCommand().equals("switch XML")) {
			String filePath = "xml/xml.xml";
			File file = new File(filePath);
			JFrame frame;

			JFileChooser fc = new JFileChooser();
			int i = fc.showOpenDialog(new JFileChooser());
			if (i == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				String filepath = f.getPath();
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
					String s1 = "", s2 = "";
					while ((s1 = bufferedReader.readLine()) != null) {
						s2 += s1 + "\n";
					}
					@SuppressWarnings("unused")
					FileWriter writer = new FileWriter(file);
					writer.write(s2);
					writer.close();
					bufferedReader.close();
					frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Xml file successfully saved.");
				} catch (IOException e1) {
					System.out.println(e1.toString() + "class: MenuController");
				}

			}

		}

		if (e.getActionCommand().equals("about")) {
			JFrame frame;
			frame = new JFrame();
			JOptionPane.showMessageDialog(frame,
					"Created by:\nAleksandar Radic\nAleksandar Skoco\nStefan Vujic\nSvjetlana Pejicic\nBoris Stanar\nJovan Djurdjic");
		}

	}

}
