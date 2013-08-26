package com.sohu.edm.action.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sohu.edm.service.TreeActionService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/action/tree")
public class FunTreeActionController {
	@Autowired
	TreeActionService treeActionService;
	@RequestMapping(value="")
	public String getTree(HttpServletRequest request,HttpServletResponse response){
		if(request.getSession().getAttribute(Constants.SESSION_USER)!=null){
			int	sessionUser	=	Constants.getOpUserId(request);
				//(Integer) request.getSession().getAttribute(Constants.SESSION_USER);
			//request.setAttribute("funTreeJson", treeActionService.getAllNodes());
			request.setAttribute("funTreeJson", treeActionService.getSessionUserPermNodes(sessionUser));
		}else{
			request.setAttribute("funTreeJson", "[]");
		}
		return ViewTarget.FUNTREE;
	}
	@RequestMapping(value="user")
	public String getUserPermTree(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("funTreeJson", treeActionService.getAllNodesNoUrl());
		return ViewTarget.PERM_USER;
	}

	@RequestMapping(value="role")
	public String getRolePermTree(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("funTreeJson", treeActionService.getAllNodesNoUrl());
		return ViewTarget.PERM_ROLE;
	}
}
