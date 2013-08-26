package com.sohu.edm.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConRoleUserDao;
import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.service.ConPermService;
import com.sohu.edm.service.ConRoleService;
import com.sohu.edm.service.ConUserService;
import com.sohu.edm.service.TreeActionService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/perm")
public class PermController {
	@Autowired
	ConUserDao conUserDao;

	@Autowired
	ConRoleUserDao conRoleUserDao;
	
	@Autowired
	ConLogDao	conLogDao;
	
	@Autowired
	ConUserService conUserService;
	
	@Autowired
	TreeActionService treeActionService;
	
	@Autowired
	ConPermService conPermService;
	
	@Autowired
	ConRoleService conRoleService;
	@RequestMapping(value="")
	public String showFunTree(){
		return ViewTarget.PERM;
	}
	
	@RequestMapping(value="user")
	public String getUserPermView(@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pageBean", conUserService.getUserByPage(currentPage));
		return ViewTarget.PERM_USER;
	}
	@RequestMapping(value="user/query")
	public String query(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cond", required = false) String cond,
			@RequestParam(value = "condition", required = false,defaultValue = "") String condition,
			HttpServletRequest request, HttpServletResponse response){
	
		if(condition.equals("1")||condition.equals("2")||condition.equals("3")){
			request.setAttribute("pageBean", conUserService.getUserByPageAndCondition(currentPage, cond, condition));
		}
		else{
			return "/perm/user";
		}
		request.setAttribute("condType", condition);
		request.setAttribute("condition", cond);
		return ViewTarget.PERM_USER;
	}
	
	@RequestMapping("user/manage")
	public String managePermByUserId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "uid", required = false) int userId){
		request.setAttribute("funTreeJson", treeActionService.getAllNodesNoUrl());
		request.setAttribute("permList", conPermService.getUserPerm(userId));
		request.setAttribute("entityId", userId);
		request.setAttribute("type", "user");
		return "/perm";
	}
	@RequestMapping("user/save")
	public String savePerm(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "entityId", required = false) int userId,
			@RequestParam(value = "permList", required = false) String permList ){
		String [] perms	=	permList.split(",");
		int opUserId	=	Constants.getOpUserId(request);
		if(null==permList||"".equals(permList)){
			conPermService.logDelUserPerm(userId, opUserId);
			conPermService.delUserPermByUserId(userId);
			return "/perm/user";
		}
		conPermService.updateUserPerm(userId, perms,opUserId);
		
		return "/perm/user";
	}
	
	/*****************************************角色************************************************/
	@RequestMapping(value="role")
	public String getRolePermView(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("pageBean", conRoleService.getRoleByPage(currentPage));
		return ViewTarget.PERM_ROLE;
	}
	@RequestMapping(value="role/query")
	public String queryRole(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "condition", required = false,defaultValue = "") String condition,
			HttpServletRequest request, HttpServletResponse response){
		
		request.setAttribute("pageBean", conRoleService.query(currentPage, condition));	
		request.setAttribute("condition", condition);
		return ViewTarget.PERM_ROLE;
	}
	@RequestMapping("role/manage")
	public String managePermByRoleId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "rid", required = false) int roleId){
		request.setAttribute("funTreeJson", treeActionService.getAllNodesNoUrl());
		request.setAttribute("permList", conPermService.getRolePerm(roleId));
		request.setAttribute("entityId", roleId);
		request.setAttribute("type", "role");
		return "/perm";
	}
	@RequestMapping("role/save")
	public String saveRolePerm(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "entityId", required = false) int roleId,
			@RequestParam(value = "permList", required = false) String permList ){
		String [] perms	=	permList.split(",");
		int opUserId	=	Constants.getOpUserId(request);
		if(null==permList||"".equals(permList)){
			conPermService.logDelRolePerm(roleId, opUserId);
			conPermService.delRolePermByRoleId(roleId);
			return "/perm/role";
		}
		conPermService.updateRolePerm(roleId, perms,opUserId);
		return "/perm/role";
	}
}
