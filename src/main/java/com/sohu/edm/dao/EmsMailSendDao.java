package com.sohu.edm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.dao.map.T_mail_send;
@Repository("emsMailSendDao")
public class EmsMailSendDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public List<T_mail_send> getAll(){
		return this.getSqlMapClientTemplate().queryForList("t_mail_send.getAll");
	}
	@SuppressWarnings("unchecked")
	public List<Object> getAllMailInfo(){
		return  this.getSqlMapClientTemplate().queryForList("t_mail_send.getAllMailInfo");
	}
	public Integer getTotalRow(){
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_mail_send.getTotalRow");
	}
	@SuppressWarnings("unchecked")
	public List<Map<Object, Object>> getMailInfoByPage(int currentPage,int pageSize){

		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("start", start);
		map.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("t_mail_send.getMailInfoByPage",map);
	}
	@SuppressWarnings("unchecked")
	public List<Map<Object,Object>> getMailInfoByPageAndCondition(int currentPage,int pageSize,String startTime,String endTime,String mailName,String entName){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();

		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		map.put("start", start);
		map.put("end", end);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("mailName", mailName);
		map.put("entName", entName);
		return this.getSqlMapClientTemplate().queryForList("t_mail_send.getMailInfoByPageAndCondition",map);	
	}
	
	public Integer getTotalRowByCondition(String startTime,String endTime,String mailName,String entName){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("mailName", mailName);
		map.put("entName", entName);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("t_mail_send.getTotalRowByCondition",map);
	}
}
