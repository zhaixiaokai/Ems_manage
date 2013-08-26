package com.sohu.edm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.dao.map.T_log;

@Repository("emsMLogDao")
public class EmsMLogDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<Map<Object,Object>> getLogByPage(int currentPage,int pageSize){
		Map<Object, Object> map	=	new	HashMap<Object, Object>();

		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		map.put("start", start);
		map.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("t_log.getLogByPage", map);
	}
	
	public Integer getTotalRow(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_log.getTotalRow");
	}
	
	
	public Integer getTotalRowByCondition(String startTime, 
			String endTime, String opUser, String entName,String opType){
		Map<Object,Object> map	=	new HashMap<Object, Object>();

		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("opUser", opUser);
		map.put("entName", entName);
		map.put("opType", opType);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_log.getTotalRowByCondition",map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<Object, Object>> getLogByPageAndCondition(int currentPage,
			int pageSize, String startTime, String endTime, String opUser,
			String entName,String opType) {
		Map<Object,Object> map	=	new HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		map.put("start", start);
		map.put("end", end);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("opUser", opUser);
		map.put("entName", entName);
		map.put("opType", opType);
		return this.getSqlMapClientTemplate().queryForList("t_log.getLogByPageAndCondition", map);
	}
	public void insertLog(T_log log){
		this.getSqlMapClientTemplate().insert("t_log.insertLog",log);
	}
	
	
}
