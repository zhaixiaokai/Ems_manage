package com.sohu.edm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.dao.EmsMLogDao;
import com.sohu.edm.model.MLogDomain;
import com.sohu.edm.model.PageBean;
import com.sohu.edm.tools.Constants;
import com.sohu.edm.tools.LogParser;

@Service("emsMLogService")
public class EmsMLogService {
	@Autowired
	EmsMLogDao emsMLogDao;
	public PageBean	getMLogByPage(int currentPage){
		PageBean pageBean	=	new	PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = emsMLogDao.getTotalRow();
		pageBean.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<Map<Object, Object>>	res	=	emsMLogDao.getLogByPage(currentPage,
				Constants.MAX_RECORD_PER_PAGE);
		List<MLogDomain> list	=	new	ArrayList<MLogDomain>();
		for(Map<?, ?> obj:res){
			MLogDomain domain	=	new	MLogDomain();
			domain.setUserName(null==obj.get("op_userid")?"--":obj.get("op_userid").toString());
			domain.setOperate(null==obj.get("log_op")?"--":obj.get("log_op").toString());
			
			String opObj	=	"--";
			if(null!=obj.get("log_desc")){
				opObj	=	LogParser.parseOPDomain(Integer.valueOf(obj.get("log_type").toString()), obj.get("log_desc").toString());
			}
			domain.setOpObject(opObj);
			
			domain.setEntName(null==obj.get("ent_name")?"--":obj.get("ent_name").toString());
			domain.setTime(null==obj.get("create_time")?"--":obj.get("create_time").toString());
			domain.setIp(null==obj.get("access_ip")?"--":obj.get("access_ip").toString());
			domain.setResult(null==obj.get("log_result")?"--":obj.get("log_result").toString());
			
			list.add(domain);
		}
		pageBean.setData(list);
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
	
	public PageBean	getMLogByPageAndCondition(int currentPage,
			String start, String end, String opUser,String entName,String opType){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = emsMLogDao.getTotalRowByCondition(start, end, opUser, entName,opType);
		pageBean.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<Map<Object,Object>>	res	=	emsMLogDao.getLogByPageAndCondition(currentPage,
				Constants.MAX_RECORD_PER_PAGE, start, end, opUser, entName,opType);
		List<MLogDomain> list	=	new	ArrayList<MLogDomain>();
		for(Map<?, ?> obj:res){
			MLogDomain domain	=	new	MLogDomain();
			domain.setUserName(null==obj.get("op_userid")?"--":obj.get("op_userid").toString());
			domain.setOperate(null==obj.get("log_op")?"--":obj.get("log_op").toString());
			
			String opObj	=	"--";
			if(null!=obj.get("log_desc")){
				opObj	=	LogParser.parseOPDomain(Integer.valueOf(obj.get("log_type").toString()), obj.get("log_desc").toString());
			}
			domain.setOpObject(opObj);
			

			domain.setEntName(null==obj.get("ent_name")?"--":obj.get("ent_name").toString());
			domain.setTime(null==obj.get("create_time")?"--":obj.get("create_time").toString());
			domain.setIp(null==obj.get("access_ip")?"--":obj.get("access_ip").toString());
			domain.setResult(null==obj.get("log_result")?"--":obj.get("log_result").toString());
			
			list.add(domain);
		}
		pageBean.setData(list);
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
}
