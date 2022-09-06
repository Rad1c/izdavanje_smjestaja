package parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import database.ConnectionInfo;
import models.ColumnModel;

public class XMLParser implements IParserType {
	private static String xmlPath = "xml/xml.xml";

	@Override
	public void parse() {
		System.out.println("XMLParser.parse()");

	}

	public static ConnectionInfo getConnectionString() {
		ConnectionInfo connInfo = new ConnectionInfo();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlPath);
			NodeList connection = doc.getElementsByTagName("connection");

			for (int i = 0; i < connection.getLength(); i++) {
				Node c = connection.item(i);
				if (c.getNodeType() == Node.ELEMENT_NODE) {
					Element con = (Element) c;
					connInfo.setUsername(con.getAttribute("username"));
					connInfo.setPassword(con.getAttribute("password"));
					connInfo.setAddress(con.getAttribute("address"));
					connInfo.setDatabase(con.getAttribute("database"));
					connInfo.setPort(con.getAttribute("port"));
					connInfo.setSchema(con.getAttribute("schema"));
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return connInfo;
	}

	public static ArrayList<ColumnModel> getColumnsFromTableName(String tableName) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ArrayList<ColumnModel> columns = new ArrayList<ColumnModel>();

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlPath);
			NodeList tableList = doc.getElementsByTagName("table");
			

			for (int i = 0; i < tableList.getLength(); i++) {
				Node t = tableList.item(i);
				
				if (t.getNodeType() == Node.ELEMENT_NODE) {
					Element table = (Element) t;
					if(tableName.equals(table.getAttribute("tableName"))) {
						NodeList columnsList = table.getChildNodes();
						
						for(int j = 0; j < columnsList.getLength(); j++) {
							Node c = columnsList.item(j);
							
							if(c.getNodeType() == Node.ELEMENT_NODE) {
								Element column = (Element) c;
								ColumnModel columnModel = new ColumnModel();
								columnModel.setDbColumnName(column.getAttribute("dbColumnName"));
								columnModel.setDataType(column.getAttribute("dataType"));
								columnModel.setTarget(column.getAttribute("target"));
								columnModel.setFk(Boolean.parseBoolean(column.getAttribute("isFk")));
								columnModel.setPk(Boolean.parseBoolean(column.getAttribute("isPk")));
								columnModel.setNullable(Boolean.parseBoolean(column.getAttribute("isNullable")));
								columnModel.setColumnName(column.getTextContent());
								columnModel.setShow(Boolean.parseBoolean(column.getAttribute("show")));
								
								columns.add(columnModel);
							}
						}
					}
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return columns;
	}
}
