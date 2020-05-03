package com.example;


import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.util.StringUtils;
import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Outils.Pair;

public class MyXmlCollect extends DefaultHandler {
	
	String mot ;
	ArrayList<String> page;
	int frequence ;
	String title;
	boolean Bmot;
	boolean Bpage;
	boolean Bfrequence;
	boolean fin ;
	public MyXmlCollect() {
		super();
		
		mot ="";
		title="";
		page=new ArrayList<String>();
		Bmot=false;
		 Bpage=false;
		Bfrequence =false;
		fin = false;
		
	}

	public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException {

		
		if (qname.equalsIgnoreCase("content"))
			Bmot= true;
		if (qname.equalsIgnoreCase("title"))
			Bpage = true;
		if (qname.equalsIgnoreCase("frequence"))
			Bfrequence = true;
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		// System.out.println("Fin de l'élément " + qName);
		if (qName.equalsIgnoreCase("content"))
			Bmot = false;
		if (qName.equalsIgnoreCase("title")) 
			Bpage = false;
		if (qName.equalsIgnoreCase("frequence")) {
			Bfrequence = false;
			add();
		}
		if (qName.equalsIgnoreCase("mot")) {
			Traitement() ;
			
		}	
		

		

	}
	
	private void add() {
		String [] parts = title.split("\\s+");
		String str=StringUtils.capitalize(parts[0]);;
		for (int i = 1; i < parts.length; i++) {
			
			str+="_"+StringUtils.capitalize(parts[i]);
		}
		
		page.add(str);
		title="";
		frequence=0;
		Bmot=false;
		Bpage=false;
		Bfrequence=false;
	}

	public void Traitement() {
		
		Pair<String,ArrayList<String>> a = new Pair<String, ArrayList<String>>(mot, page) ;
		Getter.Mapper.put(mot, page);
		mot="";
		page=new ArrayList<String>();
		frequence=0;
	}

	/**
	 * permet de récupérer la valeur d'un nœud
	 */
	public void characters(char[] data, int start, int end) {

		if (Bmot) {
			String str = new String(data, start, end);
			
			mot+=str.trim();
		}else {
			if (Bpage) {
				String str = new String(data, start, end);
				
				title+=str.trim();
			}else {
				if(Bfrequence) {
					String str = new String(data, start, end);
					
					frequence=Integer.parseInt(str.trim());
				}
			}
			
		}
		
	}
}
