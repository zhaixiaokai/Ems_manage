package com.sohu.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConFunDao;
import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConPermDao;
import com.sohu.edm.console.dao.ConRoleDao;
import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_log;
import com.sohu.edm.console.dao.map.Sys_perm;

@Service("conPermService")
public class ConPermService {
	@Autowired
	ConPermDao conPermDao;
	@Autowired
	ConLogDao conLogDao;
	@Autowired
	ConFunDao conFunDao;
	@Autowired
	ConRoleDao conRoleDao;
	@Autowired 
	ConUserDao conUserDao;
	
	public List<Integer>	getUserPerm(int userId){
		List<Integer> permList	=	new	ArrayList<Integer>();
		List<Sys_perm> sysPermList	=	conPermDao.getByUserId(userId);
		for(Sys_perm perm:sysPermList){
			permList.add(perm.getFunId());
		}
		return permList;
	}
	
	public void delUserPermByUserId(int userId){
		conPermDao.delUserPermByUserId(userId);
		return;
	}
	
	public void insertUserPermByUserId(int userId,int funId){
		conPermDao.insertUserPermByUserId(userId, funId);
	}
	
	public void insertUserPermByUserIdBatch(int userId,String [] perms){
		for(String perm:perms){
			this.insertUserPermByUserId(userId, Integer.valueOf(perm));
		}
	}
	public void updateUserPerm(int userId,String [] perms,int opUserId){

		List<Integer> oldFunIds	=	conPermDao.getPermFunListByUserId(userId);
		List<String> oldFunNames	=	conFunDao.getFunNameByIdList(oldFunIds);
		List<Integer> newFunIds	=	new	ArrayList<Integer>();
		for(String id:perms){
			newFunIds.add(Integer.valueOf(id));
		}
		List<String> newFunNames	=	conFunDao.getFunNameByIdList(newFunIds);
		this.delUserPermByUserId(userId);
		this.insertUserPermByUserIdBatch(userId, perms);
		
		Sys_log	log	=	new	Sys_log();
		log.setOpUserId(opUserId);
		log.setLogType(5);
		log.setLogDesc("修改用户权限：修改["+"用户ID:["+userId+"]"+"用户名:["+conUserDao.getUserById(userId).getUserName()+"]]的权限:旧"+oldFunNames+"新"+newFunNames+"");
		conLogDao.insertLog(log);
	}
	
	public List<Integer>	getRolePerm(int roleId){
		List<Integer> permList	=	new	ArrayList<Integer>();
		List<Sys_perm> sysPermList	=	conPermDao.getByRoleId(roleId);
		for(Sys_perm perm:sysPermList){
			permList.add(perm.getFunId());
		}
		return permList;
	}
	public void delRolePermByRoleId(int roleId){
		conPermDao.delRolePermByRoleId(roleId);
		return;
	}
	
	public void insertRolePermByRoleId(int roleId,int funId){
		conPermDao.insertRolePermByRoleId(roleId, funId);
	}
	public void insertRolePermByRoleIdBatch(int roleId,String[] perms){
		for(String perm:perms){
			this.insertRolePermByRoleId(roleId, Integer.valueOf(perm));
		}
	}
	public void updateRolePerm(int roleId,String[] perms,int opUserId){
		List<Integer> oldFunIds	=	conPermDao.getPermFunListByRole(roleId);
		List<String> oldFunNames	=	conFunDao.getFunNameByIdList(oldFunIds);
		this.delRolePermByRoleId(roleId);
		this.insertRolePermByRoleIdBatch(roleId, perms);
		List<Integer> newFunIds	=	new	ArrayList<Integer>();
		for(String id:perms){
			newFunIds.add(Integer.valueOf(id));
		}
		List<String> newFunNames	=	conFunDao.getFunNameByIdList(newFunIds);
		
		Sys_log	log	=	new	Sys_log();
		log.setOpUserId(opUserId);
		log.setLogType(5);
		log.setLogDesc("修改角色权限：修改角色["+"角色ID:["+roleId+"]"+"角色名:["+conRoleDao.getRoleById(roleId).getRoleName()+"]]的权限:旧"+oldFunNames+"新"+newFunNames+"");
		conLogDao.insertLog(log);
	}
	
	public void logDelRolePerm(int roleId,int opUserId){
		List<Integer> oldFunIds	=	conPermDao.getPermFunListByRole(roleId);
		List<String> oldFunNames	=	conFunDao.getFunNameByIdList(oldFunIds);
		Sys_log	log	=	new	Sys_log();
		log.setOpUserId(opUserId);
		log.setLogType(5);
		log.setLogDesc("删除角色权限：删除角色["+"角色ID:["+roleId+"]"+"角色名:["+conRoleDao.getRoleById(roleId).getRoleName()+"]]的权限："+oldFunNames);
		conLogDao.insertLog(log);
	}
	public void logDelUserPerm(int userId,int opUserId){
		List<Integer> oldFunIds	=	conPermDao.getPermFunListByUserId(userId);
		List<String> oldFunNames	=	conFunDao.getFunNameByIdList(oldFunIds);
		Sys_log	log	=	new	Sys_log();
		log.setOpUserId(opUserId);
		log.setLogType(5);
		log.setLogDesc("删除用户权限：删除["+"用户ID:["+userId+"]"+"用户名:["+conUserDao.getUserById(userId).getUserName()+"]]的权限"+oldFunNames);
		conLogDao.insertLog(log);		
	}
}
