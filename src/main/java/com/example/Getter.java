package com.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.xml.sax.SAXException;

import Traitement.MyXmlHandler;
import Traitement.ReadXmlFile;

public class Getter {

	public final String COLLECT_PATH="src/main/resources/storage/collecteur.xml" ;
	public static Hashtable<String, ArrayList<String>> Mapper=new Hashtable<String,ArrayList<String>>();
	private MyXmlCollect myxmlHandler;
	ReadXmlFile readxmlFile  ;
	public Getter() {
		
		myxmlHandler=new MyXmlCollect() ;
		readxmlFile = new ReadXmlFile();
		
	}
	public void start() {
		try {
			readxmlFile.saxParser.parse(new File(COLLECT_PATH), myxmlHandler);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
