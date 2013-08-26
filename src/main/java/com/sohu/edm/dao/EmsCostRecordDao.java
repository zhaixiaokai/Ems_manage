package com.sohu.edm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository("emsCostRecordDao")
public class EmsCostRecordDao extends BaseDao {
	public void addPointRecord(int entId,String userId,int point,String explain){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("entId", entId);
		map.put("userId", userId);
		map.put("point", point);
		map.put("explain", explain);
		this.getSqlMapClientTemplate().insert("t_cost_record.addPointRecord",map);
	}
	public Integer getTotalRow(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_cost_record.getTotalRow");
	}
	@SuppressWarnings("unchecked")
	public List<Map<Object,Object>> getRecordByPage(int currentPage,int pageSize){

		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("start", start);
		map.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("t_cost_record.getRecordByPage",map);
	}
	public Integer getTotalRowByCondition(int entId, String opUserId) {
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("entId", entId);
		map.put("opUserId", opUserId);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_cost_record.getTotalRowByCondition",map);
	}
	@SuppressWarnings("unchecked")
	public List<Map<Object,Object>> getRecordByPageAndCondition(int currentPage,
			int pageSize, int entId, String opUserId) {
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("entId", entId);
		map.put("opUserId", opUserId);
		return this.getSqlMapClientTemplate().queryForList("t_cost_record.getRecordByPageAndCondition",map);
	}
}
