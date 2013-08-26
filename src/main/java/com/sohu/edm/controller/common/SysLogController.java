package com.sohu.edm.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.service.ConLogService;
import com.sohu.edm.util.ViewTarget;
@Controller
@RequestMapping("slog")
public class SysLogController {
	@Autowired
	ConLogService conLogService;
	@RequestMapping(value="")
	public String getView(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("cp", currentPage);
		request.setAttribute("pageBean", conLogService.getByPage(currentPage));
		return ViewTarget.SYS_LOG;
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
			HttpServletRequest request, HttpServletResponse response){
		if("0".equals(startYear)||null==startYear||"".equals(startYear)){
			request.setAttribute("cp", currentPage);
			return "/slog";
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
		request.setAttribute("pageBean", conLogService.getMLogByPageAndCondition(currentPage,sdf.format(start), sdf.format(end), opUser));

		return ViewTarget.SYS_LOG;
	}
}
