package Outils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import Building.WriteStorage;
import Traitement.Factory;



public class Collecteur {

	
	public  Hashtable<String, ArrayList<Pair<String,Integer>>> collect = new Hashtable<String, ArrayList<Pair<String,Integer>>>();
	public final String COLLECT_PATH="src/main/resources/storage/collecteur.xml" ;
	
	
	public  void add(String mot,String page ) {
		Pair pair ;
		ArrayList <Pair<String ,Integer>> tmp;
		
		
		if(collect.containsKey(mot)) {
			
			
		tmp = collect.get(mot);
		
		if(     tmp.get(tmp.size()-1).getKey().equalsIgnoreCase(page)     ) {
			int a = tmp.get(tmp.size()-1).getValue() + 1;
			
			pair = new Pair(page, a);
			tmp.remove(tmp.size()-1);
			tmp.add(pair);
			collect.put(mot,tmp);
		}else {
			pair = new Pair(page, 1);
			tmp.add(pair);
			collect.put(mot,tmp);
			
		}
		
		
		}else {
			tmp= new  ArrayList< Pair<String,Integer > >();
			pair = new Pair(page, 1);
			tmp.add(pair);
			collect.put(mot,tmp);
		}
			
		
		
	}
	
	public  ArrayList<Pair<String,String>>  getPages(String mot){
		
		
		
		return null;
		
	}
	
	public int size() {
		return collect.size();
	}
	
	public ArrayList<Pair<String,Integer>> sort(ArrayList<Pair<String,Integer>> col,double [] pageRank){
		
		
		
		Collections.sort(col, new Comparator<Pair<String,Integer>>() {

			@Override
			public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
				/*
				Set entrySet =Structure.hashTable.entrySet();
				Iterator it = entrySet.iterator();
			     
			  
			    while(it.hasNext()) {
			    	
			    	  Map.Entry entry = (Map.Entry) it.next();
			    	 String mot =(String) entry.getKey();
			         System.out.println(mot);
			    }*/
				 int id1 =Structure.getPageId(o1.getKey());
				int id2=Structure.getPageId(o2.getKey());
				if (pageRank[id1] < pageRank[id2]) {
					return 1;
				} else {
					if (pageRank[id1] < pageRank[id2]) {
						return -1;
					} else
						return 0;
			
				}
			}
		});
		return col;
	
		
	}
	
	
	public void store(double [] pageRank) {
		WriteStorage writer = new  WriteStorage(COLLECT_PATH);
		
		 Set entrySet =Factory.collecteur.collect.entrySet();
		 
		 ArrayList< Pair  <String, ArrayList< Pair<String, Integer> > > > col= new ArrayList<>();
		 
		    // Obtain an Iterator for the entries Set
		    Iterator it = entrySet.iterator();
		     
		    ArrayList <Pair<String,Integer>> tmpList =new ArrayList <Pair<String,Integer>> ();
		    String tmpMot="";
		    ArrayList < Pair  <String, ArrayList< Pair<String, Integer> > > > total = new ArrayList< Pair  <String, ArrayList< Pair<String, Integer> > > >() ;
		    
		    while(it.hasNext()) {
		    	
		    
		    	  Map.Entry entry = (Map.Entry) it.next();
		    	  tmpList =(ArrayList<Pair<String,Integer>>) entry.getValue();
		    	
		    	  tmpList=this.sort(tmpList, pageRank);
		    	  tmpMot = (String)entry.getKey();
		      /// System.out.println("                "+tmpMot + "  "+tmpList.size());
		    	  Pair  <String, ArrayList< Pair<String, Integer> > > pair = new Pair(tmpMot,tmpList);
		    	 total.add(pair);
		        
		    
		    }
		    
		   Collections.sort(total,new Comparator<Pair  <String, ArrayList< Pair<String, Integer> > >>() {

			@Override
			public int compare(Pair<String, ArrayList<Pair<String, Integer>>> o1,
					Pair<String, ArrayList<Pair<String, Integer>>> o2) {
				
				if (o1.getKey().compareToIgnoreCase(o2.getKey())>0) {
					return 1;
				} else {
					if (o1.getKey().compareToIgnoreCase(o2.getKey())<0) {
						return -1;
					} else
						return 0;
				}
			}
		});
		   
		   writer.WritedebutDocument();
		   
		   for (int i = 0; i <total.size(); i++) {
			writer.WriteStart("mot");
			writer.WriteStart("content");
			writer.writeElement(total.get(i).getKey());
			writer.WriteFinish("content");
			for (int j = 0; j < total.get(i).getValue().size(); j++) {
				writer.WriteStart("page");
				writer.WriteStart("title");
				writer.writeElement(total.get(i).getValue().get(j).getKey());
				writer.WriteFinish("title");
				writer.WriteStart("frequence");
				writer.writeElement(total.get(i).getValue().get(j).getValue().toString());
				writer.WriteFinish("frequence");
				writer.WriteFinish("page");
			}
			writer.WriteFinish("mot");
		}
		   writer.WritefinDocument();
	}
	
}
