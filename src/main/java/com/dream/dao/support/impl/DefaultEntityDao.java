package com.dream.dao.support.impl;

import javax.annotation.Resource;

import com.dream.dao.support.DaoService;
import com.dream.dao.support.EntityDao;

public class DefaultEntityDao implements EntityDao{
	
	@Resource
	private DaoService daoService ;
	
	@Override
	public <T> T selectOne(String statementId) {
		return daoService.selectOne(statementId);
	}
	
	
/*
 * ----------------------------------分隔线--------------------------------------
 **/	
	public DaoService getDaoService() {
		return daoService;
	}
	public void setDaoService(DaoService daoService) {
		this.daoService = daoService;
	}
	
	

}
