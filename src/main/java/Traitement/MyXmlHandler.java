package Traitement;


import org.xml.sax.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyXmlHandler extends DefaultHandler {
	

	boolean title;
	boolean text;
	String texte;
	String titre;
	boolean find;
	Page page;
	boolean step;
	
	public MyXmlHandler(boolean step) {
		super();
		this.step=step;
		titre ="";
		
	}

	public void startElement(String namespaceURI, String lname, String qname, Attributes attrs) throws SAXException {

		if (qname.equalsIgnoreCase("title"))
			title = true;
		if (qname.equalsIgnoreCase("text"))
			text = true;
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		// System.out.println("Fin de l'élément " + qName);
		if (qName.equalsIgnoreCase("title"))
			title = false;
		if (qName.equalsIgnoreCase("text")) {
			text = false;
			Traitement(step);
		}

		

	}
	
	public void Traitement(boolean step) {
		
		if(step) {
		
		boolean f = Factory.find(texte);
		if(f) {
			
			titre=Factory.Cleaning(titre);
			texte=Factory.Cleaning(texte);
			
			
			Factory.Build(titre,texte);
		}
		//Factory.GettingTitles(texte,titre);
		}else {
			System.out.println("daguiggggggggggggggggggggggggggggi" +titre);
			Factory.GettingTitles(texte,titre);
			
			
		}
		texte="";
		titre="";
	}

	/**
	 * permet de récupérer la valeur d'un nœud
	 */
	public void characters(char[] data, int start, int end) {

		if (title) {
			String str = new String(data, start, end);
			
			titre+=str;
		}
		if (text && !title) {
			String str = new String(data, start, end);
			
			texte+=str;
		}
	}
}
