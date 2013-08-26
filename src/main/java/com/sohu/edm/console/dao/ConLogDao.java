package com.sohu.edm.console.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.console.dao.map.Sys_log;

@Repository("conLogDao")
public class ConLogDao extends BaseDao {

	
	@SuppressWarnings("unchecked")
	public List<Sys_log> getAll(){
		return this.getSqlMapClientTemplate().queryForList("Sys_log.getAll");
	}
	
	public boolean insertLog(Sys_log log){
		this.getSqlMapClientTemplate().insert("Sys_log.insertLog", log);
		return true;
	}
	
	public Integer getTotalRow(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Sys_log.getTotalRow");
	}
	@SuppressWarnings("unchecked")
	public List<Map<Object,Object>> getLogByPage(int currentPage,int pageSize){
		Map<Object, Object> map	=	new	HashMap<Object, Object>();

		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		map.put("start", start);
		map.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("Sys_log.getLogByPage",map);
	}
	
	public Integer getTotalRowByCondition(String startTime, String endTime, String opUser){
		Map<Object,Object> map	=	new HashMap<Object, Object>();

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("opUser", opUser);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Sys_log.getTotalRowByCondition",map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<Object, Object>> getLogByPageAndCondition(int currentPage,
			int pageSize, String startTime, String endTime, String opUser) {
		Map<Object,Object> map	=	new HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		map.put("start", start);
		map.put("end", end);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("opUser", opUser);
		return this.getSqlMapClientTemplate().queryForList("Sys_log.getLogByPageAndCondition", map);
	}
}
