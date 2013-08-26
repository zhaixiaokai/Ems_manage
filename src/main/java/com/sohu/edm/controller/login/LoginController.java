package com.sohu.edm.controller.login;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_user;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	ConUserDao	conUserDao;
	@RequestMapping(value="login")
	public String login(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "passwd", required = false) String passwd) {
		int	exists	=	conUserDao.getNumByUserName(username);
		if(exists==0){
			request.setAttribute("message", username);
			return ViewTarget.ERROR;
		}
		Sys_user	user	=	conUserDao.getUserByUserName(username);
		Long	id	=	user.getId();
		request.getSession().setAttribute(Constants.SESSION_USER, id.intValue());
		return "redirect:/index";
	}
	
	@RequestMapping(value="logout")
	public String logout(
			HttpServletRequest request,
			HttpServletResponse response){
		request.getSession().removeAttribute(Constants.SESSION_USER);
		Cookie	scdig	=	new	Cookie("scdig",null);
		scdig.setDomain(".sohuno.com");
		scdig.setMaxAge(0);
		response.addCookie(scdig);
		Cookie	scnif	=	new	Cookie("scnif",null);
		scnif.setDomain(".sohuno.com");
		scnif.setMaxAge(0);
		response.addCookie(scnif);
/*		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		for(Cookie cookie : cookies){
			//删除本地cookies
			System.err.println(cookie.getName()+"---------"+cookie.getValue());
			Cookie delcookie = new Cookie(cookie.getName(), null); 
			delcookie.setMaxAge(0);
			cookie.setPath("/");
			cookie.setDomain(".sohuno.com");
			response.addCookie(delcookie);
		} */
		return ViewTarget.LOGINOUT;
	}
	
}
