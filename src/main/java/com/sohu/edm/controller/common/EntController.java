package com.sohu.edm.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sohu.edm.dao.map.T_ent;
import com.sohu.edm.service.EmsEntService;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.util.ViewTarget;

@Controller
@RequestMapping("/ent")
public class EntController {
	
	@Autowired
	EmsEntService emsEntService;
	
	@RequestMapping(value="")
	public String getView(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("cp", currentPage);
		request.setAttribute("pageBean", emsEntService.getEntByPage(currentPage));
		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.ENT_VIEW;
	}
	@RequestMapping(value="add")
	public String addEnt(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("operate", "add");
		request.setAttribute("eid", "0");
		return ViewTarget.ENT_ADD;
	}
	@RequestMapping(value="addAction")
	public String addEntAction(
			@RequestParam(value = "entName", required = false) String entName,
			@RequestParam(value = "trade", required = false,defaultValue="0") String trade,
			@RequestParam(value = "domain", required = false) String domain,
			@RequestParam(value = "website", required = false,defaultValue="") String website,
			@RequestParam(value = "adminAccount", required = false) String adminAccount,
			@RequestParam(value = "adminPsw", required = false) String adminPsw,
			@RequestParam(value = "contact", required = false,defaultValue="") String contact,
			@RequestParam(value = "contactTel", required = false,defaultValue="") String contactTel,
			@RequestParam(value = "freq", required = false,defaultValue="0") String freq,
			@RequestParam(value = "unit", required = false,defaultValue="0") String unit,
			@RequestParam(value = "singleTransNum", required = false,defaultValue="0") String singleTransNum,
			HttpServletRequest request, HttpServletResponse response){
		
		T_ent ent	=	new	T_ent();
		ent.setEntName(entName);
		if(trade.equals("")) trade="0";
		ent.setTrade(Integer.valueOf(trade));
		ent.setDomain(domain);
		ent.setWebsite(website);
		ent.setAdminAccount(adminAccount);
		ent.setAdminPsw(adminPsw);
		ent.setContact(contact);
		ent.setContactTel(contactTel);
		if(freq.equals("")) freq="0";
		ent.setFreq(Integer.valueOf(freq));
		if(unit.equals("")) unit="0";
		ent.setUnit(Integer.valueOf(unit));
		if(singleTransNum.equals("")) singleTransNum="0";
		ent.setSingleTransNum(Integer.valueOf(singleTransNum));
		
		int opUserId	=	Constants.getOpUserId(request);
		String ip	=	Constants.getRemortIP(request);
		emsEntService.addEnt(ent,opUserId,ip);
		return "/ent";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="query")
	public String query(
			@RequestParam(value = "cp", required = false,defaultValue="1") int currentPage,
			@RequestParam(value = "sy", required = false,defaultValue="0") String startYear,
			@RequestParam(value = "sm", required = false,defaultValue="0") String startMonth,
			@RequestParam(value = "ey", required = false,defaultValue="0") String endYear,
			@RequestParam(value = "em", required = false,defaultValue="0") String endMonth,
			@RequestParam(value = "entName", required = false,defaultValue="") String entName,
			HttpServletRequest request, HttpServletResponse response){
		if("0".equals(startYear)||null==startYear||"".equals(startYear)){
			return "/ent";
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
		request.setAttribute("entName", entName);
		//request.setAttribute("pageBean", emsEntService.getEntByPage(currentPage));
		request.setAttribute("pageBean", emsEntService.getEntPageBeanByPageAndCondition(currentPage,sdf.format(start), sdf.format(end), entName));

		request.setAttribute("entOptions", emsEntService.getEntList());
		return ViewTarget.ENT_VIEW;
	}
	@RequestMapping(value="modify")
	public String modify(
			@RequestParam(value = "eid", required = false,defaultValue="") int entId,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("entInfo", emsEntService.getEntById(entId));
		request.setAttribute("eid", entId);
		request.setAttribute("operate", "modify");
		return ViewTarget.ENT_ADD;
	}
	@RequestMapping(value="modifyAction")
	public String modifyAction(
			@RequestParam(value = "eid", required = false) String eid,
			@RequestParam(value = "entName", required = false) String entName,
			@RequestParam(value = "trade", required = false,defaultValue="0") String trade,
			@RequestParam(value = "domain", required = false) String domain,
			@RequestParam(value = "website", required = false,defaultValue="") String website,
			@RequestParam(value = "adminAccount", required = false) String adminAccount,
			@RequestParam(value = "adminPsw", required = false) String adminPsw,
			@RequestParam(value = "contact", required = false,defaultValue="") String contact,
			@RequestParam(value = "contactTel", required = false,defaultValue="") String contactTel,
			@RequestParam(value = "freq", required = false,defaultValue="0") String freq,
			@RequestParam(value = "unit", required = false,defaultValue="0") String unit,
			@RequestParam(value = "singleTransNum", required = false,defaultValue="0") String singleTransNum,
			HttpServletRequest request, HttpServletResponse response){
		
		T_ent ent	=	new	T_ent();
		ent.setId(Integer.valueOf(eid));
		ent.setEntName(entName);
		if(trade.equals("")) trade="0";
		ent.setTrade(Integer.valueOf(trade));
		ent.setDomain(domain);
		ent.setWebsite(website);
		ent.setAdminAccount(adminAccount);
		ent.setAdminPsw(adminPsw);
		ent.setContact(contact);
		ent.setContactTel(contactTel);
		if(freq.equals("")) freq="0";
		ent.setFreq(Integer.valueOf(freq));
		if(unit.equals("")) unit="0";
		ent.setUnit(Integer.valueOf(unit));
		if(singleTransNum.equals("")) singleTransNum="0";
		ent.setSingleTransNum(Integer.valueOf(singleTransNum));
		
		int opUserId	=	Constants.getOpUserId(request);
		
		emsEntService.updateEnt(ent,opUserId);
		return "/ent/query";
	}
	
}
