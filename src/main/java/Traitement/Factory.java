package Traitement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Building.FileRead;
import Building.WriteXmlFile;
import Outils.CLI;
import Outils.Collecteur;
import Outils.Pair;
import Outils.Structure;

public class Factory {

	static final String[] mots = { "film", "acteur", "artiste", "musique", "cinéma", "chanteur", "musique", "fiction" };
	public static volatile int ID = 0;
	public static int compt = 0;
	public static HashSet<String> mostWord = FileRead.readFile("src/main/resources/MyFiles/french-word-list-total.csv");
	public static List<String> suffixWord = FileRead.readFileList("src/main/resources/MyFiles/suffix.csv");
	public static Collecteur collecteur = new Collecteur();
	public static Hashtable<String ,Integer> dictionnaire = new Hashtable<String , Integer>();

	public static String Cleaning(String text) {

		String str = "";
		str = text.replaceAll("[\\{\\t\\r\\/\\<\\>\\$%&@={}|'\\*:.,;\\d]", " ");
		str= str.replaceAll("[^\\p{IsLatin}&\\p{Alnum}&\\[\\]&\\n]", " ");
		
		str = str.toLowerCase();
		str = str.trim();

		return str;

	}

	public static boolean find(String text) {

		Pattern pattern = Pattern.compile("infobox");

		String[] All = text.split("\n");
		for (int i = 0; i < All.length; i++) {
			if (All[i].contains("Infobox")) {
				String str = All[i].replaceAll("[^\\p{L}&^\\p{N}]", " ");

				return finds(str);
			}
		}

		return false;
	}

	public static void GettingTitles(String texte, String titre) {
		
		Pattern pattern = Pattern.compile("\\[{2}[^\\[]*\\]{2}");

		Set<String> set = new HashSet<String>();

		Matcher m = pattern.matcher(texte);
		String str;
		titre = titre.replaceAll("[^\\p{L}&^\\p{N}]", " ");
		titre=titre.replaceAll("[^\\p{IsLatin}&\\p{Alnum}]", " ");
		
		while (m.find()) {

			str = m.group(0).replaceAll("[\\[\\]\\$%&@={}'\\*:.,;\\d]", "");
			String[] parts = str.split("\\|");

			for (int j = 0; j < parts.length; j++) {

				String[] parts2 = parts[j].trim().split("\\s");

				for (int e = 0; e < parts2.length; e++) {
					String s = parts[j].trim();
					s = s.toLowerCase();
					s = s.trim();
					set.add(s);
				}

			}
		}

		int c = 0;
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {

			int id = Structure.getPageId(it.next());

			if (id != -1) {
				c++;
				CLI.I.add(id);
				compt++;
				
			}

		}
		for (int i = 0; i < c; i++) {
			CLI.C.add((1.0 / c));
		}
		CLI.L.add(compt);
		Collecteur(texte, titre);
	}

	public static boolean finds(String text) {

		for (int i = 0; i < mots.length; i++) {
			for (String mot : text.split("\\s")) {
				if (mot.toLowerCase().equalsIgnoreCase(mots[i])) {
					return true;
				}
			}

		}
		return false;

	}

	public static void Build(String titre, String texte) {

		if (!Structure.existsPage(titre)) {
			Page page = new Page(titre, ID, texte);
			Structure.addPageaAndId(titre, ID);
			ID++;
			WritePage(page);

		}

	}

	public static void WritePage(Page page) {
		WriteXmlFile writxmlfile = WriteXmlFile.getInsance();
		writxmlfile.createElement(page);

	}

	public static String racine(String mot) {

		
		mot.trim();
		
			
		for (int i = 0; i < suffixWord.size(); i++) {
			

			if (mot.endsWith(suffixWord.get(i))) {

				mot = mot.replace(suffixWord.get(i), "");

				return mot;
			}
		}

	return mot;

	}
	
	

	public static void Collecteur(String texte, String titre) {
		
		//String s = texte.replaceAll("[^\\p{L}&^\\p{N}]", " ")+titre.replaceAll("[^\\p{L}&^\\p{N}]", " ");
		String s = texte.replaceAll("[^\\p{IsLatin}&\\p{Alnum}]", " ")+titre.replaceAll("[^\\p{IsLatin}&\\p{Alnum}]", " ");
		
		titre = titre.replaceAll("[^\\p{IsLatin}&\\p{Alnum}]", " ");
		titre.toLowerCase();
		titre=titre.trim();
		String[] parts = texte.split("\\s");
		ArrayList <Pair<String,Integer>> tmp = new ArrayList<Pair<String,Integer>>() ;
		
		Hashtable<String,Integer> dictionnairetmp =new Hashtable<String ,Integer>();
		for (int j = 0; j < parts.length; j++) {

			s = parts[j];
			s = s.replaceAll("[^\\p{L}&^\\p{N}]", " ");
			s = s.trim();
			String[] parts2 = s.split("\\s");

			for (int i = 0; i < parts2.length; i++) {
				String s1 = parts2[i];
				s1=s1.replaceAll("[^\\p{IsLatin}&\\p{Alnum}]", " ");
				s1 = s1.trim();
				s1 = s1.toLowerCase();
				Pattern numeric = Pattern.compile("-?\\d+(\\.\\d+)?");

				if (!numeric.matcher(s1).matches() && s1.length() > 3 && s1 != null && s1.compareTo("\\s") > 0) {
					if (!mostWord.contains(s1)) {

						s1 = racine(s1);
						if(dictionnairetmp.containsKey(s1)) {
							
							int a = dictionnairetmp.get(s1)+1;
							dictionnairetmp.put(s1, a);
							
						}else {
							
							dictionnairetmp.put(s1, 1);
							
						}
						

					}
				}
				
			}
		
		}
		
		
		Set entrySet =dictionnairetmp.entrySet();
		Iterator it = entrySet.iterator();
	     
	  
	    while(it.hasNext()) {
	    	
	    	  Map.Entry entry = (Map.Entry) it.next();
	    	 String mot =(String) entry.getKey();
	         int frequence=(Integer) entry.getValue();
	      
	    	tmp.add(new Pair(mot, frequence));
	    }
		
		Collections.sort(tmp, new Comparator<Pair<String,Integer>>() {

			@Override
			public int compare(Pair<String,Integer> o1, Pair<String,Integer> o2) {
				if( o1.getValue()<o2.getValue()) {
					return 1;
				}else {
					 if (o1.getValue()>o2.getValue()){
							return -1 ;
						}else return 0 ;
				}
			}
		});
		int k =0;
		
		while ( k <tmp.size() /*&& k<2*/ ) {
			
			collecteur.add(tmp.get(k).getKey(), titre);
			dictionnaire.put(tmp.get(k).getKey(),tmp.get(k).getValue());
			k++;
		}
		tmp = new ArrayList<Pair<String,Integer>>() ;
		dictionnairetmp =new Hashtable<String ,Integer>();
		
	}

}
