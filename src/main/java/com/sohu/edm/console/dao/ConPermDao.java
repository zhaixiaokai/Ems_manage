package com.sohu.edm.console.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.console.dao.map.Sys_perm;

@Repository("conPermDao")
public class ConPermDao extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public List<Sys_perm> getAll(){
		return this.getSqlMapClientTemplate().queryForList("Sys_perm.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public List<Sys_perm> getByUserId(int userId){
		return this.getSqlMapClientTemplate().queryForList("Sys_perm.getByUserId",userId);
	}
	
	public boolean delUserPermByUserId(int userId){
		this.getSqlMapClientTemplate().delete("Sys_perm.delByUserId",userId);
		return true;
	}
	
	public boolean insertUserPermByUserId(int userId,int funId){
		Map<Object, Object> paraMap	=	new	HashMap<Object, Object>();
		paraMap.put("userId", userId);
		paraMap.put("funId", funId);
		this.getSqlMapClientTemplate().insert("Sys_perm.insertByUserId",paraMap);
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> getPermFunListByUserId(int userId){
		return this.getSqlMapClientTemplate().queryForList("Sys_perm.getPermFunListByUserId",userId);
	}
	
	/*****************************角色************************************/
	@SuppressWarnings("unchecked")
	public List<Sys_perm> getByRoleId(int roleId){
		return this.getSqlMapClientTemplate().queryForList("Sys_perm.getByRoleId",roleId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sys_perm> getByRoleList(String roles){
		return this.getSqlMapClientTemplate().queryForList("Sys_perm.getByRoleList",roles);
	}
	public boolean delRolePermByRoleId(int roleId){
		this.getSqlMapClientTemplate().delete("Sys_perm.delByRoleId",roleId);
		return true;
	}
	public boolean insertRolePermByRoleId(int roleId,int funId){
		Map<Object, Object> paraMap	=	new	HashMap<Object, Object>();
		paraMap.put("roleId", roleId);
		paraMap.put("funId", funId);
		this.getSqlMapClientTemplate().insert("Sys_perm.insertByRoleId",paraMap);
		return true;
	}
	@SuppressWarnings("unchecked")
	public List<Integer> getPermFunListByRole(int roleId){
		return this.getSqlMapClientTemplate().queryForList("Sys_perm.getPermFunListByRole",roleId);
	}
	
	/*******************************按角色和用户***************************************/

	/**
	 * 按用户ID和角色ID的list获取用户权限
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getByRoleListAndUserId(int userId,List<Integer> roleList){
		Map<Object,Object> map	=	new	HashMap<Object, Object>();
		map.put("roleList", roleList);
		map.put("userId", userId);
		return this.getSqlMapClientTemplate().queryForList("Sys_perm.getByRoleListAndUserIdParaList",map);
	}
}
