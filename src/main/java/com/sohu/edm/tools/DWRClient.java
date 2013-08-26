package com.sohu.edm.tools;

import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sohu.edm.console.dao.ConRoleDao;
import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_role;
import com.sohu.edm.console.dao.map.Sys_user;
import com.sohu.edm.dao.EmsEntDao;
@Controller  
@RemoteProxy(creator = SpringCreator.class, name = "emsDwr")
public class DWRClient {
	@Autowired
	ConUserDao conUserDao;
	@Autowired
	ConRoleDao conRoleDao;
	@Autowired
	EmsEntDao	emsEntDao;
	
	public String saySth(){
		return "helloworld";
	}
	public int getUserNameNum(String username){
		return conUserDao.getNumByUserName(username);
	}
	public int getUserNameNumModify(String username,int userId){
		return conUserDao.getNumByUserName(username,userId);
	}	
	public Sys_user getUserNameById(int id){
		return conUserDao.getUserById(id);
	}
	public int getRoleNameNum(String roleName){
		return conRoleDao.getNumByRoleName(roleName);
	}
	public int getRoleNameNumModify(String roleName,int roleId){
		return conRoleDao.getNumByRoleName(roleName,roleId);
	}
	public Sys_role getRoleById(int id){
		return conRoleDao.getRoleById(id);
	}
	public int	getEntNameNun(String entName){
		return emsEntDao.getNumByEntName(entName);
	}

	public int	getEntNameNunModify(String entName,int entId){
		return emsEntDao.getNumByEntName(entName,entId);
	}
	public int	getAdminAccountNum(String adminAccount){
		return emsEntDao.getNumByAdminAccount(adminAccount);
	}

	public int	getAdminAccountNumModify(String adminAccount,int entId){
		return emsEntDao.getNumByAdminAccount(adminAccount,entId);
	}
}
