package com.sohu.edm.dao.map;

import java.util.Date;


@SuppressWarnings("serial")
public class T_ent implements java.io.Serializable {
	private long id;
	private int entId;
	private String entName;
	private int entPoint;
	private Date createTime;
	private Date updateTime;
	private String scUser;
	private String scKey;
	private String scTestUser;
	private String scTestKey;
	private int	trade;
	private String domain;
	private String website;
	private String adminAccount;
	private String adminPsw;
	private String contact;
	private String contactTel;
	private int freq;
	private int unit;
	private int singleTransNum;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getEntId() {
		return entId;
	}
	public void setEntId(int entId) {
		this.entId = entId;
	}
	public String getEntName() {
		return entName;
	}
	public void setEntName(String entName) {
		this.entName = entName;
	}
	public int getEntPoint() {
		return entPoint;
	}
	public void setEntPoint(int entPoint) {
		this.entPoint = entPoint;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getScUser() {
		return scUser;
	}
	public void setScUser(String scUser) {
		this.scUser = scUser;
	}
	public String getScKey() {
		return scKey;
	}
	public void setScKey(String scKey) {
		this.scKey = scKey;
	}
	public String getScTestUser() {
		return scTestUser;
	}
	public void setScTestUser(String scTestUser) {
		this.scTestUser = scTestUser;
	}
	public String getScTestKey() {
		return scTestKey;
	}
	public void setScTestKey(String scTestKey) {
		this.scTestKey = scTestKey;
	}
	public int getTrade() {
		return trade;
	}
	public void setTrade(int trade) {
		this.trade = trade;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	public String getAdminPsw() {
		return adminPsw;
	}
	public void setAdminPsw(String adminPsw) {
		this.adminPsw = adminPsw;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getSingleTransNum() {
		return singleTransNum;
	}
	public void setSingleTransNum(int singleTransNum) {
		this.singleTransNum = singleTransNum;
	}
	

}