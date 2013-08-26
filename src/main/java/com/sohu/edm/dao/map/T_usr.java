package com.sohu.edm.dao.map;

import java.util.Date;


@SuppressWarnings("serial")
public class T_usr implements java.io.Serializable {
	private long id;
	private String user_id;
	private String user_pwd;
	private short user_type;
	private String user_name;
	private int ent_id;
	private int org_id;
	private int user_priv;
	private String op_userid;
	private Date create_time;
	private Date update_time;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String userPwd) {
		user_pwd = userPwd;
	}
	public short getUser_type() {
		return user_type;
	}
	public void setUser_type(short userType) {
		user_type = userType;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
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
	public int getUser_priv() {
		return user_priv;
	}
	public void setUser_priv(int userPriv) {
		user_priv = userPriv;
	}
	public String getOp_userid() {
		return op_userid;
	}
	public void setOp_userid(String opUserid) {
		op_userid = opUserid;
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
}