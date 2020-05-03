package Traitement;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXmlFile {

		 public SAXParserFactory factory;
		 public SAXParser saxParser ;
		public DefaultHandler handler;
		
		public ReadXmlFile() {
			
			factory=SAXParserFactory.newInstance();
			try {
				saxParser= factory.newSAXParser();
			} catch (ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
