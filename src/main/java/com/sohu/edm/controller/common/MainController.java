package com.sohu.edm.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_user;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/")
public class MainController {
	@Autowired
	ConUserDao	conUserDao	;
	@RequestMapping
	public String Default(HttpServletRequest request, HttpServletResponse response) {
		return ViewTarget.LOGIN;
	}	
	
	@RequestMapping(value="index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return ViewTarget.INDEX;
	}

	@RequestMapping(value = "main/funTree")
	public String funArea(HttpServletRequest request,HttpServletResponse response) {
		return "/action/tree";
	}

	@RequestMapping(value = "main/banner")
	public String banner(HttpServletRequest request,HttpServletResponse response) {
		Sys_user user	=	conUserDao.getUserById(Constants.getOpUserId(request));
		request.setAttribute("USER", user);
		return ViewTarget.BANNER;
	}

	@RequestMapping(value = "main/welcome")
	public String welcome(HttpServletRequest request,HttpServletResponse response) {
		return ViewTarget.WELCOME;
	}
	
	
	/************************测试代码**********************/
	@RequestMapping(value = "test")
	public String test(HttpServletRequest request,HttpServletResponse response) {
		JSONArray	jsobj	=	JSONArray.fromObject( conUserDao.getUserByPage(10, 1) ); 
		System.out.println(jsobj);
		return ViewTarget.BANNER;
	}
}
