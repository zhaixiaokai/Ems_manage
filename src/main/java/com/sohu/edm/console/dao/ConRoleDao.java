package com.sohu.edm.console.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.console.dao.map.Sys_role;

@Repository("conRoleDao")
public class ConRoleDao extends BaseDao {

	
	@SuppressWarnings("unchecked")
	public List<Sys_role> getAll(){
		return this.getSqlMapClientTemplate().queryForList("Sys_role.getAll");
	}
	/**
	 * 分页查询角色
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sys_role> getRoleByPage(int pageSize,int currentPage){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		para.put("start", start);
		para.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("Sys_role.getRoleByPage",para);
	}
	/**
	 * 获取角色记录条数
	 * @return
	 */
	public int getTotalRow(){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_role.getTotalRow");
	}
	
	public int getNumByRoleName(String roleName){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_role.getNumByRoleName",roleName);
	}
	
	public int getNumByRoleName(String roleName,int roleId){

		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		para.put("roleName", roleName);
		para.put("roleId", roleId);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_role.getNumByRoleNameModify",para);
	}
	
	public boolean addRole(Sys_role role){
		this.getSqlMapClientTemplate().insert("Sys_role.insertRole",role);
		return true;
	}
	
	public boolean delRoleById(int id){
		this.getSqlMapClientTemplate().delete("Sys_role.delRoleById",id);
		return true;
	}
	
	public Sys_role getRoleById(int id){
		return (Sys_role) this.getSqlMapClientTemplate().queryForObject("Sys_role.getRoleById",id);
	}
	
	public boolean updateRole(Sys_role role){
		this.getSqlMapClientTemplate().update("Sys_role.updateRole",role);
		return true;
	}
	
	public int getTotalRowByRoleName(String roleName){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_role.getTotalRowByRoleName",roleName);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sys_role> getRoleByRoleName(int pageSize,int currentPage,String roleName){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		para.put("start", start);
		para.put("end", end);
		para.put("roleName", roleName);
		return this.getSqlMapClientTemplate().queryForList("Sys_role.getRoleByRoleName",para);
	}
}
