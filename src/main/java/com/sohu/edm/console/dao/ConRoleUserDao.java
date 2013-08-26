package com.sohu.edm.console.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.console.dao.map.Sys_role_user;

@Repository("conRoleUserDao")
public class ConRoleUserDao extends BaseDao{

	
	@SuppressWarnings("unchecked")
	public List<Sys_role_user> getAll(){
		return this.getSqlMapClientTemplate().queryForList("Sys_role_user.getAll");
	}
	
	public boolean delRole2UserByUserId(int userId){
		this.getSqlMapClientTemplate().delete("Sys_role_user.delRole2UserByUserId",userId);
		return true;
	}
	public boolean delRole2UserByRoleId(int roleId){
		this.getSqlMapClientTemplate().delete("Sys_role_user.delRole2UserByRoleId",roleId);
		return true;
	}
	/**
	 * 按用户ID获取角色-用户数据列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sys_role_user> getRolesByUserId(int userId){
		return this.getSqlMapClientTemplate().queryForList("Sys_role_user.getRolesByUserId",userId);
	}
	/**
	 * 按用户ID获取该用户所拥有的角色ID列表
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Integer> getRolesIdByUserId(int userId){
		return this.getSqlMapClientTemplate().queryForList("Sys_role_user.getRolesIdByUserId",userId);
	}
	/**
	 * 从角色中删除用户
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public boolean delUserFromRole(int userId,int roleId){
		Map<Object,Object> map	=	new	HashMap<Object,Object>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		this.getSqlMapClientTemplate().delete("Sys_role_user.delUserFromRole",map);
		return true;
	}
	/**
	 * 为角色添加用户
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public boolean addUserIntoRole(int userId,int roleId){
		Map<Object,Object> map	=	new	HashMap<Object,Object>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		this.getSqlMapClientTemplate().delete("Sys_role_user.addUserIntoRole",map);
		return true;
	}
	/**
	 * 查询指定用户、角色的记录条数，用来标识该用户是否在该角色内
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public Integer getCountByUserIdAndRoleId(long userId,int roleId){
		Map<Object,Object> map	=	new	HashMap<Object,Object>();
		map.put("userId", userId);
		map.put("roleId", roleId);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("Sys_role_user.getCountByUserIdAndRoleId", map);
		
	}
}
