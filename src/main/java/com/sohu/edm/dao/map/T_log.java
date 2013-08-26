package com.sohu.edm.dao.map;

import java.util.Date;


@SuppressWarnings("serial")
public class T_log implements java.io.Serializable {
	private long id;
	private int ent_id;
	private int org_id;
	private String op_userid;
	private String access_ip;
	private short log_type;
	private String log_desc;
	private Date create_time;
	private Date update_time;
	private String log_op;
	private int log_result;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getEnt_id() {
		return ent_id;
	}
	public void setEnt_id(int entId) {
		ent_id = entId;
	}
	public int getOrg_id() {
		return org_id;
	}
	public void setOrg_id(int orgId) {
		org_id = orgId;
	}
	public String getOp_userid() {
		return op_userid;
	}
	public void setOp_userid(String opUserid) {
		op_userid = opUserid;
	}
	public String getAccess_ip() {
		return access_ip;
	}
	public void setAccess_ip(String accessIp) {
		access_ip = accessIp;
	}
	public short getLog_type() {
		return log_type;
	}
	public void setLog_type(short logType) {
		log_type = logType;
	}
	public String getLog_desc() {
		return log_desc;
	}
	public void setLog_desc(String logDesc) {
		log_desc = logDesc;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date updateTime) {
		update_time = updateTime;
	}
	public String getLog_op() {
		return log_op;
	}
	public void setLog_op(String logOp) {
		log_op = logOp;
	}
	public int getLog_result() {
		return log_result;
	}
	public void setLog_result(int logResult) {
		log_result = logResult;
	}
	
}