package com.sohu.edm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConPermDao;
import com.sohu.edm.console.dao.ConRoleDao;
import com.sohu.edm.console.dao.ConRoleUserDao;
import com.sohu.edm.console.dao.map.Sys_log;
import com.sohu.edm.console.dao.map.Sys_role;
import com.sohu.edm.model.PageBean;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.tools.LogType;
@Service("conRoleService")
public class ConRoleService {
	@Autowired
	ConRoleDao conRoleDao;
	@Autowired
	ConLogDao	conLogDao;
	@Autowired
	ConRoleUserDao conRoleUserDao;
	@Autowired
	ConPermDao	conPermDao;
	public PageBean getRoleByPage(int currentPage){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow	=	conRoleDao.getTotalRow();
		pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
		pageBean.setData(conRoleDao.getRoleByPage(Constants.MAX_RECORD_PER_PAGE, currentPage));
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
	
	public void addAction(String roleName,int opUserId){
		Sys_role role = new Sys_role();
		Sys_log	log	=	new	Sys_log();
		role.setRoleName(roleName);
		

		log.setLogType(LogType.ROLE_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("添加角色：[角色名:"+roleName+"]");

		conRoleDao.addRole(role);
		conLogDao.insertLog(log);
	}
	
	public void modifyAction(int id,String roleName,int opUserId){
		Sys_role oldRole	=	conRoleDao.getRoleById(id);
		Sys_role role =	new	Sys_role();
		role.setId(id);
		role.setRoleName(roleName);
		
		Sys_log log	=	new	Sys_log();
		log.setLogType(LogType.ROLE_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("修改角色信息：原信息--" +
				"[角色名:"+oldRole.getRoleName()+"]" +
				"新信息--" +
				"[角色名:"+roleName+"]");
		conRoleDao.updateRole(role);
		conLogDao.insertLog(log);
	}
	
	public void delAction(int id,int opUserId){
		Sys_log	log	=	new	Sys_log();
		log.setLogType(LogType.ROLE_OPER);
		log.setOpUserId(opUserId);
		log.setLogDesc("删除角色：[角色名:"+conRoleDao.getRoleById(id).getRoleName()+"]");
		conRoleDao.delRoleById(id);
		conRoleUserDao.delRole2UserByRoleId(id);
		conPermDao.delRolePermByRoleId(id);
		conLogDao.insertLog(log);
	}
	
	public PageBean query(int currentPage,String condition){
		
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow	=	conRoleDao.getTotalRowByRoleName(condition);
		pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
		pageBean.setData(conRoleDao.getRoleByRoleName(Constants.MAX_RECORD_PER_PAGE, currentPage,condition));
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
}
