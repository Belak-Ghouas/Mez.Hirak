package Outils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Structure {

	private static ArrayList<String> tab = new ArrayList<String>();
	//public static Hashtable<String, ArrayList<String>> creuse = new Hashtable<String, ArrayList<String>>();
	public static Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();
	
	public static Set<String> dict = new HashSet<String>();
	
	/*public static ArrayList<Double> C = new ArrayList<Double>();
	public static ArrayList<Integer> L = new ArrayList<Integer>();
	public static ArrayList<Integer> I = new ArrayList<Integer>();*/

	public static int getPageId(String page) {
		if (existsPage(page))
			return hashTable.get(page);

		return -1;
	}

	public static String getIdpage(int id) {
		return tab.get(id);
	}

	public static void addPageaAndId(String page, int id) {
		tab.add(page);
		hashTable.put(page, id);

	}

	public static boolean existsPage(String page) {
		return hashTable.containsKey(page);
	}
}
