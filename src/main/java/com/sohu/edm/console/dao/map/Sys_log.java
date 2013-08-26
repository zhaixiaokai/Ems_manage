package com.sohu.edm.console.dao.map;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Sys_log implements Serializable {
	private long id;
	private int opUserId;
	private String accessIp;
	private int logType;
	private String logDesc;
	private Date createTime;
	private Date updateTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(int opUserId) {
		this.opUserId = opUserId;
	}
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public int getLogType() {
		return logType;
	}
	public void setLogType(int logType) {
		this.logType = logType;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
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
