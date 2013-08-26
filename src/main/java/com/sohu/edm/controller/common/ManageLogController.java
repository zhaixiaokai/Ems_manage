package com.sohu.edm.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.service.EmsEntService;
import com.sohu.edm.service.EmsMLogService;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/mlog")
public class ManageLogController {
	@Autowired
	EmsMLogService emsMLogService;
	@Autowired
	EmsEntService emsEntService;
	@RequestMapping(value="")
	public String getView(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("cp", currentPage);
		request.setAttribute("pageBean", emsMLogService.getMLogByPage(currentPage));
		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.MANAGE_LOG;
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value="query")
	public String query(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			@RequestParam(value = "sy", required = false,defaultValue="0") String startYear,
			@RequestParam(value = "sm", required = false,defaultValue="1") String startMonth,
			@RequestParam(value = "sd", required = false,defaultValue="1") String startDay,
			@RequestParam(value = "ey", required = false,defaultValue="0") String endYear,
			@RequestParam(value = "em", required = false,defaultValue="1") String endMonth,
			@RequestParam(value = "ed", required = false,defaultValue="1") String endDay,
			@RequestParam(value = "opUser", required = false,defaultValue="") String opUser,
			@RequestParam(value = "entName", required = false,defaultValue="") String entName,
			@RequestParam(value = "opType", required = false,defaultValue="") String opType,
			HttpServletRequest request, HttpServletResponse response){
		if("0".equals(startYear)||null==startYear||"".equals(startYear)){
			request.setAttribute("cp", currentPage);
			return "/mlog";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date start	=	new	Date(Integer.valueOf(startYear)-1900,Integer.valueOf(startMonth)-1,
				Integer.valueOf(startDay));
		Date end	=	new	Date(Integer.valueOf(endYear)-1900,Integer.valueOf(endMonth)-1,Integer.valueOf(endDay));
		//emsEntService.queryEnt(sdf.format(start), sdf.format(end), entName);
		request.setAttribute("sy", startYear);
		request.setAttribute("sm", startMonth);
		request.setAttribute("sd", startDay);
		request.setAttribute("ey", endYear);
		request.setAttribute("em", endMonth);
		request.setAttribute("ed", endDay);
		request.setAttribute("cp", currentPage);
		request.setAttribute("opUser", opUser);
		request.setAttribute("entName", entName);
		request.setAttribute("opType", opType);
		request.setAttribute("pageBean", emsMLogService.getMLogByPageAndCondition(currentPage,sdf.format(start), sdf.format(end), opUser,entName,opType));

		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.MANAGE_LOG;
	}
}
