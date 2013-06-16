package com.eden.dao.support;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Page implements Serializable , Cloneable{
	private static final long serialVersionUID = 1L;

	private int start = 0; 
	private int pageSize = 10; 
	private int total = 0 ;
	private int pageCount = 0 ; 
	private int currPage = 1 ; 

	private String sortType = "desc"; 
	private String sortValue; 

	private Map<String, Object> queryMap ;
	
	public Page() {
		queryMap = new HashMap<String , Object>() ;
	}
	
	public Page(int maxResults, int firstResult, int totalRecords,
			int pageCount, String findType, String findValue, String sortType,
			String sortValue) {
		this();
		this.pageSize = maxResults;
		this.start = firstResult;
		this.total = totalRecords;
		this.pageCount = pageCount;
		this.sortType = sortType;
		this.sortValue = sortValue;
	}
	
	public void update(){
		if(total > 0){
			if (total % pageSize == 0) {
				this.pageCount = total / this.pageSize;
			} else {
				this.pageCount = total / this.pageSize + 1;
			}
			if (currPage > pageCount) {
				currPage -= 1;
			}
			if(currPage > 0)
				start = (currPage - 1) * pageSize ;
			else {
				currPage = 1 ;
			}
		} else {
			start = 0; 
//			pageSize = 20; 
			total = 0 ;
			pageCount = 0 ; 
			currPage = 0 ;
		}
	}
	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrentPage(int currentPage) {
		if(currPage > pageCount) return ;
		this.currPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize < 1) return ;
		this.pageSize = pageSize;
		update() ;
	}

	public int getStart() {
		return start;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		update() ;
	}

	public int getPageCount() {
		return pageCount;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String newSortType) {
		if("desc".equalsIgnoreCase(newSortType) || "asc".equalsIgnoreCase(newSortType)){
			this.sortType = newSortType;
		}
	}

	public String getSortValue() {
		return sortValue;
	}

	public void setSortValue(String newSortValue) {
		this.sortValue = newSortValue;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage ;
	}
	
	
	@Override
	public Page clone(){
		Page page = new Page() ;
		page.start = this.start ;
		page.pageSize = this.pageSize ;
		page.pageCount = this.pageCount ;
		page.currPage = this.currPage ;

		page.sortType = this.sortType ;
		page.sortValue = this.sortValue ;
		
		
		return page ;
	}

	public Map<String, Object> getQueryMap() {
		return queryMap;
	}

	public void setQueryMap(Map<String, Object> queryMap) {
		this.queryMap = queryMap;
	}
	
	public void setQueryValue(String findType , Object findValue){
		queryMap.put(findType, findValue) ;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getQueryValue(String queryType){
		return (T)queryMap.get(queryType) ;
	}
	
	
	public String getQueryType(){
		if(queryMap.isEmpty()) return null ;
		return (String)queryMap.keySet().toArray()[0] ;
	}
}
