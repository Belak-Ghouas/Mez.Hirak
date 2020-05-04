/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;


import Building.*;
import Outils.*;
import Traitement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xml.sax.SAXException;

import javax.sql.DataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@SpringBootApplication
public class Main {




  private PageServices pageService= new PageServices();
  private static Getter getter;
  
  
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
    deploy();
    
  }


  
  private static void deploy() {
	  getter= new Getter();
	  getter.start();
  }
  
  
  private static void init() {
	  
	  WriteXmlFile filexml = WriteXmlFile.getInsance();

		filexml.WritedebutDocument();
		ReadXmlFile readxmlFile = new ReadXmlFile();
		MyXmlHandler myxmlHandler = new MyXmlHandler(true);
		
		try {
			readxmlFile.saxParser.parse(new File("src/main/resources/MyFiles/frwiki-debut.xml"), myxmlHandler);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		filexml.WritefinDocument();

		CLI.L.add(0);

		myxmlHandler = new MyXmlHandler(false);
	
		try {
			readxmlFile.saxParser.parse(new File("src/main/resources/storage/corpus.xml"), myxmlHandler);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 Set entrySet =Factory.collecteur.collect.entrySet();
		 
		 
		    // Obtain an Iterator for the entries Set
		    Iterator it = entrySet.iterator();
		     
		    ArrayList <Pair> tmp = null ;
		    ArrayList <String> tm = new ArrayList<String>() ;
		    // Iterate through Hashtable entries
		    System.out.println("Hashtable entries : ");
		    while(it.hasNext()) {
		    	
		    	  Map.Entry entry = (Map.Entry) it.next();
		    	 tmp =(ArrayList<Pair>) entry.getValue();
		         //System.out.print(entry.getKey() );
		         tm.add((String) entry.getKey());
		    	// Collections.sort(tmp);
		         System.out.println((String) entry.getKey()+"   ");
		 		for (int i = 0; i < tmp.size(); i++) {
		 			System.out.print ("     "+tmp.get(i).getKey() + " : "+tmp.get(i).getValue()+"   " );
		 		}
		 		System.out.println();
		    }
		 /*   Collections.sort(tm);
		   for (int s = 0; s < tm.size(); s++) {
				System.out.println(tm.get(s));
			}*/
		    
	  
		System.out.println(Factory.collecteur.size());
		
		double[] result= new double[CLI.L.size()-1];
		PageRank pa= new PageRank();
			//CLI cli = new CLI();
		 double[] vect =  new double[CLI.L.size()-1];
		 for (int i = 0; i < vect.length; i++) {
			vect[i]=1.0/vect.length;
						
		}
			result = pa.pageRankZap( vect, 0.0000001);
			Factory.collecteur.store(result);
		/*	for (int i = 0; i < result.size(); i++) {
				//System.out.println(Structure.getIdpage( result.get(i).getKey())+"  "+result.get(i).getValue());
			}
	*/
	  
	  
  }
  
  
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  String test (Map<String, Object> model , @RequestParam("page") Optional<Integer> page, 
	      @RequestParam("size") Optional<Integer> size) {
	  List<String> list = new ArrayList<String>();
	  List<Integer> pageNumbers = new ArrayList<>();
	  int currentPage = page.orElse(1);
      int pageSize = size.orElse(5); 
	  Page<String> listPages = pageService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
	  ((Model) model).addAttribute("pageNumbers", pageNumbers);
	  ((Model) model).addAttribute("listPages", listPages);
	  ((Model) model).addAttribute("notfound", false);
      return "index";
  }
 
  
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String searching(@RequestParam Map<String, Object>request,Map<String, Object> model,
		  @RequestParam("page") Optional<Integer> page, 
	      @RequestParam("size") Optional<Integer> size) {
	  boolean notfound ;
      int currentPage = page.orElse(1);
      int pageSize = size.orElse(5); 
	  
	  
	  String requette =(String) request.get("search");
	  pageService.getPages(requette);
	  Page<String> listPages = pageService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

      ((Model) model).addAttribute("listPages", listPages);

      int totalPages = listPages.getTotalPages();
      if (totalPages > 0) {
          List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
              .boxed()
              .collect(Collectors.toList());
          ((Model) model).addAttribute("pageNumbers", pageNumbers);
      }

  
      notfound= true ? listPages.getTotalPages()<=0 : false ;
	  ((Model) model).addAttribute("notfound", notfound);
	   
	 
	  return "index";
	 
  }  
  
  

}
