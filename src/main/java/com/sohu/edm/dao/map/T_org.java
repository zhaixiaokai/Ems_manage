package com.sohu.edm.dao.map;

import java.util.Date;


@SuppressWarnings("serial")
public class T_org implements java.io.Serializable {
	private long id;
	private int entId;
	private int orgId;
	private String orgName;
	private int orgPoints;
	private String orgMembers;
	private String opUserId;
	private Date createTime;
	private Date updateTime;
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
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getOrgPoints() {
		return orgPoints;
	}
	public void setOrgPoints(int orgPoints) {
		this.orgPoints = orgPoints;
	}
	public String getOrgMembers() {
		return orgMembers;
	}
	public void setOrgMembers(String orgMembers) {
		this.orgMembers = orgMembers;
	}
	public String getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
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
}