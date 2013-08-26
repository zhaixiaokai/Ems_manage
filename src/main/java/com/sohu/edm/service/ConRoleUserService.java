package com.sohu.edm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConRoleDao;
import com.sohu.edm.console.dao.ConRoleUserDao;
import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_log;

@Service("conRoleUserService")
public class ConRoleUserService {
	@Autowired
	ConRoleUserDao conRoleUserDao;
	@Autowired
	ConLogDao conLogDao;
	@Autowired
	ConRoleDao conRoleDao;
	@Autowired
	ConUserDao conUserDao;

	public boolean delUserFromRole(int userId, int roleId, int opUserId) {
		conRoleUserDao.delUserFromRole(userId, roleId);
		Sys_log log = new Sys_log();
		log.setLogType(4);
		log.setOpUserId(opUserId);
		log.setLogDesc("删除角色成员：从角色[[角色名："
				+ conRoleDao.getRoleById(roleId).getRoleName() + "][角色ID："
				+ roleId + "]]中删除用户[[用户名:"
				+ conUserDao.getUserById(userId).getUserName() + "][姓名:"
				+ conUserDao.getUserById(userId).getName() + "][用户ID:" + userId
				+ "]]");
		conLogDao.insertLog(log);
		return true;
	}

	public boolean addUserIntoRole(int userId, int roleId, int opUserId) {

		conRoleUserDao.addUserIntoRole(userId, roleId);
		Sys_log log = new Sys_log();
		log.setLogType(4);
		log.setOpUserId(opUserId);
		log.setLogDesc("添加角色成员：为角色[[角色名："
				+ conRoleDao.getRoleById(roleId).getRoleName() + "][角色ID："
				+ roleId + "]]中添加用户[[用户名:"
				+ conUserDao.getUserById(userId).getUserName() + "][姓名:"
				+ conUserDao.getUserById(userId).getName() + "][用户ID:" + userId
				+ "]]");
		conLogDao.insertLog(log);
		return true;
	}

}
