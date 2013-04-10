package com.eden.mybatis.dialect;

public class PostgresDialect implements Dialect{

	@Override
	public String getLimitSql(String sql, int offset, int size) {
		sql = sql  +  " limit " + size + " offset "  +  offset  ;
		return sql;
	}
}
