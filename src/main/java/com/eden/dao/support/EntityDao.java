package com.eden.dao.support;

public interface EntityDao {
	public <T> T selectOne(String statementId) ;
}
