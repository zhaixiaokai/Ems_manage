package com.sohu.edm.dao;

import org.springframework.stereotype.Repository;

@Repository("emsUserDao")
public class EmsUserDao extends BaseDao {
	
	public Integer getUserNumByUserName(String userName){
		return Integer.valueOf(this.getSqlMapClientTemplate().queryForObject("t_usr.getUserNumByUserName",userName).toString());
	}
}
