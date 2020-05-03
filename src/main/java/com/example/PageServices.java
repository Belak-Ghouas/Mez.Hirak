package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import Outils.*;

public class PageServices {

	private List<String> pages = new ArrayList();

	public Page<String> findPaginated(Pageable pageable) {
		
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<String> list;

		if (pages.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, pages.size());
			list = pages.subList(startItem, toIndex);
		}

		Page<String> ListPages = new PageImpl<String>(list, PageRequest.of(currentPage, pageSize), pages.size());

		return ListPages;
	}
	
	
	

	public ArrayList<Pair<String, String>> getPages(String request) {

		ArrayList<Pair<String, String>> Pairpages = new ArrayList<Pair<String, String>>();

		this.pages = Getter.Mapper.get(request);
		
		this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
	
		this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
this.pages.add("aaa");
		for (int i = 0; i < this.pages.size(); i++) {
			Pair<String, String> pair = new Pair<String, String>(this.pages.get(i), "HHHHHHHHHH");
			Pairpages.add(pair);
		}

		return Pairpages;

	}

}
