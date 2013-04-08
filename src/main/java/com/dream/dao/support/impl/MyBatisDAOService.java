package com.dream.dao.support.impl;

import java.sql.Connection;

import org.mybatis.spring.SqlSessionTemplate;

import com.dream.dao.support.DaoService;

public class MyBatisDAOService implements DaoService{

	private SqlSessionTemplate sqlSessionTemplate ;
	
	@Override
	public <T> T selectOne(String statementId) {
		return sqlSessionTemplate.selectOne(statementId);
	}

	public <T> T query(String sql) {
		Connection conn = sqlSessionTemplate.getConnection() ;
		return null ;
	}
//	----------------------------------分隔线--------------------------------------
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
