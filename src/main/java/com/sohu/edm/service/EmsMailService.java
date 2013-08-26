package com.sohu.edm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.dao.EmsMailSendDao;
import com.sohu.edm.model.MailInfoDomain;
import com.sohu.edm.model.PageBean;
import com.sohu.edm.tools.Constants;
@Service("emsMailService")
public class EmsMailService {

	@Autowired
	EmsMailSendDao emsMailSendDao;
	public PageBean getMailInfoByPage(int currentPage){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = emsMailSendDao.getTotalRow();
		pageBean.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<Map<Object, Object>>	res	=	emsMailSendDao.getMailInfoByPage(currentPage,
				Constants.MAX_RECORD_PER_PAGE);
		List<MailInfoDomain> list	=	new	ArrayList<MailInfoDomain>();
		for(Map<?, ?> obj:res){
			MailInfoDomain domain	=	new	MailInfoDomain();
			domain.setActPoint(null==obj.get("actpoints")?"--":obj.get("actpoints").toString());
			domain.setEnt(null==obj.get("ent_name")?"--":obj.get("ent_name").toString());
			domain.setMailName(null==obj.get("mail_name")?"--":obj.get("mail_name").toString());
			domain.setMailSubject(null==obj.get("mail_subject")?"--":obj.get("mail_subject").toString());
			domain.setOrg(null==obj.get("org_name")?"--":obj.get("org_name").toString());
			domain.setPayPoint(null==obj.get("paypoints")?"--":obj.get("paypoints").toString());
			domain.setRetPoint(null==obj.get("retpoints")?"--":obj.get("retpoints").toString());
			domain.setSendCount(null==obj.get("mail_num")?"--":obj.get("mail_num").toString());
			domain.setSendTime(null==obj.get("send_time")?"--":obj.get("send_time").toString());
			domain.setSendUser(null==obj.get("mail_fromaddr")?"--":obj.get("mail_fromaddr").toString());
			
			list.add(domain);
		}
		pageBean.setData(list);
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
	
	public PageBean getMailPageBeanByPageAndCondition(int currentPage,
			String start, String end, String mailName,String entName){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = emsMailSendDao.getTotalRowByCondition(start, end, mailName, entName);
		pageBean.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<Map<Object,Object>>	res	=	emsMailSendDao.getMailInfoByPageAndCondition(currentPage,
				Constants.MAX_RECORD_PER_PAGE, start, end, mailName, entName);
		List<MailInfoDomain> list	=	new	ArrayList<MailInfoDomain>();
		for(Map<?, ?> obj:res){
			MailInfoDomain domain	=	new	MailInfoDomain();
			domain.setActPoint(null==obj.get("actpoints")?"--":obj.get("actpoints").toString());
			domain.setEnt(null==obj.get("ent_name")?"--":obj.get("ent_name").toString());
			domain.setMailName(null==obj.get("mail_name")?"--":obj.get("mail_name").toString());
			domain.setMailSubject(null==obj.get("mail_subject")?"--":obj.get("mail_subject").toString());
			domain.setOrg(null==obj.get("org_name")?"--":obj.get("org_name").toString());
			domain.setPayPoint(null==obj.get("paypoints")?"--":obj.get("paypoints").toString());
			domain.setRetPoint(null==obj.get("retpoints")?"--":obj.get("retpoints").toString());
			domain.setSendCount(null==obj.get("mail_num")?"--":obj.get("mail_num").toString());
			domain.setSendTime(null==obj.get("send_time")?"--":obj.get("send_time").toString());
			domain.setSendUser(null==obj.get("mail_fromaddr")?"--":obj.get("mail_fromaddr").toString());
			
			list.add(domain);
		}
		pageBean.setData(list);
		pageBean.setTotalRow(totalRow);
		return pageBean;
		
	}
}
