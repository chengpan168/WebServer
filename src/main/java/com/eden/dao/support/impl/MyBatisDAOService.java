package com.eden.dao.support.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.eden.dao.support.DaoService;
import com.eden.dao.support.Page;

@Repository
public class MyBatisDAOService implements DaoService{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate ;
	
	@Override
	public <T> T selectOne(String statementId) {
		return sqlSessionTemplate.selectOne(statementId);
	}

	
	public <T> T query(String sql) {
		Connection conn = sqlSessionTemplate.getConnection() ;
		return null ;
	}

	@Override
	public <T> T selectOne(String statementId, Object param) {
		return sqlSessionTemplate.selectOne(statementId , param );
	}


	@Override
	public <T> T selectOne(Class<T> entityClass, Serializable id) {
		return sqlSessionTemplate.selectOne(getStatementId(entityClass, SELECT_ID) , id );
	}


	@Override
	public <T> List<T> selectList(String statementId) {
		return sqlSessionTemplate.selectList(statementId);
	}

	@Override
	public <T> List<T> selectList(String statementId,
			Object param) {
		return sqlSessionTemplate.selectList(statementId , param);
	}


	@Override
	public <T> List<T> selectList(String statementId,
			RowBounds rowBounds) {
		return selectList(statementId , null , rowBounds);
	}


	@Override
	public <T> List<T> selectList(String statementId,
			Object param, RowBounds rowBounds) {
		return sqlSessionTemplate.selectList(statementId , param , rowBounds);
	}


	@Override
	public <T> List<T> selectPage(String statementId,
			Page page) {
		RowBounds rowBounds = null ;
		if(page!=null){
			rowBounds = new RowBounds(page.getTotal(), page.getPageCount()) ;
		}
		return selectList(statementId , page , rowBounds);
	}


	@Override
	public long selectCount(String statementId, Page page) {
		return sqlSessionTemplate.selectOne(statementId, page) ;
	}


	@Override
	public int insert(String statement) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <T> int insert(T o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int insert(String statementId, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int insertAll(String statementId, Collection<? extends Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <T> int update(T o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int update(String statementId, Object parameters) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int updateAll(String statementId, Collection<? extends Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <T> int deleteById(Class<T> entityClass, Serializable id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <T> int delete(T o) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int delete(String statementId, Object param) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int deleteAll(String statementId, Collection<? extends Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public String getStatementId(Class<? extends Object> entityClass , String type) {
		return entityClass.getSimpleName() + "." + type ;
	}
	
//	----------------------------------分隔线--------------------------------------
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

}
