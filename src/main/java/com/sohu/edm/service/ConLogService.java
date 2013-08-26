package com.sohu.edm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.model.ConLogDomain;
import com.sohu.edm.model.PageBean;
import com.sohu.edm.tools.Constants;

@Service("conLogService")
public class ConLogService {
	
	@Autowired
	ConLogDao	conLogDao;
	public PageBean	getByPage(int currentPage){

		PageBean pageBean	=	new	PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = conLogDao.getTotalRow();
		pageBean.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<Map<Object, Object>>	res	=	conLogDao.getLogByPage(currentPage,
				Constants.MAX_RECORD_PER_PAGE);
		List<ConLogDomain> list	=	new	ArrayList<ConLogDomain>();
		for(Map<?, ?> obj:res){
			ConLogDomain domain	=	new	ConLogDomain();
			domain.setOpUserName(null==obj.get("user_name")?"--":obj.get("user_name").toString());
			domain.setTime(null==obj.get("create_time")?"--":obj.get("create_time").toString());
			domain.setLogTypeInt(null==obj.get("log_type")?0:Integer.valueOf(obj.get("log_type").toString()));
			domain.setLogDesc(null==obj.get("log_desc")?"--":obj.get("log_desc").toString());
			
			list.add(domain);
		}
		pageBean.setData(list);
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
	
	public PageBean	getMLogByPageAndCondition(int currentPage,
			String start, String end, String opUser){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = conLogDao.getTotalRowByCondition(start, end, opUser);
		pageBean.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<Map<Object,Object>>	res	=	conLogDao.getLogByPageAndCondition(currentPage,
				Constants.MAX_RECORD_PER_PAGE, start, end, opUser);
		List<ConLogDomain> list	=	new	ArrayList<ConLogDomain>();
		for(Map<?, ?> obj:res){
			ConLogDomain domain	=	new	ConLogDomain();
			domain.setOpUserName(null==obj.get("user_name")?"--":obj.get("user_name").toString());
			domain.setTime(null==obj.get("create_time")?"--":obj.get("create_time").toString());
			domain.setLogTypeInt(null==obj.get("log_type")?0:Integer.valueOf(obj.get("log_type").toString()));
			domain.setLogDesc(null==obj.get("log_desc")?"--":obj.get("log_desc").toString());
			
			list.add(domain);
		}
		pageBean.setData(list);
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
}
