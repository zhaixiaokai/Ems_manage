package com.sohu.edm.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("restriction")
public class BaseDao extends SqlMapClientDaoSupport {
	@Resource(name="emsSqlMapClient")
	SqlMapClient sqlMapClient;
	@PostConstruct  
    public void setSqlMapClientBase() {  
        super.setSqlMapClient(this.sqlMapClient);  
    }  
}
