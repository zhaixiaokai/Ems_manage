package com.sohu.edm.model;

import com.sohu.edm.dao.map.T_ent;

@SuppressWarnings("serial")
public class EntInfoDomain extends T_ent{
	private int userNum;
	public EntInfoDomain(T_ent ent){
		this.setAdminAccount(ent.getAdminAccount());
		this.setAdminPsw(ent.getAdminPsw());
		this.setContact(ent.getContact());
		this.setContactTel(ent.getContactTel());
		this.setCreateTime(ent.getCreateTime());
		this.setDomain(ent.getDomain());
		this.setEntId(ent.getEntId());
		this.setEntName(ent.getEntName());
		this.setEntPoint(ent.getEntPoint());
		this.setFreq(ent.getFreq());
		this.setId(ent.getId());
		this.setScKey(ent.getScKey());
		this.setScTestKey(ent.getScTestKey());
		this.setScTestUser(ent.getScTestUser());
		this.setSingleTransNum(ent.getSingleTransNum());
		this.setTrade(ent.getTrade());
		this.setUnit(ent.getUnit());
		this.setUpdateTime(ent.getUpdateTime());
		this.setWebsite(ent.getWebsite());
	}
	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	
}
