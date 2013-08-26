package com.sohu.edm.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.service.ConUserService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	ConUserDao conUserDao;
	
	@Autowired
	ConUserService conUserService;
	@RequestMapping
	public String getView(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pageBean", conUserService.getUserByPage(currentPage));
		return ViewTarget.USER;
	}

	@RequestMapping(value = "add")
	public String add() {
		return ViewTarget.USER_ADD;
	}
	@RequestMapping(value = "modify")
	public String modify(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) int id) {
		
		request.setAttribute("user", conUserDao.getUserById(id));
		return ViewTarget.USER_MODIFY;
	}
	
	@RequestMapping(value="query")
	public String query(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cond", required = false) String cond,
			@RequestParam(value = "condition", required = false,defaultValue = "") String condition,
			HttpServletRequest request, HttpServletResponse response){
	
		if(condition.equals("1")||condition.equals("2")||condition.equals("3")){
			request.setAttribute("pageBean", conUserService.getUserByPageAndCondition(currentPage, cond, condition));
		}
		else{
			return "/user";
		}
		request.setAttribute("condType", condition);
		request.setAttribute("condition", cond);
		return ViewTarget.USER;
	}
	@RequestMapping(value = "addAction")
	public String addUserAction(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "username", required = false) String userName,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "usertype", required = false) int userType) {
		
		int opUserId	=	Constants.getOpUserId(request);
		conUserService.addUserAction(userName, name, userType, opUserId);
		return "/user";
	}
	@RequestMapping(value="delAction")
	public String delUserAction(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "cp", required = false) String currentPage,
			@RequestParam(value = "un", required = false) int id){
		
		int opUserId	=	Constants.getOpUserId(request);
		conUserService.delUserAction(id, opUserId);
		return "/user?currentPage="+currentPage;
	}
	
	
	@RequestMapping(value="setAdmin")
	public String setAdmin(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "cp", required = false) String currentPage,
			@RequestParam(value = "un", required = false) int id){
		int opUserId	=	Constants.getOpUserId(request);
		conUserService.setAdmin(id, opUserId);
		return "/user?currentPage="+currentPage;		
	}
	@RequestMapping(value="unsetAdmin")
	public String unsetAdmin(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "cp", required = false) String currentPage,
			@RequestParam(value = "un", required = false) int id){
		int opUserId	=	Constants.getOpUserId(request);
		conUserService.unSetAdmin(id, opUserId);
		
		return "/user?currentPage="+currentPage;		
	}

	@RequestMapping(value="modifyAction")
	public String modifyAction(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "username", required = false) String userName,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "usertype", required = false) int userType){
		int opUserId	=	Constants.getOpUserId(request);
		conUserService.modifyAction(id, userName, name, userType, opUserId);	
		return "/user";		
	}
}
