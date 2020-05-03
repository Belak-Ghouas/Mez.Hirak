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
import static javax.measure.unit.SI.KILOGRAM;
import javax.measure.quantity.Mass;
import org.jscience.physics.model.RelativisticModel;
import org.jscience.physics.amount.Amount;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import Building.WriteXmlFile;
import Outils.CLI;
import Outils.PageRank;
import Outils.Pair;
import Traitement.Factory;
import Traitement.MyXmlHandler;
import Traitement.ReadXmlFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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
import java.util.Map;
import java.util.Set;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  // @Autowired
  //private DataSource dataSource;

   private static Getter getter;
  
  
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
    deploy();
    
  
  }

  @RequestMapping("/")
  String index() {
    return "index";
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
  @RequestMapping("/hello")
  String hello(Map<String, Object> model) {
      RelativisticModel.select();
      Amount<Mass> m = Amount.valueOf("12 GeV").to(KILOGRAM);
      model.put("science", "E=mc^2: 12 GeV = " + m.toString());
      return "hello";
  }

  @PostMapping("/test")
  public String ess (Map<String, Object> model) {
	  System.out.println(model.size());
	  
  return "success";
  }
  @RequestMapping("/test")
  String test (Map<String, Object> model) {
     
     
      return "Accueil";
  }
  /*
  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }*/
  
  
  @RequestMapping(value = "/searching", method = RequestMethod.POST)
  public String search(@RequestParam Map<String, Object>request,Map<String, Object> model) {
	  
	  
	  
	  
	  
	  String requette =(String) request.get("search");
	  System.out.println(requette);

	  ArrayList<Pair<String,String>> pages =new ArrayList<Pair<String,String>>();
	  
	  System.out.println(getter.Mapper.size());
	  if(getter.Mapper.containsKey(requette.trim())) {
		 ArrayList<String> tmp =getter.Mapper.get(requette);
		 for (int i = 0; i < tmp.size(); i++) {
			Pair<String ,String> pair = new Pair<String,String>(tmp.get(i),"HHHHHHHHHH");
			pages.add(pair);
		}
		  
	  }
	  
	  
	    model.put("pages",pages);
	 
	  return "bingo";
	 
  }  
  
  /*
  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }*/

}
