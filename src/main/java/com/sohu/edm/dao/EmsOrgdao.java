package com.sohu.edm.dao;
import org.springframework.stereotype.Repository;

import com.sohu.edm.dao.map.T_org;

@Repository("emsOrgDao")
public class EmsOrgdao extends BaseDao {
	public T_org getOrgByOrgId(int orgId){
		return (T_org) this.getSqlMapClientTemplate().queryForObject("t_org.getOrgByOrgId", orgId);
	}
}
