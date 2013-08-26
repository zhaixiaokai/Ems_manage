package com.sohu.edm.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConRoleDao;
import com.sohu.edm.console.dao.ConRoleUserDao;
import com.sohu.edm.service.ConRoleService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;
@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	ConRoleDao conRoleDao;
	@Autowired
	ConRoleUserDao comRoleUserDao;
	@Autowired
	ConLogDao conLogDao;
	@Autowired
	ConRoleService conRoleService;
	
	@RequestMapping
	public String getView(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){		

		request.setAttribute("pageBean", conRoleService.getRoleByPage(currentPage));
		return ViewTarget.ROLE;
	}
	
	
	@RequestMapping(value="add")
	public String add(){
		return ViewTarget.ROLE_ADD;
	}
	
	
	@RequestMapping(value = "addAction")
	public String addRoleAction(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "rolename", required = false) String roleName) {

		int opUserId	=	Constants.getOpUserId(request);
		
		conRoleService.addAction(roleName, opUserId);
		return "/role";
	}	
	
	
	@RequestMapping(value="modify")
	public String modify(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "opid", required = false) int id){
		request.setAttribute("role", conRoleDao.getRoleById(id));
		return ViewTarget.ROLE_MODIFY;
	}
	
	
	@RequestMapping(value="modifyAction")
	public String modifyAction(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "rolename", required = false) String roleName,
			@RequestParam(value = "id", required = false) int id){
		int opUserId	=	Constants.getOpUserId(request);
		conRoleService.modifyAction(id, roleName, opUserId);
		return "/role";
	}
	
	
	@RequestMapping(value="delAction")
	public String delAction(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "opid", required = false) int id){
		int opUserId	=	Constants.getOpUserId(request);
		conRoleService.delAction(id, opUserId);
		return "/role";
	}
	
	
	@RequestMapping(value="query")
	public String query(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "condition", required = false,defaultValue = "") String condition,
			HttpServletRequest request, HttpServletResponse response){
		
		request.setAttribute("pageBean", conRoleService.query(currentPage, condition));	
		request.setAttribute("condition", condition);
		return ViewTarget.ROLE;
		
	}
	
}
