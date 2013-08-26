package com.sohu.edm.service;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_log;
import com.sohu.edm.dao.EmsCostRecordDao;
import com.sohu.edm.dao.EmsEntDao;
import com.sohu.edm.dao.EmsMLogDao;
import com.sohu.edm.dao.map.T_ent;
import com.sohu.edm.dao.map.T_log;
import com.sohu.edm.model.PageBean;
import com.sohu.edm.tools.Constants;

@Service("emsPointService")	
public class EmsPointService {
	@Autowired
	EmsEntDao	emsEntDao;
	@Autowired
	EmsCostRecordDao emsCostRecordDao;
	@Autowired
	ConLogDao conLogDao;
	@Autowired
	ConUserDao conUserDao;
	@Autowired
	EmsMLogDao	emsMLogDao;
	
	public void addPoint(int entId,int point,String explain,String opUserName,int opUserId,String ip){
		//ems系统记录日志
		T_log emslog	=	new	T_log();
		emslog.setEnt_id(entId);
		emslog.setOrg_id(0);
		Map<Object,Object>	map	=	new	HashMap<Object, Object>();
		map.put("ent_id", entId);
		map.put("ent_name",emsEntDao.getEntByEntId(entId).getEntName());
		map.put("point", point);
		String str=JSONArray.fromObject(map).toString();
		emslog.setLog_desc(str.substring(1, str.length()-1));
		emslog.setAccess_ip(ip);
		emslog.setLog_op("增加点数");
		emslog.setLog_result(1);
		emslog.setLog_type((short) 6);
		emslog.setOp_userid(opUserName);
		emsMLogDao.insertLog(emslog);
		//本系统记日志
		Sys_log log	=	new	Sys_log();
		log.setAccessIp(ip);
		T_ent ent	=	emsEntDao.getEntByEntId(entId);
		log.setLogDesc("企业充点：为企业[[名称："+ent.getEntName()+"][ID:"+entId+"]]充点["+point+"点]"+"充点前["+ent.getEntPoint()+"]充点后["+(ent.getEntPoint()+point)+"]");
		log.setLogType(7);
		log.setOpUserId(opUserId);
		conLogDao.insertLog(log);
		

		emsEntDao.addPointForEnt(entId, point);
		emsCostRecordDao.addPointRecord(entId, opUserName, point, explain);
	}
	public PageBean getRecordByPage(int currentPage){
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow	=	emsCostRecordDao.getTotalRow();
		pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
		pageBean.setData(emsCostRecordDao.getRecordByPage( currentPage,Constants.MAX_RECORD_PER_PAGE));
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}
	public PageBean queryByPageAndCondition(int currentPage, int entId,String opUserId) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow	=	emsCostRecordDao.getTotalRowByCondition(entId,opUserId);
		pageBean.setTotalPage(totalRow/Constants.MAX_RECORD_PER_PAGE+0==totalRow%Constants.MAX_RECORD_PER_PAGE?0:1);
		pageBean.setData(emsCostRecordDao.getRecordByPageAndCondition( currentPage,Constants.MAX_RECORD_PER_PAGE,entId,opUserId));
		pageBean.setTotalRow(totalRow);
		return pageBean;		
	}
}
