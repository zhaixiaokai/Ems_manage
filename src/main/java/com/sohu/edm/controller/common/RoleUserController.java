package com.sohu.edm.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.service.ConRoleService;
import com.sohu.edm.service.ConRoleUserService;
import com.sohu.edm.service.ConUserService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/roleuser")
public class RoleUserController {
	@Autowired
	ConRoleService conRoleService;
	@Autowired
	ConUserService conUserService;
	@Autowired
	ConRoleUserService conRoleUserService;
	@RequestMapping(value="")
	public String getView(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("pageBean", conRoleService.getRoleByPage(currentPage));
		return ViewTarget.ROLE_USER;
	}
	@RequestMapping(value="query")
	public String query(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "condition", required = false,defaultValue = "") String condition,
			HttpServletRequest request, HttpServletResponse response){
		
		request.setAttribute("pageBean", conRoleService.query(currentPage, condition));	
		request.setAttribute("condition", condition);
		return ViewTarget.ROLE_USER;
	}
	@RequestMapping(value="user")
	public String role2Users(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "rid", required = false) int roleId,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pageBean", conUserService.getUserByPage(currentPage,roleId));
		request.setAttribute("rid", roleId);
		return ViewTarget.ROLE_USER_LIST;
	}
	@RequestMapping(value="queryUser")
	public String queryUser(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cond", required = false) String cond,
			@RequestParam(value = "condition", required = false,defaultValue = "") String condition,
			@RequestParam(value = "rid", required = false) int roleId,
			HttpServletRequest request, HttpServletResponse response){
	
		if(condition.equals("1")||condition.equals("2")||condition.equals("3")){
			request.setAttribute("pageBean", conUserService.getUserByPageAndCondition(currentPage, cond, condition,roleId));
		}
		else{
			return "/roleuser/user";
		}
		request.setAttribute("condType", condition);
		request.setAttribute("condition", cond);
		request.setAttribute("rid", roleId);
		return ViewTarget.ROLE_USER_LIST;
	}
	@RequestMapping(value="add")
	public String addUserToRole(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "rid", required = false) int roleId,
			@RequestParam(value = "cp", required = false) String currentPage,
			@RequestParam(value = "uid", required = false) int userId){
		int opUserId	=	Constants.getOpUserId(request);
		conRoleUserService.addUserIntoRole(userId, roleId, opUserId);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("uid", userId);
		request.setAttribute("rid", roleId);
		return "/roleuser/queryUser?currentPage="+currentPage;
	}
	@RequestMapping(value="rm")
	public String removeUserFromRole(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "rid", required = false) int roleId,
			@RequestParam(value = "cp", required = false) String currentPage,
			@RequestParam(value = "uid", required = false) int userId){
		int opUserId	=	Constants.getOpUserId(request);
		conRoleUserService.delUserFromRole(userId, roleId, opUserId);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("uid", userId);
		request.setAttribute("rid", roleId);
		return "/roleuser/queryUser?currentPage="+currentPage;
	}
}
