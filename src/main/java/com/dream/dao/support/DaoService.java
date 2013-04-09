package com.dream.dao.support;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface DaoService {

	public static final String INSERT = "insert";  
	  
    public static final String UPDATE = "update";  
  
    public static final String DELETE = "delete";  
  
    public static final String DELETE_ID = "deleteById";  
    
    public static final String SELECT_ID = "selectById";
  
    public static final String SELECT = "select";  
  
    public static final String SELECT_ALL = "selectAll";  
  
    public static final String SELECTMAP = "selectByMap";  
  
    public static final String SELECTSQL = "selectBySql";  
  
    public static final String COUNT = "count";  
    
  
	public <T> T selectOne(String statementId);

	public <T> T selectOne(String statementId, Object param);

	public <T> T selectOne(Class<T> entityClass, Serializable id);

	public <T> List<T> selectList(String statementId);
	
	public <T> List<T> selectList(String statementId , Object param);

	public <T> List<T> selectList(String statementId ,  RowBounds rowBounds);
	
	public <T> List<T> selectList(String statementId, Object parameters, RowBounds rowBounds);
	
	public <T> List<T> selectPage(String statementId , Page page) ;
	
	public long selectCount(String statementId , Page page) ;

	public <T> T query(String sql);
	
/*------------------------------------------------分隔线---------------------------------------------------*/
	
	public int insert(String statement);

	public <T> int insert(T o);

	public int insert(String statementId, Object param);

	public int insertAll(String statementId, Collection<? extends Object> params);

	/*------------------------------------------------分隔线---------------------------------------------------*/
	
	public <T> int update(T o);

	public int update(String statementId, Object parameters);

	public int updateAll(String statementId, Collection<? extends Object> params);

	/*------------------------------------------------分隔线---------------------------------------------------*/
	public <T> int deleteById(Class<T> entityClass, Serializable id);

	public <T> int delete(T o);

	public int delete(String statementId, Object param);

	public int deleteAll(String statementId, Collection<? extends Object> params);

}
