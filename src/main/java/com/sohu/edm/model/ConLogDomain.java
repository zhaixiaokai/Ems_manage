package com.sohu.edm.model;

public class ConLogDomain {
	private String id;
	private String opUserName;
	private String opUserId;
	private int	logTypeInt;
	private String logTypeString;
	private String logDesc;
	private String time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOpUserName() {
		return opUserName;
	}
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
	public String getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	public int getLogTypeInt() {
		return logTypeInt;
	}
	public void setLogTypeInt(int logTypeInt) {
		this.logTypeInt = logTypeInt;
		if(logTypeInt==1){
			this.logTypeString="登录";
		}else if(logTypeInt==2){
			this.logTypeString="用户增/删/改";
		}else if(logTypeInt==3){
			this.logTypeString="角色增/删/改";			
		}else if(logTypeInt==4){
			this.logTypeString="角色成员增/删/改";			
		}else if(logTypeInt==5){
			this.logTypeString="权限变更";			
		}else if(logTypeInt==6){
			this.logTypeString="企业增/改";			
		}else if(logTypeInt==7){
			this.logTypeString="企业充点";
		}else{
			this.logTypeString="---";
		}
	}
	public String getLogTypeString() {
		return logTypeString;
	}
	public void setLogTypeString(String logTypeString) {
		this.logTypeString = logTypeString;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
