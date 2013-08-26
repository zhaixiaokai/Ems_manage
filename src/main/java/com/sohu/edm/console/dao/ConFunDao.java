package com.sohu.edm.console.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.console.dao.map.Sys_fun;

@Repository("conFunDao")
public class ConFunDao extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public List<Sys_fun> getAll(){
		return this.getSqlMapClientTemplate().queryForList("Sys_fun.getAll");
	}
	

	@SuppressWarnings("unchecked")
	public List<Sys_fun> getByPermList(List<Integer> permList){
		Map map	=	new	HashMap();
		map.put("permList", permList);
		return this.getSqlMapClientTemplate().queryForList("Sys_fun.getByPermList",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<String>	getFunNameByIdList(List<Integer> funIds){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("funIds", funIds);
		return this.getSqlMapClientTemplate().queryForList("Sys_fun.getFunNameByIdList",map);
	}
}
