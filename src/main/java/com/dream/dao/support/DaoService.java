package com.dream.dao.support;


public interface DaoService {
	
	public <T> T selectOne(String statementId) ;
	
	public <T> T query(String sql) ;
}
