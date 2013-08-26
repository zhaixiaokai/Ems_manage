package com.sohu.edm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConPermDao;
import com.sohu.edm.console.dao.ConRoleUserDao;
import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_log;
import com.sohu.edm.console.dao.map.Sys_user;
import com.sohu.edm.model.PageBean;
import com.sohu.edm.model.RoleUserDomain;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.tools.LogType;

@Service("conUserService")
public class ConUserService {
	@Autowired
	ConUserDao conUserDao;
	@Autowired
	ConLogDao conLogDao;
	@Autowired
	ConRoleUserDao conRoleUserDao;
	@Autowired
	ConPermDao	conPermDao;
	public PageBean getUserByPage(int currentPage){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow	=	conUserDao.getTotalRow();
		pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
		pageBean.setData(conUserDao.getUserByPage(Constants.MAX_RECORD_PER_PAGE, currentPage));
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
	public PageBean getUserByPage(int currentPage,int roleId){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow	=	conUserDao.getTotalRow();
		pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
		List<Sys_user> userList	=	conUserDao.getUserByPage(Constants.MAX_RECORD_PER_PAGE, currentPage);
		List<RoleUserDomain> domainList	=	new	ArrayList<RoleUserDomain>();
		for(Sys_user user:userList){
			RoleUserDomain domain	=	new	RoleUserDomain();
			domain.setId(user.getId());
			domain.setName(user.getName());
			domain.setCreateTime(user.getCreateTime());
			domain.setEmail(user.getEmail());
			domain.setUpdateTime(user.getUpdateTime());
			domain.setUserName(user.getUserName());
			domain.setUserType(user.getUserType());
			domain.setInRoleFlag(conRoleUserDao.getCountByUserIdAndRoleId(user.getId(), roleId));
			domainList.add(domain);
		}
		
		pageBean.setData(domainList);
		pageBean.setTotalRow(totalRow);
		return pageBean;
		
	}
	public PageBean getUserByPageAndCondition(int currentPage,String cond,String condition){
		PageBean pageBean = new PageBean();
		if(condition.equals("1")){
			//按用户名
			pageBean.setCurrentPage(currentPage);
			int totalRow	=	conUserDao.getTotalRowByUserName(cond);
			pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
			pageBean.setData(conUserDao.getUserByPageAndUserName(Constants.MAX_RECORD_PER_PAGE, currentPage,cond));
			pageBean.setTotalRow(totalRow);
		}else if(condition.equals("2")){
			//按姓名
			pageBean.setCurrentPage(currentPage);
			int totalRow	=	conUserDao.getTotalRowByName(cond);
			pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
			pageBean.setData(conUserDao.getUserByPageAndName(Constants.MAX_RECORD_PER_PAGE, currentPage,cond));
			pageBean.setTotalRow(totalRow);
		}else if(condition.equals("3")){
			//按是否管理员
			pageBean.setCurrentPage(currentPage);
			int totalRow	=	conUserDao.getTotalRowByUserType(cond);
			pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
			pageBean.setData(conUserDao.getUserByPageAndIfAdmin(Constants.MAX_RECORD_PER_PAGE, currentPage,cond));
			pageBean.setTotalRow(totalRow);
		}
		return pageBean;
	}

	public PageBean getUserByPageAndCondition(int currentPage,String cond,String condition,int roleId){
		PageBean pageBean = new PageBean();
		if(condition.equals("1")){
			//按用户名
			pageBean.setCurrentPage(currentPage);
			int totalRow	=	conUserDao.getTotalRowByUserName(cond);
			pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
			
			List<Sys_user> userList	=	conUserDao.getUserByPageAndUserName(Constants.MAX_RECORD_PER_PAGE, currentPage,cond);
			List<RoleUserDomain> domainList	=	new	ArrayList<RoleUserDomain>();
			for(Sys_user user:userList){
				RoleUserDomain domain	=	new	RoleUserDomain();
				domain.setId(user.getId());
				domain.setName(user.getName());
				domain.setCreateTime(user.getCreateTime());
				domain.setEmail(user.getEmail());
				domain.setUpdateTime(user.getUpdateTime());
				domain.setUserName(user.getUserName());
				domain.setUserType(user.getUserType());
				domain.setInRoleFlag(conRoleUserDao.getCountByUserIdAndRoleId(user.getId(), roleId));
				domainList.add(domain);
			}
			
			pageBean.setData(domainList);
			pageBean.setTotalRow(totalRow);
		}else if(condition.equals("2")){
			//按姓名
			pageBean.setCurrentPage(currentPage);
			int totalRow	=	conUserDao.getTotalRowByName(cond);
			pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
			
			List<Sys_user> userList	=	conUserDao.getUserByPageAndName(Constants.MAX_RECORD_PER_PAGE, currentPage,cond);
			List<RoleUserDomain> domainList	=	new	ArrayList<RoleUserDomain>();
			for(Sys_user user:userList){
				RoleUserDomain domain	=	new	RoleUserDomain();
				domain.setId(user.getId());
				domain.setName(user.getName());
				domain.setCreateTime(user.getCreateTime());
				domain.setEmail(user.getEmail());
				domain.setUpdateTime(user.getUpdateTime());
				domain.setUserName(user.getUserName());
				domain.setUserType(user.getUserType());
				domain.setInRoleFlag(conRoleUserDao.getCountByUserIdAndRoleId(user.getId(), roleId));
				domainList.add(domain);
			}
			
			pageBean.setData(domainList);
			pageBean.setTotalRow(totalRow);
		}else if(condition.equals("3")){
			//按是否管理员
			pageBean.setCurrentPage(currentPage);
			int totalRow	=	conUserDao.getTotalRowByUserType(cond);
			pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
			
			List<Sys_user> userList	=	conUserDao.getUserByPageAndIfAdmin(Constants.MAX_RECORD_PER_PAGE, currentPage,cond);
			List<RoleUserDomain> domainList	=	new	ArrayList<RoleUserDomain>();
			for(Sys_user user:userList){
				RoleUserDomain domain	=	new	RoleUserDomain();
				domain.setId(user.getId());
				domain.setName(user.getName());
				domain.setCreateTime(user.getCreateTime());
				domain.setEmail(user.getEmail());
				domain.setUpdateTime(user.getUpdateTime());
				domain.setUserName(user.getUserName());
				domain.setUserType(user.getUserType());
				domain.setInRoleFlag(conRoleUserDao.getCountByUserIdAndRoleId(user.getId(), roleId));
				domainList.add(domain);
			}			
			
			pageBean.setData(domainList);
			pageBean.setTotalRow(totalRow);
		}
		return pageBean;
	}
	
	public void addUserAction(String userName,String name,int userType,int opUserId){
		Sys_user user = new Sys_user();
		Sys_log	log	=	new	Sys_log();
		user.setName(name);
		user.setEmail(userName);
		user.setUserName(userName);
		user.setUserType(userType);
		

		log.setLogType(LogType.USER_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("添加用户：[用户名:"+userName+"][姓名:"+name+"][用户类型:"+(1==userType?"管理员":"普通用户")+"]");

		conUserDao.addUser(user);
		conLogDao.insertLog(log);
	}
	
	public void delUserAction(int id,int opUserId){
		Sys_log log	=	new	Sys_log();
		log.setLogType(LogType.USER_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("删除用户：[用户名:"+conUserDao.getUserById(id).getUserName()+"]" +
				"[姓名:"+conUserDao.getUserById(id).getName()+"]" +
				"[用户类型:"+(1==conUserDao.getUserById(id).getUserType()?"管理员":"普通用户")+"]");

		conUserDao.delUserById(id);
		conRoleUserDao.delRole2UserByUserId(id);
		conPermDao.delUserPermByUserId(id);
		conLogDao.insertLog(log);
	}
	
	public void setAdmin(int id,int opUserId){
		Sys_log log	=	new	Sys_log();
		log.setLogType(LogType.USER_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("设为管理员：[用户名:"+conUserDao.getUserById(id).getUserName()+"]" +
				"[姓名:"+conUserDao.getUserById(id).getName()+"]" +
				"[用户类型:"+(1==conUserDao.getUserById(id).getUserType()?"管理员":"普通用户")+"]");

		conUserDao.setAdminById(id);
		conLogDao.insertLog(log);
	}
	
	public void unSetAdmin(int id,int opUserId){
		Sys_log log	=	new	Sys_log();
		log.setLogType(LogType.USER_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("取消管理员：[用户名:"+conUserDao.getUserById(id).getUserName()+"]" +
				"[姓名:"+conUserDao.getUserById(id).getName()+"]" +
				"[用户类型:"+(1==conUserDao.getUserById(id).getUserType()?"管理员":"普通用户")+"]");

		conUserDao.unsetAdminById(id);
		conLogDao.insertLog(log);
	}
	
	public void modifyAction(int id,String userName,String name,int userType,int opUserId){
		Sys_user oldUser	=	conUserDao.getUserById(id);
		
		Sys_user user	=	new	Sys_user();
		user.setId(id);
		user.setName(name);
		user.setUserName(userName);
		user.setUserType(userType);
		
		Sys_log log	=	new	Sys_log();
		log.setLogType(LogType.USER_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("修改用户信息：原信息--" +
				"[用户名:"+oldUser.getUserName()+"]" +
				"[姓名:"+oldUser.getName()+"]" +
				"[用户类型:"+(1==oldUser.getUserType()?"管理员":"普通用户")+"]"+
				"新信息--" +
				"[用户名:"+userName+"]" +
				"[姓名:"+name+"]" +
				"[用户类型:"+(1==userType?"管理员":"普通用户")+"]");

		conUserDao.updateUser(user);
		conLogDao.insertLog(log);	
	}
}
