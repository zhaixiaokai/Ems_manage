package com.sohu.edm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.stereotype.Repository;

import com.sohu.edm.dao.map.T_ent;
@Repository("emsEntDao")
public class EmsEntDao extends BaseDao {
	@SuppressWarnings("unchecked")
	public List<T_ent> getAll(){
		return this.getSqlMapClientTemplate().queryForList("t_ent.getAll");
	}
	public Integer getTotalRow(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_ent.getTotalRow");
	}
	@SuppressWarnings("unchecked")
	public List<T_ent> getEntByPage(int currentPage,int pageSize){

		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("start", start);
		map.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("t_ent.getEntByPage",map);
	}
	public boolean addEnt(T_ent ent){
		this.getSqlMapClientTemplate().insert("t_ent.addEnt",ent);
		return true;
	}
	@SuppressWarnings("unchecked")
	public List<T_ent> queryByTimeAndEntName(String start,String end,String entName){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("entName", entName);
		return this.getSqlMapClientTemplate().queryForList("t_ent.queryByTimeAndEntName",map);
	}
	
	public Integer getTotalRowByCondition(String start,String end,String entName){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();

		map.put("start", start);
		map.put("end", end);
		map.put("entName", entName);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_ent.getTotalRowByCondition",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<T_ent> queryByPageAndCondition(int pageSize,int currentPage,String startTime,String endTime,String entName){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();

		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		map.put("start", start);
		map.put("end", end);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("entName", entName);
		return this.getSqlMapClientTemplate().queryForList("t_ent.queryByPageAndCondition",map);
	}
	
	public T_ent getEntById(long l){
		return (T_ent) this.getSqlMapClientTemplate().queryForObject("t_ent.getEntById",l);
	}
	public T_ent getEntByEntId(int entId){
		return (T_ent) this.getSqlMapClientTemplate().queryForObject("t_ent.getEntByEntId",entId);
	}
	
	public boolean updateEnt(T_ent ent){
		this.getSqlMapClientTemplate().update("t_ent.updateEnt", ent);
		return true;
	}
	public Integer getNumByEntName(String entName){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_ent.getNumByEntName",entName);
	}
	public Integer getNumByEntName(String entName,int entId){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		para.put("entName", entName);
		para.put("entId", entId);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_ent.getNumByEntNameModify",para);
	}
	public Integer getNumByAdminAccount(String adminAccount){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_ent.getNumByAdminAccount",adminAccount);
	}
	public Integer getNumByAdminAccount(String adminAccount,int entId){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		para.put("adminAccount", adminAccount);
		para.put("entId", entId);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_ent.getNumByAdminAccountModify",para);
	}
	
	public Integer getEntUserNum(int entId){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_ent.getEntUserNum",entId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<Object, Object>> getEntOptions(){
		return this.getSqlMapClientTemplate().queryForList("t_ent.getEntOptions");
	}
	
	public void addPointForEnt(int entId,int point){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		para.put("entId", entId);
		para.put("point", point);
		this.getSqlMapClientTemplate().update("t_ent.addPointForEnt",para);
	}
	
	public T_ent getEntByEntName(String entName){
		return (T_ent) this.getSqlMapClientTemplate().queryForObject("t_ent.getEntByEntName",entName);
	}
}
