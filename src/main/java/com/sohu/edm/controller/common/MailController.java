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
import com.sohu.edm.service.EmsMailService;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/mail")
public class MailController {
	@Autowired
	EmsMailService emsMailService;
	@Autowired
	EmsEntService emsEntService;
	@RequestMapping(value="")
	public String getView(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("cp", currentPage);
		request.setAttribute("pageBean", emsMailService.getMailInfoByPage(currentPage));
		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.MAIL_SEND;
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value="query")
	public String query(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			@RequestParam(value = "sy", required = false,defaultValue="0") String startYear,
			@RequestParam(value = "sm", required = false,defaultValue="0") String startMonth,
			@RequestParam(value = "ey", required = false,defaultValue="0") String endYear,
			@RequestParam(value = "em", required = false,defaultValue="0") String endMonth,
			@RequestParam(value = "mailName", required = false,defaultValue="") String mailName,
			@RequestParam(value = "entName", required = false,defaultValue="") String entName,
			HttpServletRequest request, HttpServletResponse response){

		if("0".equals(startYear)||null==startYear||"".equals(startYear)){
			request.setAttribute("cp", currentPage);
			return "/mail";
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date start	=	new	Date(Integer.valueOf(startYear)-1900,Integer.valueOf(startMonth)-1,1);
		Date end	=	new	Date(Integer.valueOf(endYear)-1900,Integer.valueOf(endMonth),1);
		//emsEntService.queryEnt(sdf.format(start), sdf.format(end), entName);
		request.setAttribute("sy", startYear);
		request.setAttribute("sm", startMonth);
		request.setAttribute("ey", endYear);
		request.setAttribute("em", endMonth);
		request.setAttribute("cp", currentPage);
		request.setAttribute("mailName", mailName);
		request.setAttribute("entName", entName);
		request.setAttribute("pageBean", emsMailService.getMailPageBeanByPageAndCondition(currentPage,sdf.format(start), sdf.format(end), mailName,entName));

		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.MAIL_SEND;
	}
}
