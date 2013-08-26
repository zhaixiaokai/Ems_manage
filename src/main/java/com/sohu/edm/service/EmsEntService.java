package com.sohu.edm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.edm.console.dao.ConLogDao;
import com.sohu.edm.console.dao.ConUserDao;
import com.sohu.edm.console.dao.map.Sys_log;
import com.sohu.edm.dao.EmsEntDao;
import com.sohu.edm.dao.EmsMLogDao;
import com.sohu.edm.dao.map.T_ent;
import com.sohu.edm.dao.map.T_log;
import com.sohu.edm.model.EntInfoDomain;
import com.sohu.edm.model.OptionBean;
import com.sohu.edm.model.PageBean;
import com.sohu.edm.tools.Constants;

@Service("emsEntService")
public class EmsEntService {
	@Autowired
	EmsEntDao emsEntDao;
	@Autowired
	ConLogDao conLogDao;
	@Autowired
	EmsMLogDao emsMLogDao;
	@Autowired
	ConUserDao conUserDao;

	public void addEnt(T_ent ent, int opUserId,String ip) {
		emsEntDao.addEnt(ent);
		Sys_log log = new Sys_log();
		log.setLogType(6);
		log.setOpUserId(opUserId);
		String unit = null;
		if (ent.getUnit() == 0)
			unit = "月";
		if (ent.getUnit() == 1)
			unit = "周";
		if (ent.getUnit() == 2)
			unit = "天";

		log.setLogDesc("添加企业:[企业名称：" + ent.getEntName() + "][所属行业："
				+ ent.getTrade() + "][发送域名：" + ent.getDomain() + "][企业官网："
				+ ent.getWebsite() + "][管理员帐号：" + ent.getAdminAccount()
				+ "][联系人：" + ent.getContact() + "][联系电话：" + ent.getContactTel()
				+ "][发送频次：" + ent.getFreq() + "/" + unit + "][单次发送量："
				+ ent.getSingleTransNum() + "]");
		conLogDao.insertLog(log);
		//ems记录日志
		T_log emslog	=	new	T_log();
		int entId	=	emsEntDao.getEntByEntName(ent.getEntName()).getEntId();
		emslog.setEnt_id(entId);
		emslog.setOrg_id(0);
		Map<Object,Object>	map	=	new	HashMap<Object, Object>();
		map.put("ent_id", entId);
		map.put("ent_name",emsEntDao.getEntByEntId(entId).getEntName());
		map.put("point", 0);
		String str=JSONArray.fromObject(map).toString();
		emslog.setLog_desc(str.substring(1, str.length()-1));
		emslog.setAccess_ip(ip);
		emslog.setLog_op("添加企业");
		emslog.setLog_result(1);
		emslog.setLog_type((short) 6);
		emslog.setOp_userid(conUserDao.getUserById(opUserId).getUserName());
		emsMLogDao.insertLog(emslog);
	}

	public List<T_ent> queryEnt(String start, String end, String entName) {
		return emsEntDao.queryByTimeAndEntName(start, end, entName);
	}

	public PageBean getEntPageBeanByPageAndCondition(int currentPage,
			String start, String end, String entName) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = emsEntDao.getTotalRowByCondition(start, end, entName);
		pageBean
				.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<EntInfoDomain> infoList = new ArrayList<EntInfoDomain>();
		List<T_ent> entList = emsEntDao
				.queryByPageAndCondition(Constants.MAX_RECORD_PER_PAGE,
						currentPage, start, end, entName);
		for (T_ent ent : entList) {
			EntInfoDomain domain = new EntInfoDomain(ent);
			domain.setUserNum(emsEntDao.getEntUserNum(ent.getEntId()));

			infoList.add(domain);
		}
		pageBean.setData(infoList);
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}

	public PageBean getEntByPage(int currentPage) {
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		int totalRow = emsEntDao.getTotalRow();
		pageBean
				.setTotalPage(totalRow / Constants.MAX_RECORD_PER_PAGE + 0 == totalRow
						% Constants.MAX_RECORD_PER_PAGE ? 0 : 1);
		List<EntInfoDomain> infoList = new ArrayList<EntInfoDomain>();
		List<T_ent> entList = emsEntDao.getEntByPage(currentPage,
				Constants.MAX_RECORD_PER_PAGE);
		for (T_ent ent : entList) {
			EntInfoDomain domain = new EntInfoDomain(ent);
			domain.setUserNum(emsEntDao.getEntUserNum(ent.getEntId()));

			infoList.add(domain);
		}
		pageBean.setData(infoList);
		pageBean.setTotalRow(totalRow);
		return pageBean;
	}

	public T_ent getEntById(int entId) {
		return emsEntDao.getEntById(entId);
	}

	public boolean updateEnt(T_ent ent, int opUserId) {
		Sys_log log = new Sys_log();
		log.setLogType(6);
		log.setOpUserId(opUserId);
		String unit = null;
		if (ent.getUnit() == 0)
			unit = "月";
		if (ent.getUnit() == 1)
			unit = "周";
		if (ent.getUnit() == 2)
			unit = "天";
		T_ent oldEnt = emsEntDao.getEntById(ent.getId());
		String oldInfo = "旧：[企业名称：" + oldEnt.getEntName() + "][所属行业："
				+ oldEnt.getTrade() + "][发送域名：" + oldEnt.getDomain()
				+ "][企业官网：" + oldEnt.getWebsite() + "][管理员帐号："
				+ oldEnt.getAdminAccount() + "][联系人：" + oldEnt.getContact()
				+ "][联系电话：" + ent.getContactTel() + "][发送频次：" + ent.getFreq()
				+ "/" + unit + "][单次发送量：" + ent.getSingleTransNum() + "]";
		log.setLogDesc("修改企业:[企业ID：" + oldEnt.getEntId() + "]" + oldInfo
				+ "新：[企业名称：" + ent.getEntName() + "][所属行业：" + ent.getTrade()
				+ "][发送域名：" + ent.getDomain() + "][企业官网：" + ent.getWebsite()
				+ "][管理员帐号：" + ent.getAdminAccount() + "][联系人："
				+ ent.getContact() + "][联系电话：" + ent.getContactTel()
				+ "][发送频次：" + ent.getFreq() + "/" + unit + "][单次发送量："
				+ ent.getSingleTransNum() + "]");
		emsEntDao.updateEnt(ent);
		conLogDao.insertLog(log);
		return true;
	}
	
	public List<OptionBean> getEntList(){
		List<Map<Object,Object>> optionList	=	emsEntDao.getEntOptions();
		List<OptionBean> retList	=	new	ArrayList<OptionBean>();
		for(Map<Object, Object> map:optionList){
			OptionBean bean	=	new	OptionBean();
			bean.setText(map.get("text").toString());
			bean.setValue(map.get("value").toString());
			retList.add(bean);
		}
		return retList;
	}
}
