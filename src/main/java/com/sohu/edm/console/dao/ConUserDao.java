package com.sohu.edm.console.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sohu.edm.console.dao.map.Sys_user;

@Repository("conUserDao")
public class ConUserDao extends BaseDao {
	/**
	 * 获取所有用户列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sys_user> getAll(){
		return this.getSqlMapClientTemplate().queryForList("Sys_user.getAll");
	}
	/**
	 * 分页查询用户
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sys_user> getUserByPage(int pageSize,int currentPage){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		para.put("start", start);
		para.put("end", end);
		return this.getSqlMapClientTemplate().queryForList("Sys_user.getUserByPage",para);
	}
	/**
	 * 获取用户记录条数
	 * @return
	 */
	public int getTotalRow(){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_user.getTotalRow");
	}
	/**
	 * 验证用户名存在的条数，用于验证是否重复
	 * @param username
	 * @return
	 */
	public int getNumByUserName(String username){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_user.getNumByUserName",username);
	}
	/**
	 * 获取除当前id除外的与当前用户名相同的个数，用于修改时的验证
	 * @param username
	 * @param userId
	 * @return
	 */
	public int getNumByUserName(String username,int userId){

		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		para.put("username", username);
		para.put("userId", userId);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_user.getNumByUserNameModify",para);
	}
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUser(Sys_user user){
		this.getSqlMapClientTemplate().insert("Sys_user.insertUser",user);
		return true;
	}
	/**
	 * 删除用户
	 * @param id	用户ID
	 * @return
	 */
	public boolean delUserById(int id){
		this.getSqlMapClientTemplate().delete("Sys_user.delUserById",id);
		return true;
	}
	/**
	 * 设置管理员
	 * @param id	用户ID
	 * @return
	 */
	public boolean setAdminById(int id){
		this.getSqlMapClientTemplate().update("Sys_user.setAdminById",id);
		return true;
	}
	/**
	 * 解除管理员
	 * @param id	用户ID
	 * @return
	 */
	public boolean unsetAdminById(int id){
		this.getSqlMapClientTemplate().update("Sys_user.unsetAdminById",id);
		return true;
	}
	/**
	 * 通过用户id获取用户信息
	 * @param id
	 * @return
	 */
	public Sys_user getUserById(int id){
		return (Sys_user) this.getSqlMapClientTemplate().queryForObject("Sys_user.getUserById",id);
	}
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(Sys_user user){
		this.getSqlMapClientTemplate().update("Sys_user.updateUser",user);
		return true;
	}
	/**
	 * 根据用户名分页查询符合条件的记录（模糊查询）
	 * @param pageSize		分页大小
	 * @param currentPage	当前页数
	 * @param userName		用户名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sys_user> getUserByPageAndUserName(int pageSize,int currentPage,String userName){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		para.put("start", start);
		para.put("end", end);
		para.put("userName", userName);
		return this.getSqlMapClientTemplate().queryForList("Sys_user.getUserByPageAndUserName",para);
	}
	

	/**
	 * 根据用户名模糊查询获取用户记录条数
	 * @return
	 */
	public int getTotalRowByUserName(String userName){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_user.getTotalRowByUserName",userName);
	}
	/**
	 * 根据用户姓名分页查询符合条件的记录
	 * @param pageSize
	 * @param currentPage
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sys_user> getUserByPageAndName(int pageSize,int currentPage,String name){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		para.put("start", start);
		para.put("end", end);
		para.put("name", name);
		return this.getSqlMapClientTemplate().queryForList("Sys_user.getUserByPageAndName",para);
	}
	/**
	 * 根据用户姓名模糊查询获取用户记录条数
	 * @return
	 */
	public int getTotalRowByName(String name){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_user.getTotalRowByName",name);
	}
	/**
	 * 根据是否是管理员分页查询记录
	 * @param pageSize
	 * @param currentPage
	 * @param ifAdmin
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Sys_user> getUserByPageAndIfAdmin(int pageSize,int currentPage,String userType){
		Map<Object, Object> para	=	new	HashMap<Object, Object>();
		int start	=	pageSize*(currentPage-1);
		int end		=	pageSize;
		para.put("start", start);
		para.put("end", end);
		para.put("userType", userType);
		return this.getSqlMapClientTemplate().queryForList("Sys_user.getUserByPageAndIfAdmin",para);
	}
	/**
	 * 根据用户类型查询获取用户记录条数
	 * @return
	 */
	public int getTotalRowByUserType(String userType){
		return (Integer)this.getSqlMapClientTemplate().queryForObject("Sys_user.getTotalRowByUserType",userType);
	}
	
	public Sys_user getUserByUserName(String userName){
		return (Sys_user) this.getSqlMapClientTemplate().queryForObject("Sys_user.getUserByUserName",userName);
	}
}
