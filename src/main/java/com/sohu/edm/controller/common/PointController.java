package com.sohu.edm.controller.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.service.EmsEntService;
import com.sohu.edm.service.EmsPointService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/point")
public class PointController {
	@Autowired
	EmsPointService	emsPointService;
	@Autowired
	EmsEntService	emsEntService;
	
	@RequestMapping(value="")
	public String getView(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){
		
		request.setAttribute("cp", currentPage);
		request.setAttribute("entId", -1);
		request.setAttribute("opUserId", "");
		request.setAttribute("pageBean", emsPointService.getRecordByPage(currentPage));
		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.POINT;
	}
	@RequestMapping(value="add")
	public String add(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.ADD_POINT;
	}
	@RequestMapping(value="addAction")
	public String addAction(
			@RequestParam(value = "entId", required = false) int entId,
			@RequestParam(value = "point", required = false) int point,
			@RequestParam(value = "explain", required = false,defaultValue="") String explain,
			HttpServletRequest request, HttpServletResponse response){
		String opUserName="";
		if(request.getUserPrincipal()!=null){
			opUserName=request.getUserPrincipal().getName();
		}
		String ip	=	Constants.getRemortIP(request);
		int opUserId	=	Constants.getOpUserId(request);
		emsPointService.addPoint(entId, point, explain, opUserName,opUserId,ip);
		return "/point";
	}
	@RequestMapping(value="query")
	public String query(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			@RequestParam(value = "entId", required = false) int entId,
			@RequestParam(value = "opUserId", required = false,defaultValue="0") String opUserId,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("entOptions", emsEntService.getEntList());
		request.setAttribute("entId", entId);
		request.setAttribute("opUserId", opUserId);
		request.setAttribute("pageBean", emsPointService.queryByPageAndCondition(currentPage,entId,opUserId));
		return ViewTarget.POINT;
	}

}
