package com.eden.dao.support;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface EntityDao {
	public <T> T selectOne(String statementId);

	public <T> T selectOne(String statementId, Object param);

	public <T> List<T> selectList(String statementId);
	
	public <T> List<T> selectList(String statementId , Object param);

	public <T> List<T> selectList(String statementId ,  RowBounds rowBounds);
	
	public <T> List<T> selectList(String statementId, Object parameters, RowBounds rowBounds);
	
	public <T> List<T> selectPage(String statementId , Page page) ;
	
	public int selectCount(String statementId , Page page) ;

/*------------------------------------------------分隔线---------------------------------------------------*/
	
	public int insert(String statement);

	public int insert(String statementId, Object param);

	public int insertAll(String statementId, Collection<? extends Object> params);

	/*------------------------------------------------分隔线---------------------------------------------------*/
	
	public int update(String statementId, Object parameters);

	public int updateAll(String statementId, Collection<? extends Object> params);

	/*------------------------------------------------分隔线---------------------------------------------------*/

	public int delete(String statementId, Object param);

	public int deleteAll(String statementId, Collection<? extends Object> params);
}
